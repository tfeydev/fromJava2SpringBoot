package br.com.techthor.ecommerce.dao;

import br.com.techthor.ecommerce.entity.Product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM product ORDER BY id ASC", nativeQuery = true)
    List<Product> findAllNativeOrderedById();

    Page<Product> findByCategoryId(@Param("id") Long id, Pageable pageable);

    // Page<Product> findByNameContaining(@Param("name") String name, Pageable pageable);  ==> case sensitive
    Page<Product> findByNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);


    @Override
    @RestResource(exported = false)
    <S extends Product> S save(S entity); // disables POST AND PUT

    @Override
    @RestResource(exported = false)
    void deleteById(Long id); // disables DELETE

    @Override
    @RestResource(exported = false)
    void delete(Product entity);

}
