public class Enemy extends Piece {

    private int ac;
    private int hp;
    private int intiativeBonus;
    // private String speed;
    // private String actions;
    // private String reactions;
    // private String otherInfo; // Things like Skills, immunites, etc...
    // private String statBlock;

    public Enemy(String name, int intiative, int hp, int ac) {
        super(name, intiative, hp, ac);
    }

    // public Enemy(String name, int intiative, int ac, int hp, String actions,
    // String otherInfo, String speed,
    // String reactions, String statBlock) {
    // super(name, intiative);
    // this.ac = ac;
    // this.hp = hp;
    // this.actions = actions;
    // this.otherInfo = otherInfo;
    // this.speed = speed;
    // this.reactions = reactions;
    // this.statBlock = statBlock;
    // }

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

    // public String getAttacks() {
    // return actions;
    // }

    public void setInitiative(int roll, boolean useB) {
        if (useB)
            intiative = roll + intiativeBonus;
        else
            intiative = roll;
    }

}