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

    public ArrayList<Encounter> getEncounters() {
        return encounters;
    }

    public String getName() {
        return name;
    }
}
