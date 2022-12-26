package com.icinbank.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		newCustomer.setPrimaryAccountBalance(addCustomer.getPrimaryAccountBalance());
		newCustomer.setSavingAccountBalance(addCustomer.getSavingAccountBalance());
		newCustomer.setCustomerDateOfBirth(addCustomer.getCustomerDateOfBirth());
		newCustomer.setMoneyDepositStatus(addCustomer.isMoneyDepositStatus());
		newCustomer.setMoneyTransferStatus(addCustomer.isMoneyTransferStatus());
		newCustomer.setMoneyWithdrawlStatus(addCustomer.isMoneyWithdrawlStatus());
		custRepo.save(newCustomer);
		return "success";
	}

	@Override
	public String updateCustomer(CustomersDTO updateCustomer) {
		Customers updatingCustomer = new Customers();
		updatingCustomer.setCustomerId(updateCustomer.getCustomerId());
		updateCustomer.setAccountBlockStatus(updateCustomer.isAccountBlockStatus());
		updatingCustomer.setMoneyDepositStatus(updateCustomer.isMoneyDepositStatus());
		updatingCustomer.setMoneyTransferStatus(updateCustomer.isMoneyTransferStatus());
		updatingCustomer.setMoneyWithdrawlStatus(updateCustomer.isMoneyWithdrawlStatus());
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

	public Map<String, Object> validateCustomer(String customerEmail, String customerPassword) {
		boolean accountBlockStatus = false;
		Map<String, Object> returnObject = new HashMap<String, Object>();
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Customers> cq = cb.createQuery(Customers.class);
		Root<Customers> custRoot = cq.from(Customers.class);
		Predicate customerEmailPredicate = cb.equal(custRoot.get("customerEmail"), customerEmail);
		Predicate customerPasswordePredicate = cb.equal(custRoot.get("customerPassword"), customerPassword);
		Predicate customerValidationCheck = cb.and(customerEmailPredicate, customerPasswordePredicate);
		cq.where(customerValidationCheck);
		List<Customers> resultList = entityManager.createQuery(cq).getResultList();
		if (!resultList.isEmpty()) {
			for (Customers result : resultList) {
				if (result.isAccountBlockStatus()) {
					accountBlockStatus = true;
				}
			}
			if (accountBlockStatus == true) {
				returnObject.put("ValidationStatus","Account is Block please contact bank represtative for further details");
			}else {
				returnObject.put("ValidationStatus","Success");
				for (Customers result : resultList) {
					returnObject.put("customerId", result.getCustomerId());
					returnObject.put("customerAccountNo", result.getCustomerAccountNumber());
					returnObject.put("customerName", result.getCustomerName());
					returnObject.put("primaryAccountBalance", result.getPrimaryAccountBalance());
					returnObject.put("savingAccountBalance", result.getSavingAccountBalance());
					returnObject.put("customerEmail", result.getCustomerEmail());
					returnObject.put("depositMoneyStatus", result.isMoneyDepositStatus());
					returnObject.put("withdrawalMoneyStatus", result.isMoneyWithdrawlStatus());
					returnObject.put("transferMoneyStatus", result.isMoneyTransferStatus());

				}

			}
		}
return returnObject;
	}

	@Override
	public Customers findById(long customerId) {
		Customers cust=entityManager.find(Customers.class, customerId);
		return cust;
	}

	@Override
	public String updatePrimaryAccountBalanceCustomerById(long customerId, CustomersDTO updateCustomer) {
		Customers customerToUpdate = custRepo.getOne(customerId);
		customerToUpdate.setPrimaryAccountBalance(updateCustomer.getPrimaryAccountBalance());
		custRepo.save(customerToUpdate);
		return "updated";
	}

	@Override
	public String updateSavingAccountBalanceCustomerById(long customerId, CustomersDTO updateCustomer) {
		Customers customerToUpdate = custRepo.getOne(customerId);
		customerToUpdate.setSavingAccountBalance(updateCustomer.getSavingAccountBalance());
		custRepo.save(customerToUpdate);
		return "updated";
	}

	@Override
	public String updateCustomerById(long custId, CustomersDTO updateCustomer) {
		Customers updateCust= custRepo.getOne(custId);
		updateCust.setAccountBlockStatus(updateCustomer.isAccountBlockStatus());
		updateCust.setMoneyDepositStatus(updateCustomer.isMoneyDepositStatus());
		updateCust.setMoneyTransferStatus(updateCustomer.isMoneyTransferStatus());
		updateCust.setMoneyWithdrawlStatus(updateCustomer.isMoneyWithdrawlStatus());
		custRepo.save(updateCust);
		return "Customer Updated Successfully";
	}

}
