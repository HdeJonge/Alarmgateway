package nl.bprocare.alarmgateway.websocket;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;

import nl.bprocare.alarmgateway.dto.AlarmMessageDTO;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

	List<WebSocketSession> sessions = new ArrayList<>();


	@Autowired
	public WebSocketHandler() {
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		try {
			System.out.println("Connection established");
			this.sessions.add(session);
			System.out.println("Open sessions:" + sessions.size());
		} catch (Exception e) {
			System.out.println("Error in afterConnectionEstablished" + e);
		}
	}

	public void sendMessageToAll(AlarmMessageDTO message) {
		for (WebSocketSession session : sessions) {
			if (session != null && session.isOpen()) {
				try {
					System.out.println("Now sending:" + message);
					session.sendMessage(new TextMessage(new Gson().toJson(message)));
				} catch (Exception e) {
					System.out.println(e);
				}

			} else {
				System.out.println("Don't have open session to send:" + message);
			}
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("Connection closed");
		sessions.remove(session);
		System.out.println("Open sessions:" + sessions.size());
		super.afterConnectionClosed(session, status);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		if ("CLOSE".equalsIgnoreCase(message.getPayload())) {
			session.close();
		} else {
			System.out.println("Received:" + message.getPayload());
		}
	}
}
