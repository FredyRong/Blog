package top.fredyblog.blog.model.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 博客搜索持久层
 * @author Fredy
 * @date 2020/5/16 18:08
 */
public interface EsBlogRepository extends ElasticsearchRepository<EsBlogDo, Integer> {
}
