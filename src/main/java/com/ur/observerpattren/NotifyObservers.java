package com.ur.observerpattren;

import java.util.Observable;
import java.util.Observer;

import com.ticket.sendEmailNotify.EmailNotify;

public class NotifyObservers implements Observer{
   
	private TicketObservable tkObservable;
	private EmailNotify notify = new EmailNotify();
	
	
	@Override
	public void update(Observable obs, Object obj) {
		tkObservable = (TicketObservable) obs;
		notify.sendSimpleEmail(tkObservable.getTicket(),tkObservable.getIsCreate());
		System.out.println("ticket updated "+tkObservable.getTicket());
	}

}
