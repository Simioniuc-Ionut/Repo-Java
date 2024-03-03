package org.example;

import java.util.Arrays;
import java.util.HashSet;

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
    int[][] Matrix;
    private int[] ArrayOfVisitedNodes;

    private int CountCycles = 0;
    private int CountElements = 0;
    HashSet<String> recordedCycles = new HashSet<>();
    private int[][] ArrayOfVisitedEdges;

    WheelGraph(int Vertex) {
        if (Vertex <= 3) {
            this.N = 0;
            Matrix = new int[Vertex][Vertex];
            ArrayOfVisitedNodes = new int[Vertex + 1];
            ArrayOfVisitedNodes = new int[Vertex + 1];

        } else {
            this.N = Vertex;
            Matrix = new int[Vertex][Vertex];
            ArrayOfVisitedNodes = new int[Vertex];
            ArrayOfVisitedEdges = new int[Vertex][Vertex];
        }
    }

    void SetTheMatrixOfWhell() {
        int NrEdges = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N && NrEdges < 2; j++) {
                //nodul din mijloc
                if (i == 0) {
                    Matrix[i][j] = 1;
                    Matrix[j][i] = 1;
                } else {

                    Matrix[i][j] = 1;
                    Matrix[j][i] = 1;

                    if (i == 1) {
                        Matrix[i][N - 1] = 1;
                        Matrix[N - 1][i] = 1;
                    }
                    NrEdges = 2;

                }
            }
            NrEdges = 0;
        }
    }

    void GetTheMatrixOfWhell() {

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
                    System.out.print(Matrix[i][j] + " ");
                }
                System.out.println();
            }

        }
    }

    public void GetCycles() {
        if (N == 0) {
            System.out.println("Numărul de vârfuri este incorect");
        } else {
            for (int i = 0; i < N; i++) {
                ArrayOfVisitedNodes = new int[N];
                ArrayOfVisitedEdges = new int[N][N];
                CountElements = 0;
                DFSGraph(i, i);
            }
            if(CountCycles == (N*N - 3 * N + 3)) {
                System.out.println("Cicluri = " + CountCycles);
            }else{
                System.out.println("NU s a gasit  " + CountCycles);
            }

        }
    }

    private void DFSGraph(int StartVertex, int vertex) {
        ArrayOfVisitedNodes[vertex] = 1; // Marchează nodul curent ca vizitat
        CountElements++;

        for (int i = 0; i < N; i++) {
            if (Matrix[vertex][i] == 1 && ArrayOfVisitedNodes[i] == 0) {
                ArrayOfVisitedEdges[vertex][i] = 1; // marcheaza muchia ca vizitata
                ArrayOfVisitedEdges[i][vertex] = 1;

               // System.out.print(" " + i);
                DFSGraph(StartVertex, i);
                ArrayOfVisitedEdges[vertex][StartVertex] = 0; // reseteaza muchia ca nevizitata
                ArrayOfVisitedEdges[StartVertex][vertex] = 0;
                ArrayOfVisitedNodes[vertex] = 0;


            }
        }
      // System.out.println();

        if (CountElements > 2 && Matrix[vertex][StartVertex] == 1) {
            // Ciclul nu a fost deja înregistrat

            String currentCycle = Arrays.deepToString(ArrayOfVisitedEdges); // Folosește muchiile vizitate pentru a identifica ciclul
            if (!recordedCycles.contains(currentCycle)) {
                recordedCycles.add(currentCycle);
                //System.out.println("Record Cycle : " + recordedCycles);

                CountCycles++;
            }
        }


        CountElements--;
    }
}