package br.com.techthor.ecommerce.config;

import br.com.techthor.ecommerce.entity.ProductCategory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class DataRestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(br.com.techthor.ecommerce.entity.Product.class, ProductCategory.class);


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
}
