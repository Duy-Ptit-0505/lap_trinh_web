package com.htdweb.service;

import com.htdweb.entity.RoleEntity;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {

    RoleEntity findByCode(String code);
}
