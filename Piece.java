public class Piece implements Comparable<Piece> {
    protected String name;
    protected int intiative;
    // Some sort of image will go here

    public Piece(String name /* Image will also be passed here */) {
        this.name = name;
        intiative = 0;
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
        System.out.println(other);
        int otherI = other.getIntiative();
        if (intiative > otherI)
            return -1;
        else if (intiative < otherI)
            return 1;
        return 0;
    }

    @Override
    public String toString() {
        return name;
    }
}
