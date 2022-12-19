package com.icinbank.service;

import com.icinbank.dto.AdminstratorUserDTO;

public interface IAdminService {
public String adminValidation(AdminstratorUserDTO validateAdmin);
public String addAdmin(AdminstratorUserDTO addAdmin);
}
