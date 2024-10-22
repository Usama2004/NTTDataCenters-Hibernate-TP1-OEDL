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
		Cliente nuevoCliente = new Cliente();
		nuevoCliente.setNombre("Juan");
		nuevoCliente.setPrimerApellido("Pérez");
		nuevoCliente.setSegundoApellido("García");
		nuevoCliente.setNumeroDocumento("123456789");
		clienteService.insertarCliente(nuevoCliente);

		// Consultar todos los clientes
		List<Cliente> clientes = clienteService.obtenerTodosLosClientes();
		clientes.forEach(cliente -> System.out.println(cliente.getNombre()));

		// Consultar cliente por ID
		Cliente cliente = clienteService.obtenerClientePorId(1);
		System.out.println(cliente.getNombre());

		// Actualizar cliente
		cliente.setNombre("Juan Actualizado");
		clienteService.actualizarCliente(cliente);

		// Eliminar cliente
		clienteService.eliminarCliente(1);

		// Buscar clientes por nombre y apellidos
		List<Cliente> clientesBuscados = clienteService.buscarClientesPorNombreYApellidos("Juan", "Pérez", "García");
		clientesBuscados.forEach(c -> System.out.println(c.getNombre()));
	}
}
