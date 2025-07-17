package br.com.techthor.springcoredemo.common;

import org.springframework.stereotype.Component;

public class SwimCoach implements Coach {

    public SwimCoach() {
        System.out.println("In the constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Swim 1000 meters as a warum up";
    }
}
