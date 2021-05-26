package persistence;

import exception.InvalidDateException;
import model.Calendar;
import model.Date;
import model.Spending;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//tests for JsonWriter
// CITATION: modeled after JsonWriterTest from JsonSerializationDemo
public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Calendar c = new Calendar("My new calendar");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyCalendar() {
        try {
            Calendar c = new Calendar("Kenny's Calendar");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCalendar.json");
            writer.open();
            writer.write(c);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCalendar.json");
            c = reader.read();
            assertEquals("Kenny's Calendar", c.getName());
            assertEquals(0, c.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterNormalCalendar() {
        try {
            Calendar c = new Calendar("Mike's Calendar");
            List<String> categories = new ArrayList<String>();
            categories.add("Sweets");
            Spending s1 = new Spending("chocolate", 2, categories);

            List<String> categories2 = new ArrayList<String>();
            categories2.add("Electronics");
            Spending s2 = new Spending("TV", 200, categories2);

            Date d1 = new Date(1,1,2020);
            d1.addSpending(s1);
            d1.addSpending(s2);

            Date d2 = new Date(2,1,2020);
            d2.addSpending(s1);

            c.addDate(d1);
            c.addDate(d2);

            JsonWriter writer = new JsonWriter("./data/testWriterNormalCalendar.json");
            writer.open();
            writer.write(c);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterNormalCalendar.json");
            c = reader.read();
            assertEquals("Mike's Calendar", c.getName());

            List<Date> dates = c.getDates();
            d1 = dates.get(0);
            d2 = dates.get(1);

            List<Spending> spendings1 = d1.getSpendingList();
            List<Spending> spendings2 = d2.getSpendingList();

            Spending s11 = spendings1.get(0);
            Spending s12 = spendings1.get(1);
            Spending s21 = spendings2.get(0);

            assertEquals(2, dates.size());

            assertEquals(1,d1.getDay());
            assertEquals(1,d1.getMonth());
            assertEquals(2020,d1.getYear());

            assertEquals(2,d2.getDay());
            assertEquals(1,d2.getMonth());
            assertEquals(2020,d1.getYear());

            assertEquals(2,s11.getAmount());
            assertEquals(200,s12.getAmount());
            assertEquals(2,s21.getAmount());

            assertEquals("chocolate", s11.getName());
            assertEquals("TV", s12.getName());
            assertEquals("chocolate", s21.getName());

            assertEquals("Sweets", s21.getCategories().get(0));
            assertEquals("Electronics", s12.getCategories().get(0));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        } catch (InvalidDateException e) {
            fail("Exception should not have been thrown");
        }
    }


}
