package com.precavido.app.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.precavido.app.model.Payments;

@Repository
@Transactional
public interface PaymentsRepository extends JpaRepository<Payments, Long> {
	

	@Query("select p from Payments p where p.username = ?1 and month(due_date) = ?2 and year(due_date) = ?3")
	List<Payments> findByMonthAndYear(String username, Integer month, Integer year);
	
	@Query("select p from Payments p where p.username = ?1 and month(due_date) = ?2")
	List<Payments> findByMonth(String username, Integer month);
	
	@Query("select p from Payments p where p.username = ?1 and year(due_date) = ?2")
	List<Payments> findByYear(String username, Integer year);
	
	@Query("select p from Payments p where p.username = ?1 and p.amount = ?2")
	List<Payments> findByValue(String username, Double amount);
	
	List<Payments> findByUsername(String username);
	
	Payments deleteById(long id);
	
}