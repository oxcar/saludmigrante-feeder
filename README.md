# Salud Migrante - Feeder

## Tecnologias

- Oracle Java 1.7: La aplicacion de servidor esta desarrollada en Java. 
- Librerias: Spring MVC, Spring Data, Jackson (JSON), Apache Commons, Apache Tika, Apache OpenNLP, Boilerpipe, entre otras. Todas las dependencias y el workflow se gestionan mediante Maven.
- Apache Tomcat 7: Servidor de aplicaciones
- Foundation 5: libreria CSS para maquetar las vistas
- AngularJs: para la conexion con el Backend (peticiones Ajax) y para manejar el modelo de datos en la vista

## Back End

- Proceso Batch que se encarga de procesar una lista de Feeds, y en funcion de un diccionario de palabras, selecciona los articulos que considera relevantes. Estos articulos los guarda en una Base de Datos, para ser procesados manualmente por un usuario.
- Ademas expone un API REST para que el frontend pueda consultar datos a traves de llamadas Ajax.

## Front End

- A traves de una aplicacion Web los usuarios pueden:
	* Gestionar los Feeds que se deben de procesar
	* Decidir que articulos son relevantes y guardarlos como Copis de Experience


