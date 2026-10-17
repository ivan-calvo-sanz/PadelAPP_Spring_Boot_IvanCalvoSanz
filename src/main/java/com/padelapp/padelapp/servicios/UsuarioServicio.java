package com.padelapp.padelapp.servicios;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.padelapp.padelapp.entidades.Usuario;
import com.padelapp.padelapp.repositorios.UsuarioRepositorio;

@Service
public class UsuarioServicio {

    // inyección de dependencias.
    private final UsuarioRepositorio usuarioRepositorio;

    UsuarioServicio(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    // ***** FUNCION *****
    // La función de autenticación delega la búsqueda al repositorio.
    public Optional<Usuario> autenticar(String usuario, String contrasena) {
        return usuarioRepositorio.findByUsuarioAndContrasena(usuario, contrasena);
    }
}
