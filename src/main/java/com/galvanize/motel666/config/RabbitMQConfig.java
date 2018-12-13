package com.galvanize.motel666.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@Configuration
public class RabbitMQConfig implements RabbitListenerConfigurer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConfig.class);

    @Value("${AMQP_URL}")
    String amqpUri;
    @Value("${amqp.exchange.name}")
    String appExchangeName;
    @Value("${amqp.logging.binding.key}")
    String loggingBinding;
    @Value("${amqp.logging.queue}")
    String loggingQueue;



    @Bean
    public TopicExchange getAppExchange() {
        return new TopicExchange(appExchangeName);
    }

    @Bean
    public Queue loggingQueue() {
        return new Queue(loggingQueue);
    }

    @Bean
    public Binding loggingBinding() {
        return BindingBuilder.bind(loggingQueue()).to(getAppExchange()).with(loggingBinding);
    }

    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }

    @Bean
    public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(consumerJackson2MessageConverter());
        return factory;
    }

    @Bean
    ConnectionFactory connectionFactory() {
        LOGGER.info("Create Connection factory Bean with owm AMQP URI: {}", amqpUri);
        final CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setUri(amqpUri);
        return factory;
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        LOGGER.info("Create RabbitMQ Template Bean with own connection factory");
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        LOGGER.info("Create producerJackson2MessageConverter bean");
        return new Jackson2JsonMessageConverter();
    }
}