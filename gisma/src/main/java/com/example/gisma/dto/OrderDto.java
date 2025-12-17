package com.example.gisma.dto;

import java.util.List;

import com.example.gisma.entity.Order;
import org.springframework.web.multipart.MultipartFile;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class OrderDto {

    private List<MultipartFile> files;

    private Order order;

}