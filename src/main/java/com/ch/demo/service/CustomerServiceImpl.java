package com.ch.demo.service;

import com.ch.demo.mapper.CustomerMapper;
import com.ch.demo.pojo.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements  CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<Customer> queryList() {
        return customerMapper.queryList();
    }

    @Override
    public int add(Customer customer) {
        return customerMapper.add(customer);
    }

    @Override
    public int delete(int id) {
        return customerMapper.delete(id);
    }

    @Override
    public int update(Customer customer) {
        return customerMapper.update(customer);
    }

    @Override
    public List<Customer> findByIf(Map<Object, Object> map) {
        return customerMapper.findByIf(map);
    }
}
