package com.restaurant.app;

import com.restaurant.app.repository.Ingredient;
import com.restaurant.app.repository.IngredientRepository;
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
public class IngredientRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IngredientRepository repo;

    @Test
    public void testAddIngredient() {
        Ingredient ingredient = new Ingredient();
        ingredient.setDescription("orez");
        ingredient.setQuantity(2L);
        ingredient.setWeight(null);
        ingredient.setPrice(99L);
        ingredient.setCalories(333L);
        ingredient.setCategory("cereale");

        Ingredient savedIngredient = repo.save(ingredient);

        Ingredient existIngredient = entityManager.find(Ingredient.class, savedIngredient.getId());

        assertThat(ingredient.getDescription()).isEqualTo(existIngredient.getDescription());

    }
}
