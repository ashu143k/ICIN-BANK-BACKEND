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
@Table (name = "PrimaryAccount")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrimaryAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long primaryAccountId;
	private long primaryAccountBalance;
	
	@OneToOne(mappedBy = "primaryAccount")
	private Customers customer;

}
