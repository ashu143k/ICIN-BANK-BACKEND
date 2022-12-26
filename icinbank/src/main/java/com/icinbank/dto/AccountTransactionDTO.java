package com.icinbank.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountTransactionDTO {
	private long accountTransactionId;
	private String narration;
	private long withdrawal;
	private long deposit;
	private long balance;
	private long customerId;
	private String accountTypeName;
	private Date transactionDateTime;
}
