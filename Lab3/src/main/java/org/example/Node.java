package org.example;

import java.time.LocalTime;
import java.util.Objects;

public class Node {
    private String nameOfAttraction;
    private String[] coloursDisponible;
    private String currentColor;
    private int number;
    private LocalTime startInterval;
    private LocalTime endInterval;
    private boolean isColored;
    public Node(String nameOfAttraction,String[] days, LocalTime startInterval, LocalTime endInterval,int number) {
        this.nameOfAttraction=nameOfAttraction;
        int index=0;
        if(days.length==1){
            currentColor=days[0];
            isColored=true;
        }
        this.coloursDisponible = days;
        this.startInterval = startInterval;
        this.endInterval = endInterval;
        this.number = number;
        isColored=false;
    }

    public void setCurrentColor(String color){
        int ok=0;
        for(String s: coloursDisponible){
            if(Objects.equals(s, color)){
                this.currentColor=color;
                isColored=true;
                ok=1;
                break;
            }
        }
        if(ok == 0){
            System.out.println("The color is indisponible");
        }

    }

    public String getNameOfAttraction() {
        return nameOfAttraction;
    }

    public int getNumber() {
        return number;
    }

    public String getCurrentColor() {
        return currentColor;
    }

    public LocalTime getStartInterval() {
        return startInterval;
    }

    public LocalTime getEndInterval() {
        return endInterval;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean getIsColored(){
        return this.isColored;
    }
    public String[] getColours() {
        return coloursDisponible;
    }


}
