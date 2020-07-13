package webscoket;

import redis.clients.jedis.JedisPool;
import java.util.List;
import redis.clients.jedis.Jedis;

public class JedisHandleMessage {
	
	private static JedisPool pool = JedisPoolUtil.getJedisPool();

	public static List<String> getHistoryMsg(String sender,String receiver){
		String key = new StringBuilder(sender).append(":").append(receiver).toString();
		Jedis jedis = null;
		jedis = pool.getResource();
		jedis.auth("123456");
		List<String> historyData = jedis.lrange(key,0,-1);
		jedis.close();
		return historyData;
	}
	
	public static void saveChatMessage(String sender,String receiver,String message) {
		String senderKey = new StringBuilder(sender).append(":").append(receiver).toString();
		String receiverKey = new StringBuilder(receiver).append(":").append(sender).toString();
		Jedis jedis = pool.getResource();
		jedis.auth("123456");
		jedis.rpush(senderKey, message);
		jedis.rpush(receiverKey, message);

		jedis.close();
	
	}
	
}
