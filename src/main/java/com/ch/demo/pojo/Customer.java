package com.ch.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private Integer id;
    private String username;
    private Integer age;
    private String sex;
    private String type;
    private String address;
    private String avatar;
}
