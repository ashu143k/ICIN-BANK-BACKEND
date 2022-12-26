package com.icinbank.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icinbank.bean.BenificialAccount;
import com.icinbank.dto.BenificialDTO;
import com.icinbank.repository.BenificialRepository;
import com.icinbank.service.IBenificialService;

@Service
public class BenificialService implements IBenificialService {
	@Autowired
	CustomerService custService;
	@Autowired
	BenificialRepository benificialRepo;
	
	@Autowired
	EntityManager entityManager;

	@Override
	public String addBenificial(BenificialDTO addBenificial) {
		BenificialAccount newBenificial = new BenificialAccount();

		newBenificial.setBenificialAccountNumber(addBenificial.getBenificialAccountNumber());
		newBenificial.setBenificialBankName(addBenificial.getBenificialBankName());
		newBenificial.setBenificialIFSCNumber(addBenificial.getBenificialIFSCNumber());
		newBenificial.setBenificialName(addBenificial.getBenificialName());
		newBenificial.setCustomerId(custService.findById(addBenificial.getCustomerId()));
		benificialRepo.save(newBenificial);
		return "Benificial Added Successfully";
	}

	@Override
	public String updateBenificial(BenificialDTO updateBenificial) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteBenificial(long deleteUserById) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getBenificialsById(long Id) {
		
		Map<String, Object> FinalObject = new HashMap<String, Object>();
		List<Object> list=new ArrayList<>();
		for(BenificialAccount result :benificialRepo.findAll() )
		{
			if(result.getCustomerId().getCustomerId()==Id) {
				Map<String, Object> tempObject = new HashMap<String, Object>();
				tempObject.put("benificialId", result.getBenificialID());
				tempObject.put("benificialName", result.getBenificialName());
				tempObject.put("benificialAccountNumber", result.getBenificialAccountNumber());
				tempObject.put("benificialIFSCNumber", result.getBenificialIFSCNumber());
				tempObject.put("benificialBankName", result.getBenificialBankName());
				tempObject.put("customerId", result.getCustomerId().getCustomerId());
				list.add(tempObject);
				
			}
		}
		FinalObject.put("BenificialDetailArray", list);
		return FinalObject;
	}

}
