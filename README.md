# Action - Monitor

Project made for second round interview by Ágoston Székely

## Basics

The application was made with Spring Boot, using an embedded H2 file system based database with access to a console. Embedded Tomcat is used for deploying and running the project.

### Installing

For Maven users first the project must be packaged with the following commands (Unit tests will be executed)

```
mvn clean package
```

The command above will create an executable jar file in the target directory. To run this file the following command must be executed
```
java -jar target/action-monitor-0.1.0.jar
```

When deploying the application the database schema will be created and the first example data will be inserted. (resources/data.sql, resources/schema.sql)

## Using the application

Address http://localhost:8080/ is the main application where the messages will be returned.

Address http://localhost:8080/console/ is the database console for inserting or updating data. 

```
User Name : sa
Password : sa

JDBC URL: jdbc:h2:~/test-app
```

### Insert messages to the database

The following commands will trigger a message sent to the client. 
When inserting, the modified column MUST BE NULL, when updating, the modified column must be set to the current date.

```
INSERT INTO bvmessages(id, message) VALUES('teszt', 'uzenet');
UPDATE bvmessages set MESSAGE = 'updated', MODIFIED = CURRENT_TIMESTAMP WHERE id = 'teszt';
```