package com.htdweb.api.admin;

import com.htdweb.entity.BuildingEntity;
import com.htdweb.model.dto.AccountDTO;
import com.htdweb.model.dto.BuildingDTO;
import com.htdweb.model.dto.OrderDTO;
import com.htdweb.repository.BuildingRepository;
import com.htdweb.service.BuildingService;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@MultipartConfig
@RestController
public class AdminApi {
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private BuildingService buildingService;
//    @PostMapping("/admin/account")
//    public void addBuilding(@RequestBody(required = false)AccountDTO accountDTO){
//
//        System.out.println("hihi");
//    }
    @PutMapping("/admin/add-order")
    public void addOrder(@RequestBody(required = false) OrderDTO orderDTO){
        System.out.println("ok");
    }

}
