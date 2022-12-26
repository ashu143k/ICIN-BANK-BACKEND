package com.icinbank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BenificialDTO {
	private long benificialID;
	private String benificialName;
	private long benificialAccountNumber;
	private String benificialBankName;
	private String benificialIFSCNumber;
	private long customerId;
}
