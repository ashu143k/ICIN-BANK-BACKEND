package com.icinbank.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomersDTO {
	private long customerId;
	private String customerName;
	private String customerEmail;
	private String customerPassword;
	private Date customerDateOfBirth;
	private int customerAccountNumber;
	private boolean moneyTransfer;
	private boolean moneyDeposit;
	private boolean moneyWithdrawl;
	private boolean accountBlockStatus;
}
