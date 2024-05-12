package com.htdweb.service.impl;

import com.htdweb.entity.RoleEntity;
import com.htdweb.repository.RoleRepository;
import com.htdweb.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleEntity findByCode(String code) {
        return roleRepository.findByCode(code);
    }
}
