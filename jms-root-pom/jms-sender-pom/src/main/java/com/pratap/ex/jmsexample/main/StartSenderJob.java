package com.pratap.ex.jmsexample.main;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.lang.math.RandomUtils;

import com.pratap.ex.jmsexample.db.MyDbFactory;
import com.pratap.ex.jmsexample.sender.MyJmsSender;

public class StartSenderJob {

	public static void main(String[] args) throws SQLException {
		
		MyDbFactory myDbFactory = new MyDbFactory();
		myDbFactory.createOracleDataSource("jdbc:oracle:thin:@localhost:1521:XE", "system", "root");
		DataSource dataSource = myDbFactory.getOracleDataSource();
		String priorityQueueAndTableName = "pqntn";//RandomStringUtils.random( 16, true, true );
		System.out.println("-----> priorityQueueAndTableName : [ "+ priorityQueueAndTableName +" ]");
		
		MyJmsSender jmsSender = new MyJmsSender(dataSource, priorityQueueAndTableName, "userMsgId", "userMsgData", 5);
		
		
		
		for(int i=0;i<5;i++)
		{
			jmsSender.setUserMsgData("userMsgId-"+i);
			jmsSender.setUserMsgData("userMsgData-"+i);
			jmsSender.setPriority(RandomUtils.nextInt(7));
			new Thread(jmsSender).start();
		}
		
		
		
		System.out.println("::::END OF MAIN::::");

	}

}
