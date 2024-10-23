package com.nttdata.nttdatacenters_hibernate_t1_OEDL;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		ClienteService clienteService = new ClienteService();

		// Insertar un nuevo cliente
		Cliente cliente1 = new Cliente();
		cliente1.setNombre("Juan");
		cliente1.setPrimerApellido("Pérez");
		cliente1.setSegundoApellido("García");
		cliente1.setNumeroDocumento("123456789");
		clienteService.insertarCliente(cliente1);
		
		// Obtener el ID del cliente insertado
        int cliente1Id = cliente1.getId();
		
		// Insertar un nuevo cliente
		Cliente cliente2 = new Cliente();
		cliente2.setNombre("Oussama");
		cliente2.setPrimerApellido("Ed Dahabi");
		cliente2.setSegundoApellido("Lakhal");
		cliente2.setNumeroDocumento("987654321");
		clienteService.insertarCliente(cliente2);
		
		// Obtener el ID del cliente insertado
        int cliente2Id = cliente1.getId();

		// Consultar todos los clientes
		List<Cliente> clientes = clienteService.obtenerTodosLosClientes();
		clientes.forEach(cliente -> System.out.println("Nombre del cliente: "+cliente.getNombre()));

		// Consultar cliente por ID
		Cliente cliente = clienteService.obtenerClientePorId(cliente1Id);
        if (cliente != null) {
            System.out.println("Nombre del cliente segun el ID: " + cliente.getNombre());
        } else {
            System.out.println("Cliente con ID 1 no encontrado.");
        }

		// Actualizar cliente
		cliente.setNombre("Juan Actualizado");
		clienteService.actualizarCliente(cliente);

		// Eliminar cliente
		clienteService.eliminarCliente(1);

		// Buscar clientes por nombre y apellidos
		List<Cliente> clientesBuscados = clienteService.buscarClientesPorNombreYApellidos("Juan", "Pérez", "García");
		clientesBuscados.forEach(c -> System.out.println("Nombre del cliente en base a nombre, apellido1,apellido2: "+c.getNombre()));
	}
}
