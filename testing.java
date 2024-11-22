import java.util.ArrayList;

public class testing {
    public static void main(String[] args) {
        Encounter e1 = new Encounter("sword", 100);
        Enemy en1 = new Enemy("Goblin", 20);
        Piece p1 = new Piece("Ryan", 30);
        e1.addCharacter(en1);
        e1.addCharacter(p1);

        e1.calcIntiative();
        ArrayList<Piece> chars = e1.getCharacters();
        for (Piece x : chars) {
            System.out.println(x.getName());
        }

    }
}
