package top.fredyblog.blog.controller.admin;

import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.fredyblog.blog.model.dto.RestResult;
import top.fredyblog.blog.model.entity.MessageExample;
import top.fredyblog.blog.service.MessageService;
import top.fredyblog.blog.utils.ResultGenerator;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 管理员：留言管理
 * @author Fredy
 * @date 2020/5/14 21:27
 */
@Log4j2
@RestController
@RequestMapping("/admin")
public class AdminMessageController {
    //留言等级对应map
    private static Map<String, String> messageLevelMap = new HashMap<>();
    static {
        messageLevelMap.put("1", "留言");
        messageLevelMap.put("2", "回复");
    }

    @Resource
    private MessageService messageService;

    /**
     * 功能描述：留言分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation("留言分页查询")
    @GetMapping("/messages")
    public RestResult messages(@RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "10") Integer pageSize){
        MessageExample messageExample = new MessageExample();
        messageExample.createCriteria().andDelFlagEqualTo(false);
        messageExample.setOrderByClause("create_time desc");
        return ResultGenerator.getSuccessResult(messageService.page(messageExample, pageNum, pageSize));
    }

    /**
     * 功能描述：留言等级查询
     * @return
     */
    @ApiOperation("留言等级查询")
    @GetMapping("/messageLevel")
    public RestResult getMessageLevel(){
        return ResultGenerator.getSuccessResult(messageLevelMap);
    }

    @ApiOperation("留言搜索")
    @PostMapping("/message/search")
    public RestResult search(@RequestParam(defaultValue = "1") Integer pageNum,
                      @RequestParam(defaultValue = "10") Integer pageSize,
                      String messageLevel){
        MessageExample messageExample = new MessageExample();
        MessageExample.Criteria criteria = messageExample.createCriteria().andDelFlagEqualTo(false);
        messageExample.setOrderByClause("create_time desc");
        if (StringUtils.isNotBlank(messageLevel)) {
            criteria.andMessageLevelEqualTo(messageLevel);
        }
        return ResultGenerator.getSuccessResult(messageService.page(messageExample, pageNum, pageSize));
    }
}
