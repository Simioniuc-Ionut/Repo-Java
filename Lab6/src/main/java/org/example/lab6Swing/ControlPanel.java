package org.example.lab6Swing;

import javafx.scene.shape.Circle;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;

public class ControlPanel extends JPanel implements Serializable{
    final MainFrame frame;
    public ControlPanel(MainFrame frame){
        this.frame=frame;
        init();
    }
    private void init(){
        JButton loadButton = new JButton("Load");
        JButton saveButton = new JButton("Save");
        JButton exitButton = new JButton("Exit");
        JButton exportImage = new JButton("Export Image");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        //adaug functionalitatea pt save
        exportImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Creează o imagine a tablei de joc
                    BufferedImage image = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_RGB);
                    Graphics2D g = image.createGraphics();
                    frame.paint(g);
                    g.dispose();

                    // Salvează imaginea ca fișier PNG
                    ImageIO.write(image, "PNG", new File("C:\\Users\\Asus\\Documents\\Facultate\\Anul2\\Sem2\\Java\\Repo-Java_vechi\\Lab6\\src\\main\\resources\\org\\example\\lab6\\gameboard.png"));
                } catch (IOException ex) {
                    System.err.println("Eroare la salvarea imaginii: " + ex);
                }
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Salvează starea jocului
                try {
                    FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Asus\\Documents\\Facultate\\Anul2\\Sem2\\Java\\Repo-Java_vechi\\Lab6\\src\\main\\resources\\org\\example\\lab6\\gamestate.ser");
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    // Salvează starea jocului
                    out.writeObject(frame.canvas); // presupunem că 'game' este obiectul care reprezintă starea jocului
                    out.writeObject(frame.configPanel);
                    out.writeObject(frame.controlPanel);
                    SerializableCircle[][] serializableCircles = new SerializableCircle[frame.canvas.rows][frame.canvas.cols];
                    for (int i = 0; i < frame.canvas.rows; i++) {
                        for (int j = 0; j <frame.canvas.cols; j++) {
                            serializableCircles[i][j] = new SerializableCircle(frame.canvas.circles[i][j]);
                        }
                    }
                    out.writeObject(serializableCircles);
                  //  out.writeObject();
                    out.close();
                    fileOut.close();
                } catch (IOException i) {
                    System.err.println("Eroare la salvarea starii jocului: " + i);
                }
            }
        });
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Restaurează starea jocului
                try {
                    FileInputStream fileIn = new FileInputStream("C:\\Users\\Asus\\Documents\\Facultate\\Anul2\\Sem2\\Java\\Repo-Java_vechi\\Lab6\\src\\main\\resources\\org\\example\\lab6\\gamestate.ser");
                    /*ObjectInputStream in = new ObjectInputStream(fileIn);
                    frame.canvas = (DrawingPanel) in.readObject();
                    frame.configPanel = (ConfigPanel) in.readObject(); // presupunem că 'Game' este clasa care reprezintă starea jocului
                    frame.controlPanel=(ControlPanel) in.readObject();
                    in.close();
                    fileIn.close();
                   // frame.repaint();*/
                    ObjectInputStream in = new ObjectInputStream(fileIn);

                    DrawingPanel canvas = (DrawingPanel) in.readObject();
                    ConfigPanel configPanel = (ConfigPanel) in.readObject();
                    ControlPanel controlPanel = (ControlPanel) in.readObject();

                    SerializableCircle[][] loadedCircles = (SerializableCircle[][]) in.readObject();
                    Circle[][] newCircles = new Circle[canvas.rows][canvas.cols];
                    for (int i = 0; i < canvas.rows; i++) {
                        for (int j = 0; j < canvas.cols; j++) {
                            newCircles[i][j] = new Circle(loadedCircles[i][j].getCenterX(), loadedCircles[i][j].getCenterY(), loadedCircles[i][j].getRadius());
                        }
                    }
                    canvas.circles=newCircles;

                    in.close();
                    fileIn.close();
                    //doar updatez variabilele si starile importante,folosind metodele.
                    //dupa redesenez
                    frame.canvas.updateState(canvas);
                    frame.configPanel.updateState(configPanel); // presupunând că ai o metodă similară în ConfigPanel
                    frame.controlPanel.updateState(controlPanel); // presupunând că ai o metodă similară în ControlPanel
                    // Repicturizează fereastra pentru a reflecta noile stări
                    frame.repaint();
                } catch (IOException i) {
                    System.err.println("Eroare la reluarii starii jocului: " + i);
                } catch (ClassNotFoundException c) {
                    System.out.println("Clasa Game nu a fost găsită");
                }
            }
        });

        setBackground(Color.orange);
        add(loadButton);
        add(exitButton);
        add(saveButton);
        add(exportImage);
    }

    private void updateState(ControlPanel controlPanel) {

    }
}
