package br.com.techthor.battlearena.enemies.individualenemy;

import br.com.techthor.battlearena.enemies.Enemy;

public class Ogre extends Enemy implements IOgre {

    public Ogre(int healthPoints, int attackDamage) {
        super(healthPoints, attackDamage);
    }

    @Override
    public void talk() {
        System.out.println("Ogre is slamming hands all arround");
    }

    @Override
    public void specialAttack() {
        boolean didSpecialAttackWork = Math.random() < .05;
        if (didSpecialAttackWork) {
            setHealthPointsRemaining(getAttackDamage() + 4);
            System.out.println("Ogre's attack increades by 4!");
        }
    }

    @Override
    public void stareDown() {
        System.out.println("Ogre's eyes stare down opponent and it drops down to all four limbs");
    }
}
