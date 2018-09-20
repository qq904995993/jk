package com.example.demo;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

public class RabbitMQ {
	
	/**
	 * 消息生产者
	 * @param host			服务器地址		
	 * @param userName		用户名
	 * @param password		密码
	 * @param port			端口
	 * @param queue			队列名
	 * @param durable		是否持久化
	 * @param exclusive		是否独占
	 * @param autoDelete	是否断开自动删除
	 * @param arguments		队列参数
	 * @param exchange		交换机
	 * @param props			消息属性
	 * @param message		消息特容
	 * @throws Exception 
	 */
	public static void producer(String host, String userName, String password, int port, String queue, 
			boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments, 
			String exchange, BasicProperties props, String message) throws Exception {
		
		if(message == null)		throw new Exception("消息为空！");
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
	    //设置RabbitMQ相关信息
		connectionFactory.setHost(host);
		connectionFactory.setUsername(userName);
	    connectionFactory.setPassword(password);
	    connectionFactory.setPort(port);
	    
	    Connection connection = null;
	    Channel channel = null;
	    try{
	    	//创建一个新的连接
		    connection = connectionFactory.newConnection();
	    	//创建一个通道
	    	channel = connection.createChannel();
	    	//声明一个队列
	    	channel.queueDeclare(queue, durable, exclusive, autoDelete, arguments);
	    	//发送消息到队列中
	    	channel.basicPublish(exchange, queue, props, message.getBytes("UTF-8"));
	    	System.out.println("Producer Send +'" + message + "' to " + queue);
	    }
	    finally {
	    	//关闭通道和连接
	    	if(channel != null)	channel.close();
	    	if(connection != null)	connection.close();
	    }
	    
	}
	
	
	/**
	 * 消息消费者
	 * @param host			服务器地址
	 * @param queue			队列名称
	 * @param durable		是否持久化
	 * @param exclusive		是否独占
	 * @param autoDelete	是否断开自动删除
	 * @param arguments		队列参数
	 * @param autoAck		是否自动回复
	 * @throws IOException
	 * @throws TimeoutException
	 */
	public static void consumer(String host, String queue, boolean durable, boolean exclusive, 
			boolean autoDelete, Map<String, Object> arguments, boolean autoAck) 
					throws IOException, TimeoutException {
		// 创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置RabbitMQ地址
        connectionFactory.setHost(host);
        
        //创建一个新的连接
        Connection connection = connectionFactory.newConnection();
        //创建一个通道
        Channel channel = connection.createChannel();
        //声明要关注的队列
        channel.queueDeclare(queue, durable, exclusive, autoDelete, arguments);
        System.out.println("Customer Waiting Received messages from " + queue);
        
        //DefaultConsumer类实现了Consumer接口，通过传入一个频道，
        // 告诉服务器我们需要那个频道的消息，如果频道中有消息，就会执行回调函数handleDelivery
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Customer Received '" + message + "'");
            }
        };
        //自动回复队列应答 -- RabbitMQ中的消息确认机制
        channel.basicConsume(queue, autoAck, consumer);
	}
	
	
	public static void main(String args[]) throws Exception {
		String host = "127.0.0.1";
		String userName = "guest";
		String password = "guest";
		int port = 5672;
		String queue = "JK";
		boolean durable = false;
		boolean exclusive = false;
		boolean autoDelete = false;
		Map<String, Object> arguments = null;
		String message = "这是一条神奇的铁路";
		RabbitMQ.producer(host, userName, password, port, queue, durable, exclusive,
				autoDelete, arguments, "", null, message);
		
		RabbitMQ.consumer(host, queue, durable, exclusive, autoDelete, arguments, true);
	}
}
