package com.spcdg;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.GridLayout;

public class AddEncounter {

    private JButton makeEnc;
    static final String grey = "#E0E0E0";

    public AddEncounter(sqlConnect sql, ArrayList<Piece> pieces, ArrayList<Encounter> encounters) {
        makeEnc = new JButton("Add Encounter");
        makeEnc.setBackground(Color.decode(grey));
        makeEnc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ArrayList<Piece> piecesChosen = new ArrayList<>();
                JLabel nameLabel = new JLabel("Encounter Name");
                JTextField nameIn = new JTextField();
                JLabel lootLabel = new JLabel("Loot");
                JTextField lootIn = new JTextField();
                JLabel xpLabel = new JLabel("xp");
                JTextField xpIn = new JTextField();

                JButton openAddPieces = new JButton("Add characters to encounter");

                JPanel addPieces = new JPanel();
                GridLayout gridLayout = new GridLayout(0, 1);
                addPieces.setLayout(gridLayout);
                for (Piece p : pieces) {
                    JButton button = new JButton(p.getName());
                    button.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            piecesChosen.add(p);
                        }
                    });
                    addPieces.add(button);

                }

                openAddPieces.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int addPiecesConfirmaton = JOptionPane.showConfirmDialog(null, addPieces,
                                "Add characters to encounter",
                                JOptionPane.OK_OPTION,
                                JOptionPane.PLAIN_MESSAGE);
                    }
                });

                Object[] forForm = { nameLabel, nameIn, lootLabel, lootIn, xpLabel, xpIn, openAddPieces };
                int dialog = JOptionPane.showConfirmDialog(null, forForm, "Add PC", JOptionPane.YES_OPTION,
                        JOptionPane.PLAIN_MESSAGE);

                if (dialog == JOptionPane.YES_OPTION) {
                    try {
                        sql.addEncounter(nameIn.getText(), lootIn.getText(), xpIn.getText(), piecesChosen);
                        Encounter encounter = new Encounter(nameIn.getText(), lootIn.getText(), xpIn.getText());
                        for (Piece p : piecesChosen) {
                            encounter.addCharacter(p);
                        }
                        encounters.add(encounter);
                    } catch (Exception ex) {
                        System.err.println(ex);
                    }
                }
            }

        });
    }

    public JButton getButton() {
        return makeEnc;
    }
}
