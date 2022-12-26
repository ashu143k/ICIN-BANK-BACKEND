package com.icinbank.dto;

import java.util.Date;
import java.util.List;

import com.icinbank.bean.BenificialAccount;
import com.icinbank.bean.ChequeBookRequest;

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
	private long customerAccountNumber;
	private boolean moneyTransferStatus;
	private boolean moneyDepositStatus;
	private boolean moneyWithdrawlStatus;
	private boolean accountBlockStatus;
	private long primaryAccountBalance;
	private long savingAccountBalance;
}
