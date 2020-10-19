
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={LogTest.class})
public class LogTest {

    //  日志记录器
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 1. Logger 日志级别
     *  日志级别由低到高 trace < debug < info < warn < error
     *  Spring Boot 默认的日志输出级别是 info
     *  即只会输出该级别以及其后更高级别的日志信息
     */
    @Test
    public void testLogger(){
        logger.trace("这是 trace 日志");
        logger.debug("这是 debug 日志");
        logger.info("这是 info 日志");
        logger.warn("这是 warn 日志");
        logger.error("这是 error 日志");
    }



}
