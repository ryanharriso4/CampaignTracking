import java.io.*;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;

import java.lang.Integer;

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

        ArrayList<Piece> pieces = new ArrayList<>();

        JTabbedPane tp = new JTabbedPane();
        JPanel manageChar = new JPanel();
        JButton makeChar = new JButton("Add Character");
        makeChar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JLabel nameLabel = new JLabel("<HTML><br/> Character Name </HTML>");
                JTextField nameIn = new JTextField(30);
                JLabel ac = new JLabel("<HTML><br/> AC </HTML>");
                JTextField acIn = new JTextField(3);
                JLabel initativeB = new JLabel("<HTML><br/> AC </HTML>");
                JTextField ibIn = new JTextField(2);
                JLabel hp = new JLabel("<HTML><br/> HP </HTML>");
                JTextField hpIn = new JTextField(4);
                JLabel speed = new JLabel("<HTML><br/> Speed </HTML>");
                JTextField speedIn = new JTextField(3);
                JLabel actions = new JLabel("<HTML><br/> Actions </HTML>");
                JTextField actIn = new JTextField(30);
                JLabel reactions = new JLabel("<HTML><br/> Reactions </HTML>");
                JTextField reactIn = new JTextField(30);
                JLabel other = new JLabel("<HTML><br/> Other </HTML>");
                JTextField otherIn = new JTextField(6);

                JPanel stats = new JPanel(new FlowLayout());
                JPanel str = new JPanel(new FlowLayout());
                JLabel strL = new JLabel("<HTML> STR: <HTML>");
                JTextField strIn = new JTextField(5);
                JPanel dex = new JPanel(new FlowLayout());
                JLabel dexL = new JLabel("<HTML> DEX: <HTML>");
                JTextField dexIn = new JTextField(5);
                JPanel con = new JPanel(new FlowLayout());
                JLabel conL = new JLabel("<HTML> CON: <HTML>");
                JTextField conIn = new JTextField(5);
                JPanel intl = new JPanel(new FlowLayout());
                JLabel intlL = new JLabel("<HTML> INT: <HTML>");
                JTextField intlIn = new JTextField(5);
                JPanel wis = new JPanel(new FlowLayout());
                JLabel wisL = new JLabel("<HTML> WIS: <HTML>");
                JTextField wisIn = new JTextField(5);
                JPanel cha = new JPanel(new FlowLayout());
                JLabel chaL = new JLabel("<HTML> CHA: <HTML>");
                JTextField chaIn = new JTextField(5);
                str.add(strL);
                str.add(strIn);
                dex.add(dexL);
                dex.add(dexIn);
                con.add(conL);
                con.add(conIn);
                intl.add(intlL);
                intl.add(intlIn);
                wis.add(wisL);
                wis.add(wisIn);
                cha.add(chaL);
                cha.add(chaIn);
                stats.add(str);
                stats.add(dex);
                stats.add(con);
                stats.add(intl);
                stats.add(wis);
                stats.add(cha);

                Object[] objects = { nameLabel, nameIn, ac, acIn, hp, hpIn, speed, speedIn,
                        actions, actIn, reactions,
                        reactIn, other, otherIn, stats, initativeB, ibIn };
                int dialog = JOptionPane.showConfirmDialog(null, objects, "Add Character",
                        JOptionPane.YES_OPTION,
                        JOptionPane.PLAIN_MESSAGE);

                if (dialog == JOptionPane.YES_OPTION) {
                    Enemy enemy = new Enemy(nameIn.getText(), 0,
                            Integer.parseInt(ibIn.getText()),
                            Integer.parseInt(acIn.getText()),
                            Integer.parseInt(hpIn.getText()), actIn.getText(), otherIn.getText(),
                            speedIn.getText(),
                            reactIn.getText(), strIn.getText(), dexIn.getText(), conIn.getText(), intlIn.getText(),
                            wisIn.getText(), chaIn.getText());

                    JLabel label = new JLabel(enemy.toString());
                    JOptionPane.showMessageDialog(null, label, "message", JOptionPane.INFORMATION_MESSAGE);

                }

            }
        });
        manageChar.add(makeChar);

        tp.addTab("Tab 1", null, manageChar, "Does Nothing");

        frame.add(tp, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

    }
}
