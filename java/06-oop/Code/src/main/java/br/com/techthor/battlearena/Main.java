package br.com.techthor.battlearena;

public class Main {

    public static void main(String[] args) {

        Enemy enemy = new Enemy();

        enemy.setTypeOfEnemy("Zombie");

        System.out.println(enemy.getTypeOfEnemy() + " has " + enemy.getHealthPoints() +
                " health points and can do an attack of " + enemy.getAttackDamage());

        enemy.talk();
        enemy.walkForward();
        enemy.attack();

    }
}
