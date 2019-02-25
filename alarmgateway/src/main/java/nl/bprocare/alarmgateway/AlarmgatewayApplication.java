package nl.bprocare.alarmgateway;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.datatables.repository.DataTablesRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import nl.bprocare.alarmgateway.config.LabelFormatter;
import nl.bprocare.alarmgateway.dto.AlarmMessageDTO;
import nl.bprocare.alarmgateway.websocket.WebSocketHandler;

@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = DataTablesRepositoryFactoryBean.class)
public class AlarmgatewayApplication {

	public static final String queueName = "spring-boot";

	@Autowired
	private WebSocketHandler handler;

	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}
	
	@RabbitListener(queues = queueName)
    public void onMessage(AlarmMessageDTO alarmMessage) {
    	handler.sendMessageToAll(alarmMessage);
    }

	public static void main(String[] args) {
		SpringApplication.run(AlarmgatewayApplication.class, args);
	}

	@Configuration
	static class MyConfig implements WebMvcConfigurer {
		@Override
		public void addFormatters(FormatterRegistry registry) {
			registry.addFormatter(new LabelFormatter());
		}
	}
}
