package br.com.techthor.battlearena;

public class Ogre extends Enemy {

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
}
