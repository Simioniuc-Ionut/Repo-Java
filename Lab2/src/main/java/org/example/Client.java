package org.example;

import java.time.LocalTime;
import java.util.Objects;

enum ClientType{
    REGULAR, PREMIUM
}

/**
 *  Clasa Client reprezinta clientul care are un anumit nume,type,si are programare intr un anumit interval de timp
 */
public class Client{
    private String name;
    private LocalTime minTime;
    private LocalTime maxTime;
    private final ClientType type;

    public Client(String name,LocalTime minTime,LocalTime maxTime , ClientType type){
         this.name = name;
         this.minTime = minTime;
         this.maxTime = maxTime;
         this.type=type;
    }

    public String getName() {
        return name;
    }

    public LocalTime getMinTime() {
        return minTime;
    }

    public LocalTime getMaxTime() {
        return maxTime;
    }

    public ClientType getType() {
        return type;
    }

    public void setMinTime(LocalTime minTime) {
        this.minTime = minTime;
    }

    public void setMaxTime(LocalTime maxTime) {
        this.maxTime = maxTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || (! (o instanceof  Client))) return false;
        Client client = (Client) o;
        return minTime == client.minTime && maxTime == client.maxTime && Objects.equals(name, client.name) && type == client.type;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                '}';
    }

}
