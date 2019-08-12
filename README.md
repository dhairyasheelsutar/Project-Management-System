# Project-Management-System
This is BE project management system for storing records BE projects written in Java and MySQL. 

## Table of contents

* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)

## General info
BE project management system stores all the records related to project groups, sponsorship details, students marks and group reviews. There are 5 stakeholders in application as:
* Project co-ordinator
* Project guide
* Project reviewer
* Commitee assistant
* Student

## Technologies
Project is created with:
* Java 8
* MySQL 8
* Apache Tomcat 9.0.11
* Java Servlets

## Setup

* There is a SQL file named project_management_system_final.sql. Create a database in MySQL with same name as file name and run command.
```
source project_management_system_final.sql
```
* Download apache tomcat from https://archive.apache.org/dist/tomcat/tomcat-9/v9.0.11/bin/
* Set $CATALINA_HOME as tomcat working directory.
* Set $CATALINA_BASE as $CATALINA_HOME/webapps
* Copy the project to $CATALINA_HOME/webapps directory run command.
```
jar -cvf project_management_system.war *
cd $CATALINA_HOME
./bin/startup
```
* Open browser and type localhost:tomcat_port_number
* Enter username and password.
* Select war file of project and start.
