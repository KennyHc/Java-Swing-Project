package model;

import exception.InvalidDateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DateTest {

    private Date d;

    private Spending s1;
    private Spending s2;
    private Spending s3;


    @BeforeEach
    void runBefore() {
        try {
            d = new Date(1, 10, 2020);
            List<String> categories = new ArrayList<String>();
            categories.add("food");
            s1 = new Spending("cookies", 3, categories);

            List<String> categories2 = new ArrayList<String>();
            categories2.add("food");
            s2 = new Spending("ramen", 12, categories2);

            List<String> categories3 = new ArrayList<String>();
            categories3.add("electronics");
            s3 = new Spending("TV", 250, categories3);
        } catch (InvalidDateException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void constructorTest() {
        assertEquals(1,d.getDay());
        assertEquals(10,d.getMonth());
        assertEquals(2020,d.getYear());
        assertEquals(0,d.getSpendingList().size());
    }

    @Test
    void addSpendingTest() {
        assertEquals(0,d.getSpendingList().size());
        d.addSpending(s1);
        assertEquals(1,d.getSpendingList().size());
        d.addSpending(s2);
        assertEquals(2,d.getSpendingList().size());
        d.addSpending(s3);
        assertEquals(3,d.getSpendingList().size());
    }

    @Test
    void daySpendingTestAllCategories() {
        assertEquals(0,d.daySpending("all"));
        d.addSpending(s1);
        assertEquals(3,d.daySpending("all"));
        d.addSpending(s2);
        assertEquals(15,d.daySpending("all"));
        d.addSpending(s3);
        assertEquals(265,d.daySpending("all"));
    }

    @Test
    void daySpendingTestFood() {
        assertEquals(0,d.daySpending("food"));
        d.addSpending(s1);
        assertEquals(3,d.daySpending("food"));
        d.addSpending(s2);
        assertEquals(15,d.daySpending("food"));
        d.addSpending(s3);
        assertEquals(15,d.daySpending("food"));
    }

    @Test
    void daySpendingTestElectronics() {
        assertEquals(0,d.daySpending("electronics"));
        d.addSpending(s1);
        assertEquals(0,d.daySpending("electronics"));
        d.addSpending(s2);
        assertEquals(0,d.daySpending("electronics"));
        d.addSpending(s3);
        assertEquals(250,d.daySpending("electronics"));
    }

    @Test
    void testValidDate() {
        try {
            Date date = new Date(1,3,2020);
        } catch (InvalidDateException e) {
            fail("Should not have thrown InvalidDateException");
        }
    }

    @Test
    void testInvalidDateMonth() {
        try {
            Date date = new Date(1,23,2020);
            fail("Should have thrown InvalidDateException");
        } catch (InvalidDateException e) {
            //expected to be thrown
        }
    }

    @Test
    void testInvalidDateDay() {
        try {
            Date date = new Date(45,12,2020);
            fail("Should have thrown InvalidDateException");
        } catch (InvalidDateException e) {
            //expected to be thrown
        }
    }

    @Test
    void testInvalidDateDayAndMonth() {
        try {
            Date date = new Date(45,13,2020);
            fail("Should have thrown InvalidDateException");
        } catch (InvalidDateException e) {
            //expected to be thrown
        }
    }

    @Test
    void testToString() {
        try {
            Date date = new Date(5,3,2020);
            String dateString = date.toString();
            assertEquals("5/3/2020",dateString);

        } catch (InvalidDateException e) {
            fail("Should not have thrown InvalidDateException");
    }
    }
}
