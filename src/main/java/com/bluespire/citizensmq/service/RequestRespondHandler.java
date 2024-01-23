package com.bluespire.citizensmq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.stereotype.Component;

import jakarta.jms.JMSException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class RequestRespondHandler {
	
	private final MessageSender sender;
	private final MessageReceiver receiver;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public RequestRespondHandler(MessageSender sender, MessageReceiver receiver) {
		this.sender = sender;
		this.receiver = receiver;
	}
	
	public String callMq() {
		try {
			String correlationId = sender.sendMessageToQueue();
			String response = receiver.receiveMessageByCorrelationId(correlationId);
			return response;
		}
		catch (JmsException e) {
			//System.out.println("jmsException occur at RequestRespondHandler.java");
			logger.error("Eroor in services RequestRespondHandler : {}",e);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("Eroor in services RequestRespondHandler : {}",e);
		}
		return "exception occur";
	}
}
