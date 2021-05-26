package ui;

import exception.InvalidDateException;
import model.Calendar;
import model.Date;
import model.Spending;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//this class is the app that does not have GUI and runs in the console
public class BookkeepingApp {
    private static final String JSON_STORE = "./data/bookkeeping.json";
    private Calendar calendar;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: runs the Bookkeeping application
    public BookkeepingApp() throws FileNotFoundException {
        runBookkeeping();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    private void runBookkeeping() {
        boolean running = false;
        String command = null;

        init();

        while (running) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                running = false;
            } else {
                processCommand(command);
            }
        }
    }


    //MODIFIES: this
    //EFFECTS: sets the calendar, input, and json reader/writer
    private void init() {
        calendar = new Calendar("myCalendar");
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    //EFFECTS: process the command that the user inputs
    private void processCommand(String command) {
        if (command.equals("d")) {
            try {
                askDate();
            } catch (InvalidDateException e) {
                System.out.println("Invalid Date");
            }
        } else if (command.equals("v")) {
            viewSpending();
        } else if (command.equals("s")) {
            saveCalendar();
        } else if (command.equals("l")) {
            loadCalendar();
        }
    }

    //EFFECTS: view the spending
    private void viewSpending() {
        viewSpendingMenu();
        String command = input.next();

        if (command.equals("t")) {
            int total = calendar.totalSpending("all");
            System.out.println("\n Your total spending is " + total + " CAD");
        }

        if (command.equals("c")) {
            System.out.println("\n Please enter the category for which you would want to see the total spending");
            String category = input.next();
            int totalCategory = calendar.totalSpending(category);
            System.out.println("\n You have spent a total of " + totalCategory + " CAD in " + category);
        }

    }

    //EFFECTS: asks user for the date in which they want to enter spendings
    private void askDate() throws InvalidDateException {

        System.out.println("\nPlease enter the day");
        int d = input.nextInt();

        System.out.println("\nPlease enter the month");
        int m = input.nextInt();

        System.out.println("\nPlease enter the year");
        int y = input.nextInt();

        Date date = new Date(d,m,y);

        date.addSpending(askSpending());

        System.out.println("\nWould you like to enter another spending for this date?");
        String yn = input.next();

        while (yn.equals("y")) {

            date.addSpending(askSpending());

            System.out.println("\nWould you like to enter another spending for this date?");
            yn = input.next();

        }
        calendar.addDate(date);
    }

    //EFFECTS: asks user for their spending
    public Spending askSpending() {

        List<String> categories = new ArrayList<String>();

        System.out.println("\nPlease enter the name of the spending");
        String name = input.next();

        System.out.println("\nPlease enter the amount you spent for " + name + " in CAD");
        int spent = input.nextInt();

        System.out.println("\nPlease enter the category of " + name);
        String category = input.next();

        categories.add(category);

        System.out.println("\nWould you like to enter another category for " + name + "? y/n");
        String yn = input.next();

        while (yn.equals("y")) {
            System.out.println("\nPlease enter the other category of " + name);
            String categorySub = input.next();
            categories.add(categorySub);
            System.out.println("\nWould you like to enter another category for " + name + "? y/n");
            yn = input.next();
        }

        Spending spending = new Spending(name,spent,categories);

        return spending;

    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    //EFFECTS: displays menu to user
    private void displayMenu() {
        System.out.println("\nWelcome, this is the Menu!");
        System.out.println("\nPress d to add a date");
        System.out.println("\nPress v to add a view total spending");
        System.out.println("\nPress s to save calendar to file");
        System.out.println("\nPress l to load calendar from file");
        System.out.println("\nPress q to quit");
    }

    //EFFECTS: displays spending summary menu to user
    private void viewSpendingMenu() {
        System.out.println("\nWhich information would you like?");
        System.out.println("\nPress t to see your total spending");
        System.out.println("\nPress c to see the total spending of a category");
    }

    // EFFECTS: saves the calendar to file
    public void saveCalendar() {
        try {
            jsonWriter.open();
            jsonWriter.write(calendar);
            jsonWriter.close();
            System.out.println("Saved " + calendar.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: loads calendar from file
    public void loadCalendar() {
        try {
            calendar = jsonReader.read();
            System.out.println("Loaded " + calendar.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
