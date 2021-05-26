package ui;

import model.Calendar;
import model.Date;
import model.Spending;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//this class represents the menu that with data that is to be displayed regarding the dates and spendings for each date
public class DisplayData implements ActionListener {


    private Calendar calendar;
    private JPanel panel;
    private JFrame frame;

    //MODIFIES: this
    //EFFECTS: constructs a DisplayData and sets calendar equal to the menu's
    public DisplayData(Menu menu) {
        this.calendar = menu.getCalendar();
        this.frame = menu.getFrame();
    }

    //MODIFIES: this
    //EFFECTS: sets the frame to desired settings
    public void initialize() {
        panel = new JPanel();
        frame.setSize(590, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().remove(panel);
        panel.setVisible(true);
        frame.add(panel);
        panel.setBackground(Color.decode("#b0deff"));
        panel.setLayout(null);
    }

    //MODIFIES: this
    //EFFECTS: displays a menu as a list of date buttons that when pressed, will display spending data
    public void displayDates(Menu menu) {
        int i = 0;

        initialize();

        for (Date d : calendar.getDates()) {
            String dateString = d.getDay() + "/" + d.getMonth() + "/" + d.getYear();
            JButton date = new JButton(dateString);

            date.setBounds(150, (80 + i * 40), 250, 25);
            date.addActionListener(e -> displaySpendingList(d, menu));
            panel.add(date);
            i++;
        }

        JButton back = new JButton("Back to menu");
        back.setBounds(150, (100 + i * 40), 200, 25);
        back.addActionListener(e -> menu.back());
        panel.add(back);

        menu.refresh();

    }

    //MODIFIES: this
    //EFFECTS: displays all of the spending made in one day
    public void displaySpendingList(Date date, Menu menu) {

        panel.removeAll();
        makeLabel("List of spending made on " + date.toString(),55);

        int a = 0;

        for (Spending s : date.getSpendingList()) {
            a++;

            String name = s.getName();
            int amount = s.getAmount();
            String category1 = s.getCategories().get(0);
            String category2 = s.getCategories().get(1);

            makeLabel("Name of spending: " + name, 20 + a * 80);
            makeLabel("Amount spent: " + amount + " CAD", 40 + a * 80);
            makeLabel("Categories: " + category1 + ", " + category2, 60 + a * 80);
        }

        JButton back = new JButton("Back to menu");
        back.setBounds(150, (a + 1) * 80 + 30, 200, 25);
        back.addActionListener(e -> menu.back());
        panel.add(back);

        menu.refresh();
    }

    //MODIFIES: this
    //EFFECTS: makes a label with a text and a position and adds it to the panel
    public void makeLabel(String text, int position) {
        JLabel label = new JLabel(text);
        label.setBounds(160, position, 400, 25);
        panel.add(label);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
