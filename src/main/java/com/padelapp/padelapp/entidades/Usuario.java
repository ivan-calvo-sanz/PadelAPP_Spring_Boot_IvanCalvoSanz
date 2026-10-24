package com.padelapp.padelapp.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity // Indica a JPA que esta clase representa una entidad persistente.
@Table(name = "tblusuario") // Indica que los datos de esta entidad se guardan en la tabla "tblusuario".
@Getter // Lombok genera automáticamente los métodos getter para los campos de la clase.
@Setter // Lombok genera automáticamente los métodos setter para los campos de la clase.
public class Usuario {

    @Id // Marca este campo como clave primaria de la tabla.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indica que la base de datos genera el valor del identificador, normalmente mediante AUTO_INCREMENT.
    @Column(name = "id_usuario") // Asocia este campo Java con la columna "id_usuario".
    private Integer idUsuario;

    // Asocia este campo con la columna "usuario".
    // nullable = false indica que la columna no admite valores nulos.
    // length = 45 establece la longitud máxima para esta columna de texto.
    @Column(name = "usuario", nullable = false, length = 45)
    private String usuario;

    // Asocia este campo con la columna "contrasena".
    // La columna es obligatoria y admite hasta 45 caracteres.
    @Column(name = "contrasena", nullable = false, length = 45)
    private String contrasena;

    // Asocia este campo con la columna "us_rol".
    // nullable = false indica que cada usuario debe tener un rol.
    @Column(name = "us_rol", nullable = false)
    private Integer usRol;

    // Constructor vacío requerido por JPA para crear instancias de esta entidad.
    public Usuario() {
    }
}
