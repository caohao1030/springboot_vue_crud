package com.ch.demo.controller;

import com.ch.demo.mapper.CustomerMapper;
import com.ch.demo.pojo.Customer;
import com.ch.demo.service.CustomerServiceImpl;
import com.ch.demo.utils.ResultEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private CustomerServiceImpl mapper;

    //显示列表
    @RequestMapping("/list")
    public ResultEntity list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer limit, String username, String address) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("username", "%" + username + "%");
        map.put("address", "%" + address + "%");
        PageHelper.startPage(page, limit);
        List<Customer> byIf = mapper.findByIf(map);
        PageInfo<Customer> pageInfo = new PageInfo<>(byIf);
        HashMap<Object, Object> countMap = new HashMap<>();
        countMap.put("total", pageInfo.getTotal());
        countMap.put("customers", byIf);
        return ResultEntity.success(countMap);
    }

    //增加用户
    @RequestMapping("/add")
    public ResultEntity add(@RequestBody Customer customer) {
        int add = mapper.add(customer);
        System.out.println(add);
        return ResultEntity.success();
    }
    //删除
    @RequestMapping("/delete/{id}")
    public ResultEntity delete(@PathVariable("id") Integer id) {
       mapper.delete(id);
        return ResultEntity.success();
    }
    //编辑
    @RequestMapping("/update")
    public ResultEntity update(@RequestBody Customer customer) {
        mapper.update(customer);
        return ResultEntity.success();
    }
}
