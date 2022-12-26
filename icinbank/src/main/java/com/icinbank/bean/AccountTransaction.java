package com.icinbank.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="Account_Transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountTransaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long accountTransactionId;
	private String narration;
	private long withdrawal;
	private long deposit;
	private long balance;
	private Date transactionDateTime;
	private long customerId;
	private String accountTypeName;
}
