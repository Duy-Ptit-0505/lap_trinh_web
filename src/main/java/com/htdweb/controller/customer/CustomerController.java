package com.htdweb.controller.customer;
import com.htdweb.converter.RegisterDTOtoUserEntity;
import com.htdweb.entity.BuildingEntity;
import com.htdweb.entity.TransactionEntity;
import com.htdweb.entity.UserEntity;
import com.htdweb.model.dto.*;
import com.htdweb.repository.BuildingRepository;
import com.htdweb.repository.CustomerRepository;
import com.htdweb.repository.UserRepository;
import com.htdweb.service.BuildingService;
import com.htdweb.service.OrderService;
import com.htdweb.service.RoleService;
import com.htdweb.service.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private TransactionService transactionService;
    @GetMapping("/web/customer-order")
    ModelAndView homeOrderCustomer(){
        ModelAndView mav = new ModelAndView("/customer/my-order");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        List<OrderDTO> orderDTOList = orderService.getOrderListByUserName(name);
        mav.addObject("orderDTOList", orderDTOList);
        return mav;
    }
    @GetMapping("/web/customer-transaction")
    ModelAndView homeTransactionCustomer(){
        ModelAndView mav = new ModelAndView("/customer/my-transaction");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        List<TransactionDTO> transactionDTOList = transactionService.getAllTransactionForCustomer(name);
        mav.addObject("transactionDTOList", transactionDTOList);
        return mav;
    }
}
