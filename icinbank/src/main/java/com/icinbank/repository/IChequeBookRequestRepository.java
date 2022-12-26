package com.icinbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icinbank.bean.ChequeBookRequest;

public interface IChequeBookRequestRepository extends JpaRepository<ChequeBookRequest, Long> {

}
