package br.com.techthor.battlearena;

public class Main {

    public static void main(String[] args) {

        Enemy zombie = new Enemy("Zombie", 10, 1);
        Enemy ogre = new Enemy("Ogre", 20, 3);
        ogre.setAttackDamage(100);

//        System.out.println(zombie.getTypeOfEnemy() + " has " + zombie.getHealthPoints() +
//                " health points and can do an attack of " + zombie.getAttackDamage());
//
//        zombie.talk();
//        zombie.walkForward();
//        zombie.attack();
//
//        ogre.talk();
//        ogre.walkForward();

        System.out.println(zombie.getAttackDamage());
        System.out.println(ogre.getAttackDamage());

    }
}
