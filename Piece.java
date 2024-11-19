
import java.util.HashSet;

public class Piece {
    private String name;
    // Some sort of image will go here
    private static HashSet<String> characters = new <String>HashSet();

    public Piece(String name /* Image will also be passed here */) {
        if (characters.add(name)) {
            this.name = name;
            System.out.println("first");
        } else {

            System.out.println("This character already exists");
        }

    }
}
