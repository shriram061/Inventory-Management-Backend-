# Inventory Management System


This project is an inventory management system that allows store managers to add, update, and view products in their inventory. It includes microservices for adding products, updating quantities, authentication and generating reports.



# Prerequisites


•	Java 11 or later
•	Spring Boot 2.4.3 or later
•	Maven 3.6.3 or later


# Installation


To install and run the project, follow these steps:
1.	Clone the repository to your local machine using git clone <repository-url>.
2.	Navigate to the project directory using cd inventory-management-system.
3.	Build the project using mvn clean install.
  
  
# Running the Application
  
  
To run the microservices, follow these steps:
1.	Start the Eureka server using mvn spring-boot:run -pl eureka-server.
2.	Start the inventory management service using mvn spring-boot:run -pl inventorymanagement-service.
3.	Start the authentication service using mvn spring-boot:run -pl authentication-service.
4.	Start the report service using mvn spring-boot:run -pl report-service.

  (or)
1. Clone the repository
2. create the database by writing "create database inventory1" in mysql
3. Now in mysql write "CREATE USER 'readonlyuser'@'%' IDENTIFIED BY 'root';" and "GRANT SELECT ON inventory1.* TO 'readonlyuser'@'%'" lines respectively and execute them.  
4. start the microserives and use the functionality
 
  
  
# API Documentation
  
  
To view the API documentation, navigate to http://localhost:8761/ and click on the "inventory-management-system" link. This will take you to the Eureka Server , where you can browse and test the endpoints.
Technologies Used
•	Java 11
•	Spring Boot 2.4.3
•	Spring Cloud
•	Eureka Server
•	Swagger
  
  
# Contributing
  
  
To contribute to the project, follow these steps:
1.	Fork the repository.
2.	Create a new branch for your feature or bug fix.
3.	Make your changes and commit them with descriptive messages.
4.	Push your changes to your fork.
5.	Submit a pull request to the main repository.


  
# Database Setup
  
  
Create a inventory1 database for all the microservices to share. Create the necessary tables for the inventory management application. There should be at least one table for storing the details of the services being monitored. Create a readonly user for the database. There should be using in Microservice. If required, set up user accounts and permissions for accessing the database. Note: Make sure to provide the necessary details for connecting to the database in the configuration files of the microservices.
