package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public interface Visitable {
    Map<String,TimeInterval> getTimetable();
    default LocalTime getOpeningHour(String date){

            Map<String,TimeInterval> timetable = getTimetable();

            if(timetable.get(date) == null){
                System.out.println("Error ,date is incorcet");
                return LocalTime.of(0,0);
            }

            return timetable.get(date).getFirst();
    }

}
