package com.htdweb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class TransactionEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "code")
    private String code;
    @Column(name = "note")
    private String note;
    @Column(name = "create_at")
    private Date createdAt;
    @Column(name = "modified_at")
    private Date modifiedAt;
    @Column(name = "created_by")
    private Long createdBy;
    @Column(name = "modified_by")
    private Long modifiedBy;
    @Lob
    @Column(name = "image", columnDefinition = "MEDIUMBLOB")
    private String image;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customerEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    private BuildingEntity buildingEntity;
}
