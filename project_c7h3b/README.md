# Bookkeeping Application

## Functionalities

Functionalities that the app has:

- Allow users to enter spending information for any date they wish
- Able to show users their detailed spending data of all dates
- Allow users to enter information for each spending (name, amount and category)
- Allow users to see their total spending for a particular category
- Allow users to save and load data to the app
- Allows users to see their total spending
- Has a graphical user interface

## Users

This app is directed to people who want to look at their spendings by **category** and date. </p>
It is intended for people who would like to have a basic rather than complex **overview** over some of their spending.

## Interest

This project is of interest to me because I want to have a **simple** but reliable way 
to look at my expenses 
by date and by category. I think it will give people a more clear idea of where their money is mostly spent
 and for which dates.
 
## User Stories


- As a user I want to assign 2 categories to a single spending
- As a user I want to assign multiple spendings to a single date
- As a user I want to have multiple dates in my calendar
- As a user I would like to see the total amount I have spent
- As a user I would like to see the total amount I have spent in one specific category
- As a user I would like to view the spending information for all dates
- As a user, I want to be able to save my calendar to file
- As a user, I want to be able to be able to load my calendar from file 


##P4 task 2:
- I chose to implement an exception for the date class.
- Invalid date exception will tell the user that they have tried to use an invalid date
 and tell them to choose another date.
- An example of an invalid date would be 43/23/2020
 
##P4 task 3:
- The UML class diagram is an attached picture called UML.jpg

 - What I am happy about my class arrangement is that I was able to separate the GUI into 
 different classes at the very least, although it is not the most elegant code and maybe some elements could be
 in one class rather than another, I managed to not have to fit everything in one class only.
 - Each class has a specific purpose because my program has multiple panels so naturally 
 I would divide the tasks into different classes.
 - I would have made a superclass for each of the classes that have to do with a GUI, similar to 
 Tool class in the Drawing Player. This would have reduced some redundancies in my classes.
 - I would have made sure there is no method that has repetition anywhere for my code and if there was I
 would make a method for it no matter how small, this would make it more organized,
 but it would take much more planning.
 - I would have made a panel class that allows me to easily create JPanels and modify them,
 so there would be even less code in the GUI classes.

