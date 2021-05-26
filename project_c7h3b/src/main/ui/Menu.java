package ui;

import model.Calendar;
import model.Date;
import model.Spending;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

//this class is the main menu of the entire program, where a user begins is prompted with several options
public class Menu implements ActionListener {

    private static JFrame frame;
    private static JPanel panel;
    private static JLabel label;
    private static JButton addDate;
    private static JButton viewTotalSpending;
    private static JButton viewTotalSpendingForCategory;
    private static JButton save;
    private static JButton load;
    private static JButton view;
    private static JTextField ask;
    private static JButton enter;
    private static JButton back;
    private static JLabel totalSpending;
    private static JLabel totalSpendingReport;
    private static JFrame pic;
    private static JLabel loaded;
    private static JLabel saved;

    private BookkeepingApp ba;
    private AddDate ad;
    private Calendar calendar;


    //MODIFIES: this
    //EFFECTS: constructs a Menu and initializes fields
    public Menu() throws FileNotFoundException {

        addDate = new JButton();
        viewTotalSpending = new JButton();
        viewTotalSpendingForCategory = new JButton();
        save = new JButton();
        load = new JButton();
        calendar = new Calendar("myCalendar");
        ba = new BookkeepingApp();
        frame = new JFrame();
        enter = new JButton("View spending on this category");
        ask = new JTextField();
        totalSpendingReport = new JLabel();
        loaded = new JLabel();
        saved = new JLabel();
    }

    //MODIFIES: this
    //EFFECTS: initializes the buttons
    public void initializeButtons() {
        //picture taken from https://iconarchive.com/tag/save
        ImageIcon saveIcon = new ImageIcon("save.png");
        saveIcon.setImage(saveIcon.getImage().getScaledInstance(30,30,30));
        viewTotalSpending = new JButton("View my total spending");
        viewTotalSpendingForCategory = new JButton("View total spending for category");
        addDate = new JButton("Add a new date");
        save = new JButton(saveIcon);
        load = new JButton("Load");
        view = new JButton("View all dates");
        back = new JButton("Back to menu");
    }

    //MODIFIES: this
    //EFFECTS: adds buttons to the frame
    public void addButtons() {

        initializeButtons();

        addDate.setBounds(150, 100, 250, 25);
        addDate.addActionListener(e -> displayAddDate());
        panel.add(addDate);

        viewTotalSpending.setBounds(150, 140, 250, 25);
        viewTotalSpending.addActionListener(e -> displayTotalSpending());
        panel.add(viewTotalSpending);

        viewTotalSpendingForCategory.setBounds(150, 180, 250, 25);
        viewTotalSpendingForCategory.addActionListener(e -> displayTotalSpendingCategory());
        panel.add(viewTotalSpendingForCategory);

        view.setBounds(150, 220, 250, 25);
        view.addActionListener(e -> displayDateButtons());
        panel.add(view);

        load.setBounds(150, 260, 250, 25);
        load.addActionListener(e -> loadData());
        panel.add(load);

        save.setBounds(250, 300, 50, 50);
        save.addActionListener(e -> saveData());
        panel.add(save);

        back.setBounds(150, 270, 250, 25);
        back.addActionListener(e -> back());

        setAllButtonsColor();
    }



    //MODIFIES: this
    //EFFECTS: sets frame to desired settings
    public void initialize() {
        panel = new JPanel();
        frame.setSize(590, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().remove(panel);
        frame.add(panel);
        panel.setLayout(null);
        panel.setBackground(Color.decode("#b0deff"));
    }

    //MODIFIES: this
    //EFFECTS: displays main menu
    public void displayMainMenu() {

        initialize();

        label = new JLabel("Welcome to the main menu!");
        label.setBounds(160, 60, 350, 25);
        panel.add(label);

        addButtons();

        frame.setBackground(Color.BLUE);

        frame.setVisible(true);

        pic = new JFrame();
        pic.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pic.setLayout(new GridLayout());
        pic.setMinimumSize(new Dimension(400,560));
        JLabel picLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon("atm.jpg");
        picLabel.setText("Atm image");
        picLabel.setIcon(imageIcon);
        pic.add(picLabel);

    }

    //MODIFIES: this
    //EFFECTS: sets setting for atm image
    public void imageSetting() {
        pic.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: displays total spending
    public void displayTotalSpending() {
        int total = calendar.totalSpending("all");
        panel.removeAll();
        panel.add(back);
        totalSpending = new JLabel("Total spending so far is: " + total + "CAD !");
        totalSpending.setBounds(150, 140, 350, 50);
        panel.add(totalSpending);
        refresh();
    }

    //MODIFIES: this
    //EFFECTS: asks the user to input a category to see spending for
    public void displayTotalSpendingCategory() {

        panel.removeAll();
        panel.add(back);

        totalSpending = new JLabel("Please enter the category:");
        totalSpending.setBounds(153, 115, 600, 25);
        panel.add(totalSpending);

        ask.setBounds(150,150,250,25);
        panel.add(ask);

        enter.setBounds(150, 190, 250, 25);
        enter.addActionListener(e -> seeTotalSpendingForCategory(ask.getText()));
        panel.add(enter);

        refresh();
    }

    //MODIFIES: this
    //EFFECTS: displays the total spending for a particular category
    public void seeTotalSpendingForCategory(String category) {

        panel.remove(totalSpendingReport);
        int total = calendar.totalSpending(category);
        totalSpendingReport = new JLabel("Total spending on " + category + " is: " + total + " CAD");
        totalSpendingReport.setBounds(150, 230, 700, 25);
        panel.add(totalSpendingReport);
        refresh();
    }

    //MODIFIES: this
    //EFFECTS: displays add date menu
    public void displayAddDate() {
        ad = new AddDate();
        frame.getContentPane().remove(panel);
        frame.getContentPane().add(back);
        ad.displayDateMenu(this);
        refresh();
    }

    //MODIFIES: this
    //EFFECTS: displays the information for each date menu
    public void displayDateButtons() {
        clear();
        DisplayData dd = new DisplayData(this);
        dd.displayDates(this);
        refresh();
    }

    //MODIFIES: this
    //EFFECTS: reloads main menu
    public void back() {
        clear();
        displayMainMenu();
        System.out.println(calendar.size());
    }

    //MODIFIES: this
    //EFFECTS: loads data from a JSON file
    public void loadData() {
        panel.remove(loaded);
        panel.remove(saved);
        loaded = new JLabel("Successfully loaded data from bookkeeping.json !");
        loaded.setBounds(130, 340, 350, 25);
        panel.add(loaded);

        imageSetting();
        ba.loadCalendar();
        calendar = ba.getCalendar();
        refresh();
    }

    //MODIFIES: this
    //EFFECTS: saves data as JSON file
    public void saveData() {
        panel.remove(loaded);
        panel.remove(saved);
        saved = new JLabel("Successfully saved data to bookkeeping.json !");
        saved.setBounds(130, 340, 350, 25);
        panel.add(saved);

        ba.setCalendar(this.calendar);
        ba.saveCalendar();
        refresh();
    }

    //MODIFIES: this
    //EFFECTS: adds a date to the calendar
    public void addDateToCalendar(Date date) {
        calendar.addDate(date);
    }

    //MODIFIES: this
    //EFFECTS: adds a spending to a day in the calendar
    public void addSpendingToCalendar(Spending s, Date d) {
        calendar.addSpendingToCalendar(s,d);
    }

    //MODIFIES: this
    //EFFECTS: refreshes the frame
    public void refresh() {
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
    }

    //MODIFIES: this
    //EFFECTS: removes all elements from the frame
    public void clear() {
        frame.getContentPane().removeAll();
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public static JFrame getFrame() {
        return frame;
    }

    //MODIFIES: this
    //EFFECTS: sets the color of the button
    public void setButtonColor(JButton button, String color) {
        button.setBackground((Color.decode(color)));
        button.setOpaque(true);
        button.setBorderPainted(true);
    }

    //MODIFIES: this
    //EFFECTS: sets the color of all the buttonS
    public void setAllButtonsColor() {
        setButtonColor(save,"#7aff7d");
        setButtonColor(view,"#a1fffc");
        setButtonColor(load,"#7aff7d");
        setButtonColor(addDate,"#668cff");
        setButtonColor(viewTotalSpending,"#a1fffc");
        setButtonColor(viewTotalSpendingForCategory,"#a1fffc");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }


}
