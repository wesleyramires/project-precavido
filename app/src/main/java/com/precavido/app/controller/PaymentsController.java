package com.precavido.app.controller;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.precavido.app.dto.PaymentsDTO;
import com.precavido.app.model.Payments;
import com.precavido.app.model.User;
import com.precavido.app.repository.PaymentsRepository;
import com.precavido.app.service.PaymentsService;


@Controller
public class PaymentsController {

	@Autowired
	private PaymentsService paymentsService;
	
	@Autowired
	private PaymentsRepository paymentsRepository;
	
	@GetMapping(value="/dashboard/add")
	public ModelAndView dashboardAdd() {
		ModelAndView view = new ModelAndView("Payments-form");
		view.addObject("paymentsDTO", new PaymentsDTO());
		return view;
	}
	
	@PostMapping(value = "/dashboard/add")
	public ModelAndView paymentAdd(@Valid PaymentsDTO paymentsDTO, BindingResult result, RedirectAttributes attributes) {
		ModelAndView errors = new ModelAndView("Payments-form");
		if(result.hasErrors()) {
			return errors;
		}
		ModelAndView view = new ModelAndView("redirect:/dashboard/add");
		paymentsService.addPayments(paymentsDTO);
		attributes.addFlashAttribute("mensagem", "Cadastro de conta efetuado com sucesso!");
		return view;
	}
	
	@GetMapping(value = "/dashboard/payments")
	public ModelAndView showPayments() {
		ModelAndView view = new ModelAndView("Payments-table");
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)user).getUsername();
		
		Calendar cal = GregorianCalendar.getInstance();
		Integer currentYear = cal.get(Calendar.YEAR);
		Integer currentMonth = cal.get(Calendar.MONTH) + 1;
		
		view.addObject("payments", paymentsRepository.findByMonthAndYear(username, currentMonth, currentYear));
		return view;
	}
	
	@PostMapping(value = "/dashboard/payments")
	public ModelAndView searchPayments(@Param("month") Integer month, @Param("year") Integer year) {
		ModelAndView view = new ModelAndView("Payments-table");	
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)user).getUsername();
		
		if (month == null && year == null) {
			view.addObject("payments", paymentsRepository.findByUsername(username));
		}
		else if (year == null) {
			view.addObject("payments", paymentsRepository.findByMonth(username, month));
		}
		else if (month == null) {
			view.addObject("payments", paymentsRepository.findByYear(username, year));
		}
		else {
			view.addObject("payments", paymentsRepository.findByMonthAndYear(username, month, year));
		}
		
		return view;
	}
		
	
	@GetMapping(value = "/dashboard/payment/edit/{id}")
	public ModelAndView editPayment(@PathVariable("id") Payments payment) {
		ModelAndView view = new ModelAndView("Payments-form");
		view.addObject("paymentsDTO", payment);
		return view;
	}
	
	@DeleteMapping(value = "/dashboard/payments/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") long id){
		try {
			Optional<Payments> payment = this.paymentsRepository.findById(id);

			if( payment.isPresent() ){
				this.paymentsRepository.delete(payment.get());
			} else {
				throw new EntityNotFoundException();
			}

			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Título deletado com sucesso");
		}
		catch(EntityNotFoundException err){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Título não encontrado.");
		}
	}
}
