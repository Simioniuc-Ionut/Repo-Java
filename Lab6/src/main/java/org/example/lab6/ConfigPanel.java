package org.example.lab6;

import org.controlsfx.control.spreadsheet.Grid;
import org.controlsfx.control.spreadsheet.GridBase;

import javax.swing.*;
import java.awt.*;

public class ConfigPanel extends JPanel {
        Grid girdSize;
        JButton button;
        JMenuBar menuBar;
        public ConfigPanel(Grid gridSize){
            //creare grid
            this.girdSize = new GridBase(gridSize.getRowCount(),gridSize.getColumnCount());
                    //crearea de buton
                    this.button = new JButton("New Game");
                    this.button.setBackground(Color.orange);
                    //this.Button.setAction();
            //crearea menu bar
            this.menuBar = new JMenuBar();
            menuBar.setSize(700,80);

        }
}
