package com.htdweb.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BuildingDTO {
    private Long id;
    private String buildingName;
    private String address;
    private String area;
    private Integer bedRoom;
    private Integer bathRoom;
    private String yearBuild;
    private String price;
    private MultipartFile image;
    private String shortDescription;
    private String description;
    private String offerType;
    private String category;
    private String view;
    private String imageBase64;
    private String imageName;

}
