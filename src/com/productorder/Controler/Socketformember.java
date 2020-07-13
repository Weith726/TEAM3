package com.productorder.Controler;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/socketformember/{memNO}")
public class Socketformember {
	
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
	@OnOpen
	public void onOpen(@PathParam("memNO")String memno,Session userSession) throws IOException {
		System.out.println(memno);
		sessionsMap.put(memno,userSession);
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
	public static boolean check(String memno) {
		if(sessionsMap.get(memno)!=null) {
			return true;
		}
		return false;
	}
	public static void sendmessage(String memno,String str) throws IOException {
		Session newsession = sessionsMap.get(memno);
			if (newsession.isOpen())
				newsession.getBasicRemote().sendText(str);
	}
}
