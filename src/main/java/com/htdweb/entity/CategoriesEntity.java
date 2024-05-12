package com.htdweb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "categories")
public class CategoriesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "status")
    private Integer status;
//    @ManyToMany(mappedBy = "categoriesList", fetch = FetchType.LAZY)
//    private List<BuildingEntity> buildingEntityList = new ArrayList<>();
    @OneToMany(mappedBy = "categoriesEntity", fetch = FetchType.LAZY)
    private List<BuildingEntity> buildingEntityList;

}
