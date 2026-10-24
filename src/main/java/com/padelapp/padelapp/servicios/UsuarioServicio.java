package com.padelapp.padelapp.servicios;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.padelapp.padelapp.entidades.Usuario;
import com.padelapp.padelapp.repositorios.UsuarioRepositorio;

@Service // Indica a Spring que esta clase contiene la lógica de servicio de la aplicación.
public class UsuarioServicio {

    // Atributo que referencia al Repositorio de Usuario
    private final UsuarioRepositorio usuarioRepositorio;

    // Spring inyecta el repositorio al crear el servicio.
    UsuarioServicio(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    /**
     * METODO Autentica a un usuario buscando una coincidencia en el repositorio.
     * 
     * @param usuario    nombre de usuario introducido en el formulario
     * @param contrasena contraseña introducida en el formulario
     * @return un Optional con el usuario si las credenciales coinciden; vacío si no se encuentra ninguna coincidencia
     */
    public Optional<Usuario> autenticar(String usuario, String contrasena) {
        return usuarioRepositorio.findByUsuarioAndContrasena(usuario, contrasena);
    }
}
