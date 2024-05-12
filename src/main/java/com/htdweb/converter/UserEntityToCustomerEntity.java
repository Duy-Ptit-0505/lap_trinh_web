package com.htdweb.converter;

import com.htdweb.entity.CustomerEntity;
import com.htdweb.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserEntityToCustomerEntity {
    @Autowired
    private ModelMapper modelMapper;
    public CustomerEntity toCustomerEntity(UserEntity userEntity){
        return modelMapper.map(userEntity, CustomerEntity.class);
    }
}
