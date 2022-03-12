package com.restaurant.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    @Query("SELECT u FROM Ingredient u WHERE u.description = ?1")
    public Ingredient findByDescription(String description);

}
