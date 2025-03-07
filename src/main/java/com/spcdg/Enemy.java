package com.spcdg;

import java.lang.String;
import java.util.ArrayList;

public class Enemy extends Piece {

    private int intiativeBonus;
    private int speed;
    private ArrayList<String> actions;
    private ArrayList<String> reactions;
    private String otherInfo;
    private String statBlock;

    public Enemy(Enemy e) {
        super(e.getName(), e.getIntiative(), e.getHP(), e.getAC());
        this.intiativeBonus = e.getIB();
        this.speed = e.getSpeed();
        this.actions = new ArrayList<>(e.getActions());
        this.reactions = new ArrayList<>(e.getReactions());
        this.otherInfo = e.getInfo();
        this.statBlock = e.getStats();
    }

    public Enemy(String name, int intiativeBonus, int hp, int ac, int speed) {
        super(name, 0, hp, ac);
        this.speed = speed;
        actions = new ArrayList();
        reactions = new ArrayList();
        this.intiativeBonus = intiativeBonus;
    }

    public Enemy(String name, int intiative, int intiativeBonus, int ac, int hp, ArrayList<String> actions,
            ArrayList<String> reactions, String st, String skills, String vul, String res, String imu, String senses,
            String lang,
            int speed,
            String str, String dex, String con, String intl, String wis, String cha) {
        super(name, intiative, hp, ac);
        this.ac = ac;
        this.hp = hp;
        this.actions = new ArrayList<String>(actions);
        this.reactions = new ArrayList<String>(reactions);

        this.speed = speed;
        this.intiativeBonus = intiativeBonus;
        setOtherInfo(str, skills, vul, res, imu, lang, senses);
        setStatBlock(str, dex, con, intl, wis, cha);

    }

    public int getIB() {
        return intiativeBonus;
    }

    public int getSpeed() {
        return speed;
    }

    public ArrayList<String> getActions() {
        return actions;
    }

    public ArrayList<String> getReactions() {
        return reactions;
    }

    public String getInfo() {
        return otherInfo;
    }

    public String getStats() {
        return statBlock;
    }

    public void setOtherInfo(String st, String skills, String vul, String res, String immun, String lang, String sen) {
        StringBuilder sb = new StringBuilder();
        sb.append("Saving Throws: ");
        sb.append(st);
        sb.append("<br>");
        sb.append("Skills: ");
        sb.append(skills);
        sb.append("<br>");
        sb.append("Vulenarabilites: ");
        sb.append(vul);
        sb.append("<br>");
        sb.append("Resistances: ");
        sb.append(res);
        sb.append("<br>");
        sb.append("Immunities: ");
        sb.append(immun);
        sb.append("<br>");
        sb.append("Languages: ");
        sb.append(lang);
        sb.append("<br>");
        sb.append("Senses: ");
        sb.append(sen);

        this.otherInfo = sb.toString();
    }

    public void setStatBlock(String str, String dex, String con, String intl, String wis, String cha) {
        StringBuilder sb = new StringBuilder();
        sb.append(" STR      DEX      CON      INT      WIS      CHA<br/>");
        if (str.length() == 6)
            sb.append(String.format("%1$-9s", str));
        else
            sb.append(String.format("%1$-10s", str));
        // sb.append(" ");
        if (str.length() == 6)
            sb.append(String.format("%1$-9s", dex));
        else
            sb.append(String.format("%1$-10s", dex));
        // sb.append(" ");
        if (str.length() == 6)
            sb.append(String.format("%1$-9s", con));
        else
            sb.append(String.format("%1$-10s", con));
        // sb.append(" ");
        if (str.length() == 6)
            sb.append(String.format("%1$-9s", intl));
        else
            sb.append(String.format("%1$-10s", intl));
        // sb.append(" ");
        if (str.length() == 6)
            sb.append(String.format("%1$-9s", wis));
        else
            sb.append(String.format("%1$-10s", wis));
        // sb.append(" ");
        if (str.length() == 6)
            sb.append(String.format("%1$-9s", cha));
        else
            sb.append(String.format("%1$-10s", cha));
        sb.append("</p>");
        this.statBlock = sb.toString().replaceAll(" ", "&nbsp;");
    }

    public void addActions(ArrayList<String> actions) {
        this.actions = new ArrayList<String>(actions);
    }

    public void printAction() {
        System.out.println("something");
        for (String s : actions) {
            System.out.println(s);
        }
    }

    public void addReactions(ArrayList<String> reactions) {
        this.reactions = new ArrayList<String>(reactions);
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<HTML>");
        sb.append(name);
        sb.append("<hr>");
        sb.append(" Armor class: ");
        sb.append(ac);
        sb.append("<br/> Hit Points: ");
        sb.append(hp);
        sb.append("<br>Speed: ");
        sb.append(speed);
        sb.append("<hr>");
        sb.append(statBlock);
        sb.append("<hr>");
        sb.append("Actions <br>");
        for (String s : actions) {
            sb.append(s);
            sb.append("<br>");
        }
        sb.append("<hr>");
        sb.append("Reactions <br>");
        for (String s : reactions) {
            sb.append(s);
            sb.append("<br>");
        }
        sb.append("<hr>");
        sb.append(otherInfo);
        sb.append("</HTML>");
        return sb.toString();
    }

    @Override
    public int rollIntiative() {
        intiative = ((int) Math.ceil(Math.random() * 20)) + intiativeBonus;
        return intiative;
    }

}