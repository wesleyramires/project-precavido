package com.precavido.app.enums;

public enum TypeCharge {
	
	DINHEIRO("Dinheiro"),
	TRANSFERÊNCIA_BANCÁRIA("Tranferência Bancária"),
	BOLETO_BANCÁRIO("Boleto Bancário"),
	CHEQUE("Cheque"),
	DÉBITO_AUTOMÁTICO("Débito Automático"),
	CARTÃO_DE_CRÉDITO("Cartão de Crédito");
	
	private String charge;

	TypeCharge(String charge) {
		this.charge = charge;
	}
	
	public String getCharge() {
		return charge;
	}
}
