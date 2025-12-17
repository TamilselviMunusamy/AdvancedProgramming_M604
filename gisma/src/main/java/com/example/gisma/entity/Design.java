package com.example.gisma.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Design {

    private Integer frontneckdepth;

    private Integer backneckdepth;

    private Integer bust;

    private Integer waist;

    private Integer sleevelength;

    private Integer sleevecircumference;

    private String designType;

    private Integer sholderlength;

    private String neckstyle;

    private Boolean isMeasurementByVideo;

}

