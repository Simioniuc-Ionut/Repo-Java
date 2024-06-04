package org.example.app;

import org.example.com.DisplayLocales;
import org.example.com.Info;
import org.example.com.SetLocale;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LocaleExplore {
    private static ResourceBundle messages;
    private static Locale currentLocale;
    private static SetLocale setLocale;

    public static void main(String[] args) {
        setLocale = new SetLocale();
        setLocale.execute(Locale.getDefault().toLanguageTag());
        currentLocale = setLocale.getCurrentLocale();
        messages = setLocale.getMessages();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(messages.getString("prompt"));
            String command = scanner.nextLine();

            if (command.equals("locales")) {
                DisplayLocales displayLocales = new DisplayLocales();
                displayLocales.execute();
            } else if (command.startsWith("set locale ")) {
                String[] parts = command.split(" ");
                if (parts.length == 3) {
                    setLocale.execute(parts[2]);
                    currentLocale = setLocale.getCurrentLocale();
                    messages = setLocale.getMessages();
                } else {
                    System.out.println(messages.getString("invalid"));
                }
            } else if (command.equals("info")) {
                Info info = new Info(currentLocale, messages);
                info.execute();
            } else {
                System.out.println(messages.getString("invalid"));
            }
        }
    }
}