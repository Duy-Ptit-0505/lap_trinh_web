package com.htdweb.converter;

import com.htdweb.entity.CustomerEntity;
import com.htdweb.entity.OrderEntity;
import com.htdweb.entity.UserEntity;
import com.htdweb.model.dto.OrderDTO;
import com.htdweb.repository.BuildingRepository;
import com.htdweb.repository.CustomerRepository;
import com.htdweb.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class OrderDTOtoEntity {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    BuildingRepository buildingRepository;
    @Autowired
    private UserEntityToCustomerEntity userEntityToCustomerEntity;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;
    public OrderEntity toOrderEntity(OrderDTO orderDTO) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        OrderEntity orderEntity = modelMapper.map(orderDTO, OrderEntity.class);
        orderEntity.setBuildingEntity(buildingRepository.findById(orderDTO.getIdBuilding()).get());
        orderEntity.setStatus(1);
        orderEntity.setCreatedAt(new Date());
        orderEntity.setViewingDate(simpleDateFormat.parse(orderDTO.getOrderDay()));
        UserEntity b = userRepository.findById(orderDTO.getIdCustomer()).get();
        CustomerEntity a = userEntityToCustomerEntity.toCustomerEntity(b);


        orderEntity.setCustomerEntity(a);
        customerRepository.save(a);
        return orderEntity;
    }
}
