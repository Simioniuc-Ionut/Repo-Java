package org.example.lab6Swing;

import org.controlsfx.control.spreadsheet.Grid;
import org.controlsfx.control.spreadsheet.GridBase;
import org.example.lab6Swing.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigPanel extends JPanel {
         final MainFrame frame;
        JLabel label;
        JSpinner rowsSpinner;
        JSpinner colsSpinner;

    public ConfigPanel(MainFrame frame){
        this.frame=frame;
        init();
    }
        public void init(){
            //creare grid
            this.label=new JLabel("Grid size:");
            setBackground(Color.orange);
             //Crearea spinner-ilor pentru rânduri și coloane
             rowsSpinner = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));
             colsSpinner = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));

            // Crearea butonului pentru a începe un joc nou
            JButton newGameButton = new JButton("New Game");
            newGameButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int rows = (Integer) rowsSpinner.getValue();
                    int cols = (Integer) colsSpinner.getValue();
                    frame.canvas.updateGrid(rows, cols);
                }
            });
            //newGameButton.addActionListener();
            add(label); //JPanel uses FlowLayout by default
            add(rowsSpinner);
            add(colsSpinner);
            add(newGameButton);

        }

}
