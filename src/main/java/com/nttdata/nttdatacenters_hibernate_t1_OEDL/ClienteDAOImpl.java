package com.nttdata.nttdatacenters_hibernate_t1_OEDL;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ClienteDAOImpl implements ClienteDAO {
    private SessionFactory sessionFactory;

    public ClienteDAOImpl() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public void insertar(Cliente cliente) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(cliente);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Cliente> consultarTodos() {
        Session session = sessionFactory.openSession();
        List<Cliente> clientes = session.createQuery("from Cliente", Cliente.class).list();
        session.close();
        return clientes;
    }

    @Override
    public Cliente consultarPorId(int id) {
        Session session = sessionFactory.openSession();
        Cliente cliente = session.get(Cliente.class, id);
        session.close();
        return cliente;
    }

    @Override
    public void eliminar(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Cliente cliente = session.get(Cliente.class, id);
        if (cliente != null) {
            session.delete(cliente);
        }
        transaction.commit();
        session.close();
    }

    @Override
    public void actualizar(Cliente cliente) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(cliente);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Cliente> buscarPorNombreYApellidos(String nombre, String primerApellido, String segundoApellido) {
        Session session = sessionFactory.openSession();
        List<Cliente> clientes = session.createQuery("from Cliente where nombre = :nombre and primerApellido = :primerApellido and segundoApellido = :segundoApellido", Cliente.class)
                .setParameter("nombre", nombre)
                .setParameter("primerApellido", primerApellido)
                .setParameter("segundoApellido", segundoApellido)
                .list();
        session.close();
        return clientes;
    }
}

