package br.com.techthor.battlearena.enemies;

public interface IEnemy {

    public void talk();
    public void attack();
    public int getHealthPoints();

    void setHealthPointsRemaining(int healthPointsRemaining);

    public int getAttackDamage();
    public void setAttackDamage(int attackDamage);
    public int getHealthPointsRemaining();
    public void setHealthPointsRemaining();
    public void specialAttack();
    public int getId();

}
