package com.icinbank.service;

import java.util.List;

import com.icinbank.bean.Customers;
import com.icinbank.dto.CustomersDTO;

public interface ICustomerService {
public String addCustomer(CustomersDTO addCustomer);
public String updateCustomer(CustomersDTO updateCustomer);
public String deleteCustomer(long deleteUserById);
public List<Customers> getAllCustomers();
public Customers findById(long customerId);
public String updatePrimaryAccountBalanceCustomerById (long customerId,CustomersDTO updateCustomer);
public String updateSavingAccountBalanceCustomerById (long customerId,CustomersDTO updateCustomer);
public String updateCustomerById(long custId,CustomersDTO updateCustomer);
}
