package com.htdweb.converter;

import com.htdweb.entity.TransactionEntity;
import com.htdweb.model.dto.TransactionDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class TransactionEntityToDTO {
    @Autowired
    private ModelMapper modelMapper;
    public TransactionDTO toTransactionDTO(TransactionEntity transactionEntity){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        TransactionDTO transactionDTO = modelMapper.map(transactionEntity, TransactionDTO.class);
        transactionDTO.setTime(simpleDateFormat.format(transactionEntity.getCreatedAt()));
        transactionDTO.setImageBase64(transactionEntity.getBuildingEntity().getImage());
        transactionDTO.setBuilding(transactionEntity.getBuildingEntity().getBuildingName());
        transactionDTO.setIdBuilding(transactionEntity.getBuildingEntity().getId());
        transactionDTO.setImageContractBase64(transactionEntity.getImage());
        transactionDTO.setCustomer(transactionEntity.getCustomerEntity().getFirstName() + " " + transactionEntity.getCustomerEntity().getLastName());
        return transactionDTO;
    }
}
