package com.example.gisma.entity;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Address implements Serializable {

    private static final long serialVersionUID = 7774563428562961990L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="address_id", nullable = false)
    private Long id;

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
}
