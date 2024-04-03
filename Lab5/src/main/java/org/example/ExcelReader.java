package org.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.github.javafaker.Faker;

import javax.print.DocFlavor;
import java.io.*;
import java.util.*;

public class ExcelReader extends  Command{

    public ExcelReader(String path) {
        super(path);
    }
    Map<Person, List<String>> abilities = new HashMap<>();
    List<Set<Person>> maximalGroups  = new ArrayList<>();

    //non trivial solution
    Map<Person, List<String>> allPersonWithAbilities = new HashMap<>();

    @Override
    public void execute() throws IOException {

        try {
            FileInputStream fis = new FileInputStream(new File("C:/Users/Asus/Documents/Facultate/Anul2/Sem2/Java/Repo-Java_vechi/Lab5/src/main/resources/abilities.xlsx"));

            //create workbook instance
            Workbook workbook = new XSSFWorkbook(fis);

            //get the first sheet
            Sheet sheet = workbook.getSheetAt(0);

            //iterate through each rows
            for (Row row : sheet) {
                //For each row, iterate through each columns
               //System.out.println("Iterez printre linii");
                String id = "";
                String name="";
                List<String> personAbilites=new ArrayList<>();

                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    //System.out.println("column");
                    if(!Objects.equals(cell.getStringCellValue(), "ID Aangajat") && !Objects.equals(cell.getStringCellValue(),"Nume Angajat") && !Objects.equals(cell.getStringCellValue(),"Abilitati"))
                    {
                        //evitam antetul
                        if(id.isEmpty()){
                            id=cell.getStringCellValue();
                        }else if(name.isEmpty()){
                            name=cell.getStringCellValue();
                        }else {//altfel ,abilitatile sunt adaugate in ultimele coloane
                            personAbilites.add(cell.getStringCellValue());
                        }

                    }
                   // System.out.print(cell.getStringCellValue() + "\t");
                }
                if (!id.isEmpty() && !name.isEmpty()) {
                    Person person = new Person(Integer.parseInt(id),name);
                    abilities.put(person,personAbilites);
                }
                //System.out.println("");
            }
        } catch (IOException e) {
            throw new IOException(new Exception("Excel read error "));
        }
    }
    public void createNonTrivialRepository(){
        Faker faker = new Faker();

        Workbook nonTrivialWorkbook = new XSSFWorkbook(); //creez un workbook
        Sheet sheet = nonTrivialWorkbook.createSheet("Non Trivial"); //creez o foaie

        //adaug date in foaie
        for(int id =0 ; id<1000; id++){
            Row row = sheet.createRow(id);

            String fakeName =faker.funnyName().name();
            //adaug id ul unic
            Cell cell = row.createCell(0);
            cell.setCellValue(id);
            //adaug numele generat random
            Cell cell2 = row.createCell(1);
            cell2.setCellValue(fakeName);

            //creez o persoana fake
            Person randomPerson = new Person(id,fakeName);

            //luam abilitati aleatorii:
            //List<String> curentAbilitiesOfRandomPerson = new ArrayList<>();
            List<String> allAbilites = new ArrayList<>();
            allAbilites.add("Programare");
            allAbilites.add("Design Grafic");
            allAbilites.add("Managemente de Proiect");
            allAbilites.add("Ilustratie");
            allAbilites.add("Vanzari");
            allAbilites.add("Resurse Umane");
            allAbilites.add("Marketing");
            allAbilites.add("Serviciu Clietni");
            int currentNumberOfElements=7;
            int randomNumberOfAbilities = faker.number().numberBetween(1,8);
            List<String> allRandomAbilities = new ArrayList<>();
            for(int index = 1 ; index <=randomNumberOfAbilities; index++){
                int indexRandomPos = faker.random().nextInt(0,currentNumberOfElements);
                String randomAbility = allAbilites.get(indexRandomPos);
                //curentAbilitiesOfRandomPerson.add(randomAbility);
                //adaugam abilitatea in celula
                Cell cellAbility = row.createCell((1+index));
                cellAbility.setCellValue(randomAbility);
                allRandomAbilities.add(randomAbility);

                //scoatem din personAbilities abilitatea deja adaugata,pt a nu adauga aceeasi abilitate
                allAbilites.remove(indexRandomPos);
                currentNumberOfElements--;
            }
            allPersonWithAbilities.put(randomPerson,allRandomAbilities);
        }

        //scriu workbook ul intr un fisier
        // scrie workbook-ul într-un fișier
        try (FileOutputStream outputStream = new FileOutputStream("C:/Users/Asus/Documents/Facultate/Anul2/Sem2/Java/Repo-Java_vechi/Lab5/src/main/resources/NonTrivialRepository.xlsx")) {
            nonTrivialWorkbook.write(outputStream);
        } catch (IOException e) {
            System.out.println("Error at nontrivialRepository");
        }
    }
    public void createGroups(){
        //Create an invers map ,where i put for each ability a list of person that have the ability
        Map<String,List<Person>> abilitiesToPerson = new HashMap<>();
        for(Map.Entry<Person,List<String>> entry : allPersonWithAbilities.entrySet()){
            Person person = entry.getKey();
            List<String> personAbilities = entry.getValue();

            for(String ability : personAbilities){
                if(!abilitiesToPerson.containsKey(ability)){
                    abilitiesToPerson.put(ability,new ArrayList<>());
                }
                abilitiesToPerson.get(ability).add(person);
            }
        }

        //Group create
        //Use set for avoid duplicates
        List<Set<Person>> groups = new ArrayList<>();
        for(List<Person> eachPerson : abilitiesToPerson.values()){
            groups.add(new HashSet<>(eachPerson));
        }

        //Verify if exist gorups that contains another groups => is not maximal
        //List<Set<Person>> maximalGroups  = new ArrayList<>();
        for(Set<Person> group : groups){
            Boolean isMaximal= true;
            for(Set<Person> anotherGroup : groups){
                if(group!=anotherGroup && anotherGroup.containsAll(group)){
                    isMaximal=false;
                    break;
                }
            }
            if(isMaximal){
                maximalGroups.add(group);
            }
        }

    }
    public void printMaximalGroups() {
        for(Set<Person> group : maximalGroups){
            System.out.println("Group: " + group.toString() + "\n");
        }
    }
    public void printAbilitiesOfPeson(){
        for(Person p : abilities.keySet()){
            System.out.println("Person: " + p.toString() + " abilities : " + abilities.get(p) + "\n");
        }
    }
}
