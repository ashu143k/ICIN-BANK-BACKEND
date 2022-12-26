package com.icinbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.icinbank.bean.Customers;
@Repository
public interface CustomersRepository extends JpaRepository<Customers, Long> {

}
