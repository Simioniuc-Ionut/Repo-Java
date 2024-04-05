package org.example.lab6Swing;

//import com.github.javafaker.Faker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Line2D;
import java.io.Console;

public class DrawingPanel extends JPanel {
    final MainFrame mainFrame;
    int rows, cols;
    int canvasWidth, canvasHeight;
    int boardWidth, boardHeight;
    int cellWidth, cellHeight;
    int padX, padY;
    int stoneSize = 20;
    Integer[][] GridLinesPositions;

    public DrawingPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        launch((Integer) mainFrame.configPanel.rowsSpinner.getValue(), (Integer) mainFrame.configPanel.colsSpinner.getValue());
    }

    public void launch(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.padX = stoneSize + 10;
        this.padY = stoneSize + 10;
        this.canvasWidth = mainFrame.getWidth();
        this.canvasHeight = mainFrame.getHeight() - 100;
        this.cellWidth = (canvasWidth - 2 * padX) / (cols - 1);
        this.cellHeight = (canvasHeight - 2 * padY) / (rows - 1);
        this.boardWidth = (cols - 1) * cellWidth;
        this.boardHeight = (rows - 1) * cellHeight;

        this.randomLinesOfGridCreate();
        setPreferredSize(new Dimension(canvasWidth, canvasHeight));
        repaint();
    }

    public void updateGrid(int rows, int cols) {
        //canvasWidth = gridSize.
        this.rows = rows;
        this.cols = cols;

        this.launch(rows, cols);
        this.repaint();
    }

    public void randomLinesOfGridCreate() {
        GridLinesPositions = new Integer[2 * rows - 1][2 * cols - 1];
        for (int i = 0; i < 2 * rows - 1; i++) {
            for (int j = 0; j < 2 * cols - 1; j++) {
                GridLinesPositions[i][j] = 0;
            }
        }
        double randomLine;
        //random lines at horizontal
        for (int i = 0; i < 2 * rows - 1; i = i + 2) {
            for (int j = 0; j < 2 * cols - 2; j = j + 2) {
                //GridLinesPositions[i][j]=1;//punem 1 pe node
                randomLine = Math.random();//50%
                if (randomLine >= 0.5) {
                    //punem o linie speciala pe orizontala
                    GridLinesPositions[i][j] = 1;   //nodul  1
                    GridLinesPositions[i][j + 1] = 1; //muchia |
                    GridLinesPositions[i][j + 2] = 1; //nodul  1
                } else {
                    GridLinesPositions[i][j + 1] = 0;
                }
            }
        }
        //punme pe verticala
        for (int i = 0; i < 2 * rows - 2; i = i + 2) {
            for (int j = 0; j < 2 * cols - 1; j = j + 2) {
                //  randomLine=faker.number().numberBetween(0,1); //50%
                randomLine = Math.random();
                if (randomLine >= 0.5) {
                    //punem o linie speciala pe orizontala
                    GridLinesPositions[i][j] = 1;
                    GridLinesPositions[i + 1][j] = 1;
                    GridLinesPositions[i + 2][j] = 1;
                } else {
                    GridLinesPositions[i + 1][j] = 0;
                }
            }
        }
        System.out.println("------------");
        for (int i = 0; i < 2 * rows - 1; i++) {
            for (int j = 0; j < 2 * cols - 1; j++) {
                System.out.print(GridLinesPositions[i][j] + " ");
            }
            System.out.println();
        }

    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, canvasWidth, canvasHeight);
        paintGrid(g);
        paintRandomLines(g);
        //paintSticks(g);
        //paintStones(g);
    }
    private void paintGrid(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
        //horizontal lines
        //cream o matrice pt a stii coordonatele liniilor
        int x1;
        int y1;
        int x2;
        int y2;
        for (int row = 0; row < rows; row++) {

            x1 = padX;
            y1 = padY + row * cellHeight;
            x2 = padX + boardWidth;
            y2 = y1;
            g.drawLine(x1, y1, x2, y2);

        }
        //vertical lines TODO
        for (int col = 0; col < cols; col++) {
            x1 = padX + col * cellWidth;
            y1 = padY;
            x2 = x1;
            y2 = padY + boardHeight;
            g.drawLine(x1, y1, x2, y2);
        }
        //intersections
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = padX + col * cellWidth;
                int y = padY + row * cellHeight;
                g.setColor(Color.LIGHT_GRAY);
                g.drawOval(x - stoneSize / 2, y - stoneSize / 2, stoneSize, stoneSize);
                //dam draw si la liniile groase
            }
        }

    }
    private void paintRandomLines(Graphics2D g) {
            //Thinness lines Horizontal
            for (int i = 0; i <rows; i++) {
                for (int j = 0; j < cols - 1; j++) {
                    if (GridLinesPositions[2*i][2*j+1] == 1) {//mergem pe valorile dintre coloane
                        //desenam liniile
                        int X1 =  padX + j * cellWidth;
                        int Y = padY + i * cellHeight;
                        int X2= padX + (j+1) * cellWidth;
                        g.setStroke(new BasicStroke(3));
                        g.setColor(Color.red);
                        g.draw(new Line2D.Float(X1, Y, X2, Y));
                    }
                }
            }
            //Thinness lines Vertical
            for (int i = 0; i < rows - 1; i++) {
                for (int j = 0; j <cols; j++) {
                    if (GridLinesPositions[2*i+1][2*j] == 1) {
                        //desenam liniile
                        int Y1 = padY + i * cellHeight;
                        int X = padX + j * cellWidth;
                        int Y2 = padX + (i + 1) * cellHeight;
                        g.setStroke(new BasicStroke(3));
                        g.setColor(Color.red);
                        g.draw(new Line2D.Float(X, Y1, X, Y2));
                    }
                }
            }


        }
}

