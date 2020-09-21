# spring-boot-empleados
Proyecto de Spring Boot con bases de datos H2 acerca de un sistema de empleados y departamentos

## Instalación/Despliegue

Se descarga el proyecto desde GitHub y después se realiza los siguientes comandos
```
mvn clean install

mvn spring-boot:run
```
Después de que el comando termine de desplegar se puede consumir los servicios correspondientes.

## Descripción de los diferentes servicios

**uploadEmployees:** Servicio web encargado en el cargue del archivo a la base de datos.

**listTopFiveSalaries:** Servicio web que permite obtener los 5 empleados con el salario más alto.

**listDepartmentAverageSalaries:** Servicio web que permite obtener la sumatoria de salarios de un departamento, dando el nombre del departamento como parámetro de entrada en su query

**listEmployeesByDepartment:** Servicio web que permita consultar de manera paginada los empleados de un departamento, dando el nombre del departamento como parámetro de entrada en su query. Este servicio cuenta con paginación 

Los consumos de los servicios se pueden observar a continuación:
```
uploadEmployees (POST) - http://localhost:8080/empleados/v0/empleado

uploadEmployees Body - Type: form-data, key: file, value: empleados.csv

listTopFiveSalaries (GET) - http://localhost:8080/empleados/v0/empleado/top-cinco-salarios

listDepartmentAverageSalaries (GET) - http://localhost:8080/empleados/v0/departamento/salario?departamento=City Manager's Office

listEmployeesByDepartment (GET) - http://localhost:8080/empleados/v0/departamento?departamento=City Manager's Office
```
**Nota:** El archivo empleados.csv esta en la carpeta **CSV** encontrada en la ruta base del proyecto.

Para observar que el archivo .csv fue exitosamente cargado en la bases de datos H2 con el servicio **uploadEmployees**, se puede acceder a la consola en la siguiente ruta:
```
http://localhost:8080/h2-console
```
*Donde:*
- Ruta de la base de datos: jdbc:h2:mem:empleadosdb
- Usuario: sebastian
- Clave: so

![enter image description here](https://i.ibb.co/vQPNZML/H2database.png)

## Descripción del proyecto

Se tiene un archivo CSV que contiene un listado de empleados. Es necesario cargar el contenido del archivo a una base de datos. Posteriormente, se ejecutarán análisis sobre los datos, cuyos resultados servirán para la toma de decisiones estratégicas en la compañía.

**API REST a Implementar:** a continuación se describe cada uno de los servicios que deben ser implementados.

**1.** Servicio web que realice el cargue del archivo a la base de datos. Esto
incluye:
- Crear tabla Departamento (la tabla departamento tiene muchos
empleados).
- Crear la tabla Empleado (el empleado pertenece a un solo
departamento).
- Implementar método que permita leer el archivo csv.
- Implementar método que por cada línea leída desde el archivo cree un
registro en la tabla Empleado y lo asocie con el departamento
asignado. Si el departamento no existe debe crearlo.

**2.** Servicio web que permita obtener desde la base de datos los 5 empleados con el salario más alto.

**3.** Servicio web que permita obtener desde la base de datos la sumatoria de salarios agrupados por departamento.

**4.** Servicio web que permita consultar de manera paginada los empleados de un departamento dada la clave primaria (id) del departamento. El servicio debe ser paginado porque pueden ser muchos empleados por departamento.

# Desarrollado en

* [IntelliJ IDEA](https://www.jetbrains.com/idea/) - The Java IDE
* [Maven](https://maven.apache.org/) - Dependency Management
* Java 11 JDK

