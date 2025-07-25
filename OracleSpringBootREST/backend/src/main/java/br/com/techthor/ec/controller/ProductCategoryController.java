package br.com.techthor.ec.controller;

import br.com.techthor.ec.entity.ProductCategory;
import br.com.techthor.ec.repository.ProductCategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product-categories")
public class ProductCategoryController {

    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryController(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    @GetMapping
    public List<ProductCategory> getAll() {
        return productCategoryRepository.findAll();
    }
}
