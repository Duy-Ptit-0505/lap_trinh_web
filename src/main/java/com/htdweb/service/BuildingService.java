package com.htdweb.service;

import com.htdweb.model.dto.BuildingDTO;
import com.htdweb.model.dto.SearchDTO;

import java.util.List;

public interface BuildingService {
    List<BuildingDTO> getAllBuildingDTO();
    List<BuildingDTO> getAllBuildingDTOByCustomer(SearchDTO searchDTO);
    BuildingDTO getBuildingDTOById(Long id);
    List<BuildingDTO> getTop5BuildingDTOByView();
    void addOrUpdateBuilding(BuildingDTO buildingDTO);
    void deleteBuilding(Long id);
}
