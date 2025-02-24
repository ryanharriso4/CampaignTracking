package com.spcdg;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.Flow;
import java.util.jar.JarEntry;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.awt.Dimension;

import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.xml.crypto.dsig.spec.HMACParameterSpec;

import java.lang.Integer;

public class display {

    static final String grey = "#E0E0E0";

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTabbedPane tp = new JTabbedPane();

        sqlConnect sql = new sqlConnect();
        ArrayList<Piece> pieces = new ArrayList<>();
        ArrayList<Encounter> encounters = new ArrayList<>();
        ArrayList<Location> locations = new ArrayList<>();

        sql.loadCharacters(pieces);
        sql.loadEncounters(pieces, encounters);
        sql.loadLocations(encounters, locations);

        JPanel addpanel = new JPanel();
        GridLayout aGrid = new GridLayout(0, 1, 0, 5);
        addpanel.setLayout(aGrid);

        AddCharacter acButton = new AddCharacter(sql, pieces);
        AddPC apcButton = new AddPC(sql, pieces);
        AddEncounter aeButton = new AddEncounter(sql, pieces, encounters);
        AddLocation alButton = new AddLocation(sql, encounters, locations);
        DeleteCharacter dcButton = new DeleteCharacter(sql, pieces);

        addpanel.add(acButton.getButton());
        addpanel.add(apcButton.getButton());
        addpanel.add(aeButton.getButton());
        addpanel.add(alButton.getButton());
        addpanel.add(dcButton.getButton());

        CardLayout battleLayout = new CardLayout();
        JPanel battlePanel = new JPanel();
        battlePanel.setLayout(battleLayout);

        LocationMenu lc = new LocationMenu(battleLayout, battlePanel);

        tp.addTab("Manage", null, addpanel, "This screen is for adding element");
        tp.addTab("Battle", null, battlePanel, "This screen is for combat");

        tp.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (tp.getSelectedIndex() == 1) {
                    lc.getPanel(locations);
                    battlePanel.updateUI();
                    battleLayout.show(battlePanel, "1");

                }
            }
        });
        frame.add(tp);

        frame.add(tp, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

    }
}
