package com.bluespire.citizensmq.service;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.bluespire.citizensmq.model.AccountDetails;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.jakarta.jms.JMSMessage;
import com.ibm.msg.client.jakarta.jms.JmsMessage;

import jakarta.jms.BytesMessage;
import jakarta.jms.JMSException;
import jakarta.jms.Message;

@Service
public class MainFrame {
//	private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//	@Autowired
//	private Converstions getAccountDetailsService;
//
//	private final JmsTemplate jmsTemplate;
//	private final ThreadPoolTaskExecutor taskExecutor;
////	private final AccountDetails accountDetails;
//	private long messageReceivedTime;
//	private long replySentTime;
//	private long timeTakenToProcessAndSendReply;
//
//	public MainFrame(JmsTemplate jmsTemplate, ThreadPoolTaskExecutor taskExecutor) {
//		this.jmsTemplate = jmsTemplate;
//		this.taskExecutor = taskExecutor;
////		this.accountDetails=accountDetails;
//	}
//
//	@JmsListener(destination = "DEV.QUEUE.1")
//	public void receiveMessage(JMSMessage receivedMessage) throws JMSException {
//
//        System.out.println("Received Message: " + receivedMessage);
//		messageReceivedTime = System.currentTimeMillis();
//		logger.info("Mainframe:Received Message : {}", messageReceivedTime);
//
//		if (receivedMessage != null)
//			logger.info("MainFrame message received corrID-> : {}", receivedMessage.getJMSCorrelationID());
//		// System.out.println("MainFrame message received corrID->" +
//		// receivedMessage.getJMSCorrelationID());
//		
//		
//
//		replyAsync(receivedMessage, messageReceivedTime);
//	}
//
//	public void replyAsync(JMSMessage receivedMessage, long messageReceisvedTime) {
//		taskExecutor.execute(() -> {
//			try {
//				Thread.sleep(0);
//
//				// System.out.println("Reply sent: " + replyMessage);
//
//				logger.info("Mainframe:Thread ID : {} and time taken {}", Thread.currentThread().getId(),
//						timeTakenToProcessAndSendReply);
//				// System.out.println(Thread.currentThread().getId());
//
//				jmsTemplate.send("DEV.QUEUE.2", session -> {
//					BytesMessage message = session.createBytesMessage();
//					try {
//						message.writeBytes(processEbcdic());
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					message.setJMSCorrelationID(receivedMessage.getJMSCorrelationID());
//					return message;
//				});
//
//				replySentTime = System.currentTimeMillis();
//				logger.info("Mainframe:Reply sent : {}", replySentTime);
//				timeTakenToProcessAndSendReply = replySentTime - messageReceivedTime;
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//		});
//	}
//
//	public byte[] processEbcdic() throws IOException {
//		
//
//		AccountDetails accountDetails = new AccountDetails("Sai Priya", "SBIN0020538", 45678, "savings");
//
//		byte[] ebcdicResultData = getAccountDetailsService.jsonToEbcdic(accountDetails);
////		System.out.println(ebcdicResultData);
//	
//		return ebcdicResultData;
//		
//	}
}
