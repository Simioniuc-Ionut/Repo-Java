package org.example;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Report extends Command{

    public Report(String path) throws InvalidExecuteException {
        super(path);
    }
    private  Repository repo;
    public void setRepo() throws InvalidRepositoryException {
        this.repo = new Repository(path);
        this.repo.findSubdirectoare();
    }
    @Override
    public void execute() throws InvalidExecuteException {
        //crearae unui configurator freemarker
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        try {
            cfg.setDirectoryForTemplateLoading(new File("C:/Users/Asus/Documents/Facultate/Anul2/Sem2/Java/Repo-Java/Lab5/src/main/resources"));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);
            cfg.setWrapUncheckedExceptions(true);

            // Crearea datelor model
            Map<String, Object> model = new HashMap<>();
            model.put("report", this);
            if (this.repo != null) {
                List<File> items = List.of(this.repo.getSubdirectoare());  // obțineți elementele aici
                //System.out.println("haha " + items);
                model.put("items", items);
            } else {
                throw new InvalidExecuteException(new Exception("Repo este null"));
            }

            // Încărcarea șablonului
            Template template = cfg.getTemplate("report_template.ftlh");
            if (template != null) {
                // Generarea raportului
                try (Writer out = new FileWriter("C:/Users/Asus/Documents/Facultate/Anul2/Sem2/Java/Repo-Java/Lab5/src/main/resources/report.html")) {
                    template.process(model, out);
                    //deschidem fisierul:
                    System.out.println("File opened");
                    Desktop.getDesktop().browse(new File("C:/Users/Asus/Documents/Facultate/Anul2/Sem2/Java/Repo-Java/Lab5/src/main/resources/report.html").toURI());

                } catch (TemplateException e) {
                    throw new RuntimeException(e);
                }
            } else {
                throw new InvalidExecuteException(new Exception("Șablonul este null"));
            }
        } catch ( IOException e) {
            throw new InvalidExecuteException(new Exception("Invalid execute report"));
        }


    /*
        * try (ceva comanda) {}: Aceasta este o construcție try-with-resources
        * introdusă în Java 7. În parantezele după try, puteți declara una sau mai multe
        * resurse care implementează interfața AutoCloseable.
        * Aceste resurse vor fi închise automat la sfârșitul blocului try,
        * indiferent dacă o excepție a fost aruncată sau nu. Acest lucru ajută la
        * prevenirea scurgerilor de resurse.*/
    }
}
