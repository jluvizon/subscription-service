package com.jluvizon.subscription_service.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.jluvizon.subscription_service.models.Message;

@Service
public class MessageServiceImpl implements MessageService {
	
	private List<Message> messagesPosted = new ArrayList<Message>();
    
	@Autowired
    private JmsTemplate jmsTemplate;
    
	public void postMessage(Message message) {
		messagesPosted.add(message);
		jmsTemplate.convertAndSend("subscribers", message);
	}

}
