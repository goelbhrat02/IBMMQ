package com.bluespire.citizensmq.service;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.ibm.jakarta.jms.JMSMessage;
import com.ibm.msg.client.jakarta.jms.JmsMessage;
import com.ibm.jakarta.jms.JMSBytesMessage;

import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;

import jakarta.jms.BytesMessage;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MessageReceiver {
	private static Integer counter=0;
	private final JmsTemplate jmsTemplate;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private Instant messageReceivedTime;

	public MessageReceiver(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public String receiveMessageByCorrelationId(String correlationId) throws jakarta.jms.JMSException {
		LocalTime requestTime = LocalTime.now();
		// Set a receive timeout in milliseconds
		jmsTemplate.setReceiveTimeout(20000);

		// Receive the message
		JMSMessage receivedMessage = (JMSMessage) jmsTemplate.receiveSelected("DEV.QUEUE.2",
				"JMSCorrelationID='" + correlationId + "'");
		
//		System.out.println("Received message is : " + receivedMessage);
		if (receivedMessage != null) {
			logger.info("MessageReceiver    message received    corrId : {}" , receivedMessage.getJMSCorrelationID());
			LocalTime responseTime = LocalTime.now();
//			System.out.println("response time -> "+Duration.between(responseTime, requestTime).toMillis());
		}
		
		
		if (receivedMessage != null) {
			String messageBodyStringg = receivedMessage.toString();
			messageReceivedTime = Instant.now();
			logger.info("Services : Message Receiver :Time :{}", messageReceivedTime);
			return messageBodyStringg;
		} else
		{
//			System.out.println("counter   ----------------------------------> "+ counter);
			return "";
		}
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
}
