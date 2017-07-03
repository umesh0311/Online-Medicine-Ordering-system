# Online-Medicine-Ordering-system (Under Development)
This project is developed using Java EE technology with help of derby database, glassfish server, JPQL, MVC 

List of the tools need to run this project.
Netbeans
Derby database
Glassfish server
Java
 
Steps to install the code and getting it to work
 
1. Execute below command in command prompt window 1 to start glassfish server
 
asadmin start-domain 
 
2. Execute this command in a different command prompt window 2, to start Derby Database 
 
startNetworkServer 
 
 
3. CREATE CONNECTION POOL in command prompt window 1
---
asadmin create-jdbc-connection-pool --datasourceclassname=org.apache.derby.jdbc.ClientDataSource --restype=javax.sql.XADataSource --property portNumber=1527:password=APP:user=APP:serverName=localhost:databaseName=ChemistDB:connectionAttributes=create\=true CONNECTION_EB
 
 
4. CHECKING CONNECTION POOL in command prompt window 1
 
asadmin ping-connection-pool CONNECTION_EB
 
5. DATASOURCE CREATE in command prompt window 1
 
asadmin create-jdbc-resource --connectionpoolid CONNECTION_EB  jdbc/ChemistDB
 
------------------------------------------------------
Then start glassfish server on port 4848. and then deploy the  .WAR file generated after building the project.
Example: http://localhost:4848
 
launch the application from glassfish server and modify the URL as
 
http://localhost:8080/APPLICATION NAME/faces/web/login.xhtml 
 
Example: http://localhost:8080/7DaysChemist-1.0-SNAPSHOT/faces/web/index.xhtml

