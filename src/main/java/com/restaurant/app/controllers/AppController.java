package com.restaurant.app.controllers;

import com.restaurant.app.repository.Ingredient;
import com.restaurant.app.repository.IngredientRepository;
import com.restaurant.app.services.IngredientService;
import com.restaurant.app.user.User;
import com.restaurant.app.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AppController {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private IngredientRepository ingredientRepo;

    private IngredientService ingredientService;

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("user_menu")
    public String viewUserMenuPage(){return "/user_pages/user_menu";}

    @GetMapping("/manage_menu")
    public String viewManageMenu() {
        return "manage_menu";
    }

    @GetMapping("/manage_ingredients")
    public String manageIngredients(Model model) {
        List<Ingredient> listIngredients = ingredientRepo.findAll();
        model.addAttribute("listIngredients", listIngredients);
        return "manage_ingredients";
    }

    @GetMapping("/add_ingredients")
    public String addIngredients(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        return "add_ingredients";
    }

    @GetMapping("/manage_ingredients/edit/{id}")
    public String showUpdateIngredientForm(@PathVariable("id") Long ingredientId, Model model) {
        Ingredient ingredient = ingredientRepo.findById(ingredientId).orElseThrow(() -> new IllegalArgumentException("Invalid ingredient Id:" + ingredientId));
        model.addAttribute("ingredient", ingredient);
        System.out.println(ingredientId);
        return "update_ingredient";
    }

    @PostMapping("/manage_ingredients/update/{id}")
    public String updateIngredients(@PathVariable("id") Long ingredientId, Ingredient ingredient, BindingResult result, Model model) {
        if (result.hasErrors()) {
            ingredient.setId(ingredientId);
            return "update_ingredient";
        }
        ingredientRepo.save(ingredient);
        return "redirect:/manage_ingredients";
    }

    @GetMapping("/manage_ingredients/delete/{id}")
    public String deleteIngredient(@PathVariable("id") Long ingredientId, Model model){
        Ingredient ingredient = ingredientRepo.findById(ingredientId).orElseThrow(()-> new IllegalArgumentException("Invalid ingredient Id:" + ingredientId));
        ingredientRepo.delete(ingredient);
        return "redirect:/manage_ingredients";
    }

    @PostMapping("/process_ingredients")
    public String processIngredients(Ingredient ingredient) {
        ingredientRepo.save(ingredient);
        System.out.println("ingredient added to database");
        return "add_ingredients_success";
    }


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepo.save(user);

        return "register_success";
    }

    @GetMapping("/dashboard")
    public String listUsers(Model model) {
        List<User> listUsers = userRepo.findAll();
        model.addAttribute("listUsers", listUsers);

        return "dashboard";
    }

}
