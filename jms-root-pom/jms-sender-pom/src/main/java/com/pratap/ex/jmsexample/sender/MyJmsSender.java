package com.pratap.ex.jmsexample.sender;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.UUID;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import javax.sql.DataSource;

import oracle.jms.AQjmsFactory;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class MyJmsSender implements Runnable{

	private DataSource dataSource;
	private String priorityQueueAndTableName;
	private String userMsgId;
	private String userMsgData;
	private int priority;

	public MyJmsSender(DataSource dataSource, String priorityQueueAndTableName , String userMsgId, String userMsgData, int priority) {
		this.dataSource = dataSource;
		this.priorityQueueAndTableName = priorityQueueAndTableName;
		this.userMsgId = userMsgId;
		this.userMsgData = userMsgData;
		this.priority = priority;
	}

	public synchronized void run() 
	{
		try{

			ConnectionFactory connectionFactory = null;
			try {
				connectionFactory = AQjmsFactory.getQueueConnectionFactory(dataSource);
			} catch (JMSException e) {
				e.printStackTrace();
			}

			//Below code is for creating the messages
			//for(int i=1;i<=10;i++)
			//{
				System.out.println("sending message.......");
				Thread.currentThread().sleep(6000);
				JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
				jmsTemplate.setExplicitQosEnabled(true);
				//int priority = RandomUtils.nextInt(7);
				MessageCreator messageCreator = new MessageCreator() {
					//@Override
					public Message createMessage(Session session) throws JMSException {
						MapMessage message = session.createMapMessage();
						/* set some properties*/
						
						//String userMsgId = UUID.randomUUID().toString();//new BigInteger(130, new SecureRandom()).toString(10);
						//String userMsgData = UUID.randomUUID().toString();
						//int priority = RandomUtils.nextInt(7);
						System.out.println("______________ARRIVED MESSAGE DETAILS______________");
						System.out.println("USER MESSAGE ID  : " + userMsgId);
						System.out.println("USER MESSAGE DATA: " + userMsgData);
						System.out.println("MESSAGE PRIORITY : " + priority);
						System.out.println("DESTINATION QUEUE: " + priorityQueueAndTableName);
						System.out.println("___________________________________________________");
						message.setString("userMsgId", userMsgId);
						message.setString("userMsgData", userMsgData);
						message.setJMSPriority(priority);
						return message;
					}
				};
				jmsTemplate.setPriority(priority);
				jmsTemplate.setDefaultDestinationName(priorityQueueAndTableName);
				jmsTemplate.setConnectionFactory(connectionFactory);
				jmsTemplate.send(priorityQueueAndTableName, messageCreator);
				System.out.println("1* message sent.....");
			//}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String getPriorityQueueAndTableName() {
		return priorityQueueAndTableName;
	}

	public void setPriorityQueueAndTableName(String priorityQueueAndTableName) {
		this.priorityQueueAndTableName = priorityQueueAndTableName;
	}

	public String getUserMsgId() {
		return userMsgId;
	}

	public void setUserMsgId(String userMsgId) {
		this.userMsgId = userMsgId;
	}

	public String getUserMsgData() {
		return userMsgData;
	}

	public void setUserMsgData(String userMsgData) {
		this.userMsgData = userMsgData;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
}
