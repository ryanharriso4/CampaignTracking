package com.spcdg;

import java.util.ArrayList;
import java.util.Collections;

public class Encounter {
    private ArrayList<Piece> characters = new ArrayList();
    private String loot;
    private String xp;
    private String name;

    public Encounter(String name, String loot, String xp) {
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void pieceNames() {
        for (Piece p : characters)
            System.out.println(p.getName());
    }

    public ArrayList<Piece> getCharacters() {
        return characters;
    }

}
