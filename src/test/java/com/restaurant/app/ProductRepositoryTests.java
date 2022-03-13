package com.restaurant.app;

import com.restaurant.app.repository.Ingredient;
import com.restaurant.app.repository.IngredientRepository;
import com.restaurant.app.repository.Product;
import com.restaurant.app.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ProductRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository repo;

    @Test
    public void testAddIngredient() {
        Ingredient ingredient = new Ingredient();
        ingredient.setDescription("orez");
        ingredient.setQuantity(2L);
        ingredient.setWeight(null);
        ingredient.setPrice(99L);
        ingredient.setCalories(333L);
        ingredient.setCategory("cereale");


        Product product = new Product();
        product.setDescription("test");
        product.setProductCategory("suc");
        product.setPrice(20.0);
//        product.setIngredientDescription("orez");
//        product.setIngredientQuantity(2L);
//        product.setIngredientWeight(300L);
//        product.setIngredientCalories(300L);
        Product savedProduct = repo.save(product);

//        Product existProduct = entityManager.find(Product.class, savedProduct.getId());

//        assertThat(product.getDescription()).isEqualTo(existProduct.getDescription());

    }
}
