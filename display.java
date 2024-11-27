import java.io.*;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.*;

public class display {

    public static void main(String[] args) {

        /*
         * Encounter e = new Encounter("sword", 100);
         * e.addCharacter(new Piece("Ryan", 0, 16, 14));
         * e.addCharacter(new Enemy("spectator", 2, 39, 14));
         * e.calcIntiative();
         * ArrayList<Piece> characters = e.getCharacters();
         * 
         * JFrame frame = new JFrame();
         * JPanel panel = new JPanel();
         * for (Piece p : characters) {
         * JButton button = new JButton(p.getName());
         * button.addActionListener(new ActionListener() {
         * public void actionPerformed(ActionEvent e) {
         * JOptionPane.showMessageDialog(null, p.toString(), null,
         * JOptionPane.INFORMATION_MESSAGE);
         * }
         * });
         * panel.add(button);
         * }
         * 
         * frame.add(panel);
         * frame.setSize(500, 600);
         * frame.setVisible(true);
         * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         */

        /* TRY USING JTabbed pain */

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tp = new JTabbedPane();
        JPanel manageChar = new JPanel();
        JButton makeChar = new JButton("Add Character");
        makeChar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JLabel nameLabel = new JLabel("<HTML><br/> Character Name </HTML>");
                JTextField nameIn = new JTextField(30);
                JLabel ac = new JLabel("<HTML><br/> AC </HTML>");
                JTextField acIn = new JTextField(30);
                JLabel hp = new JLabel("<HTML><br/> HP </HTML>");
                JTextField hpIn = new JTextField(30);
                Object[] objects = { nameLabel, nameIn, ac, acIn, hp, hpIn };
                int dialog = JOptionPane.showConfirmDialog(null, objects, "Add Character", JOptionPane.YES_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
            }
        });
        manageChar.add(makeChar);

        tp.addTab("Tab 1", null, manageChar, "Does Nothing");

        frame.add(tp, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

    }
}
