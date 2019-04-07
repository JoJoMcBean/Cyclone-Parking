package com.example.demo.Socket.WebSocket;


import com.example.demo.CurrentlyParked.CurrentlyParkedRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Vamsi Krishna Calpakkam
 *
 */
@ServerEndpoint("/websocket/chat")
@Component
public class WebSocketServer {

    @Autowired
	CurrentlyParkedRepository currentlyParkedRepository;
	
	// Store all socket session and their corresponding username.
    private static Map<Session, String> sessionUsernameMap = new HashMap<>();
    private static Map<String, Session> usernameSessionMap = new HashMap<>();
    
    private final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
    
    @OnOpen
    public void onOpen(
    	      Session session, 
    	      @PathParam("username") String username) throws IOException 
    {
        logger.info("Entered into Open");
        
        sessionUsernameMap.put(session, username);
        usernameSessionMap.put(username, session);
        
        String message="User:" + username + " has Joined the Chat";
        	broadcast(message);
		
    }
 
    @OnMessage
    public void onMessage(Session session, String message) throws IOException
    {
        // Handle new messages

    	logger.info("Entered into Message: Got Message:"+message);
    	String username = sessionUsernameMap.get(session);
    	/*
    	int index = message.indexOf("Message\":") + 10;
    	username = "mr.poop";
    	String actualMessage = message.substring(index, message.length() - 20);
    	logger.info(actualMessage);
    	String[] test = actualMessage.split(" ");
*/
    	/*if (test[0].equals("park")) // Direct message to a user using the format "@username <message>"
    	//{
    		logger.info(test[0]);
			logger.info(test[1]);
			logger.info(test[2]);
			logger.info(username);
    		String lot = test[1];
			Instant instant = Instant.now();
			Long timeStampMillis = instant.toEpochMilli();
    		Integer spot = Integer.parseInt(actualMessage.split(" ")[2]);
    		logger.info("lot : " + lot);
    		logger.info("spot: " + spot);
    		currentlyParkedRepository.addParkedSpot(username, lot, spot, "GJS", timeStampMillis);

    		/*
    		String destUsername = message.split(" ")[0].substring(1); // don't do this in your code!
    		sendMessageToPArticularUser(destUsername, "[DM] " + username + ": " + message);
    		sendMessageToPArticularUser(username, "[DM] " + username + ": " + message);
    		*/
    	//}
    	//else // Message to whole chat
    	//{
	    	broadcast(username + ": " + message);
    	//}
    }
 
    @OnClose
    public void onClose(Session session) throws IOException
    {
    	logger.info("Entered into Close");
    	
    	String username = sessionUsernameMap.get(session);
    	sessionUsernameMap.remove(session);
    	usernameSessionMap.remove(username);
        
    	String message= username + " disconnected";
        broadcast(message);
    }
 
    @OnError
    public void onError(Session session, Throwable throwable) 
    {
        // Do error handling here
    	logger.info("Entered into Error");
    }
    
	private void sendMessageToPArticularUser(String username, String message) 
    {	
    	try {
    		usernameSessionMap.get(username).getBasicRemote().sendText(message);
        } catch (IOException e) {
        	logger.info("Exception: " + e.getMessage().toString());
            e.printStackTrace();
        }
    }
    
    private static void broadcast(String message) 
    	      throws IOException 
    {	  
    	sessionUsernameMap.forEach((session, username) -> {
    		synchronized (session) {
	            try {
	                session.getBasicRemote().sendText(message);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    });
	}
}

