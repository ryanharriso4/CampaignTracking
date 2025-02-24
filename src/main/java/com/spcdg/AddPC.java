package com.spcdg;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class AddPC {
    private JButton makePC;
    static final String grey = "#E0E0E0";

    /*
     * 
     */

    public AddPC(sqlConnect sql, ArrayList<Piece> pieces) {
        makePC = new JButton("Add Player Character");
        makePC.setBackground(Color.decode(grey));
        makePC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JLabel nameLabel = new JLabel("Character Name");
                JTextField nameIn = new JTextField();
                JLabel hpLabel = new JLabel("Character HP");
                JTextField hpIn = new JTextField();
                JLabel acLabel = new JLabel("Character AC");
                JTextField acIn = new JTextField();
                JLabel intLabel = new JLabel("Character Intiative");
                JTextField intIn = new JTextField();

                Object[] forForm = { nameLabel, nameIn, hpLabel, hpIn, acLabel, acIn, intLabel, intIn };
                int dialog = JOptionPane.showConfirmDialog(null, forForm, "Add PC", JOptionPane.YES_OPTION,
                        JOptionPane.PLAIN_MESSAGE);

                if (dialog == JOptionPane.YES_OPTION) {
                    int hp = 0;
                    int ac = 0;
                    int intv = 0;
                    boolean errorFound = false;
                    try {
                        hp = Integer.parseInt(hpIn.getText());
                    } catch (NumberFormatException ex) {
                        JLabel label = new JLabel("A non number was entered for the hp value");
                        JOptionPane.showMessageDialog(null, label, "message",
                                JOptionPane.ERROR_MESSAGE);
                        errorFound = true;
                    }
                    try {
                        ac = Integer.parseInt(acIn.getText());
                    } catch (NumberFormatException ex) {
                        JLabel label = new JLabel("A non number was entered for the ac value");
                        JOptionPane.showMessageDialog(null, label, "message",
                                JOptionPane.ERROR_MESSAGE);
                        errorFound = true;
                    }
                    try {
                        intv = Integer.parseInt(intIn.getText());
                    } catch (NumberFormatException ex) {
                        JLabel label = new JLabel("A non number was entered for the intiative value");
                        JOptionPane.showMessageDialog(null, label, "message",
                                JOptionPane.ERROR_MESSAGE);
                        errorFound = true;
                    }

                    if (!errorFound) {
                        try {
                            sql.addPC(nameIn.getText(), hp, ac);
                        } catch (IllegalArgumentException ex) {
                            JOptionPane.showMessageDialog(null, ex, "The Value player character inputed already exists",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    Piece piece = new Piece(nameIn.getText(), hp, ac, intv);
                    pieces.add(piece);
                }

            }
        });
    }

    public JButton getButton() {
        return makePC;
    }
}
