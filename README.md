# pg-scap-wicket
Para rodar o projeto ira precisar de:

* Eclipse IDE
* Wildfly
* MySQL Community e Workbench 
* MySQL Connector/J JDBC Driver
* MySQL Workbench 

# Set-up Eclipse to work with WildFly
To deploy our application in WildFly during development more easily, it's recommended to integrate the IDE Eclipse with the application server WildFly. This is done with some tools provided by JBoss/Red Hat, which can now be installed in a more straightforward way in the latest Eclipse version:

1. Open Eclipse;

2. Open the Servers view (if not visible, use the Window > Show View menu);

3. Click on the "No servers are available. Click this link to create a new server..." link, which is shown when the Servers view is empty. Alternatively, right-click the blank space at the Servers view and select New > Server;

4. Open the Red Hat JBoss Middleware folder and select JBoss AS, Wildfly, & EAP Server Tools;

5. Click Next, wait for the download, accept the license terms, click Finish and restart Eclipse;

6. Repeat steps 2 and 3 to open the New Server dialog again;

7. This time, open the JBoss Community folder and select WildFly 10.x. Click Next twice;

8. Fill in the server's directory ($WILDFLY_HOME) and click Finish.

# Set-up WildFly to connect to MySQL
WildFly comes with an H2 Database driver configured. In this tutorial, however, we use MySQL, so we need to add its driver to WildFly's configuration. Follow these steps to do it (make sure the server is stopped for this):

1. In the folder $WILDFLY_HOME/modules, create the following directory structure: com/mysql/main (we are using / to separate sub-directories; Windows users should replace it with \\ );

2. Unpack the MySQL Connector/J JDBC Driver you downloaded earlier and copy the file mysql-connector-java-5.1.41-bin.jar to the newly created folder $WILDFLY_HOME/modules/com/mysql/main. If you downloaded a different version of Connector/J, adjust the name accordingly;

3. Still at $WILDFLY_HOME/modules/com/mysql/main, create a file named module.xml with the following contents (again if you downloaded a different version of Connector/J, adjust the name accordingly):

```xml
<?xml version="1.0" encoding="UTF-8"?>
<module xmlns="urn:jboss:module:1.1" name="com.mysql">
	<resources>
		<resource-root path="mysql-connector-java-5.1.41-bin.jar"/>
	</resources>
	<dependencies>
		<module name="javax.api"/>
	</dependencies>
</module>
```

4. Now open the file $WILDFLY_HOME/standalone/configuration/standalone.xml and look for the tag <subsystem xmlns="urn:jboss:domain:datasources:4.0">. Inside this tag, locate <datasources> and then <drivers>. You should find the H2 Database driver configuration there. Next to it, add the configuration for MySQL Connector/J, as following:

```xml
<driver name="mysql" module="com.mysql">
	<driver-class>com.mysql.jdbc.Driver</driver-class>
</driver>
```

You should now be ready to develop a Java EE project in Eclipse, deploying it in WildFly and configuring it to use MySQL database for persistence. 

# Database creation and set-up
We will use JPA (Java Persistence API), one of the Java EE standards, persisting our objects in a relational database stored in the MySQL server. We need, therefore, to:

1. Create a database schema named scap;

2. Create a database user named scap with password scap;

3. Give user scap full permission for the schema scap.

# Datasource configuration in WildFly
While we could configure JPA to connect to the database we have just created in a configuration file in our project, creating a datasource for it in WildFly allows us to use JTA (Java Transaction API), another standard from Java EE, which provides us with automatic transaction management.

To create a JTA datasource for Scap in WildFly, open the file $WILDFLY_HOME/standalone/configuration/standalone.xml and look for the tag <subsystem xmlns="urn:jboss:domain:datasources:4.0">. Inside this tag, there is a <datasources> tag which holds the configuration for the java:jboss/datasources/ExampleDS datasource that WildFly comes with. Next to it, add a datasource for the scap database in MySQL:
```xml
<datasource jta="true" jndi-name="java:jboss/datasources/Scap" pool-name="ScapPool" enabled="true" use-java-context="true">
    <connection-url>jdbc:mysql://localhost:3306/scap</connection-url>
    <driver>mysql</driver>
    <security>
        <user-name>scap</user-name>
        <password>scap</password>
    </security>
</datasource>
```

# Rodando o projeto
1.Open the Servers view, right-click the WildFly server and choose Add and Remove.... Move the Scap project from the Available list to the Configured list and click Finish;

2.Rode o Wildfly e abra localhost:8080/Scap

* OBS: Necessario criar um secretario com tipoPessoa igual a 2 manualmente no mysql workbench para entrar no sistema.
* OBS2: Para o cadastro da primeira pessoa ira gerar um erro por causo do hibernate_sequence, então é só abrir o Scap novamente e fazer o cadastro normalmente.

