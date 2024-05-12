package com.htdweb.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionDTO {
    private Long id;
    private String customer;
    private String building;
    private String time;
    private String imageBase64;
    private Long idBuilding;
    private Long idOrder;
    private MultipartFile image;
    private String imageContractBase64;
}
