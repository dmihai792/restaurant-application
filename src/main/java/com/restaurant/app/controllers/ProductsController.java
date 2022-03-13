package com.restaurant.app.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.app.repository.*;
import com.restaurant.app.services.IngredientService;
import com.restaurant.app.services.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Log4j2
public class ProductsController {

    private ProductService productService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping("/manage_products")
    public String manageProducts(Model model) {
        List<Product> listProducts = productRepository.findAll();
        model.addAttribute("listProducts", listProducts);
        return "/manage_menu/manage_products/manage_products";
    }


    @GetMapping("/add_products")
    public String addProducts(Model model) {
        model.addAttribute("product", new Product());
//        model.addAttribute("ingredientRecipe", new IngredientRecipe());
//        model.addAttribute("ingredient", new Ingredient());
        return "/manage_menu/manage_products/add_products";
    }


    @PostMapping("/process_products")
    public String processProducts(Product product) {


        ObjectMapper Obj = new ObjectMapper();
        try {
            String jsonStr = Obj.writeValueAsString(product);

            log.info(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (product.getIngredientDescription().size() > 0) {
            product.getIngredientDescription().stream().forEach(productItem -> {
                productItem.setProduct(product);
            });
        }
        productRepository.save(product);
        System.out.println("product added to database");
        return "redirect:/manage_products";
    }

    @GetMapping("/manage_products/edit/{id}")
    public String showUpdateProductForm(@PathVariable("id") Long productId, Model model) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + productId));
        model.addAttribute("product", product);
        System.out.println(productId);
        return "/manage_menu/manage_products/update_product";
    }

    @PostMapping("/manage_products/update/{id}")
    public String updateProducts(@PathVariable("id") Long productId, Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            product.setId(productId);
            return "/manage_menu/manage_products/update_product";
        }
        productRepository.save(product);
        return "redirect:/manage_products";
    }

    @GetMapping("/manage_products/delete/{id}")
    public String deleteProducts(@PathVariable("id") Long productId, Model model) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Invalid ingredient Id:" + productId));
        productRepository.delete(product);
        return "redirect:/manage_products";
    }

    @RequestMapping(value = "/ingredient_name_autocomplete")
    @ResponseBody
    public List<String> ingredientNamesAutocomplete(@RequestParam(value = "term", required = false) String searchTerm) {

        return simulateSearchResult(searchTerm);

    }

    @GetMapping(value = "/ingredientSearch")
    public ResponseEntity<String> ingredientSearch(@RequestParam(value = "term", required = false) String searchTerm) {

        List<String> strings = simulateSearchResult(searchTerm);

        ObjectMapper mapper = new ObjectMapper();
        String resp = "";

        try {
            resp = mapper.writeValueAsString(strings);
        } catch (JsonProcessingException e) {

        }
        return new ResponseEntity<String>(resp, HttpStatus.OK);

    }

    private List<String> simulateSearchResult(String ingredientName) {
        List<Ingredient> listIngredients = ingredientRepository.findAll();
        List<String> allIngredientNames = new ArrayList<String>();

        // iterate a list and filter by ingredientName
        for (Ingredient ingredient : listIngredients) {
            if (ingredient.getDescription().contains(ingredientName)) {
                allIngredientNames.add(ingredient.getDescription());
            }
        }

        System.out.println(allIngredientNames);
        return allIngredientNames;
    }
}
