package br.com.techthor.ecommerce.config;

import br.com.techthor.ecommerce.entity.ProductCategory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class DataRestConfig implements RepositoryRestConfigurer {

    private EntityManager entityManager;

    @Autowired
    public DataRestConfig(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(br.com.techthor.ecommerce.entity.Product.class, ProductCategory.class);

        // call an internal helper method
        exposedIds(config);

        /* -->>>>> that was working till < Spring 3.2
        RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);

        HttpMethod[] theUnsupportedActions = {
                HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE
        };

        // disable HTTP methods for Product: PUT, POST and DELETE
        config.getExposureConfiguration()
            .forDomainType((Product.class)
            .withItemExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
            .withCollectionExposure((metaData, httpMethods) -> httpMethods.disable(theUnsupportedActions)));

        // disable HTTP methods for ProductCategory: PUT, POST and DELETE
        config.getExposureConfiguration()
            .forDomainType((ProductCategory.class)
            .withItemExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
            .withCollectionExposure((metaData, httpMethods) -> httpMethods.disable(theUnsupportedActions)));

         */

    }

    private void exposedIds(RepositoryRestConfiguration config) {

        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        List<Class> entityClasses = new ArrayList<>();

        for (EntityType tempEntityType : entities) {
            entityClasses.add(tempEntityType.getJavaType());
        }

        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
