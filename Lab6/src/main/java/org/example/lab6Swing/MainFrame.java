package org.example.lab6Swing;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas;

    public MainFrame() {
        super("My Game Application");
        launch();
    }

    public void launch(){
        //il inchidem daca dam close
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);
        setBackground(Color.lightGray);

        //adaugam configPanel ul
        configPanel = new ConfigPanel(this);
        configPanel.setLayout(new FlowLayout());
        add(configPanel,BorderLayout.NORTH);
        controlPanel = new ControlPanel(this);
        //desenam liniile
        canvas = new DrawingPanel(this);
        add(canvas,BorderLayout.CENTER);

        //setam butoanele de jos
        controlPanel = new ControlPanel(this);
        add(controlPanel,BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        //frame.launch();
    }

}
