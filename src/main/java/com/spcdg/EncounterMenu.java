package com.spcdg;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.CardLayout;

public class EncounterMenu {
    private JPanel battlePanel;
    private CardLayout battleLayout;
    CharacterMenu characterMenu;
    static final String grey = "#E0E0E0";

    public EncounterMenu(CardLayout battleLayout, JPanel battlePanel) {
        this.battlePanel = battlePanel;
        this.battleLayout = battleLayout;
    }

    public void getPanel(Location location) {
        JPanel encounterPanel = new JPanel();
        battlePanel.add(encounterPanel, "2");
        encounterPanel.setLayout(new BorderLayout());
        characterMenu = new CharacterMenu(battleLayout, battlePanel);
        JButton goBack = new JButton("Go back to locations");
        goBack.setBackground(Color.decode(grey));
        goBack.setBorder(new LineBorder(Color.BLACK));
        goBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                battleLayout.show(battlePanel, "1");
            }
        });
        encounterPanel.add(goBack, BorderLayout.NORTH);

        GridLayout encounterLayout = new GridLayout(0, 1, 0, 5);
        JPanel encounterList = new JPanel();
        encounterList.setLayout(encounterLayout);
        encounterPanel.add(encounterList, BorderLayout.CENTER);

        for (Encounter enc : location.getEncounters()) {
            JButton encounterButton = new JButton(enc.getName());
            encounterButton.setBackground(Color.decode(grey));
            encounterButton.setBorder(new LineBorder(Color.BLACK));
            encounterButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    characterMenu.getPanel(enc);
                    battlePanel.updateUI();
                    battleLayout.show(battlePanel, "3");

                }
            });
            encounterList.add(encounterButton);
        }

    }
}
