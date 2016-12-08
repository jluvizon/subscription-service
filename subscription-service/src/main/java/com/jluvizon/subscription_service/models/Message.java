package com.jluvizon.subscription_service.models;

import java.io.Serializable;

public class Message implements Serializable {
	
	private static final long serialVersionUID = 3883520363387380669L;
	
	private String content;
	private MessageType messageType;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public MessageType getMessageType() {
		return messageType;
	}
	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}
	
}
