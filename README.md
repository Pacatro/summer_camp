# Sistema de gestión de campamentos de verano

## Estructura de carpetas

El espacio de trabajo consta de las siguientes carpetas:

* `src`: la carpeta para mantener las fuentes
  * `classes`: carpeta donde se encuentran todos los modelos del sistema.
  * `database`: carpeta donde se encuentran el controlador de la base de datos.
  * `enums`: carpeta donde se hallan todas las clases enumaradas del sistema.
  * `factory`: carpeta donde se encuentra implementado el patrón abstract factory.
  * `files`: carpeta donde se encuentran los ficheros de texto dedicados a almacenar la información del sistema.
  * `managers`: carpeta donde se encuentran los gestores del sistema.
  * `menu`: carpeta donde se modela el sistema de menús.
  * `Main.java`: clase principal del sistema.
  * `properties.txt`: fichero de texto con las rutas absolutas de los archivos destinados a la base de datos.
* `docs`: carpeta con los archivos HTML de toda la documentación del sistema.
* `summer_camp.jar`: ejecutable del sistema.

Al compilar se creará una carpeta `bin` con los ficheros `.java` compilados en ficheros `.class`.

## Ejecución

Para ejecutar el archivo `summer_camp.jar` es necesario escribir el siguiente comado:

```bash
java -jar summer_camp.jar
```
