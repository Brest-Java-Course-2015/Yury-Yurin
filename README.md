# Yury Yurin
This project represents some service for auto owners. They can shape application with diiferent malfunctions their auto. Malfunction consist of name, auto and description. After analysis ,Administration can set cost for 3 criterion: repair, service, additional expanses.
Application has a simple interface without help designers.

###Steps to install this application:###

1. Download a repository with project to your local machine
2. Install Apache Tomcat server on your local machine
3. Cd to the root directory of a project: cd /../../Yury-Yurin
4. Prompt maven install command: mvn clean install
5. After all tests successfully passed browse to the folder: cd app-rest/target and copy .WAR file to the CATALINA_HOME/webapps: cd app-rest-1.0-SNAPSHOT.war /../../apache-tomcat/webapps
6. browse to the folder: cd app-rest-for-rest-client/target and copy .WAR file to the CATALINA_HOME/webapps: cd app-rest-for-rest-client-1.0-SNAPSHOT.war /../../apache-tomcat/webapps
7. Start your tomcat server
8. Browse to folder by name js-client, if you want create application and add malfunctions - open index.html, if you want fill administrator authority - open indexForAdmin.html
9. Acces your application by URL, e.g. http://localhost:8080/app-rest-for-rest-client-1.0.0-SNAPSHOT/
10. Enjoy!

email: yhra@yandex.ru

skype: yrayrin