package com.spcdg;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.Flow;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

import java.lang.Integer;

public class AddCharacter {

    JButton makeChar;
    static final String grey = "#E0E0E0";

    public AddCharacter(sqlConnect sql, ArrayList<Piece> pieces) {
        makeChar = new JButton("Add Enemy");
        makeChar.setBackground(Color.decode(grey));
        makeChar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JLabel nameLabel = new JLabel("Character Name");
                JTextField nameIn = new JTextField(30);
                JLabel acLabel = new JLabel("AC");

                JTextField acIn = new JTextField(3);
                JLabel hpLabel = new JLabel(" HP ");

                JTextField hpIn = new JTextField(4);
                JLabel speedLabel = new JLabel(" Speed ");

                JTextField speedIn = new JTextField(3);

                ArrayList<String> acts = new ArrayList<>();
                JButton createAct = new JButton("Add Action");

                JLabel stLabel = new JLabel("Saving Throw");

                JTextField stIn = new JTextField(50);
                JLabel skillsLabel = new JLabel("Skills");

                JTextField skillsIn = new JTextField(50);
                JLabel vulLabel = new JLabel("Vulnerabilities");

                JTextField vulIn = new JTextField(50);
                JLabel resLabel = new JLabel("Resistances");

                JTextField resIn = new JTextField(50);
                JLabel immunLabel = new JLabel("Immunities");
                JTextField immunIn = new JTextField(50);
                JLabel sensesLabel = new JLabel("Senses");
                JTextField sensesIn = new JTextField(50);
                JLabel langLabel = new JLabel("Languages");
                JTextField langIn = new JTextField(50);

                JPanel stats = new JPanel(new FlowLayout());
                JPanel strC = new JPanel(new FlowLayout());
                JLabel strL = new JLabel("<HTML> STR: <HTML>");
                JSpinner strSpin = new JSpinner();
                ((JSpinner.DefaultEditor) strSpin.getEditor()).getTextField().setColumns(2);

                JPanel dexC = new JPanel(new FlowLayout());
                JLabel dexL = new JLabel("<HTML> DEX: <HTML>");
                JSpinner dexSpin = new JSpinner();
                ((JSpinner.DefaultEditor) dexSpin.getEditor()).getTextField().setColumns(2);

                JPanel conC = new JPanel(new FlowLayout());
                JLabel conL = new JLabel("<HTML> CON: <HTML>");
                JSpinner conSpin = new JSpinner();
                ((JSpinner.DefaultEditor) conSpin.getEditor()).getTextField().setColumns(2);

                JPanel intlC = new JPanel(new FlowLayout());
                JLabel intlL = new JLabel("<HTML> INT: <HTML>");
                JSpinner intlSpin = new JSpinner();
                ((JSpinner.DefaultEditor) intlSpin.getEditor()).getTextField().setColumns(2);

                JPanel wisC = new JPanel(new FlowLayout());
                JLabel wisL = new JLabel("<HTML> WIS: <HTML>");
                JSpinner wisSpin = new JSpinner();
                ((JSpinner.DefaultEditor) wisSpin.getEditor()).getTextField().setColumns(2);

                JPanel chaC = new JPanel(new FlowLayout());
                JLabel chaL = new JLabel("<HTML> CHA: <HTML>");
                JSpinner chaSpin = new JSpinner();
                ((JSpinner.DefaultEditor) chaSpin.getEditor()).getTextField().setColumns(2);
                strC.add(strL);
                strC.add(strSpin);
                dexC.add(dexL);
                dexC.add(dexSpin);
                conC.add(conL);
                conC.add(conSpin);
                intlC.add(intlL);
                intlC.add(intlSpin);
                wisC.add(wisL);
                wisC.add(wisSpin);
                chaC.add(chaL);
                chaC.add(chaSpin);
                stats.add(strC);
                stats.add(dexC);
                stats.add(conC);
                stats.add(intlC);
                stats.add(wisC);
                stats.add(chaC);

                createAct.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JLabel caNamePrompt = new JLabel("Enter attack name");
                        JTextField caName = new JTextField(50);
                        JLabel caDescriptionPrompt = new JLabel("Enter attack description");
                        JTextField caDisc = new JTextField(50);

                        Object[] caObjs = { caNamePrompt, caName, caDescriptionPrompt, caDisc };
                        int dialog = JOptionPane.showConfirmDialog(null, caObjs, "Enter action", JOptionPane.YES_OPTION,
                                JOptionPane.PLAIN_MESSAGE);
                        if (dialog == JOptionPane.YES_OPTION) {
                            acts.add(caName.getText() + ": " + caDisc.getText());
                        }
                    }
                });

                ArrayList<String> reacts = new ArrayList<>();
                JButton createReact = new JButton("Add Reaction");

                createReact.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JLabel raNamePrompt = new JLabel("Enter reaction name");
                        JTextField raName = new JTextField(50);
                        JLabel raDescriptionPrompt = new JLabel("Enter reaction description");
                        JTextField raDisc = new JTextField(50);

                        Object[] caObjs = { raNamePrompt, raName, raDescriptionPrompt, raDisc };
                        int dialog = JOptionPane.showConfirmDialog(null, caObjs, "Enter action", JOptionPane.YES_OPTION,
                                JOptionPane.PLAIN_MESSAGE);
                        if (dialog == JOptionPane.YES_OPTION) {
                            reacts.add(raName.getText() + ": " + raDisc.getText());
                        }
                    }
                });

                Object[] objects = { nameLabel, nameIn, acLabel, acIn, hpLabel, hpIn, speedLabel, speedIn,
                        stLabel, stIn, skillsLabel, skillsIn, vulLabel, vulIn, resLabel,
                        resIn, immunLabel,
                        immunIn, sensesLabel, sensesIn, langLabel, langIn, stats, createAct,
                        createReact };

                int dialog = JOptionPane.showConfirmDialog(null, objects, "Add Character",
                        JOptionPane.YES_OPTION,
                        JOptionPane.PLAIN_MESSAGE);

                if (dialog == JOptionPane.YES_OPTION) {

                    int ib = 0;
                    int ac = 0;
                    int hp = 0;
                    int speed = 0;

                    boolean errorFound = false;
                    try {
                        ac = Integer.parseInt(acIn.getText());
                    } catch (NumberFormatException ex) {
                        JLabel label = new JLabel("Number inputed for ac was not a number");
                        JOptionPane.showMessageDialog(null, label, "message",
                                JOptionPane.ERROR_MESSAGE);
                        errorFound = true;
                    }

                    try {
                        hp = Integer.parseInt(hpIn.getText());
                    } catch (NumberFormatException ex) {
                        JLabel label = new JLabel("Number inputed for hp was not a number");
                        JOptionPane.showMessageDialog(null, label, "message",
                                JOptionPane.ERROR_MESSAGE);
                        errorFound = true;
                    }

                    try {
                        speed = Integer.parseInt(speedIn.getText());
                    } catch (NumberFormatException ex) {
                        JLabel label = new JLabel("Number inputed for speed was not a number");
                        JOptionPane.showMessageDialog(null, label, "message",
                                JOptionPane.ERROR_MESSAGE);
                        errorFound = true;
                    }

                    String strIn = "";
                    String dexIn = "";
                    String conIn = "";
                    String intlIn = "";
                    String wisIn = "";
                    String chaIn = "";

                    try {
                        strSpin.commitEdit();
                        int val = (Integer) strSpin.getValue();
                        int modifier = (val - 10) / 2;
                        if (modifier >= 0)
                            strIn = String.format("%d(+%d)", val, modifier);
                        else
                            strIn = String.format("%d(%d)", val, modifier);
                    } catch (Exception ex) {
                        System.err.println(ex);
                        errorFound = true;
                    }

                    try {
                        dexSpin.commitEdit();
                        int val = (Integer) dexSpin.getValue();
                        int modifier = (val - 10) / 2;
                        ib = modifier;
                        if (modifier >= 0)
                            dexIn = String.format("%d(+%d)", val, modifier);
                        else
                            dexIn = String.format("%d(%d)", val, modifier);
                    } catch (Exception ex) {
                        System.err.println(ex);
                        errorFound = true;
                    }

                    try {
                        conSpin.commitEdit();
                        int val = (Integer) conSpin.getValue();
                        int modifier = (val - 10) / 2;
                        if (modifier >= 0)
                            conIn = String.format("%d(+%d)", val, modifier);
                        else
                            conIn = String.format("%d(%d)", val, modifier);
                    } catch (Exception ex) {
                        System.err.println(ex);
                        errorFound = true;
                    }

                    try {
                        intlSpin.commitEdit();
                        int val = (Integer) intlSpin.getValue();
                        int modifier = (val - 10) / 2;
                        if (modifier >= 0)
                            intlIn = String.format("%d(+%d)", val, modifier);
                        else
                            intlIn = String.format("%d(%d)", val, modifier);
                    } catch (Exception ex) {
                        System.err.println(ex);
                        errorFound = true;
                    }

                    try {
                        wisSpin.commitEdit();
                        int val = (Integer) wisSpin.getValue();
                        int modifier = (val - 10) / 2;
                        if (modifier >= 0)
                            wisIn = String.format("%d(+%d)", val, modifier);
                        else
                            wisIn = String.format("%d(%d)", val, modifier);
                    } catch (Exception ex) {
                        System.err.println(ex);
                        errorFound = true;
                    }

                    try {
                        chaSpin.commitEdit();
                        int val = (Integer) chaSpin.getValue();
                        int modifier = (val - 10) / 2;
                        if (modifier >= 0)
                            chaIn = String.format("%d(+%d)", val, modifier);
                        else
                            chaIn = String.format("%d(%d)", val, modifier);
                    } catch (Exception ex) {
                        System.err.println(ex);
                        errorFound = true;
                    }

                    Enemy enemy;
                    JLabel contents = new JLabel();

                    if (!errorFound) {
                        try {
                            sql.addEnemy(speed, stIn.getText(), skillsIn.getText(), vulIn.getText(), resIn.getText(),
                                    immunIn.getText(), langIn.getText(), sensesIn.getText(), strIn,
                                    dexIn, conIn, intlIn, wisIn,
                                    chaIn,
                                    acts, reacts, nameIn.getText(), hp, ac, ib);

                            enemy = new Enemy(nameIn.getText(), 0, ib, ac, hp, acts, reacts, stIn.getText(),
                                    skillsIn.getText(), vulIn.getText(), resIn.getText(), immunIn.getText(),
                                    sensesIn.getText(),
                                    langIn.getText(), speed, strIn, dexIn, conIn,
                                    intlIn, wisIn, chaIn);

                            contents.setText(enemy.toString());
                            pieces.add(enemy);
                        } catch (Exception ex) {
                            System.err.println(ex);
                        }
                    }

                    JOptionPane.showMessageDialog(null, contents, "message",
                            JOptionPane.INFORMATION_MESSAGE);

                }

            }
        });
    }

    public JButton getButton() {
        return makeChar;
    }
}