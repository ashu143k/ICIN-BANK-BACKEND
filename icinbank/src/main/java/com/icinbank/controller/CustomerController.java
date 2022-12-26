package com.icinbank.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.icinbank.dto.BenificialDTO;
import com.icinbank.dto.CustomersDTO;
import com.icinbank.servicehelper.ServiceHelper;

@RestController
@CrossOrigin("http://localhost:4200/")
public class CustomerController {
	@Autowired
	ServiceHelper customerService;

	@RequestMapping(value = "/customerValidation", method = RequestMethod.POST)
	public ResponseEntity<Object> customerValidation(@RequestBody String customerDetails) {
		Map<String, Object> message = new HashMap<String, Object>();
		JSONObject json = new JSONObject(customerDetails);
		String customerEmail=json.getString("email").toString();
		String customerPassword=json.getString("password").toString();
		Map<String, Object> customerValidationStatus = customerService.customerValidation(customerEmail, customerPassword);
		message.put("CustomerDetails", customerValidationStatus);
		return new ResponseEntity<Object>(message, HttpStatus.OK);
	}

	@RequestMapping(value = "/registerCustomer", method = RequestMethod.POST)
	public ResponseEntity<Object> registerCustomer(@RequestBody CustomersDTO registerCustomer) {
		String registrationStatus = customerService.registerCustomer(registerCustomer);
		JSONObject responseJson = new JSONObject();
		responseJson.put("Message", registrationStatus);
		return new ResponseEntity<Object>(responseJson, HttpStatus.OK);
	}

	@RequestMapping(value = "/updateCustomer", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateCustomer(@RequestBody CustomersDTO updateCustomer) {
		String updateStatus = customerService.updateCustomer(updateCustomer);
		return new ResponseEntity<Object>(updateStatus, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteById/{customerId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteCustomerById(@PathVariable long customerId) {
		String deleteStatus = customerService.deleteCustomer(customerId);
		return new ResponseEntity<Object>(deleteStatus, HttpStatus.OK);
	}
	@RequestMapping(value = "/addBenficial", method = RequestMethod.POST)
	public ResponseEntity<Object> addBenficial(@RequestBody BenificialDTO addBenficial) {
		String addBenficialStatus = customerService.addBenficial(addBenficial);
		JSONObject responseJson = new JSONObject();
		responseJson.put("Message", addBenficialStatus);
		return new ResponseEntity<Object>(responseJson, HttpStatus.OK);
	}
	@RequestMapping(value = "/getBenificial/{customerId}", method = RequestMethod.GET)
	public ResponseEntity<Object> getBenificials(@PathVariable long customerId) {
		Map<String, Object> benificialDetails = customerService.getBenificials(customerId);
		List<Object> sendingBackObject=new ArrayList<>();
		sendingBackObject.add(benificialDetails);
		return new ResponseEntity<Object>(sendingBackObject, HttpStatus.OK);
	}
	@RequestMapping(value = "/tranferFund", method = RequestMethod.POST)
	public ResponseEntity<Object> transferFund(@RequestBody String fundDetails) throws ParseException {
		Map<String, Object> message = new HashMap<String, Object>();
		String customerValidationStatus = customerService.transferFund(fundDetails);
		message.put("CustomerDetails", customerValidationStatus);
		return new ResponseEntity<Object>(message, HttpStatus.OK);
	}
	@RequestMapping(value = "/viewTransaction", method = RequestMethod.POST)
	public ResponseEntity<Object> viewTransaction(@RequestBody String viewTransactionDetails) {
		Map<String, Object> viewTransaction = customerService.getTransactions(viewTransactionDetails);
		List<Object> sendingBackObject=new ArrayList<Object>();
		sendingBackObject.add(viewTransaction);
		return new ResponseEntity<Object>(sendingBackObject, HttpStatus.OK);
	}
	@RequestMapping(value = "/requestChequeBook", method = RequestMethod.POST)
	public ResponseEntity<Object> chequeBookRequest(@RequestBody String chequeBookRequestDetails) throws ParseException {
		Map<String, Object> message = new HashMap<String, Object>();
		String chequeBookRequestStatus = customerService.requestChequeBook(chequeBookRequestDetails);
		message.put("chequeBookStatus", chequeBookRequestStatus);
		return new ResponseEntity<Object>(message, HttpStatus.OK);
	}
	
}
