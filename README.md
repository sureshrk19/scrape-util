scrape-util
===========

Utility to scrape web pages

1/ Download Mongo DB from following link
 http://www.mongodb.org/downloads
 
2/ Extract MongoDB to some folder (say, F://MongoDB)

3/ cd F:/MongoDB/bin

4/ Start MongoDB process as 
mongod

5/ Check-out code from git repository

6/ Modify build.properties file to specify 'Tomcat' location
PS: Install tomcat server if not available already

7/ CD to project directory

8/ mvn install 
This will deploy war to specified tomcat server
PS: Install maven if it is not installed already.

9/ Access application from browser as
http://localhost:8080/scrape-util


