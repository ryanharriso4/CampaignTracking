import java.util.Arrays;

public class testing {
    public static void main(String[] args) {
        Piece[] pieces = new Piece[2];
        Piece p1 = new Piece("Ryan", 15);
        p1.setInitiative(20);
        Enemy p2 = new Enemy("Ivan", 10);
        p2.setInitiative(10);
        pieces[0] = p1;
        pieces[1] = p2;
        Arrays.sort(pieces);
        System.out.println(Arrays.toString(pieces));
    }
}
