package com.htdweb.service;

import com.htdweb.entity.CategoriesEntity;

public interface CategoriesService {
    CategoriesEntity findCategoriesByCode(String code);
}
