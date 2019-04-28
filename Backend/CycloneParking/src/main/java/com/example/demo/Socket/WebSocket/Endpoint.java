package com.example.demo.Socket.WebSocket;

import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/websocket/chat}", configurator = CustomConfigurator.class)
public class endpoint{
//Your code here
}