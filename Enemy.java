public class Enemy extends Piece {

    private int ac;
    private int hp;

    public Enemy(String name) {
        super(name);
    }

    public Enemy(String name, int ac, int hp) {
        super(name);
        this.ac = ac;
        this.hp = hp;
    }
}