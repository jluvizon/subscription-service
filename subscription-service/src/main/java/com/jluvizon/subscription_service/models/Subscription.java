package com.jluvizon.subscription_service.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Subscription implements Serializable {
	
	private static final long serialVersionUID = -7782880280897281616L;
	
	private long id;
	private String description;
	private List<MessageType> messageTypes = new ArrayList<MessageType>();
	private List<Message> consumedMessages = new ArrayList<Message>();
	
	public Subscription(long id, String description, List<MessageType> messageTypes){
		this.id = id;
		this.description = description;
		this.messageTypes = messageTypes;
	}
	
	public Subscription(){}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<MessageType> getMessageTypes() {
		return messageTypes;
	}
	public void setMessageTypes(List<MessageType> messageTypes) {
		this.messageTypes = messageTypes;
	}
	public List<Message> getConsumedMessages() {
		return consumedMessages;
	}
	public void setConsumedMessages(List<Message> consumedMessages) {
		this.consumedMessages = consumedMessages;
	}
	
	/**
	 * Counts all many messages have been received by the subscription grouping by {@link MessageType}.
	 * 
	 * @author Jariel Luvizon - <a href="mailto:jluvizon@gmail.com">jluvizon@gmail.com</a>
	 * @since 07 Dec 2016
	 *
	 */
	@JsonProperty("countMessagesReceived")
	public Map<MessageType, Integer> countMessagesReceived(){
		Map<MessageType, Integer> countMessagesReceived = new HashMap<MessageType, Integer>();
		for(Message message : consumedMessages){
			MessageType messageType = message.getMessageType();
			Integer countMessage = 1;
			if(countMessagesReceived.containsKey(messageType)){
				countMessage += countMessagesReceived.get(messageType);
			}
			countMessagesReceived.put(message.getMessageType(), countMessage);
		}
		return countMessagesReceived;
	}
	
	/**
	 * Checks if the a {@link Subscription} object is subscripting a determined {@link MessageType}.
	 * 
	 * @author Jariel Luvizon - <a href="mailto:jluvizon@gmail.com">jluvizon@gmail.com</a>
	 * @since 07 Dec 2016
	 *
	 */
	public boolean isSubscribing(MessageType messageType){
		for (MessageType msgTypeFollowing : messageTypes) {
			if(msgTypeFollowing.equals(messageType)){
				return true;
			}
		}
		return false;
	}

}
