package br.com.techthor.springcoredemo.config;

import br.com.techthor.springcoredemo.common.Coach;
import br.com.techthor.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

    @Bean("aquatic")
    public Coach swimCoach() {
        return new SwimCoach();
    }

}
