package br.com.techthor.ec.repository;

import br.com.techthor.ec.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
