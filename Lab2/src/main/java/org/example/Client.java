package org.example;

import java.time.LocalTime;
import java.util.Objects;

enum ClientType{
    REGULAR, PREMIUM;
}
public class Client{
    private String name;
    private int minTime;
    private int maxTime;
    private ClientType type;

    public Client(String name,int minTime,int maxTime , ClientType type){
         this.name = name;
         this.minTime = minTime;
         this.maxTime = maxTime;
         this.type=type;
    }

    public String getName() {
        return name;
    }

    public int getMinTime() {
        return minTime;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public ClientType getType() {
        return type;
    }

    public void setMinTime(int minTime) {
        this.minTime = minTime;
    }

    public void setMaxTime(int maxTime) {
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
