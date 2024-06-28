package com.lab.repositories;

import com.lab.models.Color;
import com.lab.models.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {
    List<Cat> findByName(String name);
    List<Cat> findByBreed(String breed);
    List<Cat> findByColor(Color color);
}
