import model.AuthorEntity;
import org.junit.Test;
import static org.junit.Assert.*;

public class AuthorEntityTest {

    @Test
    public void testAuthorsEntityCreation() {
        AuthorEntity author = new AuthorEntity("Mark Twain");
        assertEquals("Mark Twain", author.getName());
    }

    @Test
    public void testSetName() {
        AuthorEntity author = new AuthorEntity();
        author.setName("Samuel Langhorne Clemens");
        assertEquals("Samuel Langhorne Clemens", author.getName());
    }



}