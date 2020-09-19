package com.precavido.app.enums;

public enum StatusPendency {
	
	FIXO("Fixo"),
	VARIÁVEL("Variável");
	
	private String pendency;

	StatusPendency(String pendency) {
		this.pendency = pendency;
	}
	
	public String getPendency() {
		return pendency;
	}
	
}
