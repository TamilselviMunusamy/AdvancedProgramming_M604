package com.example.gisma.entity;

import java.io.Serializable;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 7774563428562961990L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = true)
    private String email;

    @Column(nullable = true)
    private String gstNo;

    @Column(nullable = true)
    private Long addressId;

    @Column(nullable = true)
    private Long ownerUserId;

    @Column(nullable = true)
    private int active;

    @Column(nullable = false)
    private String mobileNumber;

    @Column(nullable = false)
    private Integer role;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Column(nullable = true)
    private String deviceId;

    @Transient
    private Address address;

}

