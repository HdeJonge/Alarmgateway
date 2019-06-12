package com.bprocare;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.datatables.repository.DataTablesRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableAutoConfiguration
@EnableJpaRepositories(repositoryFactoryBeanClass = DataTablesRepositoryFactoryBean.class)
public class AlarmGatewayApplication implements CommandLineRunner {

	/*
	 * static final String topicExchangeName = "spring-boot-exchange";
	 * 
	 * static final String queueName = "/topic/public";
	 * 
	 * @Bean Queue queue() { return new Queue(queueName, false); }
	 * 
	 * @Bean TopicExchange exchange() { return new TopicExchange(topicExchangeName);
	 * }
	 * 
	 * @Bean Binding binding(Queue queue, TopicExchange exchange) { return
	 * BindingBuilder.bind(queue).to(exchange).with("/topic/public"); }
	 * 
	 * @Bean SimpleMessageListenerContainer container(ConnectionFactory
	 * connectionFactory, MessageListenerAdapter listenerAdapter) {
	 * SimpleMessageListenerContainer container = new
	 * SimpleMessageListenerContainer();
	 * container.setConnectionFactory(connectionFactory);
	 * container.setQueueNames(queueName);
	 * container.setMessageListener(listenerAdapter); return container; }
	 * 
	 * @Bean MessageListenerAdapter listenerAdapter(Receiver receiver) { return new
	 * MessageListenerAdapter(receiver, "receiveMessage"); }
	 */

	public static void main(String[] args) {
		SpringApplication.run(AlarmGatewayApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public LocalValidatorFactoryBean getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}
}
