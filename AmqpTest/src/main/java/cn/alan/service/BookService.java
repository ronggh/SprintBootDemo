package cn.alan.service;

import cn.alan.entity.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    /**
     * 监听 alan.news 队列的 Book类型的消息
     *
     * @param book
     */
    @RabbitListener(queues = "alan.news")
    public void receiveBook(Book book) {
        System.out.println("收到Book消息 ====> " + book);
    }

    /**
     * 监听 alan 队列 的所有消息
     *
     * @param message
     */
    @RabbitListener(queues = "alan")
    public void receiveMessage(Message message) {
        System.out.println("消息 ====> " + message.toString());
        // getBody()是byte[]类型
        System.out.println("消息体 ====> " + message.getBody());
        System.out.println("消息属性 ====> " + message.getMessageProperties());
    }
}
