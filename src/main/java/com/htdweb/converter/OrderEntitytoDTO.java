package com.htdweb.converter;

import com.htdweb.entity.OrderEntity;
import com.htdweb.model.dto.OrderDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

@Component
public class OrderEntitytoDTO {
    @Autowired
    private ModelMapper modelMapper;
    public OrderDTO toOrderDTO(OrderEntity orderEntity){
        OrderDTO orderDTO = modelMapper.map(orderEntity, OrderDTO.class);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        // Chuyển đổi Date "a" sang String "b" theo định dạng dd-MM-yyyy
//        String b = formatter.format(a);
        orderDTO.setIdBuilding(orderEntity.getBuildingEntity().getId());
        orderDTO.setOrderDay(simpleDateFormat.format(orderEntity.getViewingDate()));
        orderDTO.setTimeOrder(simpleDateFormat.format(orderEntity.getCreatedAt()));
        orderDTO.setBuildingName(orderEntity.getBuildingEntity().getBuildingName());
        orderDTO.setImageBase64(orderEntity.getBuildingEntity().getImage());
        orderDTO.setCustomer(orderEntity.getCustomerEntity().getFirstName() + " " + orderEntity.getCustomerEntity().getLastName());
        orderDTO.setPhone(orderEntity.getCustomerEntity().getTelephone());
        return orderDTO;
    }
}
