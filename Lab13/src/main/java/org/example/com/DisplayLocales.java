package org.example.com;

import java.util.Locale;

public class DisplayLocales {
    public void execute() {
        Locale[] locales = Locale.getAvailableLocales();
        System.out.println("Available locales:");
        for (Locale locale : locales) {
            System.out.println(locale);
        }
    }
}
