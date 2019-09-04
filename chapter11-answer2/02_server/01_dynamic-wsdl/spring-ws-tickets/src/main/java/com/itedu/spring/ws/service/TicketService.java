package com.itedu.spring.ws.service;

import java.util.Date;

import com.itedu.spring.ws.model.TicketConfirmation;

public interface TicketService {

	public TicketConfirmation order(String filmId, Date sessionDate, int quantity);
}
