# Overview
To be done!

### Projects

* avw-api

  API of project
  
* avw-impl

  Implemenation of project
  
* avw-web

  Web frontend (web-gui) of project
  
* avw-parent

  Maven parent pom with managed dependencies
  

# Install

After cloning the project do the following tasks:

* create `avw-impl/src/main/resources/META-INF/datasource.properties` and `avw-web/src/main/webapp/META-INF/datasource.properties` with the following content:

   ```
  jdbc.driverClassName=org.mariadb.jdbc.Driver
  jdbc.url=jdbc:mysql://localhost:3306/avw
  jdbc.username=yourUsername
  jdbc.password=yourPassword

  ```
  
# Starting application

Start your web server and call the url http://yourHost:yourPort/avw-web/. To see the current defined API's see
url http://yourHost:yourPort/avw-web/api
    
