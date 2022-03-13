package com.restaurant.app.repository;

import lombok.Builder;

@Builder
public class IngredientItem {

    private Ingredient id;
    private String description;
    private String slug;

    public Ingredient getId() {
        return id;
    }

    public void setId(Ingredient id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
