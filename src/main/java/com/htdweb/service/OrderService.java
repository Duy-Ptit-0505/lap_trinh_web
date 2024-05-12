package com.htdweb.service;

import com.htdweb.model.dto.OrderDTO;
import com.htdweb.model.dto.TransactionDTO;

import java.text.ParseException;
import java.util.List;

public interface OrderService {
    void saveOrder(OrderDTO orderDTO) throws ParseException;
    List<OrderDTO> getOrderListByUserName(String name);
    List<OrderDTO> getAllOrderByAdmin();
    void failedOrder(Long id);
    void doneOrder(TransactionDTO transactionDTO);
}
