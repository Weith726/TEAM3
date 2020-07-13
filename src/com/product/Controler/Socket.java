package com.product.Controler;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/socket")
public class Socket {
	
	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());
	@OnOpen
	public void onOpen(Session userSession) throws IOException {
		connectedSessions.add(userSession);
		System.out.println("open");
	}

	@OnMessage
	public void onMessage(String tester, Session SessionFromOutSide) throws IOException {
		// OnMessage 有三個參數要傳
		// 第一個是必傳 (可以有多，但是一定要有一個)
		//  - String(我要處理文字訊息)
		//  - Byte[]
		//  - PongMessage (不常用)
		// 第二個是@PathParam (選擇性的)
		// 抓URL路徑參數
		// 像是@ServerEndpoint("/socket/{route}")
		// @Pathparam("route")可以抓到
		// 第三個是session (選擇性的)
		System.out.println("Message received:success");
	}
	
	public static void sendmessage(String str) throws IOException {
		for (Session session : connectedSessions) {
			if (session.isOpen())
				session.getBasicRemote().sendText(str);
		}
	}
}
