package com.precavido.app.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.precavido.app.enums.StatusPendency;
import com.precavido.app.enums.TypeCharge;
import com.precavido.app.enums.TypePayment;


public class PaymentsDTO {
	
	private long id;
	
	@NotBlank(message="Por favor, digite a descrição do pagamento!")
	private String description;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@NotNull(message="Por favor, digite a data!")
	private Date dueDate;
	
	@NotNull(message="Por favor, digite o valor!")
	@DecimalMin(value = "0.01", message="Por favor, digite um valor maior que 0!")
	private BigDecimal amount;

	@Enumerated(EnumType.STRING)
	private TypeCharge charge;
	
	@Enumerated(EnumType.STRING)
	private TypePayment payment;
	
	@Enumerated(EnumType.STRING)
	private StatusPendency pendency;
		
	public PaymentsDTO() {}

	public PaymentsDTO(long id, String description, Date dueDate, BigDecimal amount,
			TypeCharge charge, TypePayment payment, StatusPendency pendency) {
		this.id = id;
		this.description = description;
		this.dueDate = dueDate;
		this.amount = amount;
		this.charge = charge;
		this.payment = payment;
		this.pendency = pendency;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public TypeCharge getCharge() {
		return charge;
	}

	public void setCharge(TypeCharge charge) {
		this.charge = charge;
	}

	public TypePayment getPayment() {
		return payment;
	}

	public void setPayment(TypePayment payment) {
		this.payment = payment;
	}

	public StatusPendency getPendency() {
		return pendency;
	}

	public void setPendency(StatusPendency pendency) {
		this.pendency = pendency;
	}
	
	
	

}
