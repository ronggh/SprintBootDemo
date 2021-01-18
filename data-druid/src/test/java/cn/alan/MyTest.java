package cn.alan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;


@SpringBootTest
@RunWith(SpringRunner.class)
public class MyTest {
    @Autowired
    private DataSource dataSource;

    /**
     * 1. 测试连接数据源
     *
     * @throws Exception
     */
    @Test
    public void testDataSource() throws Exception {
        System.out.println("dataSource ===> " + dataSource.getClass());
        Connection connection = dataSource.getConnection();

        System.out.println("connection ===> " + connection);
        connection.close();
    }
}