package persistence;

import exception.InvalidDateException;
import model.Calendar;
import model.Date;
import model.Spending;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

// Represents a reader that reads a calendar from JSON data stored in file
// CITATION: modeled after JsonReader from JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads calendar from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Calendar read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCalendar(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses calendar from JSON object and returns it
    private Calendar parseCalendar(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Calendar c = new Calendar(name);
        addDates(c, jsonObject);
        return c;
    }

    // MODIFIES: c
    // EFFECTS: parses dates from JSON object and adds them to calendar
    private void addDates(Calendar c, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("calendar");
        for (Object json : jsonArray) {
            JSONObject nextDate = (JSONObject) json;
            addDate(c, nextDate);
        }
    }

    // MODIFIES: c
    // EFFECTS: parses date from JSON object and adds it to calendar
    private void addDate(Calendar c, JSONObject jsonObject) {
        int day = jsonObject.getInt("day");
        int month = jsonObject.getInt("month");
        int year = jsonObject.getInt("year");

        try {
            Date date = new Date(day, month, year);
            addSpendings(date, jsonObject);
            c.addDate(date);
        } catch (InvalidDateException e) {
            //
        }
    }

    // MODIFIES: d
    // EFFECTS: parses spendings from JSON object and adds them to a date
    private void addSpendings(Date d, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("spendingList");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addSpending(d, nextThingy);
        }
    }

    //EFFECTS: parses spending from JSON object and adds it to a date
    private void addSpending(Date d, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int amount = jsonObject.getInt("amount");
        JSONArray categories = jsonObject.getJSONArray("categories");
        Spending spending = new Spending(name, amount, parseJsonArray(categories));
        d.addSpending(spending);
    }

    //EFFECTS: parses a JSON array to a List<String>
    private List<String> parseJsonArray(JSONArray array) {
        List<String> list = new ArrayList<String>();
        for (Object object : array) {
            list.add(object.toString());
        }
        return list;
    }

}


