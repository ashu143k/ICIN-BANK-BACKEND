package com.icinbank.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Benificial_Account")
public class BenificialAccount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int benificialID;
	
	private String benificialName;
	private long benificialAccountNumber;
	private String benificialBankName;
	private String benificialIFSCNumber;
	
	@ManyToOne
	private Customers customerId;

	public String getBenificialName() {
		return benificialName;
	}

	public void setBenificialName(String benificialName) {
		this.benificialName = benificialName;
	}

	public long getBenificialAccountNumber() {
		return benificialAccountNumber;
	}

	public void setBenificialAccountNumber(long benificialAccountNumber) {
		this.benificialAccountNumber = benificialAccountNumber;
	}

	public String getBenificialBankName() {
		return benificialBankName;
	}

	public void setBenificialBankName(String benificialBankName) {
		this.benificialBankName = benificialBankName;
	}

	public String getBenificialIFSCNumber() {
		return benificialIFSCNumber;
	}

	public void setBenificialIFSCNumber(String benificialIFSCNumber) {
		this.benificialIFSCNumber = benificialIFSCNumber;
	}

	public Customers getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Customers customerId) {
		this.customerId = customerId;
	}

	public int getBenificialID() {
		return benificialID;
	}
	
}
