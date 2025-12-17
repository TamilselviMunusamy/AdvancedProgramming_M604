package com.example.gisma.controller;

import java.util.List;


import com.example.gisma.entity.BoutiqueCategory;
import com.example.gisma.entity.BoutiqueProduct;
import com.example.gisma.service.BoutiqueCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/boutique-category")
public class BoutiqueCategoryController {

    @Autowired
    private BoutiqueCategoryService categoryService;

    @PostMapping
    @ResponseBody
    public BoutiqueCategory saveOrUpdate(@RequestBody BoutiqueCategory boutiqueCategory) {

        if (boutiqueCategory.getBoutiqueProducts() != null) {
            for (BoutiqueProduct product : boutiqueCategory.getBoutiqueProducts()) {
                product.setBoutiqueCategory(boutiqueCategory);
            }
        }
        return categoryService.saveOrUpdate(boutiqueCategory);
    }

    @DeleteMapping
    @ResponseBody
    public Boolean delete(@RequestBody BoutiqueCategory boutiqueCategory) {
        return categoryService.delete(boutiqueCategory);
    }

    @ResponseBody
    @GetMapping
    public List<BoutiqueCategory> fetchAll() {
        return categoryService.fetchAll();
    }

}

