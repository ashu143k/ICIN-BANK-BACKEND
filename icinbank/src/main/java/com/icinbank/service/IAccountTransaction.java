package com.icinbank.service;

import java.util.List;

import com.icinbank.bean.AccountTransaction;
import com.icinbank.dto.AccountTransactionDTO;

public interface IAccountTransaction {
public String addTransaction(AccountTransactionDTO addTransaction);
public List<AccountTransaction> getAllTransactions();
}
