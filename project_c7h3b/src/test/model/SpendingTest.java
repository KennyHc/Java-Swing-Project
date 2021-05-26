package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SpendingTest {

    Spending s;

    @BeforeEach
    void SpendingTest() {
        List<String> categories = new ArrayList<String>();
        categories.add("food");
        s = new Spending("cookies",3, categories);

    }

    @Test
    void constructorTest() {
        assertEquals(3,s.getAmount());
        assertEquals(1, s.getCategories().size());
        assertEquals("cookies", s.getName());
    }

    @Test
    void constructorStringTest() {
        Spending s2 = new Spending("cookies","3",s.getCategories());
        assertEquals(3,s.getAmount());
        assertEquals(1, s.getCategories().size());
        assertEquals("cookies", s.getName());
    }

    @Test
    void addCategoryTest() {
        assertFalse(s.hasCategory("snack"));
        s.addCategory("snack");
        assertTrue(s.hasCategory("snack"));

        assertFalse(s.hasCategory("sweet"));
        s.addCategory("sweet");
        assertTrue(s.hasCategory("sweet"));

    }

    @Test
    void hasCategoryTest() {
        assertTrue(s.hasCategory("food"));
        assertFalse(s.hasCategory("beauty"));
        s.addCategory("snack");
        assertTrue(s.hasCategory("snack"));
        s.addCategory("chocolaty");
        assertTrue(s.hasCategory("chocolaty"));
        assertFalse(s.hasCategory("furniture"));
    }

}
