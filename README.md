# Sistema de Gestión de Campamentos de Verano

## Descripción

Este repositorio contiene un sistema de gestión de campamentos de verano que utiliza el patrón de diseño Modelo-Vista-Controlador (MVC). El proyecto se ha organizado en dos directorios:

* `src/main/es/uco/pw`
  * `business`: Capa de negocio: contiene la lógica principal del proyecto, incluyendo clases y gestores.
  * `data`: Capa de datos: se encarga de la gestión de los datos y su almacenamiento en una base de datos.
  * `display`: Capa de visualización: se encarga de la interfaz de usuario y la presentación de datos.
* `src/main/webapp`
  * `mvc`: Modelo Vista Controlador: contiene todas las vistas y controladores necesarios para la web
  * `styles`: Carpeta con los estilos del proyecto

## Instrucciones de ejecución

Para ejecutar la aplicación, sigue los siguientes pasos:

1. Asegúrate de que el archivo `summer_camp.war` se encuentre en los ficheros del servidor web Apache Tomcat.

2. Inicie el servidor

3. Al acceder a `http://localhost:8080/summer_camp/` podrá empezar a usar la app.

## Autores

* Francisco de Paula Algar Muñoz
* María Teresa Alba Rueda
* Nuria Garofano Fernández
* Noelia Cobo Carrillo
