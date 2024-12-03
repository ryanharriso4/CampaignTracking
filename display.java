import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.Flow;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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

                JLabel nameLabel = new JLabel("Character Name");
                JTextField nameIn = new JTextField(30);
                JLabel ac = new JLabel("AC");

                JTextField acIn = new JTextField(3);
                JLabel initativeB = new JLabel(" Intiative Bonus");

                JTextField ibIn = new JTextField(2);
                JLabel hp = new JLabel(" HP ");

                JTextField hpIn = new JTextField(4);
                JLabel speed = new JLabel(" Speed ");

                JTextField speedIn = new JTextField(3);

                ArrayList<String> acts = new ArrayList<>();
                JButton createAct = new JButton("Add Action");

                // reactPanel.add(reactNames);
                // reactPanel.add(createReact);

                // JLabel other = new JLabel("<HTML><br/> Other </HTML>");
                // JTextField otherIn = new JTextField(6);

                JLabel stLabel = new JLabel("Saving Throw");

                JTextField stIn = new JTextField(50);
                JLabel skills = new JLabel("Skills");

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
                            JLabel label = new JLabel(caName.getText());

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
                            JLabel label = new JLabel(raName.getText());
                            // reactNames.add(label);
                            // reactPanel.updateUI();
                        }
                    }
                });

                Object[] objects = { nameLabel, nameIn, ac, acIn, hp, hpIn, speed, speedIn,
                        stLabel, stIn, skills, skillsIn, vulLabel, vulIn, resLabel,
                        resIn, immunLabel,
                        immunIn, sensesLabel, sensesIn, langLabel, langIn, stats, initativeB, ibIn, createAct,
                        createReact };

                int dialog = JOptionPane.showConfirmDialog(null, objects, "Add Character",
                        JOptionPane.YES_OPTION,
                        JOptionPane.PLAIN_MESSAGE);

                if (dialog == JOptionPane.YES_OPTION) {
                    StringBuilder sb = new StringBuilder();
                    try {
                        Enemy enemy = new Enemy(nameIn.getText(), 0,
                                Integer.parseInt(ibIn.getText()),
                                Integer.parseInt(acIn.getText()),
                                Integer.parseInt(hpIn.getText()), acts, reacts, stIn.getText(), skillsIn.getText(),
                                vulIn.getText(), resIn.getText(), immunIn.getText(), sensesIn.getText(),
                                langIn.getText(),
                                speedIn.getText(),
                                strIn.getText(), dexIn.getText(), conIn.getText(), intlIn.getText(),
                                wisIn.getText(), chaIn.getText());

                        JLabel contents = new JLabel(enemy.toString());
                        JOptionPane.showMessageDialog(null, contents, "message", JOptionPane.INFORMATION_MESSAGE);
                    } catch (NumberFormatException exc) {
                        JLabel errorMsg = new JLabel("A non digit was inputed for hp, ac, or intiative bonus");
                        JOptionPane.showMessageDialog(null, errorMsg, "Error", JOptionPane.ERROR_MESSAGE);
                    }

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
