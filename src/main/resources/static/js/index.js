// Espera a que el navegador termine de cargar y leer el HTML de la página.
document.addEventListener("DOMContentLoaded", function () {

  // Busca en el HTML el elemento cuyo id es "login-eye".
  const iconoOjo = document.getElementById("login-eye");
  // Busca el campo de contraseña cuyo id es "login-pass".
  const inputPass = document.getElementById("login-pass");

  // Si falta alguno de esos elementos, termina esta función para evitar errores.
  if (!iconoOjo || !inputPass) return;

  // Añade un evento: cuando se haga clic en el icono, ejecuta la función cambiar.
  iconoOjo.addEventListener("click", cambiar);

  // Define la función que alterna entre mostrar y ocultar la contraseña.
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
});