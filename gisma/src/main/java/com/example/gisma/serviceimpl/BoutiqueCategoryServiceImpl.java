package com.example.gisma.serviceimpl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.example.gisma.entity.BoutiqueCategory;
import com.example.gisma.repo.BoutiqueCategoryRepo;
import com.example.gisma.service.BoutiqueCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class BoutiqueCategoryServiceImpl implements BoutiqueCategoryService {

    @Autowired
    private BoutiqueCategoryRepo categoryRepo;

    @Override
    public BoutiqueCategory saveOrUpdate(BoutiqueCategory boutiqueCategory) {

        return categoryRepo.save(boutiqueCategory);
    }

    @Override
    public Boolean delete(BoutiqueCategory boutiqueCategory) {
        try {
            categoryRepo.delete(boutiqueCategory);
            return Boolean.TRUE;
        } catch (Exception e) {
            log.debug("Exception while deleting unit ", e);
        }

        return Boolean.FALSE;
    }

    @Override
    public List<BoutiqueCategory> fetchAll() {
        return categoryRepo.findAll().stream().sorted(Comparator.comparing(BoutiqueCategory::getOrderNo))
                .collect(Collectors.toList());
    }

}
