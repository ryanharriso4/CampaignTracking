public class Enemy extends Piece {

    private int ac;
    private int hp;
    private int intiativeBonus;
    private int speed;
    private String attacks;
    private String stats;
    private String infoBlock;

    public Enemy(String name) {
        super(name);
        System.out.println(name + " " + intiative);
    }

    public Enemy(String name, int ac, int hp) {
        super(name);
        this.ac = ac;
        this.hp = hp;
    }

}