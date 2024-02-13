package com.bluespire.citizensmq.controller;

import java.io.IOException;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bluespire.citizensmq.model.AccountDetails;
import com.bluespire.citizensmq.model.SavingsAccount;
import com.bluespire.citizensmq.service.Converstions;
import com.bluespire.citizensmq.service.MessageReceiver;
//import com.bluespire.citizensmq.service.MessageSender;
import com.bluespire.citizensmq.service.MessageSender;
import com.bluespire.citizensmq.service.RequestRespondHandler;

import jakarta.jms.JMSException;

import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@RestController
@RequestMapping("/")
public class MessageController {
	
	@Autowired
	private MessageSender sender;
	
	
	@Autowired
	private RequestRespondHandler requestResponseHandler;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	
//	@Autowired
//	private GetAllMessage allMsg;
	
	@PostMapping("details")
	public Object getAccountDetails(@RequestBody SavingsAccount savingsAccount ) throws IOException, javax.jms.JMSException {
		Object response = requestResponseHandler.callMq(savingsAccount);
		System.out.println(response.toString());
		
		return response;
		
	}
	
	@PostMapping("send")
	public ResponseEntity<String> putMessage() throws Exception {
		String response = sender.sendMessageToQueue(null);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
//	@GetMapping("/receive/{corrID}")
//	public ResponseEntity<String> receiveMessage(@PathVariable String corrID) throws JMSException, javax.jms.JMSException, IOException {
//		String receiveMessageByCorrelationId = receiver.receiveMessageByCorrelationId(corrID);
//		return new ResponseEntity<String>(receiveMessageByCorrelationId, HttpStatus.FOUND);
//	}
//	
	
//	@GetMapping("request")
//	public String getMethodName() throws Exception {
////		Instant requestReceivedTime = Instant.now();
//		requestReceivedTime = System.currentTimeMillis();
//		logger.info("Contoller : Request received time : {}", requestReceivedTime);
//		
//	
//		String response = requestResponseHandler.callMq(null);
//		
//		responseSendTime=System.currentTimeMillis();
//		logger.info("Controller : Respond sent time : {}",responseSendTime);
//		
//		long processTime=responseSendTime - requestReceivedTime;
//		logger.info("Contoller :Time Taken to complete request : {}",processTime);
//		
//		
//		return response;
//	}
//	
	
//	@GetMapping("all")
//	public String getMethodName(@RequestParam String param) throws UnsupportedEncodingException, JMSException {
//		allMsg.GetAllMessage();
//		return "done";
//	}
	
}
