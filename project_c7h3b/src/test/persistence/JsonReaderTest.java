package persistence;

import model.Calendar;
import model.Date;
import model.Spending;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//tests for JsonReader
// CITATION: modeled after JsonReaderTest from JsonSerializationDemo
class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Calendar c = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyCalendar() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCalendar.json");
        try {
            Calendar c = reader.read();
            assertEquals("Empty Calendar", c.getName());
            assertEquals(0, c.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderNormalCalendar.json");
        try {
            Calendar c = reader.read();
            assertEquals("Kiki's Calendar", c.getName());

            List<Date> dates = c.getDates();
            Date d1 = dates.get(0);
            Date d2 = dates.get(1);

            List<Spending> spendings1 = d1.getSpendingList();
            List<Spending> spendings2 = d2.getSpendingList();

            Spending s11 = spendings1.get(0);
            Spending s12 = spendings1.get(1);
            Spending s21 = spendings2.get(0);

            assertEquals(2, dates.size());

            assertEquals(1, d1.getDay());
            assertEquals(1, d1.getMonth());
            assertEquals(2020, d1.getYear());

            assertEquals(2, d2.getDay());
            assertEquals(1, d2.getMonth());
            assertEquals(2020, d1.getYear());

            assertEquals(2, s11.getAmount());
            assertEquals(200, s12.getAmount());
            assertEquals(2, s21.getAmount());

            assertEquals("chocolate", s11.getName());
            assertEquals("TV", s12.getName());
            assertEquals("chocolate", s21.getName());

            assertEquals("Sweets", s21.getCategories().get(0));
            assertEquals("Electronics", s12.getCategories().get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderException() {
        JsonReader reader = new JsonReader("./data/testReaderException.json");
        try {
            Calendar c = reader.read();
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
