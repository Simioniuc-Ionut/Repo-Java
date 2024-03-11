package org.example;

import java.util.*;

import static java.util.Collection.*;

public class WheelGraph {


    /*
    vertex = n >=4
    edges  = 2(n-1)

    input n
    output matrix display

    nodul din mijloc -> n - 1 edges
    nodurile exterioare -> 3 edges

    */

    int N;
    int[][] matrix;
    private int CountCycles = 0;
    private int countElements = 0;
    
    private HashSet<Integer> visitedNodes ;
    private LinkedHashSet<String> visitedEdges;
    private HashSet<String> cyclesCountSet =new HashSet<>();


    WheelGraph(int Vertex) {
        if (Vertex <= 3) {
            this.N = 0;
            matrix = new int[Vertex][Vertex];

        } else {
            this.N = Vertex;
            matrix = new int[Vertex][Vertex];
        }
    }

    void setTheMatrixOfWhell() {
        int NrEdges = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N && NrEdges < 2; j++) {
                //nodul din mijloc
                if (i == 0) {
                    matrix[i][j] = 1;
                    matrix[j][i] = 1;
                } else {

                    matrix[i][j] = 1;
                    matrix[j][i] = 1;

                    if (i == 1) {
                        matrix[i][N - 1] = 1;
                        matrix[N - 1][i] = 1;
                    }
                    NrEdges = 2;

                }
            }
            NrEdges = 0;
        }
    }

    void getTheMatrixOfWhell() {

        if (N == 0) {
            System.out.print("Number of vertexs are incorect ");
        } else {
            System.out.print("   ");
            for (int j = 0; j < N; j++) {
                System.out.print(" " + j);
            }
            System.out.println();

            System.out.print("   ");
            for (int j = 0; j < N; j++) {
                System.out.print("- ");
            }
            System.out.println();

            for (int i = 0; i < N; i++) {
                System.out.print(i + " : ");
                for (int j = 0; j < N; j++) {
                    //nodul din mijloc
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }

        }
    }

    public void getCycles() {
        if (N == 0) {
            System.out.println("Numărul de vârfuri este incorect");
        } else {
            for (int i = 0; i < N; i++) {
                visitedNodes = new HashSet<Integer>();
                visitedEdges = new LinkedHashSet<String>();
                countElements = 0;
                DFSGraph(i, i, i);
                visitedEdges.clear();
                visitedNodes.clear();
            }
            if(CountCycles == (N*N - 3 * N + 3)) {
                System.out.println("Cicluri = " + CountCycles);
                System.out.println("Cycle added : " + cyclesCountSet);
            }else{
                System.out.println("NU s a gasit  " + CountCycles);
            }
        }
    }

    private void DFSGraph(int startVertex, int vertex,int previous) {
        visitedNodes.add(vertex);
        countElements++;

        for (int i = 0; i < N; i++) {
            if (matrix[vertex][i] == 1 &&  !visitedNodes.contains(i)){
                // Reprezentam intotdeauna muchia ca "nodul mai mic - nodul mai mare"
                String muchie = vertex < i ? String.valueOf(vertex) + "-" + String.valueOf(i) : String.valueOf(i) + "-" + String.valueOf(vertex);
                visitedEdges.add(muchie);

               // System.out.print(" " + i);
                DFSGraph(startVertex, i,vertex);
                visitedEdges.remove(muchie);
                visitedNodes.remove(i);
            }
        }

      // System.out.println();

        if (countElements > 2 && matrix[vertex][startVertex] == 1) {

            // Reprezentam intotdeauna muchia ca "nodul mai mic - nodul mai mare"
            String muchie = startVertex < vertex ? String.valueOf(startVertex) + "-" + String.valueOf(vertex) : String.valueOf(vertex) + "-" + String.valueOf(startVertex);
            visitedEdges.add(muchie);

            List<String> sortedEdges = new ArrayList<>(visitedEdges);
            Collections.sort (sortedEdges);
            String SortedCycle = String.valueOf(sortedEdges);
            if(!cyclesCountSet.contains(SortedCycle)){
                    cyclesCountSet.add(SortedCycle);
                    CountCycles++;
                }
            //System.out.println("Edges " + visitedEdges);
            //System.out.println("Nodes " + visitedNodes);


            visitedEdges.remove(muchie);
        }
        countElements--;

    }
}
