package com.example.gisma.entity;


import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Entity
@Table
@Data
public class OrderAddress {
    private static final long serialVersionUID = 7774563428562961990L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", nullable = false)
    private Long id;

    @NotNull
    private Long userId;

    @Column(nullable = false)
    private String address1;

    @Column(nullable = true)
    private String address2;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String pinCode;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String country;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;
}
