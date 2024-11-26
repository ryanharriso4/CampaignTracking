import java.io.*;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.*;

public class display {

    public static void main(String[] args) {

        Encounter e = new Encounter("sword", 100);
        e.addCharacter(new Piece("Ryan", 0, 16, 14));
        e.addCharacter(new Enemy("spectator", 2, 39, 14));
        e.calcIntiative();
        ArrayList<Piece> characters = e.getCharacters();

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        for (Piece p : characters) {
            JButton button = new JButton(p.getName());
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, p.toString(), null, JOptionPane.INFORMATION_MESSAGE);
                }
            });
            panel.add(button);
        }

        frame.add(panel);
        frame.setSize(500, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
