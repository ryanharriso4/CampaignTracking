package com.spcdg;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.GridLayout;

public class AddLocation {
    private JButton addLocationButton;
    static final String grey = "#E0E0E0";

    public AddLocation(sqlConnect sql, ArrayList<Encounter> encounters, ArrayList<Location> locations) {
        addLocationButton = new JButton("Add Location");
        addLocationButton.setBackground(Color.decode(grey));
        addLocationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JLabel nameLabel = new JLabel("Name");
                JTextField nameIn = new JTextField(20);

                ArrayList<Encounter> encountersChosen = new ArrayList<>();
                JPanel addEncounters = new JPanel();
                GridLayout gridLayout = new GridLayout(0, 1);
                addEncounters.setLayout(gridLayout);
                for (Encounter enc : encounters) {
                    JButton button = new JButton(enc.getName());
                    button.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            encountersChosen.add(enc);
                        }
                    });
                    addEncounters.add(button);
                }

                JButton openAddEncounter = new JButton("Add Encounter to location");
                openAddEncounter.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int addPiecesConfirmaton = JOptionPane.showConfirmDialog(null, addEncounters,
                                "Add characters to encounter",
                                JOptionPane.OK_OPTION,
                                JOptionPane.PLAIN_MESSAGE);
                    }
                });

                Object[] forForm = { nameLabel, nameIn, openAddEncounter };
                int dialog = JOptionPane.showConfirmDialog(null, forForm, "Add PC", JOptionPane.YES_OPTION,
                        JOptionPane.PLAIN_MESSAGE);

                if (dialog == JOptionPane.YES_OPTION) {
                    try {
                        sql.addLocation(nameIn.getText(), encountersChosen);
                        Location location = new Location(nameIn.getText());
                        for (Encounter enc : encountersChosen) {
                            location.addEncounter(enc);
                        }
                        locations.add(location);

                    } catch (Exception ex) {

                    }
                }

            }
        });

    }

    public JButton getButton() {
        return addLocationButton;
    }
}
