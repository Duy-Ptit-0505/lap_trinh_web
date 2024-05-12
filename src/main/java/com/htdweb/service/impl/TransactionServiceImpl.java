package com.htdweb.service.impl;

import com.htdweb.converter.TransactionEntityToDTO;
import com.htdweb.entity.TransactionEntity;
import com.htdweb.model.dto.TransactionDTO;
import com.htdweb.repository.CustomerRepository;
import com.htdweb.repository.TransactionRepository;
import com.htdweb.service.TransactionService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TransactionEntityToDTO transactionEntityToDTO;
    @Autowired
    private TransactionRepository transactionRepository;
    @Override
    public List<TransactionDTO> getAllTransactionForCustomer(String name) {
        List<TransactionDTO> transactionDTOList = new ArrayList<>();
        List<TransactionEntity> transactionEntityList = customerRepository.findOneByUserName(name).getTransactionEntityList();
        for(TransactionEntity x: transactionEntityList){
            transactionDTOList.add(transactionEntityToDTO.toTransactionDTO(x));
        }
        return transactionDTOList;
    }

    @Override
    public List<TransactionDTO> getAllTransacactionForAdmin() {
        List<TransactionDTO> transactionDTOList = new ArrayList<>();
        List<TransactionEntity> transactionEntityList = transactionRepository.findAll();
        for(TransactionEntity x: transactionEntityList){
            transactionDTOList.add(transactionEntityToDTO.toTransactionDTO(x));
        }
        return transactionDTOList;
    }
}
