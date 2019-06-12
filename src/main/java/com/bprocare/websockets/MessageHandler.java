package com.bprocare.websockets;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class MessageHandler extends TextWebSocketHandler {

       List<WebSocketSession> sessions = new ArrayList<>();


       @Autowired
       public MessageHandler() {
       }

       @Override
       public void afterConnectionEstablished(WebSocketSession session) {
             try {

                    this.sessions.add(session);
 
             } catch (Exception e) {

             }
       }

       public void sendMessageToAll(String messageStr) {
             for (WebSocketSession session : sessions) {
                    if (session != null && session.isOpen()) {
                           try {
                                  //log.info("Now sending:" + wirelessPressDto);
                                  session.sendMessage(new TextMessage(messageStr));
                           } catch (Exception e) {
                                  //log.error(e);
                           }

                    } else {
                          
                    }
             }
       }

       @Override
       public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
             
             sessions.remove(session);

             super.afterConnectionClosed(session, status);
       }

       @Override
       protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
             if ("CLOSE".equalsIgnoreCase(message.getPayload())) {
                    session.close();
             } else {
                    
             }
       }
}

