package com.example.legendcore.repository;

import com.example.legendcore.entity.Material;
import com.example.legendcore.entity.MaterialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {


    boolean existsByMaterialType(MaterialType itemType);

    Optional<Material> findByMaterialType(MaterialType itemType);

    List<Material> findAllByMaterialCategoryId(int id);
}
