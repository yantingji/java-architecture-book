package com.itedu.spring.ws.model;

import java.util.Date;

public final class TicketConfirmation {

	private String confirmationId;
	private String filmId;
	private int quantity;
	private Date sessionDate;
	private float amount;
	
	public TicketConfirmation(String filmId, Date sessionDate, int quantity, float amount) {
		this.confirmationId = filmId + "-" + quantity;
		this.filmId = filmId;
		this.sessionDate = new Date(sessionDate.getTime());
		this.quantity = quantity;
		this.amount = amount;
	}
	
	
	public String getConfirmationId() {
		return confirmationId;
	}
	
	public String getFilmId() {
		return filmId;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public Date getSessionDate() {
		return new Date(sessionDate.getTime());
	}
	
	public float getAmount() {
		return amount;
	}
}
