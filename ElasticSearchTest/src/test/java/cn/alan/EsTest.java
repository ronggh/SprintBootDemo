package cn.alan;

import cn.alan.entity.Article;

import cn.alan.entity.Book;
import cn.alan.repository.BookRepository;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;

import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsTest {
    @Autowired
    private JestClient jestClient;

    @Autowired
    private BookRepository bookRepository;
    /**
     * 1. Jest方式测试给 Es中索引（保存）一个文档
     */
    @Test
    public void testSave() {
        //1、给Es中索引（保存）一个文档；
        Article article = new Article();
        article.setId(1);
        article.setTitle("好消息");
        article.setAuthor("zhangsan");
        article.setContent("Hello World");

        //构建一个索引功能
        Index index = new Index.Builder(article).index("alan").type("news").build();

        try {
            //执行
            jestClient.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 2. 测试搜索
     */
    @Test
    public void search() throws Exception{
        // 可使用 JSONObject 或直接拼接字符串
//        JSONObject content = new JSONObject();
//        content.put("content","hello");
//        JSONObject match = new JSONObject();
//        match.put("match",content);
//        JSONObject query = new JSONObject();
//        query.put("query",query);
//
//        System.out.println("query:" + query.toString());


        //查询表达式
        String json ="{\n" +
                "    \"query\" : {\n" +
                "        \"match\" : {\n" +
                "            \"content\" : \"hello\"\n" +
                "        }\n" +
                "    }\n" +
                "}";

        //更多操作：https://github.com/searchbox-io/Jest/tree/master/jest
        //构建搜索功能
        Search search = new Search.Builder(json).addIndex("alan").addType("news").build();

        //执行
        try {
            SearchResult result = jestClient.execute(search);

            System.out.println(result.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 3. 通过 epositoryTemplate 操作ES
     */
    @Test
    public void testRepositoryTemplate(){
        Book book = new Book();
        book.setId(1);
        book.setBookName("西游记");
        book.setAuthor("吴承恩");
        bookRepository.index(book);
    }

    /**
     * 4. 测试搜索
     */
    @Test
    public void testSearch(){
        for (Book book : bookRepository.findByBookNameLike("游")) {
            System.out.println(book);
        }
    }
}
