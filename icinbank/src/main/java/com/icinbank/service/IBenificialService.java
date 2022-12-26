package com.icinbank.service;

import java.util.Map;

import com.icinbank.dto.BenificialDTO;

public interface IBenificialService {
public String addBenificial(BenificialDTO addBenificial);
public String updateBenificial(BenificialDTO updateBenificial);
public String deleteBenificial(long deleteUserById);
public Map<String, Object> getBenificialsById(long Id);
}
