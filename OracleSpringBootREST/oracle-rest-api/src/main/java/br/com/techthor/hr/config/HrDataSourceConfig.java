package br.com.techthor.hr.config;

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
        basePackages =  "br.com.techthor.hr.repository",
        entityManagerFactoryRef = "hrEntityManagerFactory",
        transactionManagerRef = "hrTransactionManager"
)
@EntityScan(basePackages = "br.com.techthor.hr.entity")
public class HrDataSourceConfig {

    @Bean(name = "hrDataSource")
    @ConfigurationProperties(prefix = "spring.hr.datasource")
    public DataSource hrDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "hrEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean hrEntityManagerFactory(
            @Qualifier("hrDataSource") DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("br.com.techthor.hr.entity");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("hibernate.show_sql", true);

        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean(name = "hrTransactionManager")
    public PlatformTransactionManager hrTransactionManager(
            @Qualifier("hrEntityManagerFactory") LocalContainerEntityManagerFactoryBean hrEntityManagerFactory) {
        return new JpaTransactionManager(hrEntityManagerFactory.getObject());
    }
}

