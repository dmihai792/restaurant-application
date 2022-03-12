package com.restaurant.app.services;

import com.restaurant.app.repository.Ingredient;
import com.restaurant.app.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import javax.persistence.Cacheable;
import java.util.ArrayList;
import java.util.List;

@Service
public class IngredientService {

    IngredientRepository ingredientRepository;

    public List<Ingredient> getAllIngredients() {
        List<Ingredient> ingredient = new ArrayList<Ingredient>();
        ingredientRepository.findAll().forEach(ingredient1 -> ingredient.add(ingredient1));
        return ingredient;
    }

    public Ingredient getIngredientById(Long id){
        return ingredientRepository.findById(id).get();
    }

    public void updateIngredient(Ingredient ingredient){
        ingredientRepository.save(ingredient);
    }

    public void deleteIngredient(Long id){
        ingredientRepository.deleteById(id);
    }

    public List<Ingredient> fetchIngredients(String searchTerm) throws Exception {
        return ingredientRepository.findAll();
    }

}
