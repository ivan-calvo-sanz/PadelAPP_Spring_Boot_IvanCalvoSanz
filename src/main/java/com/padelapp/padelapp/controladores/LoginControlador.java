package com.padelapp.padelapp.controladores;

// El controlador va a recibir los campos del formulario, llamar al servicio y decidir a qué página redirigir
// @PostMapping conecta el método con una petición POST, y @RequestParam recibe los campos del formulario

import java.util.Optional;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.padelapp.padelapp.entidades.Usuario;
import com.padelapp.padelapp.servicios.UsuarioServicio;

// Indica que esta clase recibe peticiones web y puede responder con redirecciones.
@Controller
public class LoginControlador {

    // Spring inyecta el servicio que contiene la operación de autenticación.
    private final UsuarioServicio usuarioServicio;

    LoginControlador(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    // Atiende los formularios enviados mediante POST a la dirección /login.
    @PostMapping("/login")
    public String procesarLogin(@RequestParam("user") String usuario, @RequestParam("pass") String contrasena,
            HttpSession sesion) {

        // Pide al servicio que busque al usuario con las credenciales recibidas.
        Optional<Usuario> resultado = usuarioServicio.autenticar(usuario, contrasena);

        // Si no hay coincidencia, vuelve a la página inicial con una marca de error.
        if (resultado.isEmpty())
            return "redirect:/?error=1";

        // Obtiene el usuario encontrado y su rol.
        Usuario usuarioEncontrado = resultado.get();
        Integer rol = usuarioEncontrado.getUsRol();

        // Decide la página de destino según el rol.
        String destino;

        if (rol == null) {
            return "redirect:/?error=rol";
        } else if (rol == 1 || rol == 2) {
            destino = "redirect:/home_root.html";
        } else if (rol == 3) {
            destino = "redirect:/home_jugador.html";
        } else {
            return "redirect:/?error=rol";
        }

        // Guarda datos del usuario en sesión para usarlos en otras peticiones
        sesion.setAttribute("id_usuario", usuarioEncontrado.getIdUsuario());
        sesion.setAttribute("nombre_user", usuarioEncontrado.getUsuario());
        sesion.setAttribute("us_rol", rol);

        // Envía el navegador a la página correspondiente.
        return destino;
    }
}
