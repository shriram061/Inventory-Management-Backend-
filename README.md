Inventory Management System


This project is an inventory management system that allows store managers to add, update, and view products in their inventory. It includes microservices for adding products, updating quantities, and generating reports.



Prerequisites


•	Java 11 or later
•	Spring Boot 2.4.3 or later
•	Maven 3.6.3 or later


Installation


To install and run the project, follow these steps:
1.	Clone the repository to your local machine using git clone <repository-url>.
2.	Navigate to the project directory using cd inventory-management-system.
3.	Build the project using mvn clean install.
  
  
Running the Application
  
  
To run the microservices, follow these steps:
1.	Start the Eureka server using mvn spring-boot:run -pl eureka-server.
2.	Start the product service using mvn spring-boot:run -pl product-service.
3.	Start the quantity service using mvn spring-boot:run -pl quantity-service.
4.	Start the report service using mvn spring-boot:run -pl report-service.
  
  
API Documentation
  
  
To view the API documentation, navigate to http://localhost:8761/ and click on the "inventory-management-system" link. This will take you to the Swagger UI, where you can browse and test the endpoints.
Technologies Used
•	Java 11
•	Spring Boot 2.4.3
•	Spring Cloud
•	Eureka Server
•	Swagger
  
  
Contributing
  
  
To contribute to the project, follow these steps:
1.	Fork the repository.
2.	Create a new branch for your feature or bug fix.
3.	Make your changes and commit them with descriptive messages.
4.	Push your changes to your fork.
5.	Submit a pull request to the main repository.

