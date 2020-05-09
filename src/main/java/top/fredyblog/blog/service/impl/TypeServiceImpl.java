package top.fredyblog.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.fredyblog.blog.exception.CustomizeErrorCode;
import top.fredyblog.blog.exception.CustomizeException;
import top.fredyblog.blog.mapper.TypeMapper;
import top.fredyblog.blog.model.entity.Type;
import top.fredyblog.blog.model.entity.TypeExample;
import top.fredyblog.blog.service.TypeService;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 博客类型管理服务实现类
 * @author Fredy
 * @date 2020/5/7 18:04
 */
@Service
public class TypeServiceImpl implements TypeService {
    @Resource
    private TypeMapper typeMapper;

    /**
     * 功能描述：返回所有类型信息（Map形式）
     * @return
     */
    @Override
    public Map<Integer, String> getAllTypes() {
        TypeExample typeExample = new TypeExample();
        typeExample.createCriteria().andDelFlagEqualTo(false);
        List<Type> types = typeMapper.selectByExample(typeExample);
        return types.stream().collect(Collectors.toMap(Type::getTypeId, Type::getTypeName, (k1, k2) -> k2));
    }

    /**
     * 功能描述：获取所有类型信息
     * @return
     */
    @Override
    public List<Type> getAll() {
        TypeExample typeExample = new TypeExample();
        typeExample.createCriteria().andDelFlagEqualTo(false);
        return typeMapper.selectByExample(typeExample);
    }

    /**
     * 功能描述：分页查询博客类型信息
     * @param typeExample
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Type> page(TypeExample typeExample, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Type> list = typeMapper.selectByExample(typeExample);
        return PageInfo.of(list);
    }

    /**
     * 功能描述：主键查询
     * @param id
     * @return
     */
    @Override
    public Type findOne(Integer id) {
        return typeMapper.selectByPrimaryKey(id);
    }

    /**
     * 功能描述：根据名称查询博客类型信息
     * @param typeName
     * @return
     */
    @Override
    public Type findTypeByName(String typeName) {
        TypeExample typeExample = new TypeExample();
        typeExample.createCriteria().andTypeNameEqualTo(typeName);
        List<Type> list = typeMapper.selectByExample(typeExample);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    /**
     * 功能描述：保存类型信息
     * @param type
     */
    @Override
    public void saveType(Type type) {
        LocalDateTime now = LocalDateTime.now();
        type.setUpdateTime(now);
        if (type.getTypeId() == null) {
            type.setCreateTime(now);
            type.setDelFlag(false);
            int n = typeMapper.insertSelective(type);
            if(n != 1){
                throw new CustomizeException(CustomizeErrorCode.TYPE_INSERT_FAILED);
            }
        } else {
            int n = typeMapper.updateByPrimaryKeySelective(type);
            if(n != 1){
                throw new CustomizeException(CustomizeErrorCode.TYPE_UPDATE_FAILED);
            }
        }
    }

    /**
     * 功能描述：删除类型信息
     * @param id
     */
    @Override
    public void deleteType(Integer id) {
        Type type = new Type();
        type.setTypeId(id);
        type.setDelFlag(true);
        type.setDelTime(LocalDateTime.now());
        int n = typeMapper.updateByPrimaryKeySelective(type);
        if(n != 1){
            throw new CustomizeException(CustomizeErrorCode.TYPE_DELETE_FAILED);
        }
    }
}
