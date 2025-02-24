package com.spcdg;

import java.util.ArrayList;

public class Location {
    private ArrayList<Encounter> encounters = new ArrayList<>();
    private String name;

    public Location(String name) {
        this.name = name;
    }

    public void addEncounter(Encounter e) {
        encounters.add(e);
    }

    public void printEncounters() {
        for (Encounter e : encounters)
            System.out.println(e.getName());
    }

    public ArrayList<Encounter> getEncounters() {
        return encounters;
    }

    public String getName() {
        return name;
    }
}
