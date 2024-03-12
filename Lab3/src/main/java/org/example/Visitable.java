package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
public interface Visitable {
    //Map<LocalDate,TimeI> getTimetable();
    //default LocalDate getOpeningHour(LocalDate date){}
    String getOpenDays();
    String getOpenHours();
}
