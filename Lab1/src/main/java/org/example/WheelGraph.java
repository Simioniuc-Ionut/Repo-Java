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
    int[][] Matrix;
    private int[] ArrayOfVisitedNodes;

    private int CountCycles = 0;
    private int CountElements = 0;
    HashSet<String> recordedCycles = new HashSet<>();
    private int[][] ArrayOfVisitedEdges;


    private HashSet<Integer> VisitedNodes ;
    private LinkedHashSet<String> VisitedEdges;
    private HashSet<String> CyclesCount =new HashSet<>();


    WheelGraph(int Vertex) {
        if (Vertex <= 3) {
            this.N = 0;
            Matrix = new int[Vertex][Vertex];

        } else {
            this.N = Vertex;
            Matrix = new int[Vertex][Vertex];
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
                VisitedNodes = new HashSet<Integer>();
                VisitedEdges = new LinkedHashSet<String>();
                CountElements = 0;
                DFSGraph(i, i, i);
                VisitedEdges.clear();
                VisitedNodes.clear();
            }
            if(CountCycles == (N*N - 3 * N + 3)) {
                System.out.println("Cicluri = " + CountCycles);
                System.out.println("Cycle added : " + CyclesCount);
            }else{
                System.out.println("NU s a gasit  " + CountCycles);
            }

        }
    }

    private void DFSGraph(int StartVertex, int vertex,int previous) {
        VisitedNodes.add(vertex);
        CountElements++;

        for (int i = 0; i < N; i++) {
            if (Matrix[vertex][i] == 1 &&  !VisitedNodes.contains(i)){
                // Reprezentăm întotdeauna muchia ca "nodul mai mic - nodul mai mare"
                String muchie = vertex < i ? String.valueOf(vertex) + "-" + String.valueOf(i) : String.valueOf(i) + "-" + String.valueOf(vertex);
                VisitedEdges.add(muchie);

               // System.out.print(" " + i);
                DFSGraph(StartVertex, i,vertex);
                // Reprezentăm întotdeauna muchia ca "nodul mai mic - nodul mai mare"
                VisitedEdges.remove(muchie);
                VisitedNodes.remove(i);
            }
        }

      // System.out.println();

        if (CountElements > 2 && Matrix[vertex][StartVertex] == 1) {

            // Reprezentăm întotdeauna muchia ca "nodul mai mic - nodul mai mare"
            String muchie = StartVertex < vertex ? String.valueOf(StartVertex) + "-" + String.valueOf(vertex) : String.valueOf(vertex) + "-" + String.valueOf(StartVertex);
            VisitedEdges.add(muchie);

            List<String> sortedEdges = new ArrayList<>(VisitedEdges);
            Collections.sort (sortedEdges);
            String SortedCycle = String.valueOf(sortedEdges);
            if(!CyclesCount.contains(SortedCycle)){
                    CyclesCount.add(SortedCycle);
                    CountCycles++;
                }
            //System.out.println("Edges " + VisitedEdges);
            //System.out.println("Nodes " + VisitedNodes);


            VisitedEdges.remove(muchie);
        }
        CountElements--;

    }
}
