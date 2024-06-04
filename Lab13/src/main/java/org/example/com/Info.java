package org.example.com;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class Info {
    private Locale currentLocale;
    private ResourceBundle messages;

    public Info(Locale locale, ResourceBundle messages) {
        this.currentLocale = locale;
        this.messages = messages;
    }

    public void execute() {
        DateFormatSymbols symbols = new DateFormatSymbols(currentLocale);
        Currency currency;
        try {
            currency = Currency.getInstance(currentLocale);
        } catch (IllegalArgumentException e) {
            currency = null;  // Localizarea nu are o monedă asociată
        }
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL, currentLocale);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM d, yyyy", currentLocale);
        Date today = new Date();

        String weekdays = String.join(", ", symbols.getWeekdays()).trim();
        String months = String.join(", ", symbols.getMonths()).trim();

        System.out.println(String.format(messages.getString("country"), currentLocale.getDisplayCountry(), currentLocale.getDisplayCountry(currentLocale)));
        System.out.println(String.format(messages.getString("language"), currentLocale.getDisplayLanguage(), currentLocale.getDisplayLanguage(currentLocale)));

        if (currency != null) {
            System.out.println(String.format(messages.getString("currency"), currency.getCurrencyCode(), currency.getDisplayName()));
        } else {
            System.out.println(String.format(messages.getString("currency"), "N/A", "N/A"));
        }

        System.out.println(String.format(messages.getString("weekdays"), weekdays));
        System.out.println(String.format(messages.getString("months"), months));
        System.out.println(String.format(messages.getString("today"), dateFormat.format(today), simpleDateFormat.format(today)));
    }
}