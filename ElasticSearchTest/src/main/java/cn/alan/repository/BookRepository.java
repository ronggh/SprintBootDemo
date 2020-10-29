package cn.alan.repository;

import cn.alan.entity.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BookRepository  extends ElasticsearchRepository<Book,Integer> {
    /**
     * 基本的操作 ElasticsearchRepository中已经提供了
     * 参照 https://docs.spring.io/spring-data/elasticsearch/docs/3.0.6.RELEASE/reference/html/
     */

    /**
     也可以自定义方法，命名按照规则，就不用写注解
     或使用注解　@Query　指定搜索条件
     * @param bookName
     * @return
     */
    List<Book> findByBookNameLike(String bookName);
}
