package com.example.demo.Socket.WebSocket;

import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/websocket/chat}", configurator = CustomConfigurator.class)
public class Endpoint{
//Your code here
}