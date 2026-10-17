# PadelApp Spring Boot
Aplicación web en desarrollo orientada a la gestión de usuarios y reservas de pistas de pádel. El proyecto sirve como práctica de desarrollo con Java, Spring Boot, Spring Data JPA y MySQL.

## Estado actual
La aplicación incluye actualmente:
- Una página de inicio de sesión.
- Consulta de usuarios en MySQL mediante Spring Data JPA.
- Redirección según el rol del usuario:
  - Roles 1 y 2: página de inicio para administrador.
  - Rol 3: página de inicio para jugador.
- Almacenamiento de datos básicos del usuario en la sesión.
- Páginas estáticas diferenciadas para administrador y jugador.
- Una estructura organizada por capas: controlador, servicio, repositorio y entidad.

La gestión completa de usuarios y reservas está prevista para futuras etapas del proyecto.

## Tecnologías:
- Java 21
- Spring Boot 4.1.1
- Maven
- Spring MVC
- Spring Data JPA
- Hibernate
- MySQL
- HTML, CSS y JavaScript
- Lombok

## Estructura principal:
src/main/java/com/padelapp/padelapp/
├── controladores/
│   └── LoginControlador.java
├── entidades/
│   └── Usuario.java
├── repositorios/
│   └── UsuarioRepositorio.java
├── servicios/
│   └── UsuarioServicio.java
└── PadelappApplication.java

src/main/resources/
├── application.properties
└── static/
    ├── index.html
    ├── home_root.html
    ├── home_jugador.html
    ├── css/
    ├── img/
    └── js/

## Requisitos
Para ejecutar la aplicación necesitas:
- Java 21.
- MySQL en ejecución, por ejemplo, desde XAMPP.
- Una base de datos llamada `padel`.
- La tabla `tblusuario` con los campos que utiliza la entidad `Usuario`.

El proyecto tiene configurado `spring.jpa.hibernate.ddl-auto=none`, así que Hibernate no crea las tablas automáticamente. La base de datos y su esquema deben estar preparados antes de iniciar la aplicación.

## Preparar la base de datos

## Configurar la conexión a MySQL
src/main/resources/application.properties

Revisa estos valores y ajústalos a tu instalación local:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/padel
spring.datasource.username=root
spring.datasource.password=
server.port=8081
```

No guardes contraseñas reales en un repositorio público.

## Ejecutar en Windows:
1. Inicia MySQL desde XAMPP o desde el servicio que utilices.
2. Abre PowerShell en la carpeta raíz del proyecto, donde está `pom.xml`.
3. Ejecuta:
```powershell
.\mvnw.cmd spring-boot:run
```

Cuando Spring Boot indique que la aplicación se ha iniciado, abre:
[http://localhost:8081](http://localhost:8081)

Para compilar el proyecto sin iniciarlo, puedes ejecutar:
```powershell
.\mvnw.cmd compile
```

## Ejecutar en macOS o Linux
Desde la carpeta raíz del proyecto, ejecuta:
```bash
./mvnw spring-boot:run
```

## Flujo del inicio de sesión:
1. El usuario introduce sus credenciales en el formulario de la página principal.
2. El formulario envía una petición `POST` a `/login`.
3. `LoginControlador` recibe los datos y llama a `UsuarioServicio`.
4. El servicio solicita al repositorio que busque un usuario con esas credenciales.
5. `UsuarioRepositorio` realiza la consulta a través de Spring Data JPA.
6. Si las credenciales coinciden, el controlador guarda datos básicos del usuario en la sesión y lo redirige según su rol.
7. Si no coinciden, el usuario vuelve a la página de inicio con una indicación de error.

## Arquitectura:
El flujo principal sigue esta secuencia:
Navegador → Controlador → Servicio → Repositorio → Base de datos

- **Controlador:** recibe la petición web y decide la respuesta o redirección
- **Servicio:** contiene la lógica de la aplicación y coordina las operaciones
- **Repositorio:** proporciona acceso a los datos mediante Spring Data JPA
- **Entidad:** representa los datos de usuario que se almacenan en la tabla `tblusuario`
