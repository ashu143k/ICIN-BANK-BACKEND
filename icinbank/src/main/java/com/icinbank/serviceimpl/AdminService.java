package com.icinbank.serviceimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icinbank.bean.AdminstratorUser;
import com.icinbank.dto.AdminstratorUserDTO;
import com.icinbank.repository.AdmininstratorUserRepository;
import com.icinbank.service.IAdminService;
@Service
public class AdminService implements IAdminService {
	
	@Autowired
	AdmininstratorUserRepository repo;
	@Autowired
	EntityManager entityManager;
	@Override
	public String adminValidation(AdminstratorUserDTO validateAdmin) {
		String userId=validateAdmin.getAdminUserId();
		String userPassword=validateAdmin.getAdminPassword();
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<AdminstratorUser> cquery = cb.createQuery(AdminstratorUser.class);
		Root<AdminstratorUser> root = cquery.from(AdminstratorUser.class);
		Predicate adminIDPredicate = cb.equal(root.get("adminUserId"), userId);
		Predicate adminPassword = cb.equal(root.get("adminPassword"), userPassword);
		Predicate adminValidationCheck = cb.and(adminIDPredicate, adminPassword);
		cquery.where(adminValidationCheck);
		List<AdminstratorUser> resultlist = entityManager.createQuery(cquery).getResultList();
		if (resultlist.isEmpty()) {
			return "Invaild Username or Password";
		} else {
			return "Success";
		}
	}

	@Override
	public String addAdmin(AdminstratorUserDTO addAdmin) {
		// TODO Auto-generated method stub
		return null;
	}

}
