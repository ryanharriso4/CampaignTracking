import java.util.Arrays;

public class testing {
    public static void main(String[] args) {
        Piece[] pieces = new Piece[3];
        Piece p1 = new Piece("Ryan");
        p1.setInitiative(20);
        Piece p2 = new Piece("Ivan");
        pieces[0] = p1;
        pieces[1] = p2;
        Piece p3 = new Piece("Colin");
        p3.setInitiative(10);
        pieces[2] = p3;
        Arrays.sort(pieces);
        System.out.print(Arrays.toString(pieces));
    }
}
