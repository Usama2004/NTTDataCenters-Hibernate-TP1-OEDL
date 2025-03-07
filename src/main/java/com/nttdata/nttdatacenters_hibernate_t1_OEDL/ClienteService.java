package com.nttdata.nttdatacenters_hibernate_t1_OEDL;

import java.util.List;

public class ClienteService {
    private ClienteDAO clienteDAO;

    public ClienteService(ClienteDAO clienteDAO) {
    	this.clienteDAO = clienteDAO; // Asignar el parámetro al campo de instancia
    }

    public void insertarCliente(Cliente cliente) {
        clienteDAO.insertar(cliente);
    }

    public List<Cliente> obtenerTodosLosClientes() {
        return clienteDAO.consultarTodos();
    }

    public Cliente obtenerClientePorId(int id) {
        return clienteDAO.consultarPorId(id);
    }

    public void eliminarCliente(int id) {
        clienteDAO.eliminar(id);
    }

    public void actualizarCliente(Cliente cliente) {
        clienteDAO.actualizar(cliente);
    }

    public List<Cliente> buscarClientesPorNombreYApellidos(String nombre, String primerApellido, String segundoApellido) {
        return clienteDAO.buscarPorNombreYApellidos(nombre, primerApellido, segundoApellido);
    }
    
    public List<Cliente> getClientesByCriteria(String nombre, String primerApellido, String segundoApellido) {
        return clienteDAO.buscarPorNombreYApellidosCriteria(nombre, primerApellido, segundoApellido);
    }
}

