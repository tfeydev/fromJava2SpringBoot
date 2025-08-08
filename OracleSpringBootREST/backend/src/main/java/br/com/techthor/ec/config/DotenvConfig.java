package br.com.techthor.ec.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class DotenvConfig implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    private static final Logger logger = LoggerFactory.getLogger(DotenvConfig.class);

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        try {
            logger.debug("Lade .env-Datei...");
            Dotenv dotenv = Dotenv.configure()
                    .directory("/home/thor/development/JavaProgramming/fromJava2SpringBoot/OracleSpringBootREST/backend")
                    .filename(".env")
                    .ignoreIfMissing()
                    .load();
            logger.debug(".env-Datei erfolgreich geladen");
            dotenv.entries().forEach(entry -> {
                System.setProperty(entry.getKey(), entry.getValue());
                logger.debug("Geladene Umgebungsvariable: {}={}", entry.getKey(), entry.getValue());
            });
        } catch (Exception e) {
            logger.error("Fehler beim Laden der .env-Datei: {}", e.getMessage(), e);
            throw new RuntimeException("Fehler beim Laden der .env-Datei", e);
        }
    }
}