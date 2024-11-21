public class Enemy extends Piece {

    private int ac;
    private int hp;
    private int intiativeBonus;
    private String attacks;
    private String infoBlock; // includes: speed, stats, and additional info

    public Enemy(String name, int intiative) {
        super(name, intiative);
    }

    public Enemy(String name, int intiative, int ac, int hp, String attacks, String infoBlock) {
        super(name, intiative);
        this.ac = ac;
        this.hp = hp;
        this.attacks = attacks;
        this.infoBlock = infoBlock;
    }

    public boolean takeDamage(int amount) {
        hp -= amount;
        if (hp <= 0)
            return true; // True means that the attack killed the enemy
        return false; // False if the enemy wasn't killed
    }

    public int getHp() {
        return hp;
    }

    public boolean checkHit(int amount) {
        if (amount >= ac)
            return true; // This means the attack hits
        return false; // attack misses
    }

    public String getAttacks() {
        return attacks;
    }

    public void setInitiative(int roll, boolean useB) {
        if (useB)
            intiative = roll + intiativeBonus;
        else
            intiative = roll;
    }

    public String printInfo() {
        return infoBlock;
    }

}