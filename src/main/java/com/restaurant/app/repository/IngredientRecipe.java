package com.restaurant.app.repository;

import javax.persistence.*;

@Entity
@Table(name = "recipes")
public class IngredientRecipe {

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "ingredient_id")
    Long ingredientId;

    @Column(name="ingredient_name")
    String ingredientName;

    @Column(name="ingredient_weight")
    Long ingredientWeight;

    @Column(name="ingredient_quantity")
    Long ingredientQuantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public Long getIngredientWeight() {
        return ingredientWeight;
    }

    public void setIngredientWeight(Long ingredientWeight) {
        this.ingredientWeight = ingredientWeight;
    }

    public Long getIngredientQuantity() {
        return ingredientQuantity;
    }

    public void setIngredientQuantity(Long ingredientQuantity) {
        this.ingredientQuantity = ingredientQuantity;
    }

}
