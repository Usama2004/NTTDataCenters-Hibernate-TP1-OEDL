package com.nttdata.nttdatacenters_hibernate_t1_OEDL;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


public class ContratoDAOImpl implements ContratoDAO {
    private SessionFactory sessionFactory;
    @PersistenceContext
    private EntityManager entityManager;
    
    public ContratoDAOImpl() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        //Inicializar EntityManagerFactory y EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("prueba");
        this.entityManager = emf.createEntityManager();
    }

    @Override
    public void insertar(Contrato contrato) {
        //Session session = sessionFactory.openSession();
        //Transaction transaction = session.beginTransaction();
        //session.save(contrato);
        //transaction.commit();
        //session.close();
    	entityManager.getTransaction().begin();
    	entityManager.persist(contrato);
    	entityManager.getTransaction().commit();
    }

    @Override
    public List<Contrato> consultarTodos() {
        //Session session = sessionFactory.openSession();
        //List<Contrato> contratos = session.createQuery("from Contrato", Contrato.class).list();
        //session.close();
        //return contratos;
    	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Contrato> cq = cb.createQuery(Contrato.class);
        Root<Contrato> root = cq.from(Contrato.class);
        cq.select(root);
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public Contrato consultarPorId(int id) {
        //Session session = sessionFactory.openSession();
        //Contrato contrato = session.get(Contrato.class, id);
        //session.close();
        //return contrato;
    	return entityManager.find(Contrato.class, id);
    }

    @Override
    public void eliminar(int id) {
        //Session session = sessionFactory.openSession();
        //Transaction transaction = session.beginTransaction();
        //Contrato contrato = session.get(Contrato.class, id);
        //if (contrato != null) {
            //session.delete(contrato);
        //}
        //transaction.commit();
        //session.close();
    	Contrato contrato = consultarPorId(id);
        if (contrato != null) {
            entityManager.remove(contrato);
        }
    }

    @Override
    public void actualizar(Contrato contrato) {
        //Session session = sessionFactory.openSession();
        //Transaction transaction = session.beginTransaction();
        //session.update(contrato);
        //transaction.commit();
        //session.close();
    	entityManager.merge(contrato);
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
    
    public List<Contrato> buscarPorClienteIdCriteria(int clienteId) {
        // Implementación usando JPA Criteria
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Contrato> cq = cb.createQuery(Contrato.class);
        Root<Contrato> root = cq.from(Contrato.class);
        Predicate clienteIdPredicate = cb.equal(root.get("cliente").get("id"), clienteId);
        cq.where(clienteIdPredicate);
        return entityManager.createQuery(cq).getResultList();
    }
}

