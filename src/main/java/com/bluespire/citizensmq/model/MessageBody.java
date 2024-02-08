package com.bluespire.citizensmq.model;

import org.springframework.stereotype.Component;

public class MessageBody {
	
	public String msgBody;

	@Override
	public String toString() {
		return "MessageBody [msgBody=" + msgBody + "]";
	}

	public String getMsgBody() {
		return msgBody;
	}

	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}

}
