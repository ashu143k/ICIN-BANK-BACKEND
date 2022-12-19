package com.icinbank.servicehelper;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icinbank.bean.Customers;
import com.icinbank.dto.AdminstratorUserDTO;
import com.icinbank.dto.CustomersDTO;
import com.icinbank.serviceimpl.AdminService;
import com.icinbank.serviceimpl.CustomerService;

@Service
public class ServiceHelper {
	@Autowired
	AdminService adminService;
	@Autowired
	CustomerService custService;
	public String adminValidation(AdminstratorUserDTO admin) {
		String adminValidated= adminService.adminValidation(admin);
		JSONObject adminJsonObject = new JSONObject();
		JSONArray customerJsonArray = new JSONArray();
		if(adminValidated.contentEquals("Success")) {
			List<Customers> customerList=custService.getAllCustomers();
			for(Customers cust :customerList ) {
				JSONObject tempJson=new JSONObject();
				tempJson.put("customerName",cust.getCustomerName());
				tempJson.put("customerEmail",cust.getCustomerEmail());
				 tempJson.put("savingAccountBalance",cust.getSavingAccount().getSavingAccountBalance());
				 tempJson.put("primaryAccountBalance",cust.getPrimaryAccount().getPrimaryAccountBalance());
				tempJson.put("customerDateOfBirth",cust.getCustomerDateOfBirth());
				tempJson.put("customerAccountNumber",cust.getCustomerAccountNumber());
				tempJson.put("MoneyTransferApprovalStatus", cust.isMoneyTransfer());
				tempJson.put("MoneyDepositApprovalStatus", cust.isMoneyDeposit());
				tempJson.put("MoneyWithdrwalApprovalStatus", cust.isMoneyWithdrawl());
				customerJsonArray.put(tempJson);
			}	
		}
		adminJsonObject.put("CustomerDetails", customerJsonArray);
		return adminJsonObject.toString();	
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
	public String customerValidation(String customerEmail, String customerPassword) {
		String customerValidated=custService.validateCustomer(customerEmail,customerPassword);
		return customerValidated;
	}

}
