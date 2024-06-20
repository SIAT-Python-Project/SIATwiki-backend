package wisdom;

import com.webserver.siatwiki.person.entity.Person;
import com.webserver.siatwiki.person.service.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class PersonServiceTest {

    @Autowired
    private PersonService personService;

    private Person person;

    @BeforeEach
    public void setUp() {
        // Given
        person = Person.builder()
                .name("김지혜")
                .email("cocoa389@naver.com")
                .mbti("ISTP")
                .github("wisdom-Kim")
                .build();
    }

    @Test
    @Transactional
    public void testSaveAndRetrievePerson() {
        // When
//        Person savedPerson = personService.savePerson(person);
//
//        // Then
//        Assertions.assertNotNull(savedPerson.getId());
//        Assertions.assertEquals(person.getName(), savedPerson.getName());
//
//        // Retrieving the saved person
//        Optional<Person> retrievedPerson = personService.getPerson(savedPerson.getId());
//        Assertions.assertTrue(retrievedPerson.isPresent());
//        Assertions.assertEquals(person.getName(), retrievedPerson.get().getName());
    }
}
