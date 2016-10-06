Configuración:
Nuestro obligatorio está commiteado en el siguiente link:
https://github.com/repositorioGitHubVivival87/obligatorio
Usamos base de datos mysql, y lo deployamos en GlassFish 4.1 (build 13) con los siguientes parámetros:
1-	JDBC Connection Pools
En la pestaña General configuramos:
Pool Name: EnviosYa
Resource Type: javax.sql.DataSource
Datasource Classname: com.mysql.jdbc.jdbc2.optional.MysqlDataSource

En la pestaña Additional Properties, las propiedades que configuramos son:
Password: usbw
User: root
Url: jdbc:mysql://localhost:3307/enviosya?zeroDateTimeBehavior=convertToNull

2-	JDBC Resources

JNDI Name: DSbdEnviosYa
Pool Name: EnviosYa

Una vez que levantamos el Glassfish desde la consola CMD de Windows, correr los siguientes comandos:
a-	Create a JMS Connection Factory
create-jms-resource --restype javax.jms.ConnectionFactory jms/ConnectionFactory
b-	Create a JMS Queue
create-jms-resource --restype javax.jms.Topic --property Name=Topic jms/Topic




