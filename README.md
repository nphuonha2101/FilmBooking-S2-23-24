# FilmBooking Project

This project is a film booking system developed using Java, Servlet, and JSP.

This project demonstrates the use of various technologies in creating a functional film booking system. It provides a
good foundation for anyone looking to understand how to use Java, Servlet, and JSP in web development.

## Table of contents

- ***[Getting Started](#getting-started)***
  - [Prerequisites](#prerequisites)
  - [Clone this project](#clone-this-project)
  - [Run this project](#run-this-project)
- ***[Features](#features)***
- ***[Technologies Used](#technologies-used)***
- ***[Configure Tomcat 10 in IntelliJ IDEA](#configure-tomcat-10-in-intellij-idea)***

## Getting Started

- ##### Prerequisites:
    - Java 8
    - Maven
    - Postgres SQL
    - Tomcat 10

- ##### Clone this project:
  ```bash 
    $git clone https://github.com/nphuonha2101/FilmBooking-WebProgramming.git
  ```
  ```bash 
    $cd FilmBooking-WebProgramming
  ```
    - In this project, we use IntelliJ IDEA to develop. You can use any IDE that you want. With IntelliJ IDEA, we can prompt `idea64 .` to open this project.

- ##### Run this project:
    - Create database: you can create database with sql file in the folder **sql**. The DBMS that we used is Postgres SQL.
    - Change the database information in the file ```src/main/resources/properties/application.properties```.
    - Configure Tomcat 10 in IntelliJ IDEA: You can follow [here](#configure-tomcat-10-in-intellij-idea) to configure Tomcat 10 in IntelliJ IDEA. 
    - Run the project: You can run the project by clicking on the **Run** button in IntelliJ IDEA or you can press `Shift + F10`.
  
## Features

- **Book Film**: Allows users to book their desired films.
- **Login/Logout**: Manages user sessions.
- **Admin Management**: Provides administrative functions such as managing films, showtimes, and rooms.

## Technologies Used

- **Java**: The main programming language used for developing the application.
- **Servlet**: Used for extending the capabilities of servers that host applications accessed by means of a request-response model.
- **JSP (JavaServer Pages)**: Used for creating dynamic web content.

## Configure Tomcat 10 in IntelliJ IDEA

- Click on the **Run** menu and select **Edit Configurations...**.
- Click on the **+** button and select **Tomcat Server** -> **Local** (or you can press `Alt + Enter`).
- In the **Server** tab, click on the **Configure...** button.
- In the dialog that opens, click on the **+** button and find the Tomcat 10 folder then press `OK`. 
- Now in the **Run/Debug Configurations** dialog, you select the **Deployment** tab and click on the **+** button.
- Select **Artifact...** -> **FilmBooking:war-exploded** then press `OK`.
- (Optional) You can change the **Application context** to `/`.
- Press `OK` to save the configuration.

&copy; 2023 by **Nguyen Phuong Nha** and **Doan Quoc Dang**.

This project is licensed under the **MIT License**.
