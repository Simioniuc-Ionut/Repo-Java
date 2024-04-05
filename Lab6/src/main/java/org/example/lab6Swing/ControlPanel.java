package org.example.lab6Swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    public ControlPanel(MainFrame frame){
        this.frame=frame;
        init();
    }
    private void init(){
        JButton loadButton = new JButton("Load");
        JButton saveButton = new JButton("Save");
        JButton exitButton = new JButton("Exit");

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        setBackground(Color.orange);
        add(loadButton);
        add(exitButton);
        add(saveButton);
    }
}
