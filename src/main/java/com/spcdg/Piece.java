package com.spcdg;

public class Piece implements Comparable<Piece> {
    protected String name;
    protected int intiative;
    protected int hp;
    protected int ac;
    // Some sort of image will go here

    public Piece(Piece p) {
        this.name = p.getName();
        this.ac = p.getAC();
        this.hp = p.getHP();
        this.intiative = 0;
    }

    public Piece(String name, int intiative, int hp, int ac /* Image will also be passed here */) {
        this.name = name;
        this.intiative = intiative;
        this.hp = hp;
        this.ac = ac;
    }

    public String getName() {
        return name;
    }

    public int getHP() {
        return hp;
    }

    public int getAC() {
        return ac;
    }

    public void setInitiative(int num) {
        intiative = num;
    }

    public int getIntiative() {
        return intiative;
    }

    @Override
    public int compareTo(Piece other) {

        int otherI = other.getIntiative();
        if (intiative > otherI)
            return -1;
        else if (intiative < otherI)
            return 1;
        return 0;
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
        sb.append("<br/> Intiative: ");
        sb.append(intiative);
        sb.append("<HTML>");

        return sb.toString();
    }

    public int rollIntiative() {
        intiative = (int) Math.ceil(Math.random() * 20);
        return intiative;
    }
}
