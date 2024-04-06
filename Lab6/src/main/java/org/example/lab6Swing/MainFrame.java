package org.example.lab6Swing;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class MainFrame extends JFrame implements Serializable {
    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas;

    public MainFrame() {
        super("My Game Application");
        launch();
    }
    //constructor pt serializare,pt nu a nu apela iar launch care imi instantiaza noi obiecte
    public MainFrame(DrawingPanel canvas, ConfigPanel configPanel, ControlPanel controlPanel) {
        super("My Game Application");
        this.canvas = canvas;
        this.configPanel = configPanel;
        this.controlPanel = controlPanel;
        LoadLaunch();

    }
    public void LoadLaunch(){
        //il inchidem daca dam close
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);
        setBackground(Color.lightGray);


        //adaugam configPanel ul
       // configPanel = new ConfigPanel(this);
        configPanel.setLayout(new FlowLayout());
        add(configPanel,BorderLayout.NORTH);
        //controlPanel = new ControlPanel(this);
        //desenam liniile
       // canvas = new DrawingPanel(this);
        add(canvas,BorderLayout.CENTER);

        //setam butoanele de jos
       // controlPanel = new ControlPanel(this);
        add(controlPanel,BorderLayout.SOUTH);

        // Apeleaza pack pentru a calcula dimensiunile componentelor
        pack();

        setVisible(true);
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

        // Apeleaza pack pentru a calcula dimensiunile componentelor
        pack();

        setVisible(true);
    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        //frame.launch();
    }

}
