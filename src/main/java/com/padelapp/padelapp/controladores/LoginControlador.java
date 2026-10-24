package com.padelapp.padelapp.controladores;

import java.util.Optional;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.padelapp.padelapp.entidades.Usuario;
import com.padelapp.padelapp.servicios.UsuarioServicio;

// El controlador va a recibir los campos del formulario, llamar al servicio y decidir a qué página redirigir

// Indica que esta clase recibe peticiones web y puede responder con redirecciones
@Controller
public class LoginControlador {

    // Atributo que referencia al Servicio Usuario
    private final UsuarioServicio usuarioServicio;

    // Spring inyecta el servicio recibido y lo guarda en el atributo.
    LoginControlador(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    /**
     * METODO Procesa el formulario de inicio de sesión enviado mediante POST a /login -> en el html <form action="/login" method="post">
     * 
     * @PostMapping conecta el método con una petición POST
     * @RequestParam recibe los campos del formulario
     *
     * @param usuario    nombre recibido del campo "user" del formulario -> en el html <input name="user">
     * @param contrasena contraseña recibida del campo "pass" del formulario -> en el html <input name="pass">
     * @param sesion     sesión HTTP es el objeto que permite guardar datos asociados a la sesión
     * 
     * @return una redirección a la página correspondiente o a la página de inicio si hay un error
     */
    @PostMapping("/login")
    public String procesarLogin(@RequestParam("user") String usuario, @RequestParam("pass") String contrasena, HttpSession sesion) {

        // El Optional contiene el usuario si se encontró; si no, está vacío
        Optional<Usuario> resultado = usuarioServicio.autenticar(usuario, contrasena);
        // Si no se encuentra el usuario, vuelve a la página de inicio e indica un error en la URL (parametro "error=credenciales" en la URL)
        if (resultado.isEmpty())
            return "redirect:/?error=credenciales";

        // Obtiene el usuario encontrado y su rol
        Usuario usuarioEncontrado = resultado.get();
        // Integer puede ser null; se comprueba antes de decidir la página de destino
        Integer rol = usuarioEncontrado.getUsRol();

        // Determina la página de destino según el rol del usuario
        String destino;
        if (rol == null) {
            return "redirect:/?error=rol"; // envia el navegador a la ruta / y añade el parametro "error=rol" a la URL
        } else if (rol == 1 || rol == 2) {
            destino = "redirect:/home_root.html"; // envia el navegador a la ruta /home_root.html
        } else if (rol == 3) {
            destino = "redirect:/home_jugador.html"; // envia el navegador a la ruta /home_jugador.html
        } else {
            return "redirect:/?error=rol"; // envia el navegador a la ruta / y añade el parametro "error=rol" a la URL
        }

        // Guarda en la sesión el identificador, el nombre de usuario y el rol
        sesion.setAttribute("id_usuario", usuarioEncontrado.getIdUsuario());
        sesion.setAttribute("nombre_user", usuarioEncontrado.getUsuario());
        sesion.setAttribute("us_rol", rol);

        // Indica al navegador que solicite la página asignada al rol
        return destino;
    }
}
