package com.jluvizon.subscription_service.services;

import java.util.List;

import com.jluvizon.subscription_service.models.MessageType;
import com.jluvizon.subscription_service.models.Subscription;

public interface SubscriptionService {
	
	public Subscription createSubscription(String description, List<MessageType> messageTypes);
	
	public Subscription getSubscription(long id);
	
	public Subscription updateSubscription(long id, String description, List<MessageType> messageTypes);
	
}
