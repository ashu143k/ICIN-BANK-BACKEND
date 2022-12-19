package com.icinbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.icinbank.dto.AdminstratorUserDTO;
import com.icinbank.servicehelper.ServiceHelper;
@RestController
public class AdminstratorController {
	@Autowired
	ServiceHelper adminService;
	
	@RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
	public ResponseEntity<Object> adminValidation(@RequestBody AdminstratorUserDTO admin ){
		String adminLoginSuccess= adminService.adminValidation(admin);
		return new ResponseEntity<Object>(adminLoginSuccess,HttpStatus.OK);
	}
}
