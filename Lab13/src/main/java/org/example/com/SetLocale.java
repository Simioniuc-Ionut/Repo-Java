package org.example.com;

import java.util.Locale;
import java.util.ResourceBundle;

public class SetLocale {
    private Locale currentLocale;
    private ResourceBundle messages;

    public void execute(String languageTag) {
        Locale locale = Locale.forLanguageTag(languageTag);
        currentLocale = locale;
        messages = ResourceBundle.getBundle("res.Messages", currentLocale);
        System.out.println(String.format(messages.getString("locale.set"), locale.toLanguageTag()));
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public ResourceBundle getMessages() {
        return messages;
    }
}
