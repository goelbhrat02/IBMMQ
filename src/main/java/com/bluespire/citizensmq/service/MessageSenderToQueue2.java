//package com.bluespire.citizensmq.service;
//
//import java.io.UnsupportedEncodingException;
//import java.time.Instant;
//
//import javax.jms.Connection;
//import javax.jms.Session;
//
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import jakarta.jms.JMSException;
//
//@EnableScheduling
//@Service
//public class MessageSenderToQueue2 {
//	
//	public String createMessage() {
//		Instant currentTime = Instant.now();
//		String msg = "request message " + "(QM1, DEV.QUEUE.1) " + currentTime.toString();
//		return msg;
//	}
//	
//	public Double createNoMessage() {
//		return Math.random();
//	}
//	
//	private final JmsTemplate jmsTemplate;
//	Connection connection = null;
//	Session session = null;
//
//    public MessageSenderToQueue2(JmsTemplate jmsTemplate) {
//        this.jmsTemplate = jmsTemplate;
//    }
//    
//    @Scheduled(fixedDelay = 30000) // Send a message every 5 seconds
//    public void sendMessageToQueue() throws JMSException, UnsupportedEncodingException {
////    	byte[] messageBytes = message.getBytes("UTF-8");
//    	
////        String message = createMessage();
//    	Double message = createNoMessage();
//        jmsTemplate.convertAndSend("DEV.QUEUE.1", message);
//        System.out.println("message send: " + message);
//    }
//
//}
