package br.com.techthor.battlearena;

public class Main {

    public static void main(String[] args) {

        Enemy zombie = new Enemy();

        zombie.typeOfEnemy = "Zombie";

        System.out.println(zombie.typeOfEnemy + " has " + zombie.healthPoints +
                " health points and can do an attack of " + zombie.attackDamage);

        zombie.talk();
        zombie.walkForward();
        zombie.attack();

    }
}
