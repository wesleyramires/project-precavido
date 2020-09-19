package com.precavido.app.enums;

public enum TypePayment {
	
	VISTA("A Vista"),
	PRAZO("A Prazo");
	
	private String payment;

	TypePayment(String payment) {
		this.payment = payment;
	}
	
	public String getPayment() {
		return payment;
	}
}
