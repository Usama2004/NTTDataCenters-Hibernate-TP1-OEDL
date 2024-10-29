package com.nttdata.nttdatacenters_hibernate_t1_OEDL;

import java.time.LocalDate;
import java.util.List;

public class App {
    public static void main(String[] args) {
        // Inicializar DAOs y Servicios
        ClienteDAO clienteDAO = new ClienteDAOImpl();
        ContratoDAO contratoDAO = new ContratoDAOImpl();
        ClienteService clienteService = new ClienteService(clienteDAO);
        ContratoService contratoService = new ContratoService(contratoDAO);
        
        // Ejemplo de uso de los servicios
        Cliente cliente = new Cliente("Juan", "Perez", "Garcia", "123456789");
        clienteService.insertarCliente(cliente);
        
        // Verificar que el cliente se haya persistido correctamente
        if (cliente.getId() > 0) {
            System.out.println("Cliente persistido correctamente con ID: " + cliente.getId());

            Contrato contrato = new Contrato();
            contrato.setFechaVigencia(LocalDate.now());
            contrato.setFechaCaducidad(LocalDate.now().plusYears(1));
            contrato.setPrecioMensual(100.0);
            contrato.setCliente(cliente);

            // Persistir el contrato después de persistir el cliente
            contratoService.insertarContrato(contrato);

            if (contrato.getId() > 0) {
                System.out.println("Contrato persistido correctamente con ID: " + contrato.getId());
            } else {
                System.out.println("Error: El contrato no se ha persistido correctamente.");
            }
        } else {
            System.out.println("Error: El cliente no se ha persistido correctamente.");
        }

        // Consultar todos los clientes y contratos
        List<Cliente> clientes = clienteService.obtenerTodosLosClientes();
        List<Contrato> contratos = contratoService.obtenerTodosLosContratos();

        // Mostrar resultados
        clientes.forEach(System.out::println);
        contratos.forEach(System.out::println);
    }
}
