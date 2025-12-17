package com.example.gisma.repo;


import java.util.Optional;

import com.example.gisma.entity.BoutiqueCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BoutiqueCategoryRepo extends JpaRepository<BoutiqueCategory, String> {

    Optional<BoutiqueCategory> findByType(String type);

}
