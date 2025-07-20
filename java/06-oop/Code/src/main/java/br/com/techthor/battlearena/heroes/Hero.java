package br.com.techthor.battlearena.heroes;

import br.com.techthor.battlearena.heroes.weapons.Weapon;

public class Hero implements IHero {

    private int healthPoints;
    private int healthPointsRemaining;
    private int attackDamage;
    private boolean isWeaponEquipped = false;

    // Composition
    private Weapon weapon;

    public Hero(int healthPoints, int attackDamage) {
        this.healthPoints = healthPoints;
        this.healthPointsRemaining = healthPoints;
        this.attackDamage = attackDamage;
    }

    @Override
    public int getHealthPoints() {
        return 0;
    }

    @Override
    public int getHeathPoints() {
        return healthPoints;
    }

    @Override
    public int getHealthPointsRemaining() {
        return healthPointsRemaining;
    }

    @Override
    public void setHealthPointsRemaining(int healthPointsRemaining) {
        this.healthPointsRemaining = healthPointsRemaining;
    }

    @Override
    public int getAttackDamage() {
        return attackDamage;
    }

    @Override
    public Weapon getWeapoon() {
        return null;
    }

    @Override
    public Weapon getWeapon() {
        return weapon;
    }

    @Override
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public void equipeWeapon() {

    }

    @Override
    public void equipWeapon() {
        if (getWeapon() != null && !isWeaponEquipped()) {
            setAttackDamage(getAttackDamage() + weapon.getAttackIncrease());
            setWeaponEquipped((true));
        }
    }

    @Override
    public boolean isWeaponEquipped() {
        return isWeaponEquipped;
    }

    @Override
    public void setWeaponEquipped(boolean isWeaponEquipped) {
        this.isWeaponEquipped = isWeaponEquipped;
    }

    @Override
    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    @Override
    public void attack() {
        System.out.println("Hero attacks for " + attackDamage + " damage");
    }

}
