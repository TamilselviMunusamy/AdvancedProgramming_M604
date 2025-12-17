package com.example.gisma.service;

import com.example.gisma.entity.BoutiqueCategory;

import java.util.List;



public interface BoutiqueCategoryService {

    BoutiqueCategory saveOrUpdate(BoutiqueCategory boutiqueCategory);

    Boolean delete(BoutiqueCategory boutiqueCategory);

    List<BoutiqueCategory> fetchAll();

}