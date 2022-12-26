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
@Table(name = "ChequeBookRequest")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChequeBookRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long chequeBookRequestId;
	private String chequeBookRequestedByAccountType;
	private boolean chequeRequestStatus; 
	
	@OneToOne
	private Customers customer;
}
