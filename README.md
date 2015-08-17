# ebuy

Tools and Technologies

    JDK 1.7
    MySQL.
    Apache Tomcat.
    Apache Maven (Maven Multi Module Project).
    Spring.
    Hibernate.

Project Description

Created the backend project for Sales Order Application which contains:

    Product: The Inventory products that organization sell.
    Customer: The buyers of products from organization.
    Sales Order: The process of selling product to customer. Each sales order consist of one or more order lines.
    Order Lines: The actual products and quantities that a customer need to buy.

The backend will validate the following information with each sales order

    Quantities that have been requested are less than or equal current inventory balance.
    Total price of sales order is less than or equal (Customer Credit Limit - Customer Current Credit).

If Sales Order is valid, back end  will reduce Inventory Quantities and increase current credit.

backend also handles delete and update of sales order

Requirements

    All Project(s) are under maven control. 
    Eclipse IDE. 
    Implemented web services to communicate between backend & frontend . 
    used tomcat as your web container via spring-boot. 
    Used hibernate as ORM Implementation. 
    Used MySQL database.    

Build, Deployment and Testing

    Execute the script inside SQL folder on MySql Server which will create database "ebuy". Provide Username and password of mysql database inside backend/src/main/resources/application.properties file. Database is required for test cases to pass as DAO layer test cases require database to check problems with queries.

    To execute all test cases in single command. Go to root folder of the code, execute "mvn clean test".

	Application can be built using single command. Go to root folder of the code, execute "mvn clean install".
	
	JUnit test cases are covered for all DAO layers and Service classes. Validations are handled inside service classes.
	Once the build is successful. Execute the script inside SQL folder which will create database "ebuy". 
    
    Now lets start the service. Go to folder "backend/target". Execute "java -jar backend-0.0.1-SNAPSHOT.jar". Backend service will be up and running. To verifiy whether backend service is up and running successfully. Please visit "http://localhost:8080/ebuy" in browser. it should say "{"status":"SUCCESS","message":"Application is up and running"}".

     

