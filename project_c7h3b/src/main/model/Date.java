package model;

import exception.InvalidDateException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//represents a date with a day, month and year, which can have an array of spendings
public class Date implements Writable {

    private int day;
    private int month;
    private int year;
    private List<Spending> spendingList; //represents the spending made on a particular day


    //EFFECTS: constructs a Date with specified day, month and year
    public Date(int day, int month, int year) throws InvalidDateException {
        if (day > 31 || month > 12) {
            throw new InvalidDateException();
        }
        this.day = day;
        this.month = month;
        this.year = year;
        spendingList = new ArrayList<Spending>();
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    //MODIFIES: spendingList
    //EFFECTS: adds the specified spending to spendingList
    public void addSpending(Spending s) {
        spendingList.add(s);
    }

    public List<Spending> getSpendingList() {
        return spendingList;
    }

    //EFFECTS: returns the total spending in a day for a category c, or all for all spending
    //         as a result, if a category is named all, it will belong to all categories
    public int daySpending(String c) {
        int total = 0;

        for (Spending s : spendingList) {
            if (c.equals("all") || s.hasCategory(c)) {
                total += s.getAmount();
            }
        }
        return total;
    }

    //EFFECTS: returns the date represented as a String
    public String toString() {
        return day + "/" + month + "/" + year;
    }

    //EFFECTS: returns the date as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("day", day);
        json.put("month", month);
        json.put("year", year);
        json.put("spendingList", spendingToJson());
        return json;
    }

    // EFFECTS: returns spendings in this date as a JSON array
    private JSONArray spendingToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Spending s : spendingList) {
            jsonArray.put(s.toJson());
        }
        return jsonArray;
    }
}

