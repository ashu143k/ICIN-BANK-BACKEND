package com.icinbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.icinbank.bean.AdminstratorUser;
@Repository
public interface AdmininstratorUserRepository extends JpaRepository<AdminstratorUser, Long> {

}
