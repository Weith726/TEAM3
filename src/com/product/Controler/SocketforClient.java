package com.product.Controler;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/socketfornewproduct")
public class SocketforClient {

	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());
	@OnOpen
	public void onOpen(Session userSession) throws IOException {
		connectedSessions.add(userSession);
	}
	
	public static void sendmessage(String str) throws IOException {
		for (Session session : connectedSessions) {
			if (session.isOpen())
				session.getBasicRemote().sendText(str);
		}
	}
	
}
