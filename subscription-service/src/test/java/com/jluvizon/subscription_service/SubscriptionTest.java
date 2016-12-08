package com.jluvizon.subscription_service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jluvizon.subscription_service.models.MessageType;
import com.jluvizon.subscription_service.services.SubscriptionService;
import com.jluvizon.subscription_service.services.SubscriptionServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SubscriptionTest {
	
	SubscriptionService subscriptionService;

    @Before
    public void loadService() throws Exception {
        subscriptionService = new SubscriptionServiceImpl();
        createSubscription();
    }

    @Test
    public void createSubscription() throws Exception {
    	List<MessageType> messageTypes = new ArrayList<MessageType>();
    	messageTypes.add(MessageType.SPORTS);
        subscriptionService.createSubscription("Sport's fan", messageTypes);
    }

    @Test
    public void readNonExistingSubscription() throws Exception {
        subscriptionService.getSubscription(2);
    }

    @Test
    public void updateExistingSubscription() throws Exception {
    	List<MessageType> messageTypes = new ArrayList<MessageType>();
        subscriptionService.updateSubscription(1, "Unsubscribing", messageTypes);
    }

}
