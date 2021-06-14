package com.power.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * RabbitMQ自定义注入配置Bean相关组件
 */
@Configuration
public class RabbitmqConfig {
    //定义日志
    private static final Logger log = LoggerFactory.getLogger(RabbitmqConfig.class);

    //自动装配RabbitMQ的链接工厂实例
    @Autowired
    private CachingConnectionFactory connectionFactory;

    //自动装配消息监听器所在的容器工厂配置类实例
    @Autowired
    private SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer;

    //定义读取配置文件的环境变量实例
    @Autowired
    private Environment env;

    /** 创建简单的消息模型：队列、交换机和路由 **/
    //创建队列
    @Bean(name = "basicQueue")
    public Queue basicQueue(){
        return new Queue(env.getProperty("mq.basic.info.queue.name"),true);
    }

    //创建交换机：在这里以DirectExchange为例
    @Bean
    public DirectExchange basicExchange(){
        return new DirectExchange(env.getProperty("mq.basic.info.exchange.name"),true,false);
    }
    //创建绑定
    @Bean
    public Binding basicBinding(){
        return BindingBuilder.bind(basicQueue()).to(basicExchange()).with(env.getProperty("mq.basic.info.routing.key.name"));
    }

    /** 创建简单的消息模型：队列、交换机和路由 发送对象消息 **/
    //创建队列
    @Bean(name = "objectQueue")
    public Queue objectQueue(){
        return new Queue(env.getProperty("mq.object.info.queue.name"),true);
    }

    //创建交换机：在这里以DirectExchange为例
    @Bean
    public DirectExchange objectExchange(){
        return new DirectExchange(env.getProperty("mq.object.info.exchange.name"),true,false);
    }
    //创建绑定
    @Bean
    public Binding objectBinding(){
        return BindingBuilder.bind(objectQueue()).to(objectExchange()).with(env.getProperty("mq.object.info.routing.key.name"));
    }

    /** 创建消息模型 - fanoutExchange **/
    //创建队列1
    @Bean(name = "fanoutQueueOne")
    public Queue fanoutQueueOne(){
        return new Queue(env.getProperty("mq.fanout.queue.one.name"),true);
    }
    //创建队列2
    @Bean(name = "fanoutQueueTwo")
    public Queue fanoutQueueTwo(){
        return new Queue(env.getProperty("mq.fanout.queue.two.name"),true);
    }
    //创建交换机
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(env.getProperty("mq.fanout.exchange.name"),true,false);
    }
    //创建绑定1
    @Bean
    public Binding fanoutBindingOne(){
        return BindingBuilder.bind(fanoutQueueOne()).to(fanoutExchange());
    }
    //创建绑定2
    @Bean
    public Binding fanoutBindingTwo(){
        return BindingBuilder.bind(fanoutQueueTwo()).to(fanoutExchange());
    }

    /**创建消息模型-topicExchange **/
    //创建交换机-topicExchange
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(env.getProperty("mq.topic.exchange.name"));
    }
    //创建队列1
    @Bean(name = "topicQueueOne")
    public Queue topicQueueOne(){
        return new Queue(env.getProperty("mq.topic.queue.one.name"),true);
    }
    //创建队列2
    @Bean(name = "topicQueueTwo")
    public Queue topicQueueTwo(){
        return new Queue(env.getProperty("mq.topic.queue.two.name"),true);
    }
    //创建绑定-通配符为*的路由
    @Bean
    public Binding topicBindingOne(){
        return BindingBuilder.bind(topicQueueOne()).to(topicExchange()).with(env.getProperty("mq.topic.routing.key.one.name"));
    }
    //创建绑定-通配符为#的路由
    @Bean
    public Binding topicBindingTwo(){
        return BindingBuilder.bind(topicQueueTwo()).to(topicExchange()).with(env.getProperty("mq.topic.routing.key.two.name"));
    }

    /**
     * 下面为单一消费者实例的配置
     * @return
     */
    @Bean(name = "singleListenerContainer")
    public SimpleRabbitListenerContainerFactory listenerContainer(){
        //定义消息监听器所在的容器工厂
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        //设置容器工厂所用的实例
        factory.setConnectionFactory(connectionFactory);
        //设置消息在传输中的格式，在这里采用JSON的格式进行传输
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        //设置并发消费者实例的初始数量。在这里为1个
        factory.setConcurrentConsumers(1);
        //设置并发消费者实例的最大数量。在这里为1个
        factory.setMaxConcurrentConsumers(1);
        //设置并发消费者实例中每个实例拉取的消息数量-在这里为1个
        factory.setPrefetchCount(1);
        return factory;
    }

    /**
     * 下面为多个消费者实例的配置，主要是针对高并发业务场景的配置
     * @return
     */
    @Bean(name = "multiListenerContainer")
    public SimpleRabbitListenerContainerFactory multiListenerContainer(){
        //定义消息监听器所在的容器工厂
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        //设置容器工厂所用的实例
        factoryConfigurer.configure(factory,connectionFactory);
        //设置消息在传输中的格式，在这里采用JSON的格式进行传输
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        //设置消息的确认消费模式。在这里为NONE，表示不需要确认消费
        factory.setAcknowledgeMode(AcknowledgeMode.NONE);
        //设置并发消费者实例的初始数量。在这里为10个
        factory.setConcurrentConsumers(10);
        //设置并发消费者实例的最大数量。在这里为15个
        factory.setMaxConcurrentConsumers(15);
        //设置并发消费者实例中每个实例拉取的消息数量。在这里为10个
        factory.setPrefetchCount(10);
        return factory;
    }

    //自定义配置RabbitMQ发送消息的操作组件 RabbitTemplate
    @Bean
    public RabbitTemplate rabbitTemplate(){
        //设置“发送消息后进行确认”
        connectionFactory.setPublisherConfirms(true);
        //设置“发送消息后返回确认信息"
        connectionFactory.setPublisherReturns(true);
        //构造发送消息组件实例对象
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        //发送消息成功后，如果发送成功，则输出“消息发送成功”的反馈信息
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                log.info("消息发送成功：correlationData({}),ack({}),cause({})",correlationData,ack,cause);
            }
        });
        //发送消息后，如果发送失败，则输出“消息发送失败-消息丢失”的反馈信息
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                log.info("消息丢失：exchange({}),route({}),replyCode({}),replyText({}),message:{}",exchange,routingKey,replyCode,replyText,message);
            }
        });
        //最终返回RabbitMQ的操作组件实例RabbitTemplate
        return  rabbitTemplate;
    }
}
