package com.power.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.power.rabbitmq.entity.EventInfo;
import com.power.rabbitmq.entity.Person;
import com.power.rabbitmq.publisher.BasicPublisher;
import com.power.rabbitmq.publisher.ModelPublisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RabbitmqTest {
    private static final Logger log = LoggerFactory.getLogger(RabbitmqTest.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BasicPublisher basicPublisher;

    @Autowired
    private ModelPublisher modelPublisher;

    //用于发送消息的测试方法
    @Test
    public void test1() throws Exception{
        //定义字符串值
        String msg = "~~~~~~~~~~~这是一串字符串消息~~~~~~~~";
        //生产者实例发送消息
        basicPublisher.sendMsg(msg);
    }

    @Test
    public void test2() throws Exception{
        //构建人员实体对象信息
        Person p = new Person(1,"yanchao","stackoverflow");
        basicPublisher.sendObjectMsg(p);
    }
    @Test
    public void test3() throws Exception{
        //创建对象实例
        EventInfo info = new EventInfo(1,"增删改查模块","基于FanoutExchange的消息模型","这是基于fanoutExchange的消息模型");
        //触发生产者发送消息
        modelPublisher.sendMsg(info);
    }

    @Test
    public void test4() throws Exception{
        //定义待发送的消息，一串字符串
        String msg = "这是TopicExchange消息模型的消息";
        //此时相当于*，java替代了*的位置
        //此时由于#表示任意单词，因而也将路由到#表示的路由和对应的队列中  会同时被2个消费者消费
        String routingKeyOne = "local.middleware.mq.topic.routing.java.key";
        //此时相当于#：即php.python替代了#的位置
        String routingKeyTwo = "local.middleware.mq.topic.routing.php.python.key";
        //此时相当于#:即0个单词
        String routingKeyThree = "local.middleware.mq.topic.routing.key";
        //下面分批进行测试，为了看不同routingkey的运行效果
        //modelPublisher.sendMsgTopic(msg,routingKeyOne);
        //modelPublisher.sendMsgTopic(msg,routingKeyTwo);
        modelPublisher.sendMsgTopic(msg,routingKeyThree);

    }
}
