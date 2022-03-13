package com.restaurant.app.services;

import com.restaurant.app.repository.Ingredient;
import com.restaurant.app.repository.IngredientRecipe;
import com.restaurant.app.repository.IngredientRepository;
import com.restaurant.app.repository.Product;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class ProductService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public Double calculateTotalIngredientPrice(Product product) {
        List<String> ingredientNameList = new ArrayList<>();
        List<Ingredient> ingredientList = new ArrayList<>();
        List<IngredientRecipe> ingredientRecipes = new ArrayList<>();
        Double result = 0D;
        if (product.getIngredientDescription().size() > 0) {
            product.getIngredientDescription().stream().forEach(ingredient -> {
                ingredientNameList.add(ingredient.getIngredientName());
            });

            for (String names : ingredientNameList) {
                ingredientList.add(ingredientRepository.findByDescription(names));
            }

            log.info(ingredientList);

            ingredientRecipes.addAll(product.getIngredientDescription());

            for (int i = 0; i < ingredientList.size(); i++) {
                if(ingredientRecipes.get(i).getIngredientQuantity() == null) {
                    double ingredientPrice = (double) ingredientList.get(i).getPrice();
                    double ingredientWeight = (double) ingredientList.get(i).getWeight();
                    double recipeWeight = (double) ingredientRecipes.get(i).getIngredientWeight();
                    result = result + ((ingredientPrice / ingredientWeight) * recipeWeight);
                } else {
                    result = result + (((double)ingredientList.get(i).getPrice() / (double)ingredientList.get(i).getQuantity()) * (double)ingredientRecipes.get(i).getIngredientQuantity());
                }
            }
        }
        BigDecimal bd = new BigDecimal(result).setScale(2, RoundingMode.HALF_UP);
        double newResult = bd.doubleValue();
        return newResult;
    }

    public Double calculateProductProfit(Product product) {
        Double result = 0D;
        result = product.getPrice() - calculateTotalIngredientPrice(product);
        return result;
    }
}
