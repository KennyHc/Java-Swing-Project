package model;

import org.json.JSONObject;

import persistence.Writable;
import java.util.List;

//represents a spending that a person made, which has a name, amount and categories it falls under
public class Spending implements Writable {

    private int amount;  //represents the amount spent
    private String name; //represents the name of the spending
    private List<String> categories; //represents a list of categories the spending falls on

    //EFFECTS: constructs a Spending with a specified amount and a list of categories
    public Spending(String name, int amount, List<String> categories) {
        this.name = name;
        this.amount = amount;
        this.categories = categories;
    }

    //EFFECTS: constructs a Spending with a specified amount and a list of categories
    public Spending(String name, String amount, List<String> categories) {
        this.name = name;
        this.amount = Integer.parseInt(amount);
        this.categories = categories;
    }


    public int getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public List<String> getCategories() {
        return categories;
    }

    //MODIFIES: this
    //EFFECTS: adds a category to the list of categories
    public void addCategory(String c) {
        categories.add(c);
    }

    //EFFECTS: returns true if the spending falls under the category c
    public boolean hasCategory(String c) {
        for (String s : categories) {
            if (s.equals(c)) {
                return true;
            }
        }
        return false;
    }

    //EFFECTS: returns the spending as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("amount", amount);
        json.put("name", name);
        json.put("categories", categories);
        return json;
    }
}
