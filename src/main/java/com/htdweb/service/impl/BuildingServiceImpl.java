package com.htdweb.service.impl;

import com.htdweb.converter.BuildingDTOtoEntityConverter;
import com.htdweb.converter.BuildingEntityToDTOConverter;
import com.htdweb.entity.BuildingEntity;
import com.htdweb.model.dto.BuildingDTO;
import com.htdweb.model.dto.SearchDTO;
import com.htdweb.repository.BuildingRepository;
import com.htdweb.service.BuildingService;
import com.htdweb.utils.ImageUpload;
import com.htdweb.utils.UploadFileUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
@Transactional
@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private BuildingEntityToDTOConverter buildingEntityToDTOConverter;
    @Autowired
    private BuildingDTOtoEntityConverter buildingDTOtoEntityConverter;

    @Autowired
    private ImageUpload imageUpload;
    @Override
    public List<BuildingDTO> getAllBuildingDTO() {
        List<BuildingEntity> buildingEntityList = buildingRepository.findAll();
        List<BuildingDTO> buildingDTOList = new ArrayList<>();
        for(BuildingEntity x : buildingEntityList){
            buildingDTOList.add(buildingEntityToDTOConverter.toBuildingDTO(x));
        }
        return buildingDTOList;
    }

    @Override
    public List<BuildingDTO> getAllBuildingDTOByCustomer(SearchDTO searchDTO) {
        List<BuildingEntity> buildingEntityList = buildingRepository.getAllBuildingByCustom(searchDTO);
        List<BuildingDTO> buildingDTOList = new ArrayList<>();
        for(BuildingEntity x : buildingEntityList){
            buildingDTOList.add(buildingEntityToDTOConverter.toBuildingDTO(x));
        }
        return buildingDTOList;
    }

    @Override
    public BuildingDTO getBuildingDTOById(Long id) {
        BuildingEntity buildingEntity = buildingRepository.findById(id).get();
        return buildingEntityToDTOConverter.toBuildingDTO(buildingEntity);
    }

    @Override
    public List<BuildingDTO> getTop5BuildingDTOByView() {
        List<BuildingEntity> buildingEntityList = buildingRepository.getTop5BuildingByView();
        List<BuildingDTO> list = new ArrayList<>();
        for(BuildingEntity x : buildingEntityList){
            list.add(buildingEntityToDTOConverter.toBuildingDTO(x));
        }
        return list;
    }

    @Override
    public void addOrUpdateBuilding(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = buildingDTOtoEntityConverter.toBuildingEntity(buildingDTO);
        MultipartFile image = buildingDTO.getImage();
        if (image == null) {
            buildingEntity.setImage(null);
        } else {
            imageUpload.uploadFile(image);
            try {
                buildingEntity.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        buildingRepository.save(buildingEntity);
    }

    @Override
    public void deleteBuilding(Long id) {
        buildingRepository.deleteById(id);
    }
}
