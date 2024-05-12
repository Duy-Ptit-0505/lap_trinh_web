package com.htdweb.converter;

import com.htdweb.entity.BuildingEntity;
import com.htdweb.entity.CategoriesEntity;
import com.htdweb.model.dto.BuildingDTO;
import com.htdweb.service.CategoriesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BuildingDTOtoEntityConverter {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoriesService categoriesService;
    public BuildingEntity toBuildingEntity(BuildingDTO buildingDTO){
        BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
        buildingEntity.setArea(Double.parseDouble(buildingDTO.getArea()));
        CategoriesEntity categoriesEntity = categoriesService.findCategoriesByCode(buildingDTO.getCategory());
        buildingEntity.setCategoriesEntity(categoriesEntity);
        buildingEntity.setYearBuild(new Date());
//        buildingEntity.setImage64(buildingDTO.getImageBase64());
        if(buildingDTO.getId() == null){
            buildingEntity.setView(0l);
            buildingEntity.setStatus(1);
        }else {
            buildingEntity.setView(Long.parseLong(buildingDTO.getView()));
        }
        return buildingEntity;
    }
}
