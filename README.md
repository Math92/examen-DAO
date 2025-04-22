# Clínica Odontológica - Sistema de Gestión

## Descripción
Este proyecto es una aplicación web para la gestión de una clínica odontológica, desarrollada con Spring Boot. Implementa el patrón DAO (Data Access Object) para el acceso a datos y ofrece una API REST para gestionar pacientes, odontólogos y turnos.

## Estructura del Proyecto
- `src/main/java/com/example/clinicaOdontologica/`: Código fuente principal
  - `Controller/`: Controladores REST
    - `OdontologoControllerRest.java`
    - `PacienteControllerRest.java`
    - `TurnoController.java`
  - `Dao/`: Implementaciones del patrón DAO
    - `BD.java`: Gestión de la base de datos
    - `DomicilioDAOH2.java`
    - `OdontologoDAOH2.java`
    - `OdontologoDAOInMemory.java`
    - `PacienteDAOH2.java`
    - `TurnoDAOLISTA.java`
    - `iDao.java`: Interfaz DAO genérica
  - `Model/`: Entidades del modelo
    - `Domicilio.java`
    - `Odontologo.java`
    - `Paciente.java`
    - `Turno.java`
  - `Service/`: Capa de servicio
  - `ClinicaOdontologicaApplication.java`: Clase principal
- `src/main/resources/`: Recursos
  - `static/js/`: JavaScript para el frontend
    - `get_odontologos.js`
    - `post_odontologos.js`
  - `static/`: Archivos HTML
    - `get_odontologos.html`
    - `index.html`
    - `post_odontologos.html`
    - `post_paciente.html`
  - `templates/`: Plantillas
  - `application.properties`: Configuración
  - `log4j.properties`: Configuración de logging
- `src/test/`: Pruebas unitarias
  - `ClinicaOdontologicaApplicationTests.java`
  - `OdontologoServiceTest.java`
  - `PacienteServiceTest.java`

## Requisitos Previos
- Java JDK 11 o superior
- Maven
- Base de datos H2 (incluida como dependencia)

## Instalación
1. Clonar el repositorio
   ```
   git clone https://github.com/Math92/examen-DAO.git
   ```
2. Navegar al directorio del proyecto
   ```
   cd examen-DAO
   ```
3. Compilar el proyecto con Maven
   ```
   mvn clean install
   ```

## Uso
1. Ejecutar la aplicación
   ```
   mvn spring-boot:run
   ```
   o
   ```
   java -jar target/clinica-odontologica.jar
   ```
2. Acceder a la aplicación web
   ```
   http://localhost:8080
   ```

## API REST
La aplicación ofrece los siguientes endpoints:

### Odontólogos
- `GET /odontologos`: Obtener todos los odontólogos
- `POST /odontologos`: Crear un nuevo odontólogo
- `PUT /odontologos`: Actualizar un odontólogo
- `DELETE /odontologos/{id}`: Eliminar un odontólogo

### Pacientes
- `GET /pacientes`: Obtener todos los pacientes
- `POST /pacientes`: Crear un nuevo paciente
- `PUT /pacientes`: Actualizar un paciente
- `DELETE /pacientes/{id}`: Eliminar un paciente

### Turnos
- `GET /turnos`: Obtener todos los turnos
- `POST /turnos`: Crear un nuevo turno
- `DELETE /turnos/{id}`: Eliminar un turno

## Tecnologías Utilizadas
- Java 11
- Spring Boot
- Spring Web
- H2 Database
- JUnit para pruebas unitarias
- Maven como gestor de dependencias
- HTML, CSS y JavaScript para el frontend

## Autor
- [Math92](https://github.com/Math92)
