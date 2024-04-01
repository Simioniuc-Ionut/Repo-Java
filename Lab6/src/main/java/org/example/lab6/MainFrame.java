package org.example.lab6;

import org.controlsfx.control.spreadsheet.Grid;
import org.controlsfx.control.spreadsheet.GridBase;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    ConfigPanel configPanel;

  //  public MainFrame(){
        //this.configPanel = new ConfigPanel();
   // }

    public void launch(){
        //cream frame ul
        JFrame frame = new JFrame("It's a game");
        JPanel panel = new JPanel(new GridLayout(10,10));
         for (int i = 0; i < 9; i++) {
            panel.add(new JButton("Buton " + (i + 1))); // Adaugă un buton în fiecare celulă a grilei
        }

        //il inchidem daca dam close
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(800,600);
        frame.setBackground(Color.lightGray);
        panel.setSize(800,600);
        panel.setBackground(Color.black);
        frame.add(panel);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.launch();
    }
}
