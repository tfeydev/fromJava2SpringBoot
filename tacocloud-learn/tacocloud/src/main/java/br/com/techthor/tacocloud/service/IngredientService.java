package br.com.techthor.tacocloud.service;

import br.com.techthor.tacocloud.model.Ingredient;
import br.com.techthor.tacocloud.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> getAllIngredients() {
        return (List<Ingredient>) ingredientRepository.findAll();
    }

}
