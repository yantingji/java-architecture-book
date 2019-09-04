package com.itedu.spring.integration.ws.gateway;

import org.springframework.integration.annotation.Gateway;

import com.itedu.spring.integration.ws.types.TicketRequest;
import com.itedu.spring.integration.ws.types.TicketResponse;

public interface TicketGateway {

	/**
	 * Entry to the messaging system. All invocations to this method will be intercepted and sent to the SI "system entry" gateway
	 * 
	 * @param request
	 */
	@Gateway
	public TicketResponse invoke(TicketRequest request);
}
