package br.com.techthor.battlearena;

public class Ogre extends Enemy {

    public Ogre(int healthPoints, int attackDamage) {
        super(healthPoints, attackDamage);
    }

    @Override
    public void talk() {
        System.out.println("Ogre is slamming hands all arround");
    }
}
