package com.icinbank.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.icinbank.bean.AccountTransaction;
import com.icinbank.dto.AccountTransactionDTO;
import com.icinbank.repository.IAccountTransactionRepository;
import com.icinbank.service.IAccountTransaction;

@Service
public class AccountTransactionService implements IAccountTransaction {
	@Autowired
	IAccountTransactionRepository accountTxnRepo;
	@Override
	public String addTransaction(AccountTransactionDTO addTransaction) {
		AccountTransaction addTransactionObject=new AccountTransaction();
		addTransactionObject.setNarration(addTransaction.getNarration());
		addTransactionObject.setWithdrawal(addTransaction.getWithdrawal());
		addTransactionObject.setDeposit(addTransaction.getDeposit());
		addTransactionObject.setCustomerId(addTransaction.getCustomerId());
		addTransactionObject.setBalance(addTransaction.getBalance());
		addTransactionObject.setAccountTypeName(addTransaction.getAccountTypeName());
		addTransactionObject.setTransactionDateTime(addTransaction.getTransactionDateTime());
		
		accountTxnRepo.save(addTransactionObject);
		return "Transaction was successful";
	}

	@Override
	public List<AccountTransaction> getAllTransactions() {
		String sortBy = "transactionDateTime";
		String sortDir = "desc";
		 Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
	                Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();
		return accountTxnRepo.findAll(sort);
	}

}
