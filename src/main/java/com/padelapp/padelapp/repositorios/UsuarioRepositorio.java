package com.padelapp.padelapp.repositorios;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.padelapp.padelapp.entidades.Usuario;

// Proporciona operaciones JPA para la entidad Usuario.
// <Usuario, Integer> indica la entidad y el tipo de su clave primaria.
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

    // JpaRepository incluye operaciones generales, pero no conoce de antemano los
    // campos propios de tu tabla, como usuario y contrasena
    // por ello hay que hacer el método concreto, en Jpa identifica el nombre que se
    // le da al metodo
    // tienen que ser los mismo que la bbdd los campos que queremos buscar
    // de esta forma JpaRepository genera la consulta SQL automaticamente SELECT *
    // FROM tblusuario WHERE usuario = ? AND contrasena = ?;
    // Devuelve un usuario si encuentra una coincidencia; si no, Optional estará
    // vacío
    Optional<Usuario> findByUsuarioAndContrasena(String usuario, String contrasena);

    // para consultas mas especificas JpaRepository necesita que le indiquemos cual
    // es la consulta
    // sería algo así
    // @Query("""SELECT u FROM Usuario u WHERE u.usuario = :usuario AND u.contrasena
    // = :contrasena""")
    // Optional<Usuario> buscarPorCredenciales(@Param("usuario") String usuario,
    // @Param("contrasena") String contrasena
}
