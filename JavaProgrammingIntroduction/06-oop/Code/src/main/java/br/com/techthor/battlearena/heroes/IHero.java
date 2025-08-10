package br.com.techthor.battlearena.heroes;

import br.com.techthor.battlearena.heroes.weapons.Weapon;

public interface IHero {

    int getHealthPoints();

    int getHeathPoints();

    int getHealthPointsRemaining();
    void setHealthPointsRemaining(int healthPointsRemaining);
    int getAttackDamage();
    Weapon getWeapoon();

    Weapon getWeapon();

    void setWeapon(Weapon weapon);
    void equipeWeapon();

    void equipWeapon();

    boolean isWeaponEquipped();
    void setWeaponEquipped(boolean isWeaponEquipped);
    void setAttackDamage(int attackDamage);
    void attack();

}
