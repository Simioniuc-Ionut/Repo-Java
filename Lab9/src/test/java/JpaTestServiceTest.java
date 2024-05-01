import org.example.service.JpaTestService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JpaTestServiceTest {

    @Test
    public void testJPA() {
        JpaTestService jpaTestService = new JpaTestService();
        jpaTestService.testJPA();
        // Aici adaug aserturi suplimentare pentru a verifica rezultatele testului, dacă este necesar.
    }
}
