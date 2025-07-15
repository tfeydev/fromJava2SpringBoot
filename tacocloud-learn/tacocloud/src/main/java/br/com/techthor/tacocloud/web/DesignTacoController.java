package br.com.techthor.tacocloud.web;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import br.com.techthor.tacocloud.repositories.IngredientRepository;
import br.com.techthor.tacocloud.repositories.TacoRepository;
import jakarta.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
// import org.springframework.web.bind.annotation.ModelAttribute; // Thymeleaf-related, commented out
// import org.springframework.ui.Model; // Thymeleaf-related, commented out
// import org.springframework.web.bind.annotation.SessionAttributes; // Thymeleaf-related, commented out


import br.com.techthor.tacocloud.Ingredient;
import br.com.techthor.tacocloud.Taco;
import br.com.techthor.tacocloud.TacoOrder;

@Slf4j
// @Controller // old annotation for Thymeleaf
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/design") // Customized path for REST API
// @SessionAttributes("tacoOrder") // Thymeleaf-related, commented out
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;
    private final TacoRepository tacoRepo;

    public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository tacoRepo) {
        this.ingredientRepo = ingredientRepo;
        this.tacoRepo = tacoRepo;
    }

    // Thymeleaf-based method, commented out
    /*@ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        );

        Type[] types = Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
            filterByType(ingredients, type));
        }
    }*/

    // Thymeleaf-based method, commented out
    /*@ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }*/

    // Thymeleaf-based method, commented out
    /*@ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }
    @GetMapping
    public String showDesignForm() {
        return "design";
    }*/

    @GetMapping("/ingredients")
    public List<Ingredient> getIngredients() {
        return StreamSupport
                .stream(ingredientRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    // Thymeleaf-based method, commented out
    /*@PostMapping
    public String processTaco(
            @Valid Taco taco, Errors errors,
            @ModelAttribute TacoOrder tacoOrder) {

        if (errors.hasErrors()) {
            return "design";
        }

        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);

        return "redirect:/orders/current";
    }*/

    @PostMapping
    public ResponseEntity<?> processTaco(@Valid @RequestBody Taco taco, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getAllErrors());
        }
        Taco saved = tacoRepo.save(taco);
        return ResponseEntity.ok(saved);
    }

    // Thymeleaf-based helper method, commented out
    /*private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }*/

}
