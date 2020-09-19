package com.precavido.app.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.precavido.app.enums.StatusPendency;
import com.precavido.app.enums.TypeCharge;
import com.precavido.app.enums.TypePayment;


@Entity
@Table(name = "payments")
public class Payments {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;	
	
	@Column(name = "description")
	@NotBlank(message="Por favor, digite a Descrição do Pagamento!")
	private String description;
	
	@Column(name = "due_date")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@NotNull(message="Por favor, Digite a Data!")
	private Date dueDate;
	
	@Column(name = "amount")
	@NotNull(message="Por favor, Digite o Valor!")
	private BigDecimal amount;
	
	@Column(name = "type_charge")
	@Enumerated(EnumType.STRING)
	private TypeCharge charge;
	
	@Column(name = "type_payment")
	@Enumerated(EnumType.STRING)
	private TypePayment payment;
	
	@Column(name = "type_pendency")
	@Enumerated(EnumType.STRING)
	private StatusPendency pendency;
	
	@Column(name = "username")
	private String username;
	
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payments other = (Payments) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
