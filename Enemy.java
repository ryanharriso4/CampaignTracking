import java.lang.String;

public class Enemy extends Piece {

    private int ac;
    private int hp;
    private int intiativeBonus;
    private String speed;
    private String actions;
    private String reactions;
    private String otherInfo; // Things like Skills, immunites, etc...
    private String statBlock;

    public Enemy(String name, int intiative, int hp, int ac) {
        super(name, intiative, hp, ac);
    }

    public Enemy(String name, int intiative, int intiativeBonus, int ac, int hp, String actions,
            String otherInfo, String speed,
            String reactions, String str, String dex, String con, String intl, String wis, String cha) {
        super(name, intiative, hp, ac);
        this.ac = ac;
        this.hp = hp;
        this.actions = actions;
        this.otherInfo = otherInfo;
        this.speed = speed;
        this.reactions = reactions;
        // this.statBlock = statBlock;
        StringBuilder sb = new StringBuilder();
        sb.append(" STR      DEX      CON      INT      WIS      CHA<br/>");
        sb.append(String.format("%1$-10s", str));
        // sb.append(" ");
        sb.append(String.format("%1$-10s", dex));
        // sb.append(" ");
        sb.append(String.format("%1$-10s", con));
        // sb.append(" ");
        sb.append(String.format("%1$-10s", intl));
        // sb.append(" ");
        sb.append(String.format("%1$-10s", wis));
        // sb.append(" ");
        sb.append(String.format("%1$-10s", cha));
        sb.append("</p>");
        // System.out.println(String.format("%1$-5s", str));

        this.statBlock = sb.toString().replaceAll(" ", "&nbsp;");

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

    // public String getAttacks() {
    // return actions;
    // }

    public void setInitiative(int roll, boolean useB) {
        if (useB)
            intiative = roll + intiativeBonus;
        else
            intiative = roll;
    }

    public String getStats() {
        return statBlock;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<HTML>");
        sb.append(name);
        sb.append("<br/> Armor class: ");
        sb.append(ac);
        sb.append("<br/> Hit Points: ");
        sb.append(hp);
        sb.append("<br/>");
        sb.append(statBlock);
        sb.append("</HTML>");

        return sb.toString();
    }

}