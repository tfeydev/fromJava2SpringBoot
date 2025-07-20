package br.com.techthor.battlearena;

import static br.com.techthor.battlearena.Enemy.getNumberOfEnemies;

public class Main {

    public static void main(String[] args) {

        Zombie zombie = new Zombie(10, 1);
        Ogre ogre = new Ogre(20, 3);

        System.out.println("There are " + getNumberOfEnemies() +
                " enemies ready to fight!");

        zombie.battleStance();
        ogre.stareDown();

        battle(zombie, ogre);
    }

    public static void battle(Enemy e1, Enemy e2) {
        e1.talk();
        e2.talk();

        while (e1.getHealthPointsRemaining() > 0 && e2.getHealthPointsRemaining() > 0) {
            System.out.println("---------");
            e1.specialAttack();
            e2.specialAttack();
            System.out.println("Enemy 1: " + e1.getHealthPointsRemaining() + " HP left");
            System.out.println("Enemy 2: " + e2.getHealthPointsRemaining() + " HP left");
            e2.attack();
            e1.setHealthPointsRemaining(e1.getHealthPointsRemaining() - e2.getAttackDamage());
            e1.attack();
            e2.setHealthPointsRemaining(e1.getHealthPointsRemaining() - e1.getAttackDamage());
        }
        System.out.println("-------- Final---------");

        if (e1.getHealthPointsRemaining() > 0) {
            System.out.println("Enemy 1 wins!");
        } else {
            System.out.println("Enemy 2 wins!");
        }
    }
}
