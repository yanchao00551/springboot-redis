package com.power.rabbitmq.publisher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.power.rabbitmq.entity.EventInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.awt.*;

/**
 * 消息模型-生产者
 */
@Component
public class ModelPublisher {
    private static final Logger log = LoggerFactory.getLogger(ModelPublisher.class);

    //定义JSON序列化和反序列化实例
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 定义rabbitmq消息发送组件RabbitTemplate
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 定义环境变量读取实例
     */
    @Autowired
    private Environment env;

    /**
     * 发送消息
     */
    public void sendMsg(EventInfo info){
        //判断是否为null
        if(info != null){
            try{
                //定义消息传输格式为JSON字符串格式
                rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
                //指定消息模型中的交换机
                rabbitTemplate.setExchange(env.getProperty("mq.fanout.exchange.name"));
                //创建消息实例
                Message msg = MessageBuilder.withBody(objectMapper.writeValueAsBytes(info)).build();
                //发送消息
                rabbitTemplate.convertAndSend(msg);
                //打印日志信息
                log.info("fanoutExchange消息模型-生产者-发送消息：{}",msg);
            }catch (Exception e){
                log.error("消息模型fanoutExchange-生产者-发送消息发生异常:{}",info,e.fillInStackTrace());
            }
        }
    }

    /**
     * 发送消息-基于TopicExchange消息模型
     * @param msg
     * @param routingKey
     */
    public void sendMsgTopic(String msg,String routingKey){
        //判断是否为null
        if(!Strings.isNullOrEmpty(msg) && !Strings.isNullOrEmpty(routingKey)){
            try{
                //定义消息传输格式为JSON字符串格式
                rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
                //指定消息模型中的交换机
                rabbitTemplate.setExchange(env.getProperty("mq.topic.exchange.name"));
                //指定路由的实际取值，根据不同取值，RabbitMQ将自行进行匹配通配符，从而路由到不同的队列
                rabbitTemplate.setRoutingKey(routingKey);
                //创建消息
                Message message = MessageBuilder.withBody(msg.getBytes("utf-8")).build();
                //发送消息
                rabbitTemplate.convertAndSend(message);
                //打印日志信息
                log.info("TopicExchange消息模型-生产者-发送消息：{}  路由：{}",msg,routingKey);
            }catch (Exception e){
                log.error("消息模型TopicExchange-生产者-发送消息发生异常：{}",msg,e.fillInStackTrace());
            }
        }
    }
}
