package com.precavido.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.precavido.app.dto.PaymentsDTO;
import com.precavido.app.model.Payments;
import com.precavido.app.model.User;
import com.precavido.app.repository.PaymentsRepository;


@Service
public class PaymentsService {

	@Autowired
	private PaymentsRepository paymentsRepository;
	
	public Payments addPayments(PaymentsDTO paymentsDTO) {
		Payments payments = new Payments();
		payments.setId(paymentsDTO.getId());
		payments.setDescription(paymentsDTO.getDescription());
		payments.setDueDate(paymentsDTO.getDueDate());
		payments.setAmount(paymentsDTO.getAmount());
		payments.setCharge(paymentsDTO.getCharge());
		payments.setPendency(paymentsDTO.getPendency());
		payments.setPayment(paymentsDTO.getPayment());
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (user instanceof UserDetails) {
			username = ((UserDetails)user).getUsername();
			payments.setUsername(username);
		} 
		
		return paymentsRepository.save(payments);
	}

	public void deletePayment(long id) {
		Payments payment = (Payments) paymentsRepository.deleteById(id);
		paymentsRepository.delete(payment);
	}
	
}
