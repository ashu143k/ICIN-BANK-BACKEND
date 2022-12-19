package com.icinbank.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
	private int customerAccountNumber;
	private boolean moneyTransfer;
	private boolean moneyDeposit;
	private boolean moneyWithdrawl;
	private boolean accountBlockStatus;
	
	@OneToMany(mappedBy = "customerId")
	private List<BenificialAccount> BenificialAccount;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "primaryAccount", joinColumns = {@JoinColumn(name = "customerId", referencedColumnName = "customerId")},
    inverseJoinColumns = {@JoinColumn(name = "primaryAccountId", referencedColumnName = "primaryAccountId")})
	private PrimaryAccount primaryAccount;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "savingAccount", joinColumns = {@JoinColumn(name = "customerId", referencedColumnName = "customerId")},
    inverseJoinColumns = {@JoinColumn(name = "savingAccountId", referencedColumnName = "savingAccountId")})
	private SavingAccount savingAccount;

}
