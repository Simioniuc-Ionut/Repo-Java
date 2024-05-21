import model.BookEntity;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.BoolVar;
import org.chocosolver.solver.variables.IntVar;
import repository.simple.BookRepository;

import java.util.List;

public class ChocoSolver {
    public static void main(String[] args) {
        // Creez modelul
        Model model = new Model("Book selection problem");

        // Obtine cartile din baza de date
        BookRepository findAllBooks = new BookRepository();
        List<BookEntity> books = findAllBooks.findFirstN(20);
        int kBooks= 10;
        int differenceYearExceed = 20;
        String firstLetter = "H"; // litera cu care incep titlurile cartilor

        // Presupun ca am n carti
        int n = books.size(); // numarul total de carti

        // Informatii despre carti
        String[] titles = new String[n]; // titlurile cartilor
        IntVar[] years = new IntVar[n]; // anii de publicare a cartilor
        for (int i = 0; i < n; i++) {
            titles[i] = books.get(i).getTitle();
            years[i] = model.intVar("year" + i, books.get(i).getYear());
        }

        // Creez variabilele
        BoolVar[] isSelected = model.boolVarArray("isSelected", n);


        // Constrangerea pentru titlurile cartilor
        for (int i = 0; i < n; i++) {
            if (Character.toLowerCase(titles[i].charAt(0)) == Character.toLowerCase(firstLetter.charAt(0))) {
                isSelected[i] = model.boolVar("isSelected" + i, true);
                System.out.println("Carte selectata: " + titles[i] + " || year of publication " + years[i]);
            } else {
                isSelected[i] = model.boolVar("isSelected" + i, false);

            }
        }
        model.sum(isSelected, ">=", kBooks).post();

        // Constrangerea pt years exceed
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                model.ifThen(
                        model.and(isSelected[i], isSelected[j]),
                        model.and(
                                model.arithm(years[i].sub(years[j]).intVar(), "<=", differenceYearExceed),
                                model.arithm(years[j].sub(years[i]).intVar(), "<=", differenceYearExceed)
                        )
                );
            }
        }



        // Rezolv modelul
        if (model.getSolver().solve()) {
            System.out.println("Selected books:");
            for (int i = 0; i < n; i++) {
                if (isSelected[i].getValue() == 1) {
                    System.out.println("Book: " + titles[i] + " || Year of publication: " + years[i].getValue());
                }
            }
        } else {
            System.out.println("No solution found.");
        }
    }
}
