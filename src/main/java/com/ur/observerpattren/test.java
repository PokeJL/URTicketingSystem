package com.ur.observerpattren;

public class test {

	public static void main(String[] args) {
		TicketObservable tko=new TicketObservable();
		NotifyObservers no=new NotifyObservers();
       tko.addObserver(no);
       
       
	}

}
