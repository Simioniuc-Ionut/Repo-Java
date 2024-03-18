package org.example;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;

public class  TravelPlan  {

       Map<String , LinkedList<Attraction>> travelPlan;
       ArrayList<String> allDays;
       ArrayList<Node> allNodes;
       public TravelPlan(Pair<LocalDate,LocalDate> periodOfTime) {
              //adaug perioada de trip.
              allDays = new ArrayList<>();
              for(LocalDate day = periodOfTime.getFirst(); !day.isAfter(periodOfTime.getSecond()); day=day.plusDays(1)){
                     allDays.add(String.valueOf(day.getDayOfWeek().getDisplayName(TextStyle.FULL,Locale.ENGLISH)));
              }
       }

       public void setTravelPlan(ArrayList<Attraction> attractionsCopy) {
              travelPlan = new HashMap<>();
              int numberOfAttractions=attractionsCopy.size();
              //System.out.println(numberOfAttractions+" number of attractions remains");
              /**
               * Sortam attractions, astfel incat primele atractii parcurse sunt cele cu cele mai putine zile opendays.
               */
              attractionsCopy.sort(Comparator.comparingInt(a -> a.getOpenDays().length));

                     for(Attraction notAlocatedAttraction : attractionsCopy){
                            nextAttraction:
                            for(int countPositionOfOpenDays =0; countPositionOfOpenDays<7; countPositionOfOpenDays++){
                                 //  for(String day : allDays){
                                          //verific daca exista zile aduagate pentru vizitare ,ca sa nu incerc sa accesez o zona de memorie nealocata
                                          if(countPositionOfOpenDays<notAlocatedAttraction.getOpenDays().length){
                                                 tryAnotherDay:
                                                 for(String day : allDays){
                                                        if(Objects.equals(notAlocatedAttraction.getOpenDays()[countPositionOfOpenDays], day)  ) {

                                                               if(travelPlan.get(day) == null ){
                                                                      //pt prima valoare, cand ziua e goala;
                                                                      //creez si lista

                                                                      travelPlan.put(day,new LinkedList<>());
                                                                      travelPlan.get(day).add(notAlocatedAttraction);//adaug atractia in lista
                                                                      numberOfAttractions--;
                                                                      break nextAttraction;
                                                               }else if(travelPlan.get(day)!= null){//am deja o atractie adaugata in acea zi
                                                                      //ma uit la intervalul orar.
                                                                      int index=0; //poztia elementelor existente din lista
                                                                      while(index < travelPlan.get(day).size()){
                                                                             //daca orarele se suprarupn (cat de putin) atunci nu iau in calcul.
                                                                            //ora deschidere din atractia pusa deja in lista >= ora de inchidere noua atractie
                                                                             if(travelPlan.get(day).get(index).getStartHour().getHour() >= notAlocatedAttraction.getEndHour().getHour() ) {
                                                                                    travelPlan.get(day).add(index, notAlocatedAttraction);//adaug atractia in lista
                                                                                    numberOfAttractions--;
                                                                                    break nextAttraction;
                                                                             }else if(travelPlan.get(day).get(index).getEndHour().getHour() <= notAlocatedAttraction.getStartHour().getHour() ) {
                                                                                    //ora de inchidere a atractiei puse deja in lista <= ora de deschidere a atractiei noi
                                                                                    //trebuie sa am grija ,in caz ca mai am elemente adaugate in lista,trebuie sa le parcurg si pe acelea
                                                                                    //pt a nu supraune
                                                                                    if (travelPlan.get(day).size() == 1){//am un singur element,deci po adauga dupa.
                                                                                           travelPlan.get(day).add(index + 1, notAlocatedAttraction);
                                                                                           numberOfAttractions--;
                                                                                           break nextAttraction;
                                                                                    }else { //am mai mult de 1 element in lista, trebuie sa mai parcurg
                                                                                    index ++;
                                                                                    }

                                                                             }else {
                                                                                    //se suprapune intervalul,incercam sa potrivim in alta zi
                                                                                    break tryAnotherDay;
                                                                             }

                                                                      }

                                                               }
                                                        }
                                          }
                                          //}
                            }
                     }
              }
              // System.out.println(numberOfAttractions+" number of attractions remains");
              if(numberOfAttractions!=0){
                     //au mai ramas atractii care nu se pot vizita in nici una din zile,deoarece se viziteaza in acea zi alte atractii
                     System.out.println("Not all attractions are assigned,in TravelPlan");
              }
       }

       public void printTravelPlan() {
              for(String day : allDays){
                     if(travelPlan.get(day) != null){
                            System.out.println(day + " - " + travelPlan.get(day));
                     }
              }
       }

       /**
        * acest algoritm coloreaza graful ,graful fiind trip ul.
        * culorile folosite sunt zilele saptamanii,iar nodurile sunt atractiile
        * nodurile colorate cu aceeasi culoare reprezinta atractiile care se pot vizita in aceeasi zi
        * nodurile sunt conectate cu muchii,
        * iar muchiile reprezinta intervalele orare dintre 2 noduri ,care sunt suprause = nu se pot vizita in aceeasi zi.
        * complexitate O(n^2)
        * @param attractions - un array cu toate atractiule
        */
       public void greedyColoring(ArrayList<Attraction> attractions){

               allNodes = new ArrayList<>();
              for(int i=0; i< attractions.size(); i++){
                     Node n = new Node(attractions.get(i).getName(),attractions.get(i).getOpenDays(),attractions.get(i).getStartHour(),attractions.get(i).getEndHour(),i);
                     allNodes.add(n);
              }
              //le am alocat o ordine ,in functie de numarul de culori posibile
              allNodes.sort(Comparator.comparingInt(a -> a.getColours().length));

              for(int i=0; i< attractions.size(); i++){
                     System.out.println("  " +allNodes.get(i).getNumber()+ " "+ allNodes.get(i).getNameOfAttraction());
              }
              int[][] matrixOfNodes= new int[allNodes.size()][allNodes.size()];

              //trebuie sa creez matricea unde muchiile reprezinta attractiile care sunt imposibil de vizitat in aceeasi zi.

              for(int i=0; i<allNodes.size(); i++) {
                     for(int j=0; j<allNodes.size(); j++) {
                            if(i != j && allNodes.get(i).getEndInterval().isAfter(allNodes.get(j).getStartInterval()) && allNodes.get(i).getStartInterval().isBefore(allNodes.get(j).getEndInterval())) {
                                   //Nodul i se termină după ce a început nodul j și nodul i începe înainte de a se termina nodul j = suprapunere, nu putem vizita în aceeași zi
                                   matrixOfNodes[i][j]=1;
                                   matrixOfNodes[j][i]=1;
                           }else {
                                   matrixOfNodes[i][j]=0;
                                   matrixOfNodes[j][i]=0;
                           }
                     }
              }

              System.out.println("Matrix of sorted Nodes" + Arrays.deepToString(matrixOfNodes));
              ArrayList<String> colorsUsed = new ArrayList<>();
              for(int i=0; i<allNodes.size(); i++) {
                     colorsUsed.clear();//resetam culorile
                     for(int j=0; j<allNodes.size(); j++) {
                            if(matrixOfNodes[i][j] == 1){//inseamna ca cele 2 noduri nu pot fi colorate cu aceeasi culoare
                                if(allNodes.get(j).getIsColored()){
                                       colorsUsed.add(allNodes.get(j).getCurrentColor());
                                }
                            }
                     }
                     for(String colors : allNodes.get(i).getColours()){
                            if(!colorsUsed.contains(colors)){
                                   allNodes.get(i).setCurrentColor(colors);
                             break;
                            }
                     }

              }


       }
       public void printGreedyColoring(){
              System.out.println("------Nodes------");
              int ok=0;
              for(String color : this.allDays){
                   ok=0;
                     for(Node n : allNodes){
                            if(Objects.equals(n.getCurrentColor(), color)){
                                   if(ok == 0){
                                          System.out.print(color+ " : ");
                                          ok=1;
                                   }
                                   System.out.println(n.getNumber()  + ":" + n.getNameOfAttraction());
                            }
                     }

              }
       }

}
