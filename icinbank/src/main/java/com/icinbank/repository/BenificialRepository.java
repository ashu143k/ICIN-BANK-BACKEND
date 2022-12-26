package com.icinbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.icinbank.bean.BenificialAccount;
@Repository
public interface BenificialRepository extends JpaRepository<BenificialAccount, Long> {

}
