package com.icinbank.dto;

import com.icinbank.bean.Customers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChequeBookRequestDTO {
	private long chequeBookRequestId;
	private String chequeBookRequestedByAccountType;
	private boolean chequeRequestStatus; 
	private long customerId;
}
