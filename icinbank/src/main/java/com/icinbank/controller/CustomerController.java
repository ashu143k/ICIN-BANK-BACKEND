package com.icinbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.icinbank.dto.CustomersDTO;
import com.icinbank.servicehelper.ServiceHelper;

@RestController
public class CustomerController {
	@Autowired
	ServiceHelper customerService;
	
	@RequestMapping(value = "/customerValidation", method = RequestMethod.POST)
	public ResponseEntity<Object> customerValidation(@RequestParam String customerEmail,@RequestParam String customerPassword){
		String customerValidationStatus=customerService.customerValidation(customerEmail,customerPassword);
		return new ResponseEntity<Object>(customerValidationStatus,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/registerCustomer", method = RequestMethod.POST)
	public ResponseEntity<Object> registerCustomer(@RequestBody CustomersDTO registerCustomer ){
		String registrationStatus=customerService.registerCustomer(registerCustomer);
		return new ResponseEntity<Object>(registrationStatus,HttpStatus.OK);
	}
	@RequestMapping(value = "/updateCustomer", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateCustomer(@RequestBody CustomersDTO updateCustomer ){
		String updateStatus=customerService.updateCustomer(updateCustomer);
		return new ResponseEntity<Object>(updateStatus,HttpStatus.OK);
	}
	@RequestMapping(value = "/deleteById/{customerId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteCustomerById(@PathVariable long customerId ){
		String deleteStatus=customerService.deleteCustomer(customerId);
		return new ResponseEntity<Object>(deleteStatus,HttpStatus.OK);
	}
}
