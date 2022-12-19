package com.icinbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icinbank.bean.Customers;

public interface CustomersRepository extends JpaRepository<Customers, Long> {

}
