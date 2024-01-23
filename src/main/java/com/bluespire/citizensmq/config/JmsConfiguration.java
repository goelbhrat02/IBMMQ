package com.bluespire.citizensmq.config;

import org.springframework.context.annotation.Configuration;

import jakarta.jms.JMSConnectionFactory;

@Configuration
public class JmsConfiguration {
	
//	@Bean
//	public JmsTemplate jmsTemplate() throws JMSException, javax.jms.JMSException{
//		JmsTemplate jmsTemplate = new JmsTemplate();
//		jmsTemplate.setConnectionFactory(cachingConnectionFactory());
//		return jmsTemplate;
//	}
//	@Bean
//	public JmsTemplate jmsTemplate() throws JMSException, jakarta.jms.JMSException {
//		JmsTemplate jmsTemplate = new JmsTemplate();
//		jmsTemplate.setConnectionFactory(createConnectionFactory());
//		return jmsTemplate;
//	}
//	
//	@Bean
//	public CachingConnectionFactory cachingConnectionFactory() throws JMSException, javax.jms.JMSException{
//		CachingConnectionFactory factory = new CachingConnectionFactory();
//		factory.setSessionCacheSize(10);
//		factory.setTargetConnectionFactory((ConnectionFactory) createConnectionFactory());
//		factory.setReconnectOnException(true);
//		factory.setCacheConsumers(false);
//		factory.afterPropertiesSet();
//		
//		return factory;
//	}

//	
//	@Bean
//	public JMSConnectionFactory createConnectionFactory() throws JMSException, javax.jms.JMSException{
//		JmsFactoryFactory ff = com.ibm.msg.client.jms.JmsFactoryFactory.getInstance(WMQConstants.WMQ_PROVIDER)
//		JmsConnectionFactory cf = ff.createConnectionFactory();
//		cf.setStringProperty(WMQConstants.WMQ_QUEUE_MANAGER,"QM1");
//		cf.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE, WMQConstants.WMQ_CM_CLIENT);
//		return cf;
//		
//	}
//	
}