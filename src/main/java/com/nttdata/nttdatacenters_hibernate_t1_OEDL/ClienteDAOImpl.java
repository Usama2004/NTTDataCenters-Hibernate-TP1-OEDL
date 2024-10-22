package com.nttdata.nttdatacenters_hibernate_t1_OEDL;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ClienteDAOImpl implements ClienteDAO {
	private SessionFactory sessionFactory;

	public ClienteDAOImpl() {
		// SessionFactory es una interfaz de Hibernate que se utiliza para crear y
		// gestionar objetos Session.
		// Una Session es una conexión ligera y de corta duración entre la aplicación y
		// la base de datos.
		sessionFactory = new Configuration().configure().buildSessionFactory();
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
	}

	// Propósito: Inserta un nuevo objeto Cliente en la base de datos.
	@Override
	public void insertar(Cliente cliente) {
		// Abre una nueva sesión.
		Session session = sessionFactory.openSession();
		// Inicia una transacción.
		Transaction transaction = session.beginTransaction();
		// Guarda el objeto Cliente en la base de datos.
		session.save(cliente);
		// Confirma la transacción.
		transaction.commit();
		// Cierra la sesión.
		session.close();
	}

	// Recupera todos los objetos Cliente de la base de datos.
	@Override
	public List<Cliente> consultarTodos() {
		// Abre una nueva sesión.
		Session session = sessionFactory.openSession();
		// Ejecuta una consulta HQL ("from Cliente") para obtener todos los clientes.
		List<Cliente> clientes = session.createQuery("from Cliente", Cliente.class).list();
		/*
		 * Crea una consulta HQL (Hibernate Query Language). La consulta "from Cliente"
		 * selecciona todos los registros de la tabla Cliente
		 */
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
}
