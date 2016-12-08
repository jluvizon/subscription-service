package com.jluvizon.subscription_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jluvizon.subscription_service.models.Subscription;
import com.jluvizon.subscription_service.services.SubscriptionService;

@RestController
public class SubscriptionController {
	
	@Autowired
	private SubscriptionService subscriptionService;

	@PostMapping(value = "/subscriptions")
	public ResponseEntity createSubscription(@RequestBody Subscription subView) {
		Subscription subscription = subscriptionService.createSubscription(subView.getDescription(), subView.getMessageTypes());
		return new ResponseEntity<Subscription>(subscription, HttpStatus.OK);
	}
    
	@GetMapping("/subscriptions/{id}")
	public ResponseEntity readSubscription(@PathVariable("id") Long id) {
		Subscription subscription = subscriptionService.getSubscription(id);
		if(subscription == null){
			return new ResponseEntity("No Subscription found for id: " + id, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Subscription>(subscription, HttpStatus.OK);
	}
	
	@PutMapping("/subscriptions/{id}")
	public ResponseEntity updateSubscription(@PathVariable Long id, @RequestBody Subscription subView) {
		Subscription subscription = subscriptionService.updateSubscription(id, subView.getDescription(), subView.getMessageTypes());
		if(subscription == null){
			return new ResponseEntity("No Subscription found for id: " + subView.getId(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Subscription>(subscription, HttpStatus.OK);
	}

}
