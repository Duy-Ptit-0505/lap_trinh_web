package com.htdweb.converter;

import com.htdweb.entity.BuildingEntity;
import com.htdweb.model.dto.BuildingDTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.text.DecimalFormat;
import java.util.Base64;
import java.util.Calendar;

@Component
public class BuildingEntityToDTOConverter {
    @Autowired
    private ModelMapper modelMapper;
    public BuildingDTO toBuildingDTO(BuildingEntity buildingEntity){
        BuildingDTO buildingDTO = modelMapper.map(buildingEntity, BuildingDTO.class);
        buildingDTO.setPrice(buildingEntity.getPrice().toString());
        buildingDTO.setArea(buildingEntity.getArea().toString());
//        buildingDTO.setPrice(new DecimalFormat("###,###").format(buildingEntity.getPrice()));
//        buildingDTO.setArea(new DecimalFormat("##,##").format(buildingEntity.getArea()));
        buildingDTO.setImageBase64(buildingEntity.getImage());
        buildingDTO.setCategory(buildingEntity.getCategoriesEntity().getName());
        Calendar cal = Calendar.getInstance();
        cal.setTime(buildingEntity.getYearBuild());

        // Lấy năm từ Calendar
        Integer year = cal.get(Calendar.YEAR);
        buildingDTO.setYearBuild(year.toString());
        return buildingDTO;
    }
    private static String decodeBase64Image(byte[] base64Data) {
        // Giải mã chuỗi Base64 sang mảng byte
        byte[] decodedBytes = Base64.getDecoder().decode(base64Data);

        // Chuyển đổi mảng byte sang chuỗi UTF-8
        try {
            return new String(decodedBytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Failed to decode Base64 image: " + e.getMessage(), e);
        }
    }
}
