package br.com.techthor.battlearena;

public class Enemy {

    private String typeOfEnemy;
    private int healthPoints;
    private int attackDamage;

    public Enemy(String typeOfEnemy, int healthPoints, int attackDamage) {
        this.typeOfEnemy = typeOfEnemy;
        this.healthPoints = healthPoints;
        this.attackDamage = attackDamage;
    }

    // getter amd setter
    public String getTypeOfEnemy() {
        return typeOfEnemy;
    }

    public void setTypeOfEnemy(String typeOfEnemy) {
        this.typeOfEnemy = typeOfEnemy;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    // methods
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
