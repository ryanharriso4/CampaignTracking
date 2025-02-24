package com.spcdg;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class DeleteCharacter {
    private JButton deleteCharacter;
    static final String grey = "#E0E0E0";

    public DeleteCharacter(sqlConnect sql, ArrayList <Piece> pieces){
        deleteCharacter = new JButton("Delete Character");
        deleteCharacter.setBackground(Color.decode(grey));
        deleteCharacter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String [] pNames = new String[pieces.size()];
                for( int i = 0; i < pieces.size(); i++){
                    pNames[i] = pieces.get(i).getName();
                }



                JComboBox cb = new JComboBox<>(pNames);

                int dialog = JOptionPane.showConfirmDialog(null, cb, "Add PC", JOptionPane.YES_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
                
            
                if(dialog == JOptionPane.YES_OPTION){
                    try{
                        
                    }catch(Exception ex){

                    }
                }
            }
        });
    }

    JButton getButton(){
        return deleteCharacter;
    }

}
