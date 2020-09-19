package com.precavido.app.dto;

import java.math.BigDecimal;

public class DashboardDTO {
	
	private Integer allPayments;
	
	private Integer allPaymentsPerMonth;
	
	private BigDecimal monthlyValue;
	
	public DashboardDTO() {
		super();
	}

	public DashboardDTO(Integer allPayments, Integer allPaymentsPerMonth, BigDecimal monthlyValue) {
		super();
		this.allPayments = allPayments;
		this.allPaymentsPerMonth = allPaymentsPerMonth;
		this.monthlyValue = monthlyValue;
	}

	public Integer getAllPayments() {
		return allPayments;
	}

	public void setAllPayments(Integer allPayments) {
		this.allPayments = allPayments;
	}

	public Integer getAllPaymentsPerMonth() {
		return allPaymentsPerMonth;
	}

	public void setAllPaymentsPerMonth(Integer allPaymentsPerMonth) {
		this.allPaymentsPerMonth = allPaymentsPerMonth;
	}

	public BigDecimal getMonthlyValue() {
		return monthlyValue;
	}

	public void setMonthlyValue(BigDecimal monthlyValue) {
		this.monthlyValue = monthlyValue;
	}
	
	
	

}
