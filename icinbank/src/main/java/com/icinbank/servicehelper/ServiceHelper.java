package com.icinbank.servicehelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icinbank.bean.AccountTransaction;
import com.icinbank.bean.ChequeBookRequest;
import com.icinbank.bean.Customers;
import com.icinbank.dto.AccountTransactionDTO;
import com.icinbank.dto.AdminstratorUserDTO;
import com.icinbank.dto.BenificialDTO;
import com.icinbank.dto.ChequeBookRequestDTO;
import com.icinbank.dto.CustomersDTO;
import com.icinbank.serviceimpl.AccountTransactionService;
import com.icinbank.serviceimpl.AdminService;
import com.icinbank.serviceimpl.BenificialService;
import com.icinbank.serviceimpl.ChequeBookRequestService;
import com.icinbank.serviceimpl.CustomerService;

@Service
public class ServiceHelper {
	@Autowired
	AdminService adminService;
	@Autowired
	CustomerService custService;
	@Autowired
	BenificialService benificialService;
	@Autowired
	AccountTransactionService accountTransactionService;
	@Autowired
	ChequeBookRequestService chequeBookRequestService;
	
	
	public Map<String, Object> adminValidation(AdminstratorUserDTO admin) {
		String adminValidated= adminService.adminValidation(admin);
		Map<String, Object> adminLoginResponseObject=new HashMap<>();
		if(adminValidated.contentEquals("Success")) {
			List<Customers> customerList=custService.getAllCustomers();
			List<ChequeBookRequest> chequeRequestList=chequeBookRequestService.getAllChequeBookRequests();
			adminLoginResponseObject.put("CustomerDetails", customerList);
			adminLoginResponseObject.put("ChequeBookRequest", chequeRequestList);
		}
		return adminLoginResponseObject;	
	}
	public String registerCustomer(CustomersDTO registerCustomer) {
		String registrationStatus=custService.addCustomer(registerCustomer);
		return registrationStatus;
	}
	public String updateCustomer(CustomersDTO updateCustomer) {
		String updateCustomerStatus=custService.updateCustomer(updateCustomer);
		return updateCustomerStatus;
	}
	public String deleteCustomer(long customerId) {
		String deleteCustomerStatus=custService.deleteCustomer(customerId);
		return deleteCustomerStatus;
	}
	public Map<String, Object> customerValidation(String customerEmail, String customerPassword) {
		Map<String, Object> customerValidated=custService.validateCustomer(customerEmail,customerPassword);
		return customerValidated;
	}
	public String addBenficial(BenificialDTO addBenficial) {
		String addBenifical=benificialService.addBenificial(addBenficial);
		return addBenifical;
	}
	public Map<String, Object> getBenificials(long customerId) {
		Map<String, Object> getBenificial=benificialService.getBenificialsById(customerId);
		
		return getBenificial;
	}
	public String transferFund(String fundDetails) throws ParseException {
		AccountTransactionDTO addTransaction=new AccountTransactionDTO();
		JSONObject json = new JSONObject(fundDetails);
		String transferFundStatus;
		String benificialBankName=json.getString("benificialBankName").toString();
		String accountTypeSelected=json.getString("accountType").toString();
		String benificialName = json.getString("benificialName").toString();
		String transactionDate= json.get("transactionDateTime").toString();
		Date trancasctionDateTime=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(transactionDate);  
		long benificialAccountNumber=json.getLong("benificialAccountNumber");
		if(accountTypeSelected.equalsIgnoreCase("primaryAccount")) {
			String narration="Amount Transfer to  "+benificialName+", Bank Name "+benificialBankName+", Account Number "+benificialAccountNumber;
			addTransaction.setNarration(narration);
			addTransaction.setWithdrawal(json.getLong("transferAmount"));
			addTransaction.setDeposit(0);
			addTransaction.setTransactionDateTime(trancasctionDateTime);
			Customers forAmountDeductionCustomers=custService.findById(json.getLong("customerId"));
			long actualAmount=forAmountDeductionCustomers.getPrimaryAccountBalance();
			long withdrawalAmount=json.getLong("transferAmount");
			long balanceAmount=actualAmount-withdrawalAmount;
			CustomersDTO updateBalance=new CustomersDTO();
			updateBalance.setPrimaryAccountBalance(balanceAmount);
			custService.updatePrimaryAccountBalanceCustomerById(json.getLong("customerId"),updateBalance);
			addTransaction.setBalance(balanceAmount);
			addTransaction.setCustomerId(json.getLong("customerId"));
			addTransaction.setAccountTypeName(accountTypeSelected);
			transferFundStatus=accountTransactionService.addTransaction(addTransaction);
			
		}else {
		String narration="Amount Transfer to  "+benificialName+", Bank Name "+benificialBankName+", Account Number "+benificialAccountNumber;
		addTransaction.setNarration(narration);
		addTransaction.setWithdrawal(json.getLong("transferAmount"));
		addTransaction.setDeposit(0);
		addTransaction.setTransactionDateTime(trancasctionDateTime);
		Customers forAmountDeductionCustomers=custService.findById(json.getLong("customerId"));
		long actualAmount=forAmountDeductionCustomers.getSavingAccountBalance();
		long withdrawalAmount=json.getLong("transferAmount");
		long balanceAmount=actualAmount-withdrawalAmount;
		CustomersDTO updateBalance=new CustomersDTO();
		updateBalance.setSavingAccountBalance(balanceAmount);
		custService.updateSavingAccountBalanceCustomerById(json.getLong("customerId"),updateBalance);
		addTransaction.setBalance(balanceAmount);
		addTransaction.setCustomerId(json.getLong("customerId"));
		addTransaction.setAccountTypeName(accountTypeSelected);
		transferFundStatus=accountTransactionService.addTransaction(addTransaction);
		}
		return transferFundStatus;
	}
	public Map<String, Object> getTransactions(String viewTransactionDetails) {
		
		List<AccountTransaction> getTransaction=accountTransactionService.getAllTransactions();
		JSONObject json = new JSONObject(viewTransactionDetails);
		long customerId=json.getLong("customerId");
		String accountType=json.getString("accountType").toString();
		Map<String, Object> viewTransaction=new HashMap<>();
		List<Object> list=new ArrayList<>();
		for(AccountTransaction at:getTransaction) {
			if(customerId==at.getCustomerId() && accountType.equals(at.getAccountTypeName())) {
				Map<String, Object> tempObject=new HashMap<>();
				tempObject.put("narration", at.getNarration());
				tempObject.put("withdrawal", at.getWithdrawal());
				tempObject.put("deposit", at.getDeposit());
				tempObject.put("balance", at.getBalance());
				tempObject.put("transactionDateTime", at.getTransactionDateTime());
				list.add(tempObject);
			}
		}
		viewTransaction.put("Transactions", list);
		return viewTransaction;
	}
	public String requestChequeBook(String chequeBookRequestDetails) {
		JSONObject json = new JSONObject(chequeBookRequestDetails); 
		String chequeBookRequestByAccountType= json.getString("chequeBookRequestByAccountType").toString();
		Boolean chequeRequestStatus= json.getBoolean("chequeRequestStatus");
		long customerId=json.getLong("customerId");
		ChequeBookRequestDTO chequeBookRequest= new ChequeBookRequestDTO();
		chequeBookRequest.setChequeBookRequestedByAccountType(chequeBookRequestByAccountType);
		chequeBookRequest.setChequeRequestStatus(chequeRequestStatus);
		chequeBookRequest.setCustomerId(customerId);
		String chequeBookRequestStatus= chequeBookRequestService.addChequeBookRequest(chequeBookRequest);
		return chequeBookRequestStatus;
	}
	public Map<String, Object> updateCustomer(String updateCustomer) {
		Map<String, Object> responseObject= new HashMap<>();
		JSONObject json = new JSONObject(updateCustomer);
		long customerId = json.getLong("customerId");
		boolean accountBlockStatus = json.getBoolean("accountBlockStatus");
		boolean moneyTransferStatus = json.getBoolean("moneyTransferStatus");
		boolean moneyDepositStatus = json.getBoolean("moneyDepositStatus");
		boolean moneyWithdrawlStatus = json.getBoolean("moneyWithdrawlStatus");
		CustomersDTO updateCustomerDto = new CustomersDTO();
		updateCustomerDto.setAccountBlockStatus(accountBlockStatus);
		updateCustomerDto.setMoneyDepositStatus(moneyDepositStatus);
		updateCustomerDto.setMoneyTransferStatus(moneyTransferStatus);
		updateCustomerDto.setMoneyWithdrawlStatus(moneyWithdrawlStatus);
		updateCustomerDto.setCustomerId(customerId);
		String updateStatus = custService.updateCustomerById(customerId,updateCustomerDto);
		responseObject.put("CustomerUpdated", updateStatus);
		return responseObject;
	}
	public Map<String, Object> updateChequeBookStatus(long updateChequeBookStatus) {
		Map<String, Object> responseObject= new HashMap<>();
		String chequeBookStat= chequeBookRequestService.updateChequeRequestStatusbyId(updateChequeBookStatus);
		responseObject.put("chequeBookStatus", chequeBookStat);
		return responseObject;
	}

}
