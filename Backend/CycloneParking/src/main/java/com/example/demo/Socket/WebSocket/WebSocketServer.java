package com.example.demo.Socket.WebSocket;


import com.example.demo.CurrentlyParked.CurrentlyParkedRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;


@ServerEndpoint("/websocket/chat")
@Component
public class WebSocketServer {

    @Autowired
	CurrentlyParkedRepository repo;
	
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


		try {
			JSONObject jsonMessage = new JSONObject(message);
			JSONObject data = new JSONObject(jsonMessage.get("data").toString());
			logger.info(data.toString());
			String username1 = data.get("User").toString();
			String actualMessage = data.get("Message").toString();
			logger.info(username + ": " + actualMessage);

			if (actualMessage.startsWith("!park"))
			{
				String [] parkCommand = actualMessage.split(" ");
				String lot = parkCommand[1];
				Integer spot = Integer.parseInt(parkCommand[2]);

				Instant instant = Instant.now();
				Long timeStampMillis = instant.toEpochMilli();

				logger.info("lot : " + lot);
				logger.info("spot: " + spot);
				logger.info(username1);
				String license = "";
				try {
					license = repo.getLicenseFromUsername(username1);
				}
				catch(Exception e){
					e.printStackTrace();
				}
				logger.info(license);
				logger.info(timeStampMillis.toString());
				repo.addParkedSpot(username1, lot, spot, license, timeStampMillis);
				logger.info("GOT PAST ADD");

			}
			else // Message to whole chat
			{
				broadcast(username + ": " + message);
			}


		}
		catch (JSONException e) {
			e.printStackTrace();
		}


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

