package com.icinbank.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminstratorUserDTO {
	private int adminId;
	private String adminUserId;
	private String adminPassword;

}
