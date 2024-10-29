package com.nttdata.nttdatacenters_hibernate_t1_OEDL;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class ClienteDAOImpl implements ClienteDAO {
	private SessionFactory sessionFactory;
	@PersistenceContext
	private EntityManager entityManager;

	public ClienteDAOImpl() {
		// SessionFactory es una interfaz de Hibernate que se utiliza para crear y
		// gestionar objetos Session.
		// Una Session es una conexión ligera y de corta duración entre la aplicación y
		// la base de datos.
		//sessionFactory = new Configuration().configure().buildSessionFactory();
		/*
		 * new Configuration(): Crea una instancia de Configuration, que se utiliza para
		 * configurar Hibernate. .configure(): Lee la configuración de Hibernate desde
		 * el archivo hibernate.cfg.xml. .buildSessionFactory(): Construye una instancia
		 * de SessionFactory basada en la configuración.
		 * 
		 * Este código configura una implementación de ClienteDAO utilizando Hibernate
		 * para la gestión de sesiones. La clase ClienteDAOImpl se encarga de
		 * inicializar sessionFactory en su constructor, lo que permite a la clase
		 * gestionar las sesiones de Hibernate para interactuar con la base de datos
		 */
		sessionFactory = new Configuration().configure().buildSessionFactory();
		//Inicializar EntityManagerFactory y EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("prueba");
        this.entityManager = emf.createEntityManager();
	}

	// Propósito: Inserta un nuevo objeto Cliente en la base de datos.
	@Override
	public void insertar(Cliente cliente) {
		// Abre una nueva sesión.
		//Session session = sessionFactory.openSession();
		// Inicia una transacción.
		//Transaction transaction = session.beginTransaction();
		// Guarda el objeto Cliente en la base de datos.
		//session.save(cliente);
		// Confirma la transacción.
		//transaction.commit();
		// Cierra la sesión.
		//session.close();
		entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();
	}

	// Recupera todos los objetos Cliente de la base de datos.
	@Override
	public List<Cliente> consultarTodos() {
		// Abre una nueva sesión.
		//Session session = sessionFactory.openSession();
		// Ejecuta una consulta HQL ("from Cliente") para obtener todos los clientes.
		//List<Cliente> clientes = session.createQuery("from Cliente", Cliente.class).list();
		/*
		 * Crea una consulta HQL (Hibernate Query Language). La consulta "from Cliente"
		 * selecciona todos los registros de la tabla Cliente
		 */
		//session.close();
		//return clientes;
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cliente> cq = cb.createQuery(Cliente.class);
        Root<Cliente> root = cq.from(Cliente.class);
        cq.select(root);
        return entityManager.createQuery(cq).getResultList();
	}

	@Override
	public Cliente consultarPorId(int id) {
		return entityManager.find(Cliente.class, id);
	}

	@Override
	public void eliminar(int id) {
		//Session session = sessionFactory.openSession();
		//Transaction transaction = session.beginTransaction();
		//Cliente cliente = session.get(Cliente.class, id);
		//if (cliente != null) {
			//session.delete(cliente);
		//}
		//transaction.commit();
		//session.close();
		Cliente cliente = consultarPorId(id);
        if (cliente != null) {
            entityManager.remove(cliente);
        }
	}

	@Override
	public void actualizar(Cliente cliente) {
		//Session session = sessionFactory.openSession();
		//Transaction transaction = session.beginTransaction();
		//session.update(cliente);
		//transaction.commit();
		//session.close();
		entityManager.merge(cliente);
	}

	@Override
	public List<Cliente> buscarPorNombreYApellidos(String nombre, String primerApellido, String segundoApellido) {
		Session session = sessionFactory.openSession();
		/*
		 * Objetivo: Buscar clientes en la base de datos que coincidan con un nombre y
		 * apellidos específicos. 
		 * Pasos: 
		 * Crear la consulta: Selecciona todos los registros de la tabla Cliente donde los campos nombre, primerApellido, y
		 * segundoApellido coinciden con los valores proporcionados. 
		 * Asignar parámetros: Usa setParameter para pasar los valores de nombre, primerApellido, y
		 * segundoApellido a la consulta. 
		 * Ejecutar la consulta: Devuelve una lista de objetos Cliente que cumplen con los criterios de búsqueda.
		 */
		List<Cliente> clientes = session.createQuery(
				"from Cliente where nombre = :nombre and primerApellido = :primerApellido and segundoApellido = :segundoApellido",
				Cliente.class).setParameter("nombre", nombre).setParameter("primerApellido", primerApellido)
				.setParameter("segundoApellido", segundoApellido).list();
		session.close();
		return clientes;
	}
	@Override
    public List<Cliente> buscarPorNombreYApellidosCriteria(String nombre, String primerApellido, String segundoApellido) {
        // Implementación usando JPA Criteria
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cliente> cq = cb.createQuery(Cliente.class);
        Root<Cliente> root = cq.from(Cliente.class);
        Predicate nombrePredicate = cb.equal(root.get("nombre"), nombre);
        Predicate primerApellidoPredicate = cb.equal(root.get("primerApellido"), primerApellido);
        Predicate segundoApellidoPredicate = cb.equal(root.get("segundoApellido"), segundoApellido);
        cq.where(cb.and(nombrePredicate, primerApellidoPredicate, segundoApellidoPredicate));
        return entityManager.createQuery(cq).getResultList();
    }
}
