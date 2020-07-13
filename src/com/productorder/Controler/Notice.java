package com.productorder.Controler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class Notice {
	
	private static JedisPool pool = JedisPoolUtil.getJedisPool();
	
	
	public static String getmessage(){
		Jedis jedis = null;
		jedis = pool.getResource();
		jedis.auth("123456");
		Set<String> keys = jedis.keys("*");
		JSONObject jObj = new JSONObject();
		for(String key:keys) {
			try {
				jObj.put(key, jedis.get(key));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		jedis.close();
		System.out.println("JOBJ = " + jObj);
		return jObj.toString();
		
	}
	
	public static void saveChatMessage(String orderid, String cancelmsg) {
		Jedis jedis = pool.getResource();
		jedis.auth("123456");
		jedis.set(orderid, cancelmsg);
		jedis.close();
	}
}
