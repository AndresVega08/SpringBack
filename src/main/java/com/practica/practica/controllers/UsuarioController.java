package com.practica.practica.controllers;

import com.practica.practica.dao.UsuarioDao;
import com.practica.practica.models.Usuario;
import com.practica.practica.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/usuarios/{id}")
    public Usuario getUsuario(@PathVariable Long id) {
        Usuario usuario =new Usuario();
        usuario.setId(id);
        usuario.setNombre("Andres");
        usuario.setApellido("Vega");
        usuario.setEmail("andres@gmail.com");
        usuario.setTelefono("31526234");
        return usuario;
}
    @RequestMapping(value = "api/usuarios",  method = RequestMethod.GET)
    public List <Usuario> getUsuarios(@RequestHeader(value = "Authorization") String token) {

        String usuarioId = jwtUtil.getKey(token);
        if(usuarioId == null){
            return new ArrayList<>();
        }

       return usuarioDao.getUsuarios();
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void registrartUsuario(@RequestBody Usuario usuario) {

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);
       usuarioDao.registrar(usuario);
    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.PUT)
    public Usuario editar() {
        Usuario usuario = new Usuario();
        usuario.setNombre("");
        usuario.setApellido("");
        usuario.setEmail("");
        usuario.setTelefono("");
        return usuario;
    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar(@PathVariable Long id) {
        usuarioDao.eliminar(id);
    }

    @RequestMapping(value = "api/usuario7")
    public Usuario buscar() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Andres");
        usuario.setApellido("Vega");
        usuario.setEmail("andres@gmail.com");
        usuario.setTelefono("31526234");
        return usuario;
    }
}
