package com.nttdata.nttdatacenters_hibernate_t1_OEDL;

import java.util.List;

public interface ContratoDAO {
    void insertar(Contrato contrato);
    List<Contrato> consultarTodos();
    Contrato consultarPorId(int id);
    void eliminar(int id);
    void actualizar(Contrato contrato);
    List<Contrato> buscarPorClienteId(int clienteId);
    //Nuevo método usando JPA Criteria
    List<Contrato> buscarPorClienteIdCriteria(int clienteId);
}
