package com.icinbank.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icinbank.bean.ChequeBookRequest;
import com.icinbank.bean.Customers;
import com.icinbank.dto.ChequeBookRequestDTO;
import com.icinbank.repository.IChequeBookRequestRepository;
import com.icinbank.service.IChequeBookRequestService;
@Service
public class ChequeBookRequestService implements IChequeBookRequestService {
	@Autowired
	CustomerService custService;
	@Autowired
	IChequeBookRequestRepository chequeBookRepo;
	@Override
	public String addChequeBookRequest(ChequeBookRequestDTO addChequeRequest) {
		ChequeBookRequest chequeBookRequest = new ChequeBookRequest();
		chequeBookRequest.setChequeBookRequestedByAccountType(addChequeRequest.getChequeBookRequestedByAccountType());
		chequeBookRequest.setChequeRequestStatus(addChequeRequest.isChequeRequestStatus());
		Customers addChequeBookRequest= custService.findById(addChequeRequest.getCustomerId());
		chequeBookRequest.setCustomer(addChequeBookRequest);
		chequeBookRepo.save(chequeBookRequest);
		return "Cheque Book Requested";
	}

	@Override
	public List<ChequeBookRequest> getAllChequeBookRequests() {
		return chequeBookRepo.findAll();
	}

	@Override
	public String updateChequeRequestStatusbyId(long chequeBookRequestId) {
		@SuppressWarnings("deprecation")
		ChequeBookRequest updateChequeBookRequestStatus= chequeBookRepo.getOne(chequeBookRequestId);
		updateChequeBookRequestStatus.setChequeRequestStatus(true);
		chequeBookRepo.save(updateChequeBookRequestStatus);
		return "Cheque Book Approved";
	}

}
