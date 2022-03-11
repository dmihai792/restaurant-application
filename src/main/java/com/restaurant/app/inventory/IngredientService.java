package com.restaurant.app.inventory;

import java.util.ArrayList;
import java.util.List;

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


}
