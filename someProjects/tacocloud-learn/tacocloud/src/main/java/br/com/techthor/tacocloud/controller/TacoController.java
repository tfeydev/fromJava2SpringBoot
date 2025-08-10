package br.com.techthor.tacocloud.controller;

import br.com.techthor.tacocloud.model.Taco;
import br.com.techthor.tacocloud.repository.TacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tacos")
public class TacoController {

    private final TacoRepository tacoRepository;

    @Autowired
    public TacoController(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    @PostMapping
    public Taco createTaco(@RequestBody Taco taco) {
        return tacoRepository.save(taco);
    }

}
