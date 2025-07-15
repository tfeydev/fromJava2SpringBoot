package br.com.techthor.tacocloud.repositories;

import org.springframework.data.repository.CrudRepository;
import br.com.techthor.tacocloud.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}


