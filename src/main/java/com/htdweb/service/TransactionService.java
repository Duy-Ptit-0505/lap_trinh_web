package com.htdweb.service;

import com.htdweb.model.dto.TransactionDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {
    List<TransactionDTO> getAllTransactionForCustomer(String name);
    List<TransactionDTO> getAllTransacactionForAdmin();
}
