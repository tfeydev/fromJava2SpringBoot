package br.com.techthor.ec.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = "br.com.techthor.ec.repository",
        entityManagerFactoryRef = "ecEntityManagerFactory",
        transactionManagerRef = "ecTransactionManager"
)
@EntityScan(basePackages = "br.com.techthor.ec.entity")
public class EcDataSourceConfig {

    @Bean(name = "ecDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource ecDataSource() {
        return DataSourceBuilder
                .create()
                .build();
    }

    @Bean(name = "ecEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean ecEntityManagerFactory(
            @Qualifier("ecDataSource") DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource);
        factory.setPackagesToScan("br.com.techthor.ec.entity");
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("hibernate.show_sql", true);

        factory.setJpaPropertyMap(properties);
        return factory;
    }

    @Bean(name = "ecTransactionManager")
    public PlatformTransactionManager ecTransactionManager(
            @Qualifier("ecEntityManagerFactory") LocalContainerEntityManagerFactoryBean ecEntityManagerFactory) {
        return new JpaTransactionManager(ecEntityManagerFactory.getObject());
    }
}
