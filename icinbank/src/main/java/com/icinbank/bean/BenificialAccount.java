package com.icinbank.bean;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Benificial_Account")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "benificial.findByCustomerId", query = "SELECT b FROM BenificialAccount b")
public class BenificialAccount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long benificialID;
	private String benificialName;
	private long benificialAccountNumber;
	private String benificialBankName;
	private String benificialIFSCNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customerId")
	private Customers customerId;

}
