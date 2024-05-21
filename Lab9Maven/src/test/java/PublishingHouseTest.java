

import model.PublishingHouseEntity;
import org.junit.Before;
import org.junit.Test;

import repository.simple.PublishingHouseRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class PublishingHouseTest {
    private PublishingHouseRepository publishingHouseRepository;

    @Before
    public void setUp() {
        publishingHouseRepository = new PublishingHouseRepository();
    }

    @Test
    public void testCreateAndFindById() {
        PublishingHouseEntity publishingHouse = new PublishingHouseEntity("Test label");
        publishingHouseRepository.create(publishingHouse);

        PublishingHouseEntity genreFound = publishingHouseRepository.findById(publishingHouse.getId());
        assertEquals(publishingHouse, genreFound);
    }
//    @Test
//    public void testFindByName() {
//        PublishingHouseEntity publishingHouse1 = new PublishingHouseEntity("Test label1");
//        PublishingHouseEntity publishingHouse2 = new PublishingHouseEntity("Test label2");
//        publishingHouseRepository.create(publishingHouse1);
//        publishingHouseRepository.create(publishingHouse2);
//
//        List<PublishingHouseEntity> labels = publishingHouseRepository.findByName("Test");
//        assertTrue(labels.contains(publishingHouse1));
//        assertTrue(labels.contains(publishingHouse2));
//    }
}
