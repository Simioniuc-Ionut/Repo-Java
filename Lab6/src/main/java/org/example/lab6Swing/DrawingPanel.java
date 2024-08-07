package org.example.lab6Swing;

//import com.github.javafaker.Faker;

import javafx.scene.shape.Circle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DrawingPanel extends JPanel implements Serializable {
    final MainFrame mainFrame;
    int rows, cols;
    int canvasWidth, canvasHeight;
    int boardWidth, boardHeight;
    int cellWidth, cellHeight;
    int padX, padY;
    int stoneSize = 20;
    Integer[][] GridLinesPositions;
    transient Circle[][] circles; //nu pot serializa obiecte de tip Circel,asa ca le marchez ca transient pt a nu le serializa
    int lastPosCirclei;
    int lastPosCirclej;
    int playerTurn = -1;
    int countAllNodesValid;
    boolean gameOver= false;
    String winner;
    int  canvasHeightRecalculated;
    int  canvasWeidthRecalculated;

    //AI data members used
    int[][] aiGridLine;
    int value;
    int lastMoveFromActionI;
    int lastMoveFromActionJ;
    int countAllNodesValidFromActions;

    public DrawingPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        canvasHeightRecalculated=this.mainFrame.getHeight();
        canvasWeidthRecalculated=this.mainFrame.getWidth();
        System.out.println("Board width1: "+canvasWeidthRecalculated);
        System.out.println("Board height1: "+canvasHeightRecalculated);
        launch((Integer) mainFrame.configPanel.rowsSpinner.getValue(), (Integer) mainFrame.configPanel.colsSpinner.getValue(),this.mainFrame.getWidth(),this.mainFrame.getHeight());
    }

    public void launch(int rows, int cols ,int boardWidth,int boardHeight) {
        System.out.println("Board width: "+boardWidth);
        System.out.println("Board height: "+boardHeight);
        this.rows = rows;
        this.cols = cols;
        this.padX = stoneSize + 10;
        this.padY = stoneSize + 10;
        this.canvasWidth = boardWidth;
        this.canvasHeight = boardHeight;
        this.cellWidth = (canvasWidth - 2 * padX) / (cols - 1);
        this.cellHeight = (canvasHeight - 2 * padY) / (rows - 1);
        this.boardWidth = (cols - 1) * cellWidth;
        this.boardHeight = (rows - 1) * cellHeight;

        circles=new Circle[rows][cols];
        playerTurn=-1;
        countAllNodesValid=0;
        gameOver=false;

        this.randomLinesOfGridCreate();

        //functia 1 vs 1 Co op
          //eventStones();

        //functia 1 vs AI Robots
          aiPlay();

        setPreferredSize(new Dimension(canvasWidth, canvasHeight));
        repaint();
    }
    public void updateGrid(int rows, int cols) {
        //canvasWidth = gridSize.
        this.rows = rows;
        this.cols = cols;

        // Eliminați vechii ascultători de evenimente de mouse ,pt a nu avea buguri cand fac new game pt AI
        for (MouseListener ml : this.getMouseListeners()) {
            this.removeMouseListener(ml);
        }

        this.launch(rows, cols,this.canvasWeidthRecalculated,this.canvasHeightRecalculated);
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
                    if(GridLinesPositions[i][j]==0) {    //nodul 1
                        GridLinesPositions[i][j] = 1;
                        countAllNodesValid++;
                    }
                    GridLinesPositions[i][j + 1] = 1; //muchia |

                    if(GridLinesPositions[i][j+2]==0) {  //nodul 2
                        GridLinesPositions[i][j+2] = 1;
                        countAllNodesValid++;
                    }
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
                    if(GridLinesPositions[i][j] == 0){ //nodul 1
                        GridLinesPositions[i][j] = 1;
                        countAllNodesValid++;
                    }
                    GridLinesPositions[i + 1][j] = 1;//muchia |

                    if(GridLinesPositions[i+2][j] == 0){//nodul 2
                    GridLinesPositions[i + 2][j] = 1;
                    countAllNodesValid++;
                    }
                } else {
                    GridLinesPositions[i + 1][j] = 0;
                }
            }
        }

       //grila Ai
        setAiGridLine();
        //gameStatusProgress();
    }
    private void setAiGridLine(){
        aiGridLine = new int[rows][cols];
        System.out.println("---Ai grid---");
        for (int i = 0; i < 2 * rows - 1; i = i + 2) {
            for (int j = 0; j < 2 * cols - 1; j = j + 2) {
                if(GridLinesPositions[i][j] == 1){ // nod disponibil
                    aiGridLine[i/2][j/2] = 1;
                }
            }
        }
       printAiGridLines();
    }
    private void printAiGridLines(){
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                System.out.print(" " + aiGridLine[i][j]);
            }
            System.out.println();
        }
    }
    private void printGridLines(){
        System.out.println("------"+ playerTurn+"------");
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
        gameStatusProgress(); // am nevoie sa se apeleze la fiecare redesenare pe tabela

        paintGrid(g);
        paintRandomLines(g);//paintSticks(g);
        paintStones(g);     //paintStones(g);
        paintGameOver(g);

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
                // Creăm un nou obiect Circle dacă acesta nu există
                if (circles[row][col] == null) {
                    circles[row][col] = new Circle();
                }
                //setam coordonatele centrului fiecarui cerc;
                circles[row][col].setCenterX(x);
                circles[row][col].setCenterY(y);
                circles[row][col].setRadius(stoneSize);
                g.setColor(Color.LIGHT_GRAY);
                g.drawOval(x - stoneSize / 2, y - stoneSize / 2, stoneSize, stoneSize);
                //dam draw si la liniile groase
            }
        }

    }
    private void paintStones(Graphics2D g){
        for(int i=0; i<rows;i++) {
            for (int j = 0; j < cols; j++) {
                if (GridLinesPositions[2 * i][2 * j] == 2) {
                    double centerX = circles[i][j].getCenterX();
                    double centerY = circles[i][j].getCenterY();
                    g.setColor(Color.blue);
                    g.fillOval((int) centerX - stoneSize / 2, (int) centerY - stoneSize / 2, stoneSize, stoneSize);
                } else if (GridLinesPositions[2 * i][2 * j] == 3) {
                    double centerX = circles[i][j].getCenterX();
                    double centerY = circles[i][j].getCenterY();
                    g.setColor(Color.yellow);
                    g.fillOval((int) centerX - stoneSize / 2, (int) centerY - stoneSize / 2, stoneSize, stoneSize);
                }
            }
        }
    }
    private void gameStatusProgress(){
      //  System.out.println("ProgressStatus");
        if(countAllNodesValid == 0){
            gameOver=true;
            if(playerTurn == 1){
                winner = "Player Blue";
            }else {
                winner = "Player Yellow";
            }
        }else {

            if (playerTurn != -1) { //daca jocul inca nu a inceput,nu verificam
                boolean allNeighborsInvalid = true;
                for (int dx = -2; dx <= 2; dx += 2) {
                    for (int dy = -2; dy <= 2; dy += 2) {
                        int ni = lastPosCirclei + dx;
                        int nj = lastPosCirclej + dy;
                       // System.out.println("i " + lastPosCirclei + " j " +lastPosCirclej +" dx " + dx + " dy " + dy + " ni " + ni +  " nj " + nj);
                        // Verificăm dacă ni și nj sunt în interiorul matricei
                        if (ni >= 0 && ni < 2*rows - 1 && nj >= 0 && nj < 2*cols - 1) {
                            // Verificăm dacă nodul vecin este valid
                       //     System.out.println("| "+ GridLinesPositions[ni][nj] +" | " + ni + " i- " + nj + " -j ");
                            if (GridLinesPositions[ni][nj] == 1) {
                               // System.out.println("De");
                                allNeighborsInvalid = false;
                                break;
                            }
                        }
                    }
                    if (!allNeighborsInvalid) {
                        break;
                    }
                }
              //  System.out.println("o iter : allneighInvalid " + allNeighborsInvalid);
              //  printGridLines();
                if (allNeighborsInvalid) {
                    //System.out.println("AICI?" + playerTurn);
                    gameOver = true;
                    //mesajul de victorie
                    if (playerTurn == 1) {
                        winner = "Player Blue";
                    } else {
                        winner = "Player Yellow";
                    }
                }
            }
        }
    }

    //aceasta functie imi aplica modificarea de pe grila ai rowsXcols pe grila mare 2*rows-1X2*cols-1
    private void aiGridLineToGridLinesPositions(int i , int j , int PlayerColor){
        GridLinesPositions[2*i][2*j] = PlayerColor;
        lastPosCirclei=2*i;
        lastPosCirclej=2*j;
        countAllNodesValid--;
       // System.out.println("---- count nodes ---" + countAllNodesValid);
    }
    public void aiPlay() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // while (!gameOver) {
                if (!gameOver) {
                    //pt prima mutare
                    if (playerTurn == -1) {
                        playerTurn = 1; //las player blue sa face prima mutare;
                        //fac prima mutare
                        int i = 0;
                        int j = 0;
                        while (true) {
                            if (aiGridLine[i][j] == 1) { //daca nodul este valabil
                                aiGridLine[i][j] = 2;
                                aiGridLineToGridLinesPositions(i, j, 2);
                                break;
                            } else if (j < cols) {
                                j++;
                            } else {
                                i++;
                                j = 0;
                            }
                        }
                        //actualizam mutarea facuta
                        countAllNodesValidFromActions = countAllNodesValid;
                        lastMoveFromActionI = i;
                        lastMoveFromActionJ = j;

                        playerTurn = 0;

                        System.out.println(" First mutare player 1");
                        printAiGridLines();
                    }
                    else if (playerTurn == 0) { //player yellow(2)
                        // Aleg cea mai bună mutare folosind algoritmul MinMax
                        MoveAction bestMove = chooseBestMove();
                        // Fac mutarea
                        System.out.println("--- player 0 ,BEST move is --- " + bestMove.getI() + " " + bestMove.getJ());
                        makeMove(bestMove);

                        //ma asigur ca atunci cand jocul se termina sa nu schimb jucatorul
                        // Schimb rândul jucătorului
                        gameStatusProgress();
                        if(!gameOver){
                            playerTurn = 1;}

                    } else if (playerTurn == 1) { //player blue(3)
                        // Alege cea mai bună mutare folosind algoritmul MinMax
                        MoveAction bestMove = chooseBestMove();
                        System.out.println("--- player 1 ,BEST move is --- " + bestMove.getI() + " " + bestMove.getJ());
                        // Fac mutarea
                        makeMove(bestMove);

                        //ma asigur ca atunci cand jocul se termina sa nu schimb jucatorul
                        // Schimbă rândul jucătorului
                        gameStatusProgress();//updatez starea jocului,ca sa nu mi schimbe playerul iar
                        if(!gameOver){
                             playerTurn = 0;}
                    }
                    // Redeseneaz tabla de joc
                    repaint();
                }
            }
        });
    }
    private MoveAction chooseBestMove() {
        List<MoveAction> actions = getAvailableMove(aiGridLine);
        MoveAction bestMove = new MoveAction(-1,-1);
        System.out.println("COOSE BEST MOVE");
        int bestValue = (playerTurn == 1) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (MoveAction action : actions) {

            //copiez matircea
            int[][] copyOfAiGridLines = new int[aiGridLine.length][];
            for (int i = 0; i < aiGridLine.length; i++) {
                copyOfAiGridLines[i] = aiGridLine[i].clone();
            }
            int[][] newGridLines = Result(copyOfAiGridLines, action,playerTurn);

            int value = minMax(newGridLines,playerTurn);//pun celalat player,deoarece in result ul de mai sus deja s afacut o miscare pe grila

            if (playerTurn == 1 && value > bestValue) {
                bestValue = value;
                bestMove.setI(action.getI());
                bestMove.setJ(action.getJ());
               //daca gasesc o mutare buna pot face brake,nu ma mai intereseaza celelalte,insa s ar putea sa scap miscari mai bune
                //break;
            } else if (playerTurn == 0 && value < bestValue) {
                bestValue = value;
                bestMove.setI(action.getI());
                bestMove.setJ(action.getJ());
                //daca gasesc o mutare buna pot face brake,nu ma mai intereseaza celelalte,insa s ar putea sa scap miscari mai bune
                //break;
            }
        }

        return bestMove;
    }
    private void makeMove(MoveAction move) {
        int i = move.getI();
        int j = move.getJ();

        if(i == -1 && j == -1){
            System.out.println("Stop Game");
            gameOver=true;
        }else  if (playerTurn == 1) {
                    aiGridLine[i][j] = 2; // Codul pentru Player Blue
                    aiGridLineToGridLinesPositions(i,j,2); //updatez matricea mare,care este folosita pt desenare

                    //actualizam mutarea facuta
                    countAllNodesValidFromActions--;
                    lastMoveFromActionI=i;
                    lastMoveFromActionJ=j;
            System.out.println("---Maked move ---");
            printGridLines();

            gameStatusProgress(); //updatez starea jocului,ca sa nu mi schimbe playerul iar
            if(!gameOver){
                 playerTurn = 0;}

                } else {
                    aiGridLine[i][j] = 3;
                    aiGridLineToGridLinesPositions(i,j,3); //updatez matricea mare,care este folosita pt desenare

                    //actualizam mutarea facuta
                    countAllNodesValidFromActions--;
                    lastMoveFromActionI=i;
                    lastMoveFromActionJ=j;
            System.out.println("---Maked move ---");
            printGridLines();

            gameStatusProgress();//updatez starea jocului,ca sa nu mi schimbe playerul iar
                     if(!gameOver){
                          playerTurn = 1;}
        }
        repaint();
    }
    private int terminal(int[][] GridLines,int playerTurn){
        //daca starea jocului a ajuns sau nu in stare terminala
        if(countAllNodesValidFromActions == 0){
                if(playerTurn == 1){
                    return 1;
                }else {
                    return -1;
                }
            }else {
                if (playerTurn != -1) { //daca jocul inca nu a inceput,nu verificam
                    boolean allNeighborsInvalid = true;
                    for (int dx = 0; dx <= 2; dx ++) {
                        for (int dy = 0; dy <= 2; dy ++) {
                            int ni = lastMoveFromActionI + dx;
                            int nj = lastMoveFromActionJ + dy;
                           // System.out.println("i " + lastMoveFromActionI + " j " +lastMoveFromActionJ +" dx " + dx + " dy " + dy + " ni " + ni +  " nj " + nj);
                            // Verificăm dacă ni și nj sunt în interiorul matricei
                            if (ni >= 0 && ni <rows && nj >= 0 && nj < cols) {
                                // Verificăm dacă nodul vecin este valid
                                //System.out.println("| "+ GridLines[ni][nj] +" | " + ni + " i- " + nj + " -j ");
                                if (GridLines[ni][nj] == 1) {
                                  //  System.out.println("De");
                                    allNeighborsInvalid = false;
                                    break;
                                }
                            }
                        }
                        if (!allNeighborsInvalid) {
                            break;
                        }
                    }
                    //System.out.println("o iter : allneighInvalid  AI" + allNeighborsInvalid);
                    //printAiGridLines();

                    if (allNeighborsInvalid) {
                        if (playerTurn == 1) {
                           // winner = "Player Blue";
                            return 1;
                        } else {
                           // winner = "Player Yellow";
                            return -1;
                        }
                    }else{
                        return 0;
                    }
                }
            }
            return 0;
    }
    private int minMax(int[][] GridLines, int playerTurn){
        int scor = terminal(GridLines,playerTurn);
        if(scor == 1)
            return 1;
        else if(scor == -1)
            return -1;

        if (playerTurn == 1){ //player blue(2)
            value = -100;
            List<MoveAction> actions = getAvailableMove(GridLines);
            for(MoveAction action : actions){
                int lastOnePosi = lastMoveFromActionI;
                int lastOnePosj = lastMoveFromActionJ;
                value = Math.max(value,minMax(Result(GridLines,action,playerTurn),0));
                //practic dupa ce ma intorc din recursie, nodul redevine valid,iar pozitia curenta se modifica la iteratia curenta,in loc de cea din recursie
                countAllNodesValidFromActions++;
                lastMoveFromActionI =lastOnePosi;
                lastMoveFromActionJ=lastOnePosj;
            }
            return value;
        }
        if (playerTurn == 0){ //player yellow(3)
            value = 100;
            List<MoveAction> actions = getAvailableMove(GridLines);
            for(MoveAction action : actions){
                int lastOnePosi = lastMoveFromActionI;
                int lastOnePosj = lastMoveFromActionJ;
                value = Math.min(value,minMax(Result(GridLines,action,playerTurn),1));
                //practic dupa ce ma intorc din recursie, nodul redevine valid,iar pozitia curenta se modifica la iteratia curenta,in loc de cea din recursie
                countAllNodesValidFromActions++;
                lastMoveFromActionI =lastOnePosi;
                lastMoveFromActionJ=lastOnePosj;
            }
            return value;
        }
        return -200;
    }
    private int[][] Result(int[][] GridLines, MoveAction action,int playerTurn) {
        // Obținem coordonatele mutării din acțiune
        int i = action.getI();
        int j = action.getJ();

        // Colorăm nodul corespunzător în matricea newGridLines
        if (playerTurn == 1) {
            GridLines[i][j] = 3; // Codul pentru Player Blue
            playerTurn=0;
            lastMoveFromActionI = i;
            lastMoveFromActionJ = j;
            countAllNodesValidFromActions--;
        } else {
            GridLines[i][j] = 2; // Codul pentru Player Yellow
            playerTurn=1;
            lastMoveFromActionI = i;
            lastMoveFromActionJ = j;
            countAllNodesValidFromActions--;
        }


        // Returnăm noua stare a jocului/cream o copie pe care o returnam
        int[][] newCopyOfGridLines =new int[GridLines.length][];
        for (int k = 0; k < GridLines.length; k++) {
            newCopyOfGridLines[k] = GridLines[k].clone();
        }
        return newCopyOfGridLines;
    }
    private List<MoveAction> getAvailableMove(int[][] fromThisGridLines){
        List<MoveAction> availableMoves = new ArrayList<>();

        // Parcurgem matricea GridLinesPositions
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Verificam daca nodul este valid (adica daca nu a fost inca colorat)
                if(fromThisGridLines[i][j] == 1 ) {
                    if(((i - 1) == lastMoveFromActionI || (i + 1) == lastMoveFromActionI || i == lastMoveFromActionI) && ((j - 1) == lastMoveFromActionJ || (j + 1) == lastMoveFromActionJ || j == lastMoveFromActionJ))
                    {
                    // Daca nodul este valid, il adaugam in lista de mutari disponibile
                    availableMoves.add(new MoveAction(i, j));
                   }
                }
            }
        }

        return availableMoves;
    }

    private void eventStones(){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!gameOver) {
                    int x = e.getX();
                    int y = e.getY();
                    //verificam daca cercul a fost deja colorat
                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols; j++) {
                            //verificam daca cercul este valid
                            if (GridLinesPositions[2 * i][2 * j] == 1) {
                                double centerX = circles[i][j].getCenterX();
                                double centerY = circles[i][j].getCenterY();
                                double radius = circles[i][j].getRadius();
                                // Calculam distanta dintre centrul cercului si punctul de click
                                double distance = Math.sqrt(Math.pow(centerX - x, 2) + Math.pow(centerY - y, 2));
                                if (distance <= radius) {
                                    //ne aflam pe aria cercului
                                    if (playerTurn == -1) {
                                        playerTurn = 0;
                                        lastPosCirclei = 2*i;
                                        lastPosCirclej = 2*j;
                                    }
                                   // System.out.println("Intra aici ?");
                                    //aici merg pe muchii ,.daca exista /*&& ((i - 1) == lastPosCirclei || (i + 1) == lastPosCirclei || i == lastPosCirclei) && ((j - 1) == lastPosCirclej || (j + 1) == lastPosCirclej || j == lastPosCirclej)*/
                                    //daca se afla in jurul celui mai recent nod colorat
                                    if(((i - 1) == lastPosCirclei/2 || (i + 1) == lastPosCirclei/2 || i == lastPosCirclei/2) && ((j - 1) == lastPosCirclej/2 || (j + 1) == lastPosCirclej/2 || j == lastPosCirclej/2))
                                    {
                                        //System.out.println("in cadran ");
                                        if (playerTurn == 0) { //punem pt player 1 culoarea 2 in gridLines
                                            lastPosCirclei = 2 * i;
                                            lastPosCirclej = 2 * j;

                                            GridLinesPositions[2 * i][2 * j] = 2;
                                            countAllNodesValid--; //cand coloram un nod ,acesta devine invalid
                                            playerTurn = 1;
                                            System.out.println("player1: " + countAllNodesValid);

                                        } else if (playerTurn == 1) {
                                            lastPosCirclei = 2 * i;
                                            lastPosCirclej = 2 * j;

                                            playerTurn = 0;
                                            GridLinesPositions[2 * i][2 * j] = 3;
                                            countAllNodesValid--; //cand coloram un nod acesta devine invalid
                                            System.out.println("player2 :" + countAllNodesValid);

                                        }
                                        repaint();
                                    }

                                }
                            }
                        }

                    }
                }
            }
        });
    }
    private void paintGameOver(Graphics2D g){
        if(gameOver){
            System.out.println("plyer : " + playerTurn);
            if(playerTurn == 1) {
                g.setColor(Color.BLUE);
            }else{
                g.setColor(Color.ORANGE);
            }
            g.setFont(new Font("Arial", Font.BOLD, 30));
            FontMetrics fm = g.getFontMetrics();
            int stringWidth = fm.stringWidth(winner + " Won!");
            int stringHeight = fm.getAscent();
            g.drawString(winner + " Won!", (canvasWidth - stringWidth) / 2, (canvasHeight - stringHeight) / 2);
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
    public void updateState(DrawingPanel newPanel) {
        // Actualizează starea necesară
        this.rows = newPanel.rows;
        this.cols = newPanel.cols;
        this.canvasWidth = newPanel.canvasWidth;
        this.canvasHeight = newPanel.canvasHeight;
        this.boardWidth = newPanel.boardWidth;
        this.boardHeight = newPanel.boardHeight;
        this.cellWidth = newPanel.cellWidth;
        this.cellHeight = newPanel.cellHeight;
        this.padX = newPanel.padX;
        this.padY = newPanel.padY;
        this.stoneSize = newPanel.stoneSize;
        this.GridLinesPositions = newPanel.GridLinesPositions;
        this.circles = newPanel.circles;
        this.lastPosCirclei = newPanel.lastPosCirclei;
        this.lastPosCirclej = newPanel.lastPosCirclej;
        this.playerTurn = newPanel.playerTurn;
        this.countAllNodesValid = newPanel.countAllNodesValid;
        this.gameOver = newPanel.gameOver;
        this.winner = newPanel.winner;
        this.canvasHeightRecalculated = newPanel.canvasHeightRecalculated;
        this.canvasWeidthRecalculated = newPanel.canvasWeidthRecalculated;
    }
}

