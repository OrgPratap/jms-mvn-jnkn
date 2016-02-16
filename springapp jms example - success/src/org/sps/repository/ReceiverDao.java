package org.sps.repository;

import java.sql.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sps.domain.Product;
import oracle.jms.AQjmsFactory;
import javax.sql.DataSource;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import javax.jms.Message;
import javax.jms.MapMessage;
import javax.jms.Session;


public class ReceiverDao extends JdbcProductDao
{
	protected Connection connection;
	public void processProduct(final Product prod) 
	{
		logger.info("creating jms-message for product: " + prod.getDescription());
		final int priority =3;
		DataSource dataSource = getDataSource();
		ConnectionFactory connectionFactory = null;
		try {
			connectionFactory = AQjmsFactory.getQueueConnectionFactory(dataSource);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
		jmsTemplate.setExplicitQosEnabled(true);
		MessageCreator messageCreator = new MessageCreator() {
		        @Override
		        public Message createMessage(Session session) throws JMSException {
		            MapMessage message = session.createMapMessage();
		            // ... set some properties
		            message.setJMSPriority(priority);
		            message.setInt("id", prod.getId());
		            message.setString("description", prod.getDescription());
		            message.setDouble("price", prod.getPrice());
		            logger.info("[[[MESSAGE-ID :" + message.getJMSMessageID() + " ]]]");
		            return message;
		        }
		};
		jmsTemplate.setPriority(priority);
		jmsTemplate.setDefaultDestinationName(RECEIVER_QUEUE_NAME);
		jmsTemplate.setConnectionFactory(connectionFactory);
		jmsTemplate.send(RECEIVER_QUEUE_NAME, messageCreator);
		logger.info(">>createdted successfully...");
	}
		
}
