
public class Piece implements Comparable<Piece> {
    protected String name;
    protected int intiative;
    protected int hp;
    protected int ac;
    // Some sort of image will go here

    public Piece(String name, int intiative, int hp, int ac /* Image will also be passed here */) {
        this.name = name;
        this.intiative = intiative;
        this.hp = hp;
        this.ac = ac;
    }

    public String getName() {
        return name;
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
        sb.append(name);
        sb.append("<HTML><br/> Armor class: </HTML>");
        sb.append(ac);
        sb.append("<HTML><br/> Hit Points: </HTML>");
        sb.append(hp);
        sb.append("<HTML><hr> ");

        return sb.toString();
    }
}
