package com.htdweb.service.impl;

import com.htdweb.entity.CategoriesEntity;
import com.htdweb.repository.CategoriesRepository;
import com.htdweb.service.CategoriesService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class CategoriesSeviceImpl implements CategoriesService {
    @Autowired
    private CategoriesRepository categoriesRepository;
    @Override
    public CategoriesEntity findCategoriesByCode(String code) {
        CategoriesEntity categoriesEntity = categoriesRepository.findByCode(code);
        return categoriesEntity;
    }
}
