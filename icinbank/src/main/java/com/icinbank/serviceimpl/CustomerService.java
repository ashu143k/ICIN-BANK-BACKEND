package com.icinbank.serviceimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icinbank.bean.Customers;
import com.icinbank.dto.CustomersDTO;
import com.icinbank.repository.CustomersRepository;
import com.icinbank.service.ICustomerService;

@Service
public class CustomerService implements ICustomerService {
	@Autowired
	CustomersRepository custRepo;
	@Autowired
	EntityManager entityManager;

	@Override
	public String addCustomer(CustomersDTO addCustomer) {
		Customers newCustomer = new Customers();
		newCustomer.setCustomerName(addCustomer.getCustomerName());
		newCustomer.setCustomerAccountNumber(addCustomer.getCustomerAccountNumber());
		newCustomer.setCustomerEmail(addCustomer.getCustomerEmail());
		newCustomer.setCustomerPassword(addCustomer.getCustomerPassword());
		/*
		 * newCustomer.setPrimaryAccountBalance(addCustomer.getPrimaryAccountBalance());
		 * newCustomer.setSavingAccountBalance(addCustomer.getSavingAccountBalance());
		 */
		newCustomer.setCustomerDateOfBirth(addCustomer.getCustomerDateOfBirth());
		newCustomer.setMoneyDeposit(addCustomer.isMoneyDeposit());
		newCustomer.setMoneyTransfer(addCustomer.isMoneyTransfer());
		newCustomer.setMoneyWithdrawl(addCustomer.isMoneyWithdrawl());
		custRepo.save(newCustomer);
		return "success";
	}

	@Override
	public String updateCustomer(CustomersDTO updateCustomer) {
		Customers updatingCustomer = new Customers();
		updatingCustomer.setCustomerId(updateCustomer.getCustomerId());
		updatingCustomer.setCustomerName(updateCustomer.getCustomerName());
		updatingCustomer.setCustomerAccountNumber(updateCustomer.getCustomerAccountNumber());
		updatingCustomer.setCustomerEmail(updateCustomer.getCustomerEmail());
		updatingCustomer.setCustomerPassword(updateCustomer.getCustomerPassword());
		/*
		 * updatingCustomer.setPrimaryAccountBalance(updateCustomer.
		 * getPrimaryAccountBalance());
		 * updatingCustomer.setSavingAccountBalance(updateCustomer.
		 * getSavingAccountBalance());
		 */
		updatingCustomer.setCustomerDateOfBirth(updateCustomer.getCustomerDateOfBirth());
		updatingCustomer.setMoneyDeposit(updateCustomer.isMoneyDeposit());
		updatingCustomer.setMoneyTransfer(updateCustomer.isMoneyTransfer());
		updatingCustomer.setMoneyWithdrawl(updateCustomer.isMoneyWithdrawl());
		custRepo.save(updatingCustomer);
		return "Customer Details Updated";
	}

	@Override
	public String deleteCustomer(long customerId) {
		custRepo.deleteById(customerId);
		return "Deleted was Successful";
	}

	@Override
	public List<Customers> getAllCustomers() {

		return custRepo.findAll();
	}


	public String validateCustomer(String customerEmail, String customerPassword) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Customers> cq=cb.createQuery(Customers.class);
		Root<Customers> custRoot=cq.from(Customers.class);
		Predicate customerEmailPredicate = cb.equal(custRoot.get("customerEmail"), customerEmail);
		Predicate customerPasswordePredicate = cb.equal(custRoot.get("customerPassword"), customerPassword);
		Predicate customerValidationCheck = cb.and(customerEmailPredicate, customerPasswordePredicate);
		cq.where(customerValidationCheck);
		List<Customers> resultList=entityManager.createQuery(cq).getResultList();
		if(resultList.isEmpty())
		return "Incorret UserId or Password";
		else
		return "Success";
	}

}
