package top.fredyblog.blog.model.es;

import org.springframework.data.domain.Page;

/**
 * 搜索服务接口
 * @author Fredy
 * @date 2020/5/16 18:04
 */
public interface EsBlogService {
    /**
     * 功能描述：标题关键字搜索
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @param userId
     * @return
     */
    Page<EsBlogDo> search(String keyword, Integer pageNum, Integer pageSize, Integer userId);
}
