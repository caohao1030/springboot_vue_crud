package com.ch.demo.mapper;

import com.ch.demo.pojo.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface CustomerMapper {
    List<Customer> queryList();
    int add(Customer customer);
    int delete(int id);
    int update(Customer customer);
    List<Customer>findByIf(Map<Object,Object>map);

}
