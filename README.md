# Sistema de Gestión de Incidencias con JPA y EJB

Este proyecto es una aplicación web desarrollada en Java que permite la gestión de **empleados** e **incidencias** dentro de una empresa. Los empleados pueden registrarse, iniciar sesión y gestionar incidencias mediante una interfaz web basada en JSP, conectada a una base de datos MySQL mediante **JPA (Java Persistence API)** y **EJB (Enterprise Java Beans)**.

---

## 🧱 Estructura del Proyecto

- `src/java/pojo/` → Clases POJO `Empleado` e `Incidencia`
- `src/java/ejb/` → Beans EJB para gestionar empleados e incidencias
- `src/java/servlets/` → Servlets que comunican la lógica con las JSP
- `web/` → Ficheros JSP (menús de empleados e incidencias)
- `web/css/` → Archivos CSS personalizados para el diseño visual
- `resources/` → 
  - Archivo `persistence.xml` (configuración de JPA)
  - Archivo `DDBB.sql` con el script de creación de la base de datos y datos de prueba

---

## 🗃️ Base de Datos (MySQL)

Se utilizan dos tablas principales:

### Empleados

| Campo           | Tipo       | Descripción                        |
|----------------|------------|------------------------------------|
| id             | INT (PK)   | Identificador único del empleado   |
| usuario        | VARCHAR    | Nombre de usuario                  |
| contraseña     | VARCHAR    | Contraseña                         |
| nombreCompleto | VARCHAR    | Nombre completo del empleado       |
| telefono       | VARCHAR    | Teléfono de contacto               |

### Incidencias

| Campo          | Tipo       | Descripción                              |
|----------------|------------|------------------------------------------|
| id            | INT (PK)   | Identificador único de la incidencia     |
| fechaHora     | DATETIME   | Fecha y hora de creación                 |
| detalle       | TEXT       | Descripción de la incidencia             |
| tipo          | CHAR(1)    | Tipo de incidencia: U (Urgente), N (Normal) |
| empleadoOrigen| FK         | Empleado que crea la incidencia          |
| empleadoDestino| FK        | Empleado al que va dirigida la incidencia |

> 🔧 **Nota:** Puedes crear la base de datos y cargar datos de prueba ejecutando el script `resources/DDBB.sql` en tu gestor de bases de datos MySQL.

---

## 🖥️ Funcionalidades

### Gestión de Empleados (vía `empleados.jsp`)

- ✅ Insertar un nuevo empleado en la base de datos
- 🔐 Validar usuario y contraseña (inicio de sesión)
- 📝 Modificar el perfil de un empleado existente
- 🔑 Cambiar la contraseña de un empleado
- 🗑️ Eliminar un empleado

### Gestión de Incidencias (vía `incidencias.jsp`)

Accesible tras el inicio de sesión exitoso.

- 🔍 Obtener una incidencia por su ID
- 📜 Listar todas las incidencias del sistema
- ➕ Insertar una nueva incidencia
- 📤 Listar incidencias creadas por un empleado
- 📥 Listar incidencias destinadas a un empleado

---

## 🔧 Tecnologías Utilizadas

- Java EE (JPA, EJB)
- JSP + Servlets
- MySQL
- CSS personalizado
- Apache Tomcat / GlassFish (como servidor de aplicaciones)

---

# 📌 Proyecto de Gestión de Incidencias con Java EE, JPA y EJB

Este proyecto es una aplicación web para la gestión de incidencias entre empleados. Utiliza Java EE, JPA, EJB y MySQL como sistema gestor de base de datos. Está orientado al aprendizaje del desarrollo con tecnologías empresariales en Java.

## 🧱 Estructura del Proyecto

- **POJO:** Contiene las clases `Empleado` e `Incidencia`.
- **Servlets y EJBs:** Para manejar la lógica de negocio y el acceso a datos.
- **JSPs:** Interfaz de usuario para empleados e incidencias.
- **resources/DDBB.sql:** Script SQL para crear la base de datos y añadir datos de prueba.
- **css/:** Carpeta que contiene varios archivos CSS que dan estilo a las páginas JSP.

## 🧩 Funcionalidades

### Gestión de Empleados (empleados.jsp)
- Insertar un nuevo empleado.
- Validar el inicio de sesión de un empleado (usuario + contraseña).
- Modificar el perfil de un empleado.
- Cambiar la contraseña.
- Eliminar un empleado.

### Gestión de Incidencias (accesible tras login correcto)
- Obtener una incidencia por su ID.
- Listar todas las incidencias.
- Insertar una nueva incidencia con campos como origen, destino, fecha y tipo.
- Obtener incidencias creadas por un empleado concreto.
- Obtener incidencias destinadas a un empleado.

## ⚙️ Tecnologías Utilizadas

- Java EE (JSP, Servlets, EJB)
- JPA (Hibernate)
- MySQL
- GlassFish o Tomcat
- CSS para diseño visual

## 🚀 Cómo Ejecutar

1. **Clona el repositorio:**

```bash
git clone https://github.com/TU_USUARIO/NOMBRE_DEL_REPOSITORIO.git
```

2. **Importa el proyecto en NetBeans o Eclipse con soporte para Java EE**

3. **Ejecuta el script DDBB.sql**
 que se encuentra en la carpeta resources/ usando tu gestor de base de datos MySQL para crear las tablas y cargar los datos de prueba.

4. **Configura la conexión a la base de datos en el archivo persistence.xml**
 con tus credenciales de MySQL (usuario, contraseña y URL).

5. **Asegúrate de que tienes GlassFish corriendo como servidor de aplicaciones**

6. **Ejecuta el proyecto desde tu entorno de desarrollo**

7. **Accede desde el navegador a:**
http://localhost:8080/NombreProyecto/empleados.jsp

---

📌 **Autor:** *edulumulu*
