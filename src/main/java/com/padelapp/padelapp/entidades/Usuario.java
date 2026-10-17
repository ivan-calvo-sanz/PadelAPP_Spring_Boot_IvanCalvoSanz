package com.padelapp.padelapp.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // indica que esta clase se mapea a una BBDD
@Table(name = "tblusuario") // nombre con el que se mapea esta Clase a la BBDD
@Getter // Genera los métodos get...
@Setter // Genera los métodos set...
@NoArgsConstructor // Genera el constructor sin parámetros que necesita JPA.
public class Usuario {

    @Id // indica que este atributo "id_usuario" es la Clave primaria de la Tabla para que genera automaticamente el id, cada Usuario tiene uno único y la base de datos se encarga de asignarlo
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "usuario", nullable = false, length = 45)
    private String usuario;

    @Column(name = "contrasena", nullable = false, length = 45)
    private String contrasena;

    @Column(name = "us_rol", nullable = false)
    private Integer usRol;
}
