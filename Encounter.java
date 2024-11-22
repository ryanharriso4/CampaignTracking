import java.util.ArrayList;
import java.util.Collections;

public class Encounter {
    private ArrayList<Piece> characters = new ArrayList();
    private String loot;
    private int xp;

    public Encounter(String loot, int xp) {
        this.loot = loot;
        this.xp = xp;
    }

    public void calcIntiative() {
        Collections.sort(characters);
    }

    public void addCharacter(Piece p) {
        characters.add(p);
    }

    public String showRewards() {
        return xp + "\n" + loot;
    }

    public ArrayList<Piece> getCharacters() {
        return characters;
    }

}
