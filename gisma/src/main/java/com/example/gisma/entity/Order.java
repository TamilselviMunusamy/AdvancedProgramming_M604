package com.example.gisma.entity;

import java.io.Serializable;
import java.util.Date;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Entity
@Table(name = "boutique_order")
@Data
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private BoutiqueProduct boutiqueProduct;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private OrderAddress orderAddress;

    @NotNull
    private Integer quantity;

    private Double soldPrice;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private Design design;

    private Date expectedArrivalDate;

    private Date actualArrivalDate;

    private String message;

    private Boolean customDesign;

    @NotNull
    private String status;

    @CreatedDate
    private Date createdOn;

    @LastModifiedDate
    private Date updatedOn;

}
