package com.jluvizon.subscription_service.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.jluvizon.subscription_service.models.Message;
import com.jluvizon.subscription_service.models.MessageType;
import com.jluvizon.subscription_service.models.Subscription;

@Service
public class SubscriptionServiceImpl implements SubscriptionService, MessageListener {
	
	private static final Logger log = LoggerFactory.getLogger(SubscriptionServiceImpl.class);
	
	private Map<Long, Subscription> subscriptions = new HashMap<Long, Subscription>();
    private AtomicLong sequenceSubscriptions = new AtomicLong();
    
  
	@Override
	public Subscription createSubscription(String description, List<MessageType> messageTypes) {
		Subscription sub = new Subscription(sequenceSubscriptions.incrementAndGet(), description, messageTypes);
		subscriptions.put(sub.getId(), sub);
		return sub;
	}

	@Override
	public Subscription getSubscription(long id) {
		return subscriptions.get(id);
	}

	@Override
	public Subscription updateSubscription(long id, String description, List<MessageType> messageTypes) {
		Subscription sub = getSubscription(id);
		if(sub!=null){
			sub.setDescription(description);
			sub.setMessageTypes(messageTypes);
		}
		return sub;
	}
	
	/**
	 * This method is called by the {@link MessageListener} when a new
	 * {@link Message} is posted. <br>
	 * It broadcasts the message to all the subscriptions who are keeping track of the same
	 * {@link MessageType} presents in the new Message.
	 * 
	 * @author Jariel Luvizon - <a href="mailto:jluvizon@gmail.com">jluvizon@gmail.com</a>
	 * @since 07 Dec 2016
	 *
	 */
	@JmsListener(destination = "subscribers")
	public void onMessage(javax.jms.Message jmsMessage) {
		Message message = castMessage(jmsMessage);
		
		if(subscriptions.isEmpty() || message == null) return;
		
		for(Long idSubscription : subscriptions.keySet()) {
			Subscription subscription = getSubscription(idSubscription);
			if(subscription.isSubscribing(message.getMessageType())){
				subscription.getConsumedMessages().add(message);
			}
		}
	}
	
	/**
	 * Cast a {@link javax.jms.Message} to a {@link Message}. <br>
	 * Returns null in case the jmsMessage can't be casted to a {@link Message} object.
	 * 
	 * @author Jariel Luvizon - <a href="mailto:jluvizon@gmail.com">jluvizon@gmail.com</a>
	 * @since 07 Dec 2016
	 *
	 */
	private Message castMessage(javax.jms.Message jmsMessage){
		try {
			Message message = (Message) ((ObjectMessage) jmsMessage).getObject();
			return message;
		}catch (ClassCastException e) {
			log.warn("Message type invalid.");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

}
