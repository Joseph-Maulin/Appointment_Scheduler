# Appointment_Scheduler
Java Appointment Scheduler


## Introduction
This is a Java program for managing clients and client appointments. This project is mainly focused on MySQL connector, ZonedDateTime, Locale detection, JavaFx, and designing an ituitive user iterface. The main improvement I would like to make to this project would be try and make the appointment calendars a little more efficient. Otherwise, I feel as though the user experience using this software is pretty straight-forward. This project was a great learning experience in becoming more familar with various aspects of programming in Java.



## SCENARIO
You are working for a software company that has been contracted to develop a scheduling desktop user interface application. The contract is with a global consulting organization that conducts business in multiple languages and has main offices in Phoenix, Arizona; New York, New York; and London, England. The consulting organization has provided a MySQL database that your application must pull data from. The database is used for other systems and therefore its structure cannot be modified.



The organization outlined specific business requirements that must be included as part of the application. From these requirements, a system analyst at your company created solution statements for you to implement in developing the application. These statements are listed in the requirements section.

## REQUIREMENTS
Your submission must be your original work. No more than a combined total of 30% of the submission and no more than a 10% match to any one individual source can be directly quoted or closely paraphrased from sources, even if cited correctly. An originality report is provided when you submit your task that can be used as a guide.



You must use the rubric to direct the creation of your submission because it provides detailed criteria that will be used to evaluate your work. Each requirement below may be evaluated by more than one rubric aspect. The rubric aspect titles may contain hyperlinks to relevant portions of the course.

 

You are not allowed to use frameworks or external libraries. The database does not contain data, so it needs to be populated. You must use “test” as the username and password to log-in.



A.   Create a log-in form that can determine the user’s location and translate log-in and error control messages (e.g., “The username and password did not match.”) into two languages.


![Alt text](Appointment_Scheduler_Screenshots/Login.JPG?raw=true "Login")

![Alt text](Appointment_Scheduler_Screenshots/Main_Menu.JPG?raw=true "Main")



B.   Provide the ability to add, update, and delete customer records in the database, including name, address, and phone number.


![Alt text](Appointment_Scheduler_Screenshots/Customer.JPG?raw=true "Customer")

![Alt text](Appointment_Scheduler_Screenshots/Add_Customer.JPG?raw=true "Add Customer")
 

C.   Provide the ability to add, update, and delete appointments, capturing the type of appointment and a link to the specific customer record in the database.

![Alt text](Appointment_Scheduler_Screenshots/Add_Appointment.JPG?raw=true "Add Appointment")


D.   Provide the ability to view the calendar by month and by week.

![Alt text](Appointment_Scheduler_Screenshots/Monthly_Calendar.JPG?raw=true "Monthly Calendar")

![Alt text](Appointment_Scheduler_Screenshots/Weekly_Calendar.JPG?raw=true "Weekly Calendar")
 

E.    Provide the ability to automatically adjust appointment times based on user time zones and daylight saving time.

 

F.   Write exception controls to prevent each of the following. You may use the same mechanism of exception control more than once, but you must incorporate at least  two different mechanisms of exception control.

•   scheduling an appointment outside business hours

•   scheduling overlapping appointments

•   entering nonexistent or invalid customer data

•   entering an incorrect username and password



G.  Write two or more lambda expressions to make your program more efficient, justifying the use of each lambda expression with an in-line comment.
 

H.   Write code to provide an alert if there is an appointment within 15 minutes of the user’s log-in.



I.   Provide the ability to generate each  of the following reports:

•   number of appointment types by month

•   the schedule for each consultant

•   one additional report of your choice

![Alt text](Appointment_Scheduler_Screenshots/Reports.JPG?raw=true "Reports")



J.   Provide the ability to track user activity by recording timestamps for user log-ins in a .txt file. Each new record should be appended to the log file, if the file already exists.



K. Demonstrate professional communication in the content and presentation of your submission.
