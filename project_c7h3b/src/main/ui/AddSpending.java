package ui;

import model.Date;
import model.Spending;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

//this class represents the add spending menu that allows users to input one or multiple spendings
public class AddSpending implements ActionListener {

    private static JFrame frame;
    private static JPanel panel;
    private static JLabel label;
    private static JLabel nameLabel;
    private static JLabel amountLabel;
    private static JLabel category1Label;
    private static JLabel category2Label;
    private static JLabel response;
    private static JTextField name;
    private static JTextField amount;
    private static JTextField category1;
    private static JTextField category2;
    private static JButton enter;
    private static JButton enterAndAdd;
    private Date date;


    //EFFECTS: constructs an add spending menu
    public AddSpending() {
        name = new JTextField();
        amount = new JTextField();
        category1 = new JTextField();
        category2 = new JTextField();

    }

    //EFFECTS: returns the date as a string
    public String printDate() {
        int d = date.getDay();
        int m = date.getMonth();
        int y = date.getYear();
        return d + "/" + m + "/" + y;
    }

    //MODIFIES: this
    //EFFECTS: sets frame to desired settings
    public void initialize() {
        panel = new JPanel();
        frame.getContentPane().add(panel);
        frame.setSize(700,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);
        panel.setLayout(null);
        panel.setBackground(Color.decode("#b0deff"));
    }

    //MODIFIES: this
    //EFFECTS: adds desired labels to the panel
    public void addLabels() {
        label = new JLabel("Please enter the spending you made on " + printDate() + ":");
        label.setBounds(150,20,400,25);
        panel.add(label);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(150,50,165,25);
        panel.add(nameLabel);

        amountLabel = new JLabel("Amount spent:");
        amountLabel.setBounds(150,80,165,25);
        panel.add(amountLabel);

        category1Label = new JLabel("Category 1:");
        category1Label.setBounds(150,110,165,25);
        panel.add(category1Label);

        category2Label = new JLabel("Category 2:");
        category2Label.setBounds(150,140,165,25);
        panel.add(category2Label);
    }

    //MODIFIES: this
    //EFFECTS: adds desired text fields to the panel
    public void addTextFields() {
        name = new JTextField(20);
        name.setBounds(300,50,250,25);
        panel.add(name);

        amount = new JTextField(20);
        amount.setBounds(300,80,80,25);
        panel.add(amount);

        category1 = new JTextField(20);
        category1.setBounds(300,110,250,25);
        panel.add(category1);

        category2 = new JTextField(20);
        category2.setBounds(300,140,250,25);
        panel.add(category2);
    }

    //MODIFIES: this
    //EFFECTS: displays the spending menu
    public void displaySpendingMenu(Menu menu, Date date) {

        this.date = date;

        this.frame = menu.getFrame();

        initialize();

        addLabels();

        addTextFields();

        enter = new JButton("Enter");
        enter.setBounds(300,170,65,25);
        enter.addActionListener(e -> addSpending(menu, date, true));
        panel.add(enter);

        enterAndAdd = new JButton("Enter and add another spending");
        enterAndAdd.setBounds(380,170,240,25);
        enterAndAdd.addActionListener(e -> addSpending(menu,date,false));
        panel.add(enterAndAdd);

        response = new JLabel();
        response.setBounds(100,200,500,25);
        panel.add(response);

    }

    //MODIFIES: this
    //EFFECTS: adds a spending to the desired date in the calendar, and returns to main menu if finished.
    public void addSpending(Menu menu, Date date, Boolean finished) {

        frame.getContentPane().remove(panel);

        String nameValue = name.getText();
        String amountValue = amount.getText();
        String category1Value = category1.getText();
        String category2Value = category2.getText();

        List<String> spendingList = new ArrayList<String>();
        spendingList.add(category1Value);
        spendingList.add(category2Value);

        Spending spending = new Spending(nameValue,amountValue,spendingList);

        menu.addSpendingToCalendar(spending, date);

        if (finished) {
            frame.getContentPane().removeAll();
            menu.displayMainMenu();
        } else {
            displaySpendingMenu(menu, date);
            response.setText("Spending on " + nameValue + " " +  amountValue + " CAD " + "entered successfully!");
        }

        refresh();
    }

    //MODIFIES: this
    //EFFECTS: refreshes the frame
    public void refresh() {
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
    }

    public Date getDate() {
        return date;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}