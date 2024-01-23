package com.bluespire.citizensmq.service;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import jakarta.jms.BytesMessage;
import lombok.extern.log4j.Log4j2;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.UUID;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MessageSender {
    private final JmsTemplate jmsTemplate;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Instant messageSenderTime;

    public MessageSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
    
    public String createMessage() {
    	double random = Math.random();
    	random = Math.round(random*1000);
    	Instant now = Instant.now();
    	String msg = "Msg->"+random+"      creationTime->" + now;
    	return msg;
    }
    
    public String sendMessageToQueue() throws jakarta.jms.JMSException {
        String message = createMessage();
        byte[] messageBytes;
        String[] correlationId = { "emptyString" }; // Using an array to make it mutable
        
        try {
            messageBytes = message.getBytes("UTF-8");

            BytesMessage execute = jmsTemplate.execute(session -> {
                BytesMessage jmsMessage = session.createBytesMessage();
                jmsMessage.writeBytes(messageBytes);

                jmsMessage.setJMSType("1");

                // Generating and set JMSCorrelationID
                correlationId[0] = UUID.randomUUID().toString();
//                System.out.println("correlationId: " + correlationId[0]);
                jmsMessage.setJMSCorrelationID(correlationId[0]);
                jmsMessage.setJMSMessageID(correlationId[0]);

//                System.out.println("jms message is: " + jmsMessage);
                sendJmsMessage(jmsMessage, "DEV.QUEUE.1");

//                System.out.println("Message sent to DEV.QUEUE.1: " + message + jmsMessage.getJMSMessageID());

                return jmsMessage;
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return correlationId[0];
    }


    public void sendJmsMessage(BytesMessage jmsMessage, String queueName) {
    	jmsTemplate.convertAndSend(queueName, jmsMessage, mess -> {
    		jmsMessage.setIntProperty("JMS_IBM_MQMD_MsgType", 1);
    		messageSenderTime=Instant.now();
    		
//    		System.out.println("jmsmessage from sendjmsMessage: "+jmsMessage);
    		logger.info("Message Sender    msg send to DEV.QUEUE.1    corrId {} Time : {}",jmsMessage.getJMSCorrelationID(),messageSenderTime);
    		return jmsMessage;
    	});
    }
}
