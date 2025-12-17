package com.example.gisma.repo;

import java.util.Optional;

import com.example.gisma.entity.BoutiqueProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BoutiqueProductRepo extends JpaRepository<BoutiqueProduct, String> {

    Optional<BoutiqueProduct> findByTitle(String type);
}
