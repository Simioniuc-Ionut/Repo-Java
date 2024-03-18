package org.example;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;

public class  TravelPlan  {

       Map<String , LinkedList<Attraction>> travelPlan;
       ArrayList<String> allDays;

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
      
}