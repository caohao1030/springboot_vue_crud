package com.ch.demo.service;

import com.ch.demo.pojo.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerService {
    List<Customer> queryList();
    int add(Customer customer);
    int delete(int id);
    int update(Customer customer);
    List<Customer>findByIf(Map<Object,Object> map);
}
