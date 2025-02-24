package com.spcdg;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CharacterMenu {

    private JPanel battlePanel;
    private CardLayout battleLayout;
    static final String grey = "#E0E0E0";

    public CharacterMenu(CardLayout battleLayout, JPanel battlePanel) {

        this.battlePanel = battlePanel;
        this.battleLayout = battleLayout;
    }

    public void getPanel(Encounter encounter) {

        JPanel characterPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(characterPanel);
        battlePanel.add(scrollPane, "3");
        characterPanel.setLayout(new BorderLayout());

        ArrayList<Piece> charListCopy = new ArrayList();
        for (Piece p : encounter.getCharacters()) {
            if (p instanceof Enemy)
                charListCopy.add(new Enemy((Enemy) p));
            else
                charListCopy.add(new Piece(p));
        }

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(2, 1, 0, 5));

        JPanel charList = new JPanel();
        // GridLayout charLayout = new GridLayout(0, 1, 0, 5);
        // charList.setLayout(charLayout);
        charList.setLayout(new BoxLayout(charList, BoxLayout.Y_AXIS));

        JButton goBack = new JButton("Go back to encounters");
        goBack.setBackground(Color.decode(grey));
        goBack.setBorder(new LineBorder(Color.BLACK));
        goBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                battleLayout.show(battlePanel, "2");
            }
        });
        northPanel.add(goBack);

        JButton sortCharacters = new JButton("Get combat order");
        sortCharacters.setBackground(Color.decode(grey));
        sortCharacters.setBorder(new LineBorder(Color.BLACK));
        sortCharacters.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                charListCopy.sort(null);
                loadCharacters(charList, charListCopy);
                charList.updateUI();
                // battlePanel.updateUI();
            }
        });
        northPanel.add(sortCharacters);

        for (Piece p : charListCopy) {
            p.rollIntiative();
        }

        characterPanel.add(northPanel, BorderLayout.NORTH);
        characterPanel.add(charList, BorderLayout.CENTER);
        loadCharacters(charList, charListCopy);

    }

    private void loadCharacters(JPanel charPanel, ArrayList<Piece> charList) {

        for (Component c : charPanel.getComponents()) {
            charPanel.remove(c);
        }

        for (Piece p : charList) {
            JPanel charContainer = new JPanel(new BorderLayout());
            JPanel stats = new JPanel();

            JButton viewInfo = new JButton("View Character Info");
            viewInfo.setBackground(Color.decode(grey));
            viewInfo.setBorder(new LineBorder(Color.BLACK));
            viewInfo.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JLabel contents = new JLabel("" + p.toString());
                    JOptionPane.showMessageDialog(null, contents, "message", JOptionPane.INFORMATION_MESSAGE);
                }
            });

            charContainer.add(viewInfo, BorderLayout.CENTER);
            // BoxLayout bl = new BoxLayout(stats, BoxLayout.X_AXIS);
            stats.setLayout(new GridLayout(0, 4));
            JLabel nameLabel = new JLabel(p.getName());
            nameLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
            JLabel hpLabel = new JLabel("HP: " + p.getHP());
            hpLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
            JLabel acLabel = new JLabel("Armor Class: " + p.getAC());
            acLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
            JLabel intLabel = new JLabel("Intiative: " + p.getIntiative());
            intLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));

            stats.add(nameLabel);
            stats.add(hpLabel);
            stats.add(acLabel);
            stats.add(intLabel);
            charContainer.add(stats, BorderLayout.NORTH);

            JPanel damagePanel = new JPanel(new FlowLayout());
            JSpinner damageSpinner = new JSpinner();
            ((JSpinner.DefaultEditor) damageSpinner.getEditor()).getTextField().setColumns(2);
            JButton submitDamage = new JButton("Take Damage:");
            submitDamage.setBackground(Color.decode(grey));
            submitDamage.setBorder(new LineBorder(Color.BLACK));
            submitDamage.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int damageTaken;
                    try {
                        damageSpinner.commitEdit();
                        damageTaken = (Integer) damageSpinner.getValue();
                        int hp = Integer.parseInt(hpLabel.getText().substring(4));
                        hpLabel.setText("HP: " + (hp - damageTaken));
                    } catch (Exception ex) {
                        System.err.println(ex);
                    }

                }
            });

            damagePanel.add(submitDamage);
            damagePanel.add(damageSpinner);

            JPanel intiativePanel = new JPanel(new FlowLayout());
            JSpinner intiativeSpinner = new JSpinner();
            ((JSpinner.DefaultEditor) intiativeSpinner.getEditor()).getTextField().setColumns(2);
            JButton submitIntiative = new JButton("New Intiative:");
            submitIntiative.setBackground(Color.decode(grey));
            submitIntiative.setBorder(new LineBorder(Color.BLACK));
            submitIntiative.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        intiativeSpinner.commitEdit();
                        intLabel.setText("Initative: " + intiativeSpinner.getValue());
                        p.setInitiative((Integer) intiativeSpinner.getValue());
                    } catch (Exception ex) {
                        System.err.println(ex);
                    }
                }
            });

            intiativePanel.add(submitIntiative);
            intiativePanel.add(intiativeSpinner);

            JPanel EastPanel = new JPanel();
            EastPanel.setLayout(new BoxLayout(EastPanel, BoxLayout.Y_AXIS));
            EastPanel.add(damagePanel);
            EastPanel.add(intiativePanel);
            charContainer.add(EastPanel, BorderLayout.EAST);

            JPanel SouthPanel = new JPanel();
            SouthPanel.setLayout(new BoxLayout(SouthPanel, BoxLayout.Y_AXIS));
            JLabel additionInfoLabel = new JLabel("Additional Info:");
            JTextArea additionalInfo = new JTextArea(1, 1);
            SouthPanel.add(additionInfoLabel);
            SouthPanel.add(additionalInfo);
            charContainer.add(SouthPanel, BorderLayout.SOUTH);

            charPanel.add(charContainer);

        }
    }

}
