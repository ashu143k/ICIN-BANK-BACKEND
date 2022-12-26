package com.icinbank.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.icinbank.dto.AdminstratorUserDTO;
import com.icinbank.servicehelper.ServiceHelper;
@RestController
@CrossOrigin("http://localhost:4200/")
public class AdminstratorController {
	@Autowired
	ServiceHelper adminService;
	
	@RequestMapping(value = "/adminValidation", method = RequestMethod.POST)
	public ResponseEntity<Object> adminValidation(@RequestBody AdminstratorUserDTO admin ){
		Map<String, Object>  adminLoginSuccess= adminService.adminValidation(admin);
		return new ResponseEntity<Object>(adminLoginSuccess,HttpStatus.OK);
	}
	@RequestMapping(value = "/updateCustomer", method = RequestMethod.POST)
	public ResponseEntity<Object> updateCustomer(@RequestBody String updateCustomer ){
		Map<String, Object>  updateCustomerStatus= adminService.updateCustomer(updateCustomer);
		return new ResponseEntity<Object>(updateCustomerStatus,HttpStatus.OK);
	}
	@RequestMapping(value = "/updateChequeBookStatus", method = RequestMethod.POST)
	public ResponseEntity<Object> updateChequeBookStatus(@RequestBody long updateChequeBookStatus ){
		Map<String, Object>  updateChequeBookStatu= adminService.updateChequeBookStatus(updateChequeBookStatus);
		return new ResponseEntity<Object>(updateChequeBookStatu,HttpStatus.OK);
	}
}
