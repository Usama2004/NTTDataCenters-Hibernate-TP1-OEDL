package com.nttdata.nttdatacenters_hibernate_t1_OEDL;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ContratoDAOImpl implements ContratoDAO {
    private SessionFactory sessionFactory;

    public ContratoDAOImpl() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public void insertar(Contrato contrato) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(contrato);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Contrato> consultarTodos() {
        Session session = sessionFactory.openSession();
        List<Contrato> contratos = session.createQuery("from Contrato", Contrato.class).list();
        session.close();
        return contratos;
    }

    @Override
    public Contrato consultarPorId(int id) {
        Session session = sessionFactory.openSession();
        Contrato contrato = session.get(Contrato.class, id);
        session.close();
        return contrato;
    }

    @Override
    public void eliminar(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Contrato contrato = session.get(Contrato.class, id);
        if (contrato != null) {
            session.delete(contrato);
        }
        transaction.commit();
        session.close();
    }

    @Override
    public void actualizar(Contrato contrato) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(contrato);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Contrato> buscarPorClienteId(int clienteId) {
        Session session = sessionFactory.openSession();
        List<Contrato> contratos = session.createQuery("from Contrato where cliente.id = :clienteId", Contrato.class)
                                          .setParameter("clienteId", clienteId)
                                          .list();
        session.close();
        return contratos;
    }
}

