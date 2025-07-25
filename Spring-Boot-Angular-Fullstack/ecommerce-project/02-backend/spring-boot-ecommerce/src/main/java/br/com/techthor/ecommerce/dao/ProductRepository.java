package br.com.techthor.ecommerce.dao;

import br.com.techthor.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {

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
