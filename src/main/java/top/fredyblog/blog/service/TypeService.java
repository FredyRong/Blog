package top.fredyblog.blog.service;

import com.github.pagehelper.PageInfo;
import top.fredyblog.blog.model.entity.Type;
import top.fredyblog.blog.model.entity.TypeExample;

import java.util.List;
import java.util.Map;

/**
 * 博客类型服务层接口
 * @author Fredy
 * @date 2020/5/7 17:59
 */
public interface TypeService {
    /**
     * 功能描述：返回所有类型信息（Map形式）
     * @return
     */
    Map<Integer,String> getAllTypes();

    /**
     * 功能描述：获取所有类型信息
     * @return
     */
    List<Type> getAll();

    /**
     * 功能描述：分页查询博客类型信息
     * @param typeExample
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Type> page(TypeExample typeExample, Integer pageNum, Integer pageSize);

    /**
     * 功能描述：主键查询
     * @param id
     * @return
     */
    Type findOne(Integer id);

    /**
     * 功能描述：根据名称查询博客类型信息
     * @param typeName
     * @return
     */
    Type findTypeByName(String typeName);

    /**
     * 功能描述：保存类型信息
     * @param type
     */
    void saveType(Type type);

    /**
     * 功能描述：删除类型信息
     * @param id
     */
    void deleteType(Integer id);
}
