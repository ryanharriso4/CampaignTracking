public class Piece {
    private String name;
    // Some sort of image will go here

    public Piece(String name /* Image will also be passed here */) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
