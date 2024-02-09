package com.bluespire.citizensmq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import javax.jms.BytesMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.ibm.jakarta.jms.JMSMessage;
import com.ibm.msg.client.jakarta.jms.JmsMessage;
import com.bluespire.citizensmq.model.AccountDetails;
import com.google.gson.JsonObject;
import com.ibm.jakarta.jms.JMSBytesMessage;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MessageReceiver {
	
	@Autowired
	private GetAccountDetailsService getAccountDetailsService;


	private final JmsTemplate jmsTemplate;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private Instant messageReceivedTime;

	public MessageReceiver(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public Object receiveMessageByCorrelationId(String correlationId) throws jakarta.jms.JMSException, IOException {
		// Set a receive timeout in milliseconds (e.g., 5000 for 5 seconds)
		jmsTemplate.setReceiveTimeout(30000);

		// Receive the message
		JMSMessage receivedMessage = (JMSMessage) jmsTemplate.receiveSelected("DEV.QUEUE.2",
				"JMSCorrelationID='" + correlationId + "'");
//		System.out.println("Received message is : " + receivedMessage);
		if (receivedMessage != null) {
			logger.info("MessageReceiver    message received    corrId : {}" , receivedMessage.getJMSCorrelationID());
		}

//		return extractMessageBody(receivedMessage);

		if (receivedMessage != null) {
			
			System.out.println("Received message:"+receivedMessage);
			String messageBodyStringg = receivedMessage.toString();
			String[] lines = messageBodyStringg.split("\n");
	        
	        // Get the last line`
	        String lastLine = lines[lines.length - 3]+"\n"+lines[lines.length - 2]+"\n"+lines[lines.length - 1];
	        byte[] code=lastLine.getBytes(Charset.forName("IBM1047"));
	        
	        for(byte b:code)System.out.println(b);
			messageReceivedTime = Instant.now();
			logger.info("Services : Message Receiver :Time :{}", messageReceivedTime);
			String output=getAccountDetailsService.ebcdicToJson(code);
			System.out.println(output);
			return output;
		} else
			return "msg not found";
	}
	
	
	

	private String extractMessageBody(JmsMessage message) throws JMSException {
		// Check if the message is a TextMessage
		try {
			if (message instanceof TextMessage) {
				return ((TextMessage) message).getText();
			} else if (message instanceof JMSBytesMessage) {
				byte[] messageBytes = new byte[(int) ((JMSBytesMessage) message).getBodyLength()];
				((JMSBytesMessage) message).readBytes(messageBytes);
				return new String(messageBytes, "UTF-8");
			} else if (message instanceof jakarta.jms.BytesMessage) {
				byte[] messageBytes = new byte[(int) ((jakarta.jms.BytesMessage) message).getBodyLength()];
				((jakarta.jms.BytesMessage) message).readBytes(messageBytes);
				return new String(messageBytes, "UTF-8");
			} else {
				System.out.println("class of message is : " + message.getClass());
				return "unsupported message type";
			}
		} catch (UnsupportedEncodingException e) {
			logger.error("Error in service: MessageReceiver {}",e);
		}

		return "exception found";
	}
	
	
	
	
//	public String receiveMessageByCorrelationId(String correlationId) throws jakarta.jms.JMSException {
//		// Set a receive timeout in milliseconds (e.g., 5000 for 5 seconds)
//		jmsTemplate.setReceiveTimeout(30000);
//
//		// Receive the message
//		JMSMessage receivedMessage = (JMSMessage) jmsTemplate.receiveSelected("DEV.QUEUE.2",
//				"JMSCorrelationID='" + correlationId + "'");
////		System.out.println("Received message is : " + receivedMessage);
//		if (receivedMessage != null) {
//			logger.info("MessageReceiver    message received    corrId : {}" , receivedMessage.getJMSCorrelationID());
//		}
//
////		return extractMessageBody(receivedMessage);
//
//		if (receivedMessage != null) {
//			
//			String messageBodyStringg = receivedMessage.toString();
//			messageReceivedTime = Instant.now();
//			logger.info("Services : Message Receiver :Time :{}", messageReceivedTime);
//			return messageBodyStringg;
//		} else
//			return "msg not found";
//	}
	
	
	
	


	
	
	
}
	
	
	
	
	
	