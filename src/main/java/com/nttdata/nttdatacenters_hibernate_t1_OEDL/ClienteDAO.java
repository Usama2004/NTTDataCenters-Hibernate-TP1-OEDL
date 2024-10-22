package com.nttdata.nttdatacenters_hibernate_t1_OEDL;

import java.util.List;
/*
La interfaz ClienteDAO define un contrato para las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) 
y de búsqueda específicas para la tabla Cliente.

Proporciona una capa de abstracción que separa la definición de las operaciones de su implementación. 
Esto permite cambiar la implementación sin afectar el resto del código que utiliza la interfaz.
Es decir permite diferentes implementaciones.
*/
public interface ClienteDAO {
    void insertar(Cliente cliente);
    List<Cliente> consultarTodos();
    Cliente consultarPorId(int id);
    void eliminar(int id);
    void actualizar(Cliente cliente);
    List<Cliente> buscarPorNombreYApellidos(String nombre, String primerApellido, String segundoApellido);
}

