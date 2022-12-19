package com.icinbank.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SavingAccount")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SavingAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long savingAccountId;
	private long savingAccountBalance;
	
	@OneToOne(mappedBy = "savingAccount")
	private Customers customer;
	
}
