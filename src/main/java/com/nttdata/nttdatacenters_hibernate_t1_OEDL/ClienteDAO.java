package com.nttdata.nttdatacenters_hibernate_t1_OEDL;

import java.util.List;

public interface ClienteDAO {
    void insertar(Cliente cliente);
    List<Cliente> consultarTodos();
    Cliente consultarPorId(int id);
    void eliminar(int id);
    void actualizar(Cliente cliente);
    List<Cliente> buscarPorNombreYApellidos(String nombre, String primerApellido, String segundoApellido);
}

