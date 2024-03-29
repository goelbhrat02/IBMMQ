package com.bluespire.citizensmq.controller;

import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
	private MessageReceiver receiver;
	
	@Autowired
	private RequestRespondHandler requestResponseHandler;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	private long requestReceivedTime;
	private long responseSendTime;
	
	@GetMapping("request")
	public ResponseEntity<String> getMethodName() throws JMSException {
//		Instant requestReceivedTime = Instant.now();
		requestReceivedTime = System.currentTimeMillis();
		LocalTime requestTime = LocalTime.now();
//		logger.info("Contoller : Request received time : {}", requestReceivedTime);
		
	
		String response = requestResponseHandler.callMq();
		
		LocalTime responseTime = LocalTime.now();
//		System.out.println("response time -> "+Duration.between(responseTime, requestTime).toMillis());
		
		responseSendTime=System.currentTimeMillis();
//		logger.info("Controller : Respond sent time : {}",responseSendTime);
		
		long processTime=responseSendTime - requestReceivedTime;
		logger.info("Contoller :Time Taken to complete request : {}",processTime);
		
		if(response != "")
		return new ResponseEntity<String>(response, HttpStatus.OK);
		else return new ResponseEntity<>(HttpStatus.REQUEST_TIMEOUT);
	}
	
	
//	@GetMapping("all")
//	public String getMethodName(@RequestParam String param) throws UnsupportedEncodingException, JMSException {
//		allMsg.GetAllMessage();
//		return "done";
//	}
	
}
