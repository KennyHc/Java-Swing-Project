package model;

import org.json.JSONArray;

import org.json.JSONObject;
import persistence.Writable;
import java.util.ArrayList;
import java.util.List;

//represents an array of dates, with a name of type string
public class Calendar implements Writable {

    private List<Date> calendar;
    private String name;

    //EFFECTS: creates an empty calendar
    public Calendar(String name) {
        this.name = name;
        this.calendar = new ArrayList<Date>();
    }

    //EFFECTS: returns the total spending of all days in the calendar for category c,
    //         or the total spending if the chosen category is all
    public int totalSpending(String c) {
        int total = 0;
        for (Date d : calendar) {
            total += d.daySpending(c);
        }
        return total;
    }

    //MODIFIES: calendar
    //EFFECTS: adds the date to the calendar
    public void addDate(Date d) {
        this.calendar.add(d);
    }

    public String getName() {
        return this.name;
    }

    public List<Date> getDates() {
        return calendar;
    }

    //EFFECTS: returns the size of the calendar
    public int size() {
        return this.calendar.size();
    }


    //EFFECTS: adds a spending to a certain day of the calendar
    public void addSpendingToCalendar(Spending spending, Date date) {
        for (Date d : calendar) {
            boolean sameYear = d.getYear() == date.getYear();
            boolean sameMonth = d.getMonth() == date.getMonth();
            boolean sameDay = d.getDay() == date.getDay();
            if (sameDay && sameMonth && sameYear) {
                d.addSpending(spending);
            }
        }

    }

    //EFFECTS: returns the calendar as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("calendar", dateToJson());
        return json;
    }

    //EFFECTS: returns dates in this calendar as a JSON array
    private JSONArray dateToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Date d : calendar) {
            jsonArray.put(d.toJson());
        }
        return jsonArray;
    }
}


