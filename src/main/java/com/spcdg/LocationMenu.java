package com.spcdg;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.CardLayout;

public class LocationMenu {
    JPanel locationPanel;
    private JPanel battlePanel;
    private CardLayout battleLayout;
    private EncounterMenu encounterMenu;
    static final String grey = "#E0E0E0";

    public LocationMenu(CardLayout battleLayout, JPanel battlePanel) {
        locationPanel = new JPanel();
        battlePanel.add(locationPanel, "1");
        this.battleLayout = battleLayout;
        this.battlePanel = battlePanel;

    }

    public JPanel getPanel(ArrayList<Location> locations) {
        encounterMenu = new EncounterMenu(battleLayout, battlePanel);
        GridLayout locationLayout = new GridLayout(0, 1, 0, 5);
        locationPanel.setLayout(locationLayout);

        for (Component c : locationPanel.getComponents()) {
            locationPanel.remove(c);
        }

        for (Location loc : locations) {
            JButton locationButton = new JButton(loc.getName());
            locationButton.setBackground(Color.decode(grey));
            locationButton.setBorder(new LineBorder(Color.BLACK));
            locationButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    encounterMenu.getPanel(loc);
                    battleLayout.show(battlePanel, "2");
                }
            });
            locationPanel.add(locationButton);
        }
        return locationPanel;
    }
}
