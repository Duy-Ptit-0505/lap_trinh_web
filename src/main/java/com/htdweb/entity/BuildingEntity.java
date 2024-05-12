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
@Getter
@Setter
@Entity
@Table(name = "buildings")
public class BuildingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "building_name")
    private String buildingName;
    @Column(name = "address")
    private String address;
    @Column(name = "area")
    private Double area;
    @Column(name = "bed_room")
    private Integer bedRoom;
    @Column(name = "bath_room")
    private Integer bathRoom;
    @Column(name = "year_build")
    private Date yearBuild;
    @Column(name = "price")
    private Double price;
    @Lob
    @Column(name = "image", columnDefinition = "MEDIUMBLOB")
    private String image;
    @Column(name = "short_description")
    private String shortDescription;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private Integer status;
    @Column(name = "offertype")
    private String offerType;
    @Column(name = "view")
    private Long view;
//    @Column(name = "image64")
//    private String image64;
    @OneToMany(mappedBy = "buildingEntity", fetch = FetchType.LAZY)
    private List<ProductImagesEntity> listProductImages;
//    @ManyToMany
//    @JoinTable(
//            name = "building_categories",
//            joinColumns = @JoinColumn(name = "building_id"),
//            inverseJoinColumns = @JoinColumn(name = "category_id")
//    )
//    private List<CategoriesEntity> categoriesList = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoriesEntity categoriesEntity;


    @OneToMany(mappedBy = "buildingEntity", fetch = FetchType.LAZY)
    List<AssignmentBuilding> assignmentBuildingList = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "building_coupon",
            joinColumns = @JoinColumn(name = "building_id"),
            inverseJoinColumns = @JoinColumn(name = "coupon_id")
    )
    private List<CouponsEntity> couponsEntityList = new ArrayList<>();
    @OneToMany(mappedBy = "buildingEntity", fetch = FetchType.LAZY)
    private List<TransactionEntity> transactionEntityList = new ArrayList<>();
    @OneToMany(mappedBy = "buildingEntity", fetch = FetchType.LAZY)
    private List<OrderEntity> orderEntityList = new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "likes",
            joinColumns = @JoinColumn(name = "building_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    private List<CustomerEntity> customerEntityList = new ArrayList<>();

}
