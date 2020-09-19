package com.precavido.app.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precavido.app.dto.DashboardDTO;
import com.precavido.app.model.Payments;
import com.precavido.app.repository.PaymentsRepository;

@Service
public class DashboardService {
	
	@Autowired
	PaymentsRepository paymentsRepository;
	
	public DashboardDTO serachPayments(String username) {
		DashboardDTO dashboardDTO = new DashboardDTO();
		dashboardDTO.setAllPayments(paymentsRepository.findByUsername(username).size());
		
		Calendar cal = GregorianCalendar.getInstance();
		Integer month = cal.get(Calendar.MONTH) + 1;
		dashboardDTO.setAllPaymentsPerMonth(paymentsRepository.findByMonth(username, month).size());

		List<Payments> list = paymentsRepository.findByMonth(username, month);
		BigDecimal value = new BigDecimal("0");
		for (Payments payment : list) {
			value = value.add(payment.getAmount());
		}
		dashboardDTO.setMonthlyValue(value);
		return dashboardDTO;
	}	
	
	
	public BigDecimal monthValue(String username, Integer month) {
		Calendar cal = GregorianCalendar.getInstance();
		Integer year = cal.get(Calendar.YEAR);
		
		List<Payments> list = paymentsRepository.findByMonthAndYear(username, month, year);
		BigDecimal value = new BigDecimal("0");
		for (Payments payment : list) {
			value = value.add(payment.getAmount());
		}
		return value;
	}
	
	public Integer monthPayments(String username, Integer month) {
		Calendar cal = GregorianCalendar.getInstance();
		Integer year = cal.get(Calendar.YEAR);
		List<Payments> list = paymentsRepository.findByMonthAndYear(username, month, year);
		return list.size();
	}
	
	public Integer currentYearPayments(String username, Integer month) {
		Calendar cal = GregorianCalendar.getInstance();
		Integer year = cal.get(Calendar.YEAR);
		List<Payments> list = paymentsRepository.findByMonthAndYear(username, month, year);
		return list.size();
	}

}
