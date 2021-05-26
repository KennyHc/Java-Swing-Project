package model;


import exception.InvalidDateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CalendarTest {

    private Calendar calendar;
    private Date d1;
    private Date d2;
    private Spending s1;
    private Spending s2;
    private Spending s3;
    private Spending s4;
    private Spending s5;
    private Spending s6;

    @BeforeEach
    void runBefore() {
        calendar = new Calendar("myCalendar");

        try {
            d1 = new Date(1, 10, 2020);

            d2 = new Date(2, 10, 2020);

            List<String> categories = new ArrayList<String>();
            categories.add("food");
            s1 = new Spending("cookies", 3, categories);

            List<String> categories2 = new ArrayList<String>();
            categories2.add("food");
            s2 = new Spending("ramen", 12, categories2);

            List<String> categories3 = new ArrayList<String>();
            categories3.add("electronics");
            s3 = new Spending("TV", 250, categories3);

            List<String> categories4 = new ArrayList<String>();
            categories4.add("food");
            s4 = new Spending("sushi", 19, categories4);

            List<String> categories5 = new ArrayList<String>();
            categories5.add("electronics");
            s5 = new Spending("Laptop", 1620, categories5);

            List<String> categories6 = new ArrayList<String>();
            categories6.add("musical");
            s6 = new Spending("guitar", 150, categories6);

            d1.addSpending(s1);
            d1.addSpending(s2);
            d1.addSpending(s3);

            d2.addSpending(s4);
            d2.addSpending(s5);
            d2.addSpending(s6);
        } catch (InvalidDateException e) {
            fail("Should not have thrown exception");
        }
    }

    @Test
    void totalSpendingTest() {
        assertEquals(0, calendar.totalSpending("all"));
        calendar.addDate(d1);
        assertEquals(265, calendar.totalSpending("all"));
        assertEquals(15, calendar.totalSpending("food"));
        assertEquals(250, calendar.totalSpending("electronics"));
        assertEquals(0, calendar.totalSpending("musical"));
        assertEquals(0, calendar.totalSpending("furniture"));
        calendar.addDate(d2);
        assertEquals(2054, calendar.totalSpending("all"));
        assertEquals(34, calendar.totalSpending("food"));
        assertEquals(1870, calendar.totalSpending("electronics"));
        assertEquals(150, calendar.totalSpending("musical"));
        assertEquals(0, calendar.totalSpending("furniture"));
    }

    @Test
    void addDateTest() {
        assertEquals(0, calendar.size());
        calendar.addDate(d1);
        assertEquals(1, calendar.size());
        calendar.addDate(d2);
        assertEquals(2, calendar.size());
    }

    @Test
    void sizeTest() {
        assertEquals(0, calendar.size());
        calendar.addDate(d1);
        assertEquals(1, calendar.size());
        calendar.addDate(d2);
        calendar.addDate(d2);
        calendar.addDate(d2);
        calendar.addDate(d2);
        calendar.addDate(d2);
        assertEquals(6, calendar.size());
    }

    @Test
    void testAddSpendingToCalendar() {
        Calendar calendar2 = new Calendar("my");
        try {
            Date day = new Date(1, 1, 2020);
            Date day2 = new Date(2, 3, 2021);
            Date day3 = new Date(1, 1, 2021);
            Date day4 = new Date(2, 1, 2021);
            Date day5 = new Date(2, 1, 2020);
            Date day6 = new Date(1, 2, 2023);
            Date day7 = new Date(1, 2, 2020);
            Date day8 = new Date(1, 1, 2013);
            assertEquals(0, calendar2.size());
            calendar2.addDate(day2);
            assertEquals(1, calendar2.size());
            calendar2.addDate(day3);
            assertEquals(2, calendar2.size());
            calendar2.addDate(day4);
            assertEquals(3, calendar2.size());
            calendar2.addDate(day5);
            assertEquals(4, calendar2.size());
            calendar2.addDate(day6);
            assertEquals(5, calendar2.size());
            calendar2.addDate(day7);
            assertEquals(6, calendar2.size());
            calendar2.addDate(day8);
            assertEquals(7, calendar2.size());
            calendar2.addDate(day);
            assertEquals(8, calendar2.size());
            assertEquals(0, day.getSpendingList().size());
            calendar2.addSpendingToCalendar(s1, day);
            assertTrue(day.getSpendingList().contains(s1));
        } catch (InvalidDateException e) {
            fail("Should not have thrown Invalid date exception");
        }
    }

}