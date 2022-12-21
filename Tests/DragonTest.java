package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.ProcessBuilder.Redirect.Type;

import org.junit.Before;
import org.junit.Test;

import models.Dragon;
import models.Dragon.Gender;


public class DragonTest {

    Dragon dragon;

    @Before
    public void setup() {
        dragon = new Dragon("Gore Magala", Dragon.Type.WYRM);
        dragon.setGender(Gender.FEMALE);
    }

    @Test
    public void dragonTest() {
        assertEquals(Gender.FEMALE, dragon.getGender());
    }

    // @Test
    // public void assignDragonTest(){
    // assertEquals("Gore Magala", dragons.assignDragon());
    // }
}
