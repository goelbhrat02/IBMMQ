//package com.bluespire.citizensmq.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jms.annotation.EnableJms;
//import org.springframework.jms.connection.CachingConnectionFactory;
//import org.springframework.jms.core.JmsTemplate;
//
//import com.ibm.msg.client.jakarta.jms.JmsConnectionFactory;
//import com.ibm.msg.client.jakarta.jms.JmsConstants;
//import com.ibm.msg.client.jakarta.jms.JmsFactoryFactory;
//import com.ibm.msg.client.jakarta.wmq.WMQConstants;
//
//import jakarta.jms.JMSException;
//
//@Configuration
//@EnableJms
//public class JmsConfig {
//	
//	@Bean
//	public JmsTemplate jmsTemplate() {
//		JmsTemplate jmsTemplate = new JmsTemplate(getConnectionFactory());
//		return jmsTemplate;
//	}
//	
////	@Bean
////	public CachingConnectionFactory getCacheConnectionFactory(){
////		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(getConnectionFactory());
////		cachingConnectionFactory.setSessionCacheSize(10);
////		cachingConnectionFactory.setReconnectOnException(true);
////		cachingConnectionFactory.setCacheConsumers(false);
////		return cachingConnectionFactory;
////	}
//	
//	@Bean
//	public JmsConnectionFactory getConnectionFactory() {
//		try {
//			JmsFactoryFactory ff = JmsFactoryFactory.getInstance(JmsConstants.JAKARTA_WMQ_PROVIDER);
//			JmsConnectionFactory connectionFactory = ff.createConnectionFactory();
////			connectionFactory.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE, WMQConstants.WMQ_CM_CLIENT);
//			return connectionFactory;
//		}
//		catch(Exception e){
//			System.out.println("exception occur -> JmsConfig.java -> getConnectionFactory()");
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//}
