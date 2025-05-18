# Hospital-Management-System
Hospital Management System
A simple console-based Hospital Management System built with Java and MySQL. This application allows hospital staff to manage patients, doctors, and appointments efficiently through an interactive menu-driven interface.

Features
Add new patients with their details.

View the list of registered patients.

View the list of doctors available.

Book appointments between patients and doctors with date validation.

Check doctor availability before booking appointments.

View all booked appointments.

Technologies Used
Java (JDK 8+)

MySQL Database

JDBC for database connectivity

Getting Started
Setup MySQL Database

Create a database named hospital.

Create tables: patients, doctors, and appointments with appropriate columns.

Configure Database Connection

Update the database URL, username, and password in the HospitalManagementSystem class as per your local MySQL setup.

Run the Application

Compile and run the Java program.

Use the console menu to interact with the system.

Future Improvements
Add update and delete operations for patients and doctors.

Implement user authentication for staff.

Develop a GUI or web interface for better user experience.

Add search functionality for patients, doctors, and appointments.

Send email notifications for upcoming appointments

-----------------------------------------------------DATABASE CONNECTION---------------------------------------------------

create database hospital;
 use hospital;
 create table patients(
    -> id INT AUTO_INCREMENT PRIMARY KEY,
    -> name VARCHAR(255) NOT NULL,
    -> age INT NOT NULL,
    -> gender VARCHAR(10) NOT NULL
    -> );
	
	 CREATE TABLE DOCTORS(
    -> id INT AUTO_INCREMENT PRIMARY KEY,
    -> name VARCHAR(255) NOT NULL,
    -> specialization VARCHAR(255) NOT NULL
    -> );
	
	  CREATE TABLE IF NOT EXISTS appointments (
    ->     appointment_id INT AUTO_INCREMENT PRIMARY KEY,
    ->     patient_id INT NOT NULL,
    ->     doctor_id INT NOT NULL,
    ->     appointment_date DATE NOT NULL
    -> );
	
	show tables;
+--------------------+
| Tables_in_hospital |
+--------------------+
| appointments       |
| doctors            |
| patients           |
+--------------------+


INSERT INTO DOCTORS(name,specialization)VALUES("pankaj Jain","Physician");
Query OK, 1 row affected (0.01 sec)

mysql> INSERT INTO DOCTORS(name,specialization)VALUES("Harshit Amod","NeuroSurgeon");
Query OK, 1 row affected (0.01 sec)

mysql> select * from doctors;
+----+--------------+----------------+
| id | name         | specialization |
+----+--------------+----------------+
|  1 | pankaj Jain  | Physician      |
|  2 | Harshit Amod | NeuroSurgeon   |
+----+--------------+----------------+
2 rows in set (0.00 sec)




