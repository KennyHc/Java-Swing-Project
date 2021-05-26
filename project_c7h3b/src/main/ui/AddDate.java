package ui;

import exception.InvalidDateException;
import model.Date;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//this class represents the add date menu that allows users to input a date
public class AddDate implements ActionListener {

    private static JFrame frame;
    private static JPanel panel;
    private static JLabel label;
    private static JTextField day;
    private static JTextField month;
    private static JTextField year;
    private static JButton enter;
    private static JButton back;
    private Date date;
    private AddSpending addSpending;


    //MODIFIES: this
    //EFFECTS: constructs an AddDate and initializes fields
    public AddDate() {

        day = new JTextField();
        month = new JTextField();
        year = new JTextField();
        addSpending = new AddSpending();

    }

    //MODIFIES: this
    //EFFECTS: initialize fields
    public void initialize() {

        label = new JLabel("Please enter the date:");
        day = new JTextField("1");
        month = new JTextField("1");
        year = new JTextField("2020");
        enter = new JButton("Enter");
        back = new JButton("Back");
    }

    //MODIFIES: this
    //EFFECTS: add buttons to panel
    public void addButtons(Menu menu) {
        enter = new JButton("Enter");
        enter.setBounds(310, 45, 65, 25);
        enter.addActionListener(e -> enterDate(menu));
        panel.add(enter);

    }

    //MODIFIES: this
    //EFFECTS: adds text fields to panel
    public void addTextFields() {
        day = new JTextField("1");
        day.setBounds(150, 40, 40, 25);
        day.setEditable(true);
        panel.add(day);

        month = new JTextField("1");
        month.setBounds(200, 40, 40, 25);
        month.setEditable(true);
        panel.add(month);

        year = new JTextField("2020");
        year.setBounds(250, 40, 50, 25);
        year.setEditable(true);
        panel.add(year);
    }

    //MODIFIES: this
    //EFFECTS: displays the add date menu
    public void displayDateMenu(Menu menu) {

        panel = new JPanel();

        this.frame = menu.getFrame();

        frame.getContentPane().add(panel);
        frame.setSize(700, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);
        frame.setBackground(Color.BLUE);
        panel.setBackground(Color.decode("#b0deff"));

        initialize();

        panel.setLayout(null);

        label = new JLabel("Please enter the date:");
        label.setBounds(150, 20, 165, 25);
        panel.add(label);

        addTextFields();

        addButtons(menu);

        frame.setVisible(true);
    }


    //MODIFIES: this, menu
    //EFFECTS: enters the date to the calendar and takes user to spending menu
    public void enterDate(Menu menu) {
        String dayValue = day.getText();
        String monthValue = month.getText();
        String yearValue = year.getText();

        int dayInt = Integer.parseInt(dayValue);
        int monthInt = Integer.parseInt(monthValue);
        int yearInt = Integer.parseInt(yearValue);

        try {
            date = new Date(dayInt,monthInt, yearInt);
            menu.addDateToCalendar(date);
            frame.getContentPane().remove(panel);
            addSpending.displaySpendingMenu(menu, date);
        } catch (InvalidDateException e) {
            displayErrorMessage();
        }

        refresh();
    }

    //MODIFIES: this
    //EFFECTS: refreshes the frame
    public void refresh() {
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
    }

    //EFFECTS: displays an error message for invalid Date
    public void displayErrorMessage() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        frame.getContentPane().add(panel);
        frame.setSize(290, 70);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);
        frame.setBackground(Color.BLUE);

        JLabel message = new JLabel("Invalid date please enter again!");
        message.setBounds(150, 40, 250, 25);
        panel.add(message);

        JButton ok = new JButton("Ok");
        ok.setBounds(375, 45, 65, 25);
        ok.addActionListener(e -> closeWindow(frame));
        panel.add(ok);
    }

    //EFFECTS: closes the window
    public void closeWindow(Frame frame) {
        frame.setVisible(false);
    }

    //EFFECTS: returns date
    public Date getDate() {
        return date;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}
