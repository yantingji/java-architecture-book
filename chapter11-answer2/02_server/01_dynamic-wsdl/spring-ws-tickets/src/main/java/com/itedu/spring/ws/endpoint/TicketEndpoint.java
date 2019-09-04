package com.itedu.spring.ws.endpoint;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.itedu.spring.ws.model.TicketConfirmation;
import com.itedu.spring.ws.service.TicketService;
import com.itedu.spring.ws.types.TicketRequest;
import com.itedu.spring.ws.types.TicketResponse;
import com.itedu.spring.ws.util.DateUtils;

@Endpoint
public class TicketEndpoint {
	private int retries;

	@Autowired
	private TicketService ticketService;
	
	@PayloadRoot(localPart="ticketRequest", namespace="http://www.com.itedu.spring.samples.com/tickets")
	public @ResponsePayload TicketResponse order(@RequestPayload TicketRequest ticketRequest) throws InterruptedException {
		Calendar sessionDate = Calendar.getInstance();
		sessionDate.set(2019, 9, 1);
		
		TicketConfirmation confirmation = ticketService.order(
				ticketRequest.getFilmId(), DateUtils.toDate(ticketRequest.getSessionDate()), ticketRequest.getQuantity().intValue());
		
		TicketResponse response = buildResponse(confirmation);
		
		retries++;
		if (retries < 1) {
			throw new RuntimeException("not enough retries");
		}
		else {
			retries = 0;
		}
		
		//Thread.sleep(8000);
		return response;
	}
	
	private TicketResponse buildResponse(TicketConfirmation confirmation) {
		TicketResponse response = new TicketResponse();
		response.setConfirmationId(confirmation.getConfirmationId());
		response.setFilmId(confirmation.getFilmId());
		response.setSessionDate(DateUtils.convertDate(confirmation.getSessionDate()));
		BigInteger quantity = new BigInteger(Integer.toString(confirmation.getQuantity()));
		response.setQuantity(quantity);
		BigDecimal amount = new BigDecimal(Float.toString(confirmation.getAmount()));
		response.setAmount(amount);
		
		return response;
	}
}
