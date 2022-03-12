package com.restaurant.app.repository;

import javax.persistence.*;

@Entity
@Table(name = "recipes")
public class Recipes {

    @Id
    @Column(name = "product_id")
    Long productId;

    @Column(name = "ingredient_id")
    Long ingredientId;

    @Column(name = "ingredient_description")
    String ingredientDescription;

    @Column(name="weight")
    Long weight;

    @Column(name="quantity")
    Long quantity;

    @Column(name="calories")
    Long calories;


}
