package org.example;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CSVDataImporter {
    public static void main(String[] args) {
        String csvFile = "C:\\Users\\Asus\\Documents\\Facultate\\Anul2\\Sem2\\Java\\Repo-Java_vechi\\Lab8\\src\\main\\resources\\bookCsv.csv";  // înlocuiți cu calea către fișierul dvs. CSV
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(csvFile));
            List<String[]> data = reader.readAll();
            // Acum aveți datele într-o listă de șiruri de caractere, puteți să le procesați după cum aveți nevoie

            Connection connection = ConnectionPool.getDataSource().getConnection();

            var books = new BookDAO();
            //SimpleDateFormat dateYearFormat = new SimpleDateFormat("yyyy");
            int id;
            int year;
            String language;
            int page;
            String title;
            List<String> authors;
            String[] authorsArray ;
            data.remove(0); //elimin antetul
            System.out.println(data.size() + " size ul");
            for (String[] row : data) {
                //TODO: procesez linie cu linie
               // id = Integer.parseInt(row[0]);
                title = row[1];
                language = row[6];
                page = Integer.parseInt(row[7]);
                String ceva = row[10];

                SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date = inputFormat.parse(ceva);  // converteste sirul de caractere in obiect Date
                SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy");
                String yearString = outputFormat.format(date);  // extrage anul ca sir de caractere

                 year = Integer.parseInt(yearString);  // converteste sirul de caractere
                authorsArray =  row[2].split("/");
                authors = new ArrayList<String>(Arrays.asList(authorsArray));

                books.create(year,language,page,title,authors,null);

            }
            //la final dau comit
            connection.commit();

            books.printAllBooks();

        } catch (ParseException | IOException | SQLException | CsvException e) {
            System.out.println(e.getMessage());
        }
         finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Error closing CSVReader");
                }
            }

        }
    }
}
