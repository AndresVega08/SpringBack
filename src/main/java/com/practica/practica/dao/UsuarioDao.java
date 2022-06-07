package com.practica.practica.dao;

import com.practica.practica.models.Usuario;
import java.util.List;

public interface UsuarioDao {

    List<Usuario> getUsuarios();

    void eliminar(Long id);

    void registrar(Usuario usuario);

    Usuario obtenerCredenciales(Usuario usuario);
}
