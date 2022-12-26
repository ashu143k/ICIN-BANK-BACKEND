package com.icinbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.icinbank.bean.AccountTransaction;
@Repository
public interface IAccountTransactionRepository extends JpaRepository<AccountTransaction, Long> {

}
