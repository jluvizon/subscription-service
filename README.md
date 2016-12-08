# subscription-service

Microservice spec:
A simple message subscription service that exposes the following functionality:
- create a subscription
     (Would have at least one parameter, which would be a list of messageTypes the subscription wants to keep track of)
- read the subscription
- update the subscription
- post a message
 
The message would have at least a ‘messageType’ property.
 
In the response to the 'read subscription’ we would like also see how many times a particular message type has been received by a subscription. There may be more than one subscription listening for the same message type(s).
