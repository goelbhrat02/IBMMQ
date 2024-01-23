//package com.bluespire.citizensmq.service;
//
//import java.io.UnsupportedEncodingException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.jms.annotation.JmsListener;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//import org.springframework.stereotype.Service;
//
//import com.ibm.jakarta.jms.JMSBytesMessage;
//import com.ibm.jakarta.jms.JMSMessage;
//import com.ibm.msg.client.jakarta.jms.JmsMessage;
//import com.ibm.msg.client.jakarta.jms.JmsMessageConsumer;
//
//import jakarta.jms.BytesMessage;
//import jakarta.jms.JMSException;
//import jakarta.jms.TextMessage;
//
//@Service
//public class GetAllMessage {
//
//    private final JmsTemplate jmsTemplate;
//    
//
//    @Value("DEV.QUEUE.2")
//    private String outputQueue;
//
//    public GetAllMessage(JmsTemplate jmsTemplate) {
//        this.jmsTemplate = jmsTemplate;
//    }
//    
//    public void GetAllMessage() throws UnsupportedEncodingException, JMSException {
//    	receiveMessage(null);
//    }
//    
//    @JmsListener(destination = "DEV.QUEUE.2")
//    public void receiveMessage(JmsMessage receivedMessage) throws JMSException, UnsupportedEncodingException {
//        System.out.println("Received Message: " + receivedMessage);
//    }
//}