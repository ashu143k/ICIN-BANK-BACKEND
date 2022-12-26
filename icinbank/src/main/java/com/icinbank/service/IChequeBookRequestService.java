package com.icinbank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.icinbank.bean.ChequeBookRequest;
import com.icinbank.dto.ChequeBookRequestDTO;

@Service
public interface IChequeBookRequestService {
public String addChequeBookRequest(ChequeBookRequestDTO addChequeRequest);
public List<ChequeBookRequest> getAllChequeBookRequests();
public String updateChequeRequestStatusbyId(long chequeBookRequestId);
}