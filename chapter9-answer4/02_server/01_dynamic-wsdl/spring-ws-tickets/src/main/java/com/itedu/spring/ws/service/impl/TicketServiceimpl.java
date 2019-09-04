package com.itedu.spring.ws.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.itedu.spring.ws.model.TicketConfirmation;
import com.itedu.spring.ws.service.TicketService;

@Service
public class TicketServiceimpl implements TicketService {

  @Override
  public TicketConfirmation order(String filmId, Date sessionDate, int quantity) {
    float amount = 5.95f * quantity;
    filmId = filmId + " Response";
    TicketConfirmation confirmation = new TicketConfirmation(filmId, sessionDate, quantity, amount);
    return confirmation;
  }
}
