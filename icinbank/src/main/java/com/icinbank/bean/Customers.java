package com.icinbank.bean;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customers")
@NamedQuery(name = "Customers.findAll", query = "SELECT c FROM Customers c")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
