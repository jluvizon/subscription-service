package com.jluvizon.subscription_service.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The MessageType Enum represents all the possible categories which a {@link Subscription} can subscribe to keep track of.
 * 
 * @author Jariel Luvizon - <a href="mailto:jluvizon@gmail.com">jluvizon@gmail.com</a>
 * @since 07 Dec 2016
 *
 */
public enum MessageType {
	
	@JsonProperty("sports")
	SPORTS, 
	@JsonProperty("entertainment")
	ENTERTAINMENT, 
	@JsonProperty("politics")
	POLITICS
	
}
