package com.restaurant.app.repository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@SecondaryTable(name="recipes")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 45)
    private String description;

    @Column(name = "product_category", nullable = false, length = 45)
    private String productCategory;

    @Column(name = "set_price", nullable = false)
    private Double price;

    @Column(name = "ingredient_price", nullable = true)
    private Double ingredientPrice;

    @Column
    private Double profit;

    @Column
    private Long calories;

    @Column
    private Long quantity;

    @Column(table="recipes", name = "ingredient_id")
    Long ingredientId;

    @Column(table="recipes", name = "ingredient_description")
    String ingredientDescription;

    @Column(table="recipes", name="weight")
    Long ingredientWeight;

    @Column(table="recipes", name="quantity")
    Long ingredientQuantity;

    @Column(table="recipes", name="calories")
    Long ingredientCalories;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getIngredientPrice() {
        return ingredientPrice;
    }

    public void setIngredientPrice(Double ingredientPrice) {
        this.ingredientPrice = ingredientPrice;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Long getCalories() {
        return calories;
    }

    public void setCalories(Long calories) {
        this.calories = calories;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getIngredientDescription() {
        return ingredientDescription;
    }

    public void setIngredientDescription(String ingredientDescription) {
        this.ingredientDescription = ingredientDescription;
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

    public Long getIngredientCalories() {
        return ingredientCalories;
    }

    public void setIngredientCalories(Long ingredientCalories) {
        this.ingredientCalories = ingredientCalories;
    }
}
