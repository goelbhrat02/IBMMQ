package com.bluespire.citizensmq.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.ibm.jakarta.jms.JMSMessage;

import jakarta.jms.JMSException;
import jakarta.jms.Message;

@Service
public class MainFrame {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());		
	

	private final JmsTemplate jmsTemplate;
	private final ThreadPoolTaskExecutor taskExecutor;
	private long messageReceivedTime;
	private long replySentTime;
	private long timeTakenToProcessAndSendReply;
	
	@Value("${queue.input}")
	private String requestQueue;
	@Value("${queue.output}")
	private String responseQueue;

	public MainFrame(JmsTemplate jmsTemplate, ThreadPoolTaskExecutor taskExecutor) {
		this.jmsTemplate = jmsTemplate;
		this.taskExecutor = taskExecutor;
	}

	@JmsListener(destination = "${queue.input}")
	public void receiveMessage(JMSMessage receivedMessage) throws JMSException {
//		System.out.println("receive message function ${queue.input}");

//        System.out.println("Received Message: " + receivedMessage);
		messageReceivedTime=System.currentTimeMillis();
		logger.info("Mainframe:Received Message : {}",messageReceivedTime);
		
		if (receivedMessage != null)
			logger.info("MainFrame message received corrID-> : {}",receivedMessage.getJMSCorrelationID());
			//System.out.println("MainFrame    message received    corrID->" + receivedMessage.getJMSCorrelationID());

		replyAsync(receivedMessage,messageReceivedTime);
	}

	public void replyAsync(JMSMessage receivedMessage,long messageReceisvedTime) {
		taskExecutor.execute(() -> {
			try {
				Thread.sleep(10000);

				Message replyMessage = jmsTemplate.execute(session -> session.createMessage());

				// Setting the correlation ID and message ID from the received message
				replyMessage.setJMSCorrelationID(receivedMessage.getJMSCorrelationID());
				replyMessage.setJMSMessageID(receivedMessage.getJMSMessageID());

				// Sending the reply message to the output queue
				jmsTemplate.convertAndSend(responseQueue, replyMessage);
				replySentTime=System.currentTimeMillis();
				logger.info("Mainframe:Reply sent : {}",replySentTime);
				timeTakenToProcessAndSendReply=replySentTime-messageReceivedTime;
				//System.out.println("Reply sent: " + replyMessage);
				
				logger.info("Mainframe:Thread ID : {} and time taken {}",Thread.currentThread().getId(),timeTakenToProcessAndSendReply);
				//System.out.println(Thread.currentThread().getId());
			} catch (InterruptedException | JMSException e) {
				logger.error("error in mainframe : {}",e);
			}
		});
	}
}
