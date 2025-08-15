package br.com.techthor.ecommerce.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import jakarta.annotation.PostConstruct;

@Configuration
public class DotenvConfig {

    private final ConfigurableEnvironment env;

    public DotenvConfig(ConfigurableEnvironment env) {
        this.env = env;
    }

    @PostConstruct
    public void loadEnv() {
        Dotenv dotenv = Dotenv.configure()
                              .directory("/wallet") // oder Pfad zur .env
                              .ignoreIfMissing()
                              .load();

        dotenv.entries().forEach(entry ->
            env.getSystemProperties().put(entry.getKey(), entry.getValue())
        );
    }
}

