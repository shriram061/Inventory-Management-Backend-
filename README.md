# Inventory Management System


This project is an inventory management system that allows store managers to add, update, and view products in their inventory. It includes an executive manager user-role that adds product to the cart, checkout them and generate delivery reports. It also includes an external user who can contact the store manager for solving their query and provide with their valuable feedback. It includes 3 microservices( all connected through feign client ) for main CRUD operation, authentication and generating reports respectively.




# Prerequisites


•	Java 11 or later
•	Spring Boot 2.4.3 or later
•	Maven 3.6.3 or later
• Redis server 3.0.504


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
5. Start the redis server 
 
# Architecture
  
The Inventory Management Application is designed as a microservice-based architecture. The application consists of three microservices, a Eureka server, and a centralized database. The microservices communicate with each other using the Feign client and are registered with the Eureka server. The Eureka server acts as a service registry and facilitates service discovery. The centralized database is used to store the details and data of each service.
  
# API Documentation
  
  
To view the API documentation, navigate to http://localhost:8761/ and click on the "inventory-management-system" link. This will take you to the Eureka Server , where you can browse and test the endpoints.
Technologies Used
•	Java 11
•	Spring Boot 2.4.3
•	Spring Cloud
•	Eureka Server
•	Swagger
• Redis Cache
  
# Configuration
  
To configure the application, you can modify the application.properties files for each microservice. The files contain properties for configuring the database connection, server port, and other settings. You can also modify the Feign clients to change the endpoints used to check the  other service.
  
  
# Usage
  
Provide instructions on how to use the application from a inventory manager's and executive's perspective , including categorying products , searching for products by keyword, viewing product details, adding items to the cart, checking out and generate report for both manager and executive.

# Admin functionality
  
Provide instructions on how to access the admin interface, and the functionality available to administrators, including managing inventory, managing exective accounts, and generating reports.
  
# Database Setup
  
  
Create a inventory1 database for all the microservices to share. Create the necessary tables for the inventory management application. There should be at least one table for storing the details of the services being monitored. Create a readonly user for the database. There should be using in Microservice. If required, set up user accounts and permissions for accessing the database. Note: Make sure to provide the necessary details for connecting to the database in the configuration files of the microservices. Also start the redis cache server.
