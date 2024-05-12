package com.htdweb.converter;

import com.htdweb.entity.RoleEntity;
import com.htdweb.entity.UserEntity;
import com.htdweb.model.dto.RegisterDTO;
import com.htdweb.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Component
public class RegisterDTOtoUserEntity {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RoleService roleService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public UserEntity toUserEntity(RegisterDTO registerDTO){
        UserEntity userEntity = modelMapper.map(registerDTO, UserEntity.class);
        List<RoleEntity> list = new ArrayList<>();
        RoleEntity roleEntity = roleService.findByCode(registerDTO.getRole());
        list.add(roleEntity);
        userEntity.setRoleEntityList(list);
        userEntity.setPassword(bCryptPasswordEncoder.encode(registerDTO.getPassword()));
        userEntity.setEnabled(1);
        userEntity.setCreatedAt(new Date());
        return userEntity;
    }
}
