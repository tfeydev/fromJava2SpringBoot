package br.com.techthor.battlearena;

public class Enemy {

    String typeOfEnemy;
    int healthPoints = 10;
    int attackDamage = 1;

    public void talk() {
        System.out.println("I am a " + typeOfEnemy + " be prepared to fight!");
    }

    public void walkForward() {
        System.out.println(typeOfEnemy + " moves closer to you");
    }

    public void attack() {
        System.out.println(typeOfEnemy + " attacks for " + attackDamage + " damage");
    }
}
