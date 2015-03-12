# Salud Migrante - Feeder

## Introduccion

- Este proyecto sirve para apoyar a la generacion de Copis de Experience, para el proyecto Salud Migrante.
- Es una Aplicación Web, que expone un Front End y un API REST para consumo del Front End.

## Back End

- Proceso Batch que se encarga de procesar una lista de Feeds, y en funcion de un diccionario de palabras, selecciona los articulos que considera relevantes. Estos articulos los guarda en una Base de Datos, para ser procesados manualmente por un usuario.
- Ademas expone un API REST para que el frontend pueda consultar datos a traves de llamadas Ajax.

## Front End

- A traves de una aplicacion Web los usuarios pueden:
	* Gestionar los Feeds que se deben de procesar
	* Decidir que articulos son relevantes y guardarlos como Copis de Experience

## Tecnologias

- Oracle Java 1.7: La aplicacion de servidor esta desarrollada en Java. 
- Librerias Java (gestionadas mediante Maven): 
	* Spring MVC
	* Spring Data
	* Jackson (JSON)
	* Apache Commons
	* Apache Tika
	* Apache OpenNLP
	* Boilerpipe
	* otras
- Apache Tomcat 7: Servidor de aplicaciones
- Foundation 5: libreria CSS para maquetar las vistas
- AngularJs: para la conexion con el Backend (peticiones Ajax) y para manejar el modelo de datos en la vista
- MongoDB: como Base de Datos

## Dependencias

- Java 1.7 [Descargar](http://www.oracle.com/technetwork/es/java/javase/downloads/jdk7-downloads-1880260.html) 
- Maven 3  [Descargar](http://maven.apache.org/download.cgi)
- Apache Tomcat 7 [Descargar](http://tomcat.apache.org/download-70.cgi)
- MongoDB 2.6 [Descargar](http://www.mongodb.org/downloads#previous)

## Instalación

- No es el proposito de este documento explicar como instalar ninguna de las tecnologías que usa el proyecto. Una vez instaladas solo es necesario cargar en MongoDB las tablas **states** y **filter_words**
- Para empaquetar el proyecto, es necesario colocarse en la carpeta del proyecto y ejecutar en el terminal

```
> mvn clean compile package
```
- Una vez empaquetado, en la carpeta **target** esta el fichero **feeder.war** que se puede instalar en el servidor de aplicaciones Tomcat.





