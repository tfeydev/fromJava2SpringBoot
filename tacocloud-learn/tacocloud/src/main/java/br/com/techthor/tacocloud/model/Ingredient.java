package br.com.techthor.tacocloud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Ingredient {

    @Id
    private String id;

    private String name;
    private String type;

}
