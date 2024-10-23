package com.nttdata.nttdatacenters_hibernate_t1_OEDL;

import java.util.List;

public class ContratoService {
    private ContratoDAO contratoDAO;

    public ContratoService(ContratoDAO contratoDAO) {
        this.contratoDAO = contratoDAO;
    }

    public void insertarContrato(Contrato contrato) {
        contratoDAO.insertar(contrato);
    }

    public List<Contrato> obtenerTodosLosContratos() {
        return contratoDAO.consultarTodos();
    }

    public Contrato obtenerContratoPorId(int id) {
        return contratoDAO.consultarPorId(id);
    }

    public void eliminarContrato(int id) {
        contratoDAO.eliminar(id);
    }

    public void actualizarContrato(Contrato contrato) {
        contratoDAO.actualizar(contrato);
    }

    public List<Contrato> buscarContratosPorClienteId(int clienteId) {
        return contratoDAO.buscarPorClienteId(clienteId);
    }
}

