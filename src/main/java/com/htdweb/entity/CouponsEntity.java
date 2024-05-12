package com.htdweb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "coupons")
@Setter
@Getter
public class CouponsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "discount_value")
    private Double discountValue;
    @Column(name = "code")
    private String code;
    @Column(name = "coupon_description")
    private String couponDescription;
    @Column(name = "coupon_start_date")
    private Date couponStartDate;
    @Column(name = "coupon_end_date")
    private Date couponEndDate;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "modified_at")
    private Date couponStartAt;
    @Column(name = "time_used")
    private Integer timeUsed;
    @Column(name = "discount_type")
    private String discountType;
    @ManyToMany(mappedBy = "couponsEntityList", fetch = FetchType.LAZY)
    private List<BuildingEntity> buildingEntityList = new ArrayList<>();

}
