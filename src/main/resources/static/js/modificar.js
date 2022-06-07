
$(document).ready(function() {
//on ready

});

async function modificarUsuario(){

    let datos ={};
    datos.nombre= document.getElementById('txtNombre').value;
    datos.apellido= document.getElementById('txtApellido').value;
    datos.email= document.getElementById('txtEmail').value;
    datos.password= document.getElementById('txtPassword').value;

    let repetirPassword = document.getElementById('txtRepetirPassword').value;

    if(repetirPassword != datos.password){
    alert("las contraseñas no coinciden");
    return;
    }

    const request = await fetch('api/usuarios', {
        method: 'PUT',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
      });

      alert("La cuenta fue actualizada")
      window.location.href = 'usuarios.html'
}
