package top.fredyblog.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import top.fredyblog.blog.exception.CustomizeErrorCode;
import top.fredyblog.blog.exception.CustomizeException;
import top.fredyblog.blog.mapper.MessageExtendMapper;
import top.fredyblog.blog.mapper.MessageMapper;
import top.fredyblog.blog.mapper.ReplyExtendMapper;
import top.fredyblog.blog.model.dto.MessageDTO;
import top.fredyblog.blog.model.entity.Message;
import top.fredyblog.blog.model.entity.MessageExample;
import top.fredyblog.blog.model.entity.User;
import top.fredyblog.blog.model.pojo.MessageExtend;
import top.fredyblog.blog.service.MessageService;
import top.fredyblog.blog.utils.MqUtil;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Fredy
 * @date 2020/5/10 11:49
 */
@Service
@Log4j2
public class MessageServiceImpl implements MessageService {
    @Resource
    private MessageExtendMapper messageExtendMapper;
    @Resource
    private ReplyExtendMapper replyExtendMapper;
    @Resource
    private MessageMapper messageMapper;
    @Resource
    private MqUtil mqUtil;

    /**
     * 功能描述：分页获取留言信箱列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<MessageExtend> page(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        MessageExample messageExample = new MessageExample();
        messageExample.createCriteria().andMessageLevelEqualTo("1").andDelFlagEqualTo(false);
        messageExample.setOrderByClause("create_time desc");
        List<MessageExtend> list = messageExtendMapper.page(messageExample);
        for (MessageExtend messageExtend : list) {
            messageExample.clear();
            messageExample.createCriteria().andMessageLevelEqualTo("2")
                    .andParentMessageIdEqualTo(messageExtend.getMessageId()).andDelFlagEqualTo(false);
            messageExample.setOrderByClause("create_time desc");
            List<Message> replyList = messageMapper.selectByExampleWithBLOBs(messageExample);
            messageExtend.setReplyList(replyList);
            messageExtend.setReplyNums(replyList.size());
        }
        return PageInfo.of(list);
    }

    /**
     * 功能描述：新增留言
     * @param nickname
     * @param email
     * @param content
     * @param user
     */
    @Override
    public void addMessage(String nickname, String email, String content, User user) {
        Message message = new Message();
        if (user != null) {
            message.setMessageUserId(user.getUserId());
        } else {
            message.setMessageUserId(0);
        }
        message.setMessageNickname(nickname);
        message.setMessageEmail(email);
        message.setMessageContent(content);
        message.setMessageLevel("1");
        message.setParentMessageId(0);
        LocalDateTime now = LocalDateTime.now();
        message.setCreateTime(now);
        message.setUpdateTime(now);
        message.setDelFlag(false);
        int n = messageMapper.insertSelective(message);
        if(n != 1){
            throw new CustomizeException(CustomizeErrorCode.MESSAGE_INSERT_FAILED);
        }
        //组装数据发送到mq中，mq异步发送邮件到指定邮箱
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setNickname(nickname);
        messageDTO.setContent(content);
        mqUtil.sendMessageToMq(messageDTO);
    }

    /**
     * 功能描述：新增留言回复
     * @param nickname
     * @param email
     * @param content
     * @param messageId
     * @param repliedUserId
     * @param repliedUserNickname
     * @param user
     */
    @Override
    public void addMessageReply(String nickname, String email, String content, Integer messageId, Integer repliedUserId, String repliedUserNickname, User user) {
        Message message = new Message();
        if (user != null) {
            message.setMessageUserId(user.getUserId());
        } else {
            message.setMessageUserId(0);
        }
        message.setRepliedUserId(repliedUserId);
        message.setRepliedUserNickname(repliedUserNickname);
        message.setMessageNickname(nickname);
        message.setMessageEmail(email);
        message.setMessageContent(content);
        message.setMessageLevel("2");
        message.setParentMessageId(messageId);
        LocalDateTime now = LocalDateTime.now();
        message.setCreateTime(now);
        message.setUpdateTime(now);
        message.setDelFlag(false);
        int n = messageMapper.insertSelective(message);
        if(n != 1){
            throw new CustomizeException(CustomizeErrorCode.MESSAGE_INSERT_FAILED);
        }
        Message parentMessage = messageMapper.selectByPrimaryKey(messageId);
        if (parentMessage == null) {
            log.error("编号[{}]留言信息不存在！", messageId);
        }
        //组装数据发送到mq中，mq异步发送邮件到指定邮箱
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setNickname(nickname);
        messageDTO.setContent(content);
        messageDTO.setParentNickName(repliedUserNickname);
        messageDTO.setParentContent(parentMessage.getMessageContent());
        messageDTO.setEmail(parentMessage.getMessageEmail());
        mqUtil.sendMessageReplyToMq(messageDTO);
    }

    /**
     * 功能描述：分页查询留言信息（不考虑分层）
     * @param messageExample
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Message> page(MessageExample messageExample, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Message> list = messageMapper.selectByExampleWithBLOBs(messageExample);
        return PageInfo.of(list);
    }
}
