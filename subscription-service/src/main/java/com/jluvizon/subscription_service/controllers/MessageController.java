package com.jluvizon.subscription_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jluvizon.subscription_service.models.Message;
import com.jluvizon.subscription_service.services.MessageService;

@RestController
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	@PostMapping(value = "/messages")
	public ResponseEntity postMessage(@RequestBody Message msgView) {
		messageService.postMessage(msgView);
		return new ResponseEntity<Message>(msgView, HttpStatus.OK);
	}

}
