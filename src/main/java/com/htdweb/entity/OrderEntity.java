package com.htdweb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "status")
    private Integer status;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "total")
    private Double total;
    @Column(name = "create_at")
    private Date createAt;
    @Column(name = "modified_at")
    private Date modifiedAt;
    @Column(name = "viewing_date")
    private Date viewingDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customerEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    private BuildingEntity buildingEntity;

}
