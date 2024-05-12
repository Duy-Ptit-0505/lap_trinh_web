package com.htdweb.repository;

import com.htdweb.entity.CategoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<CategoriesEntity, Long> {
    CategoriesEntity findByCode(String code);
}
