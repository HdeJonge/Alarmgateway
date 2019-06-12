package nl.bprocare.alarmgateway.controllers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import nl.bprocare.alarmgateway.AlarmgatewayApplication;
import nl.bprocare.alarmgateway.dto.AlarmMessageDTO;
import nl.bprocare.alarmgateway.websocket.WebSocketHandler;
@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequestMapping("private")
public class AlarmMessageController {
	
	@Autowired
	private WebSocketHandler handler;
	
	@Autowired
	private RabbitTemplate template;
	
	@GetMapping("messageForm")
	public String messageForm(Model model) {
		model.addAttribute("alarmMessageDTO", new AlarmMessageDTO());
		return "private/messageForm";
	}
	
	@PostMapping("sendMessage")
	public String sendMessage(AlarmMessageDTO alarmMessageDTO) {
		template.convertAndSend(AlarmgatewayApplication.queueName,alarmMessageDTO);
		//handler.sendMessageToAll(alarmMessageDTO);
		return "private/messageForm";
	}
	
	@GetMapping("messages")
	public String messageForm() {
		return "private/messages";
	}
}
