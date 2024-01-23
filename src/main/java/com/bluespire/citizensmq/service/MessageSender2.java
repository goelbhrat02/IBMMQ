//package com.bluespire.citizensmq.service;
//
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.stereotype.Service;
//
//import com.bluespire.citizensmq.model.MessageBody;
//
//import jakarta.jms.BytesMessage;
//import java.io.UnsupportedEncodingException;
//import java.util.UUID;
//
//@Service
//public class MessageSender2 {
//
//    private final JmsTemplate jmsTemplate;
//
//    public MessageSender2(JmsTemplate jmsTemplate) {
//        this.jmsTemplate = jmsTemplate;
//    }
//
//    public String sendMessageToQueue(String mess) throws jakarta.jms.JMSException {
//        
//        byte[] messageBytes;
//        String[] correlationId = { "emptyString" }; // Using an array to make it mutable
//        
//        MessageBody message = null;
//        message.setMsgBody(mess);
//
//        try {
////            messageBytes = message.getBytes("UTF-8");
//
//            BytesMessage execute = jmsTemplate.execute(session -> {
//                BytesMessage jmsMessage = session.createBytesMessage();
////                jmsMessage.writeBytes(messageBytes);
//                jmsMessage.writeObject(message);
//
//                jmsMessage.setJMSType("1");
//
//                // Generating and set JMSCorrelationID
//                correlationId[0] = UUID.randomUUID().toString();
//                System.out.println("correlationId: " + correlationId[0]);
//                jmsMessage.setJMSCorrelationID(correlationId[0]);
//                jmsMessage.setJMSMessageID(correlationId[0]);
//
//                System.out.println("jms message is: " + jmsMessage);
//                sendJmsMessage(jmsMessage, "DEV.QUEUE.1");
//
//                System.out.println("Message sent to DEV.QUEUE.1: " + message + jmsMessage.getJMSMessageID());
//
//                return jmsMessage;
//            });
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//        return correlationId[0];
//    }
//
//
//    public void sendJmsMessage(BytesMessage jmsMessage, String queueName) {
//    	jmsTemplate.convertAndSend(queueName, jmsMessage, mess -> {
//    		jmsMessage.setIntProperty("JMS_IBM_MQMD_MsgType", 1);
//    		System.out.println("jmsmessage from sendjmsMessage: "+jmsMessage);
//    		return jmsMessage;
//    	});
//    }
//}
