package com.example.gisma.entity;

import java.io.Serializable;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Entity
@Table(name = "boutique_category")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BoutiqueCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String type;

    @OneToMany(
            mappedBy = "boutiqueCategory",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<BoutiqueProduct> boutiqueProducts;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer orderNo;

    @Column(nullable = false)
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date addedOn;
}
