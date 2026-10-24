// Espera a que el navegador termine de cargar el HTML
document.addEventListener("DOMContentLoaded", function () {


  /* ***********************************************************
     CONTROL OJO ABIERTO CERRADO DEL PASSWORD
  *********************************************************** */
  // Busca el icono del ojo y el campo de contraseña.
  const iconoOjo = document.getElementById("login-eye");
  const inputPass = document.getElementById("login-pass");

  // Añade un evento: cuando se haga clic en el icono, ejecuta la función cambiar
  iconoOjo.addEventListener("click", cambiar);

  /**
   * FUNCION alterna el ojo abierto o cerrado de mostrar y ocultar la contraseña
   */
  function cambiar() {
    // Comprueba si el campo está ocultando la contraseña.
    if (inputPass.getAttribute("type") === "password") {
      // Cambia el campo a texto para que la contraseña se pueda ver.
      inputPass.setAttribute("type", "text");
      // Añade la clase del icono de ojo abierto.
      iconoOjo.classList.add("fa-eye");
      // Quita la clase del icono de ojo tachado.
      iconoOjo.classList.remove("fa-eye-slash");
    } else {
      // Si la contraseña se ve, la oculta
      inputPass.setAttribute("type", "password");
      // Quita la clase del icono de ojo abierto.
      iconoOjo.classList.remove("fa-eye");
      // Añade la clase del icono de ojo tachado.
      iconoOjo.classList.add("fa-eye-slash");
    }
  }
  /* ***********************************************************
     FIN
  *********************************************************** */

  /*  **********************************************************
      CONTROL AVISOS DE ERROR DE USUARIO Y CONTRASEÑA INCORRECTA
  *********************************************************** */
  // Lee los parámetros que aparecen en la URL, por ejemplo ?error=credenciales.
  const parametros = new URLSearchParams(window.location.search);
  // Obtiene el valor del parámetro "error".
  const error = parametros.get("error");
  // Busca el contenedor donde se mostrará el mensaje.
  const aviso = document.getElementById("mensaje-error");

  // prepara el mensaje correspondiente..
  if (aviso && error === "credenciales") {
    aviso.innerHTML = `<i class="fa-solid fa-circle-xmark fa-lg"></i> Usuario o contraseña incorrectos`;
    aviso.style.display = "block";
  } else if (aviso && error === "rol") {
    aviso.innerHTML = `<i class="fa-solid fa-circle-xmark fa-lg"></i> El usuario no tiene un rol válido`;
    aviso.style.display = "block";
  }
  /* ***********************************************************
     FIN
  *********************************************************** */

});