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
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan");
        cliente.setPrimerApellido("Pérez");
        cliente.setSegundoApellido("García");
        cliente.setNumeroDocumento("123456789");

        clienteService.insertarCliente(cliente);

        Contrato contrato = new Contrato();
        contrato.setFechaVigencia(LocalDate.now());
        contrato.setFechaCaducidad(LocalDate.now().plusYears(1));
        contrato.setPrecioMensual(100.0);
        contrato.setCliente(cliente);

        contratoService.insertarContrato(contrato);

        // Consultar todos los clientes y contratos
        List<Cliente> clientes = clienteService.obtenerTodosLosClientes();
        List<Contrato> contratos = contratoService.obtenerTodosLosContratos();

        // Mostrar resultados
        clientes.forEach(System.out::println);
        contratos.forEach(System.out::println);
    }
}

