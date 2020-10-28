package cn.alan;

import cn.alan.entity.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.ws.BindingType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AmqpTest {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    /**
     * 1、单播（点对点）
     */
    @Test
    public void testPer2Per() {
        // 1.使用 rabbitTemplate.send(exchage,routeKey,message);
        //   Message需要自己构造一个;定义消息体内容和消息头

        // 2. 使用 rabbitTemplate.convertAndSend(exchage,routeKey,object);
        // object默认当成消息体，只需要传入要发送的对象，自动序列化发送给rabbitmq；
        Map<String,Object> map = new HashMap<>();
        map.put("msg","这是第一个消息");
        map.put("data", Arrays.asList("helloworld",123,true));
        // 对象被序列化后发送，默认是 application/x-java-serialized-object 序列化方式
//        rabbitTemplate.convertAndSend("exchange.direct","alan.news",map);

        //对象被默认序列化以后发送出去
        rabbitTemplate.convertAndSend("exchange.direct","alan.news",new Book("西游记","吴承恩"));
    }

    /**
     * 2. 测试接受消息
     *   默认使用 SimpleMessageConverter 进行转换 <br/>
     *   可以通过创建一个配置类，使用其它转换器，<br/>
     *   如：Jackson2JsonMessageConverter
     */
    @Test
    public void testReceiveMsg(){
        Object object = rabbitTemplate.receiveAndConvert("alan.news");
        System.out.println(object);
        System.out.println(object.getClass());
    }

    /**
     * 3. 测试广播消息
     */
    @Test
    public void sendMsg(){
        rabbitTemplate.convertAndSend("exchange.fanout","",new Book("红楼梦","曹雪芹"));
    }

    /**
     * 4. 测试创建 Exchange, Queue, Binding
     */
    @Test
    public void testCreate(){
        // declareXXX(),创建
        amqpAdmin.declareExchange(new DirectExchange("admin.direct"));
        amqpAdmin.declareQueue(new Queue("admin.queue",true));
        amqpAdmin.declareBinding(new Binding("admin.queue",Binding.DestinationType.QUEUE,"admin.direct","admin.queue",null));
        System.out.println("创建成功");
    }

    /**
     * 5. 测试删除 Exchange, Queue, Binding
     */
    @Test
    public void testDelete(){
        amqpAdmin.removeBinding(new Binding("admin.queue",Binding.DestinationType.QUEUE,"admin.direct","admin.queue",null));
        amqpAdmin.deleteExchange("admin.direct");
        amqpAdmin.deleteQueue("admin.queue");
        System.out.println("删除成功");
    }
}