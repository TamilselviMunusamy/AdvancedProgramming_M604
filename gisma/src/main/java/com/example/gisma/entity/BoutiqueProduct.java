package com.example.gisma.entity;

import java.io.Serializable;
import java.util.Date;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
@Entity
@SuperBuilder
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoutiqueProduct implements Serializable {

    private static final long serialVersionUID = 7774563428562961990L;

    @Id
    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "category_type",
            referencedColumnName = "type",
            nullable = false
    )
    @JsonBackReference
    private BoutiqueCategory boutiqueCategory;

    @Lob
    @Column(nullable = false, length = 100000)
    private String description;

    @Column(nullable = false)
    private String imgUrl;

    @Column(nullable = false)
    private String extension;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double actualPrice;

    @Column(nullable = false)
    private Double marketPrice;

    @Column(nullable = false)
    private Double offerPrice;

    @Column(nullable = false)
    private Integer offerPercentage;

    @Column(nullable = false)
    private Date expiryDate;

    @Column(nullable = false)
    private Integer orderNo;

    @Column(nullable = true)
    private String size;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Date addedOn;

}
