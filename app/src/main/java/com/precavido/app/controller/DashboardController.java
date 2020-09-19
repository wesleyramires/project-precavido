package com.precavido.app.controller;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.precavido.app.model.User;
import com.precavido.app.repository.PaymentsRepository;
import com.precavido.app.service.DashboardService;


@Controller
public class DashboardController {
	
	@Autowired
	DashboardService dashboardService;
	
	@Autowired
	PaymentsRepository paymentsRepository;
	
	@RequestMapping(value = "/dashboard")
	public ModelAndView allPayments(Model model) {
		ModelAndView view = new ModelAndView("Dashboard");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)user).getUsername();
		view.addObject("dashboardDTO", dashboardService.serachPayments(username));
		
		Map<String, BigDecimal> graphData = new TreeMap<>();
        graphData.put("01 Jan", dashboardService.monthValue(username, 1));
        graphData.put("02 Fev", dashboardService.monthValue(username, 2));
        graphData.put("03 Mar", dashboardService.monthValue(username, 3));
        graphData.put("04 Abril", dashboardService.monthValue(username, 4));
        graphData.put("05 Maio", dashboardService.monthValue(username, 5));
        graphData.put("06 Jun", dashboardService.monthValue(username, 6));
        graphData.put("07 Jul", dashboardService.monthValue(username, 7));
        graphData.put("08 Ago", dashboardService.monthValue(username, 8));
        graphData.put("09 Set", dashboardService.monthValue(username, 9));
        graphData.put("10 Out", dashboardService.monthValue(username, 10));
        graphData.put("11 Nov", dashboardService.monthValue(username, 11));
        graphData.put("12 Dez", dashboardService.monthValue(username, 12));
        model.addAttribute("chartData", graphData);
        
        
        Map<String, Integer> graphPizza = new TreeMap<>();
        graphPizza.put("Janeiro", dashboardService.monthPayments(username, 1));
        graphPizza.put("Fevereiro", dashboardService.monthPayments(username, 2));
        graphPizza.put("Março", dashboardService.monthPayments(username, 3));
        graphPizza.put("Abril", dashboardService.monthPayments(username, 4));
        graphPizza.put("Maio", dashboardService.monthPayments(username, 5));
        graphPizza.put("Junho", dashboardService.monthPayments(username, 6));
        graphPizza.put("Julho", dashboardService.monthPayments(username, 7));
        graphPizza.put("Agosto", dashboardService.monthPayments(username, 8));
        graphPizza.put("Setembro", dashboardService.monthPayments(username, 9));
        graphPizza.put("Outubro", dashboardService.monthPayments(username, 10));
        graphPizza.put("Novembro", dashboardService.monthPayments(username, 11));
        graphPizza.put("Dezembro", dashboardService.monthPayments(username, 12));
        model.addAttribute("chartPizza", graphPizza);
       
        return view;
	}	
	
}
