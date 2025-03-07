package com.nttdata.nttdatacenters_hibernate_t1_OEDL;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/*
Esta clase Cliente representa una tabla cliente en una base de datos, mapeada con anotaciones JPA para 
su uso con Hibernate. Cada cliente tiene un identificador único (id), nombre, primer apellido, segundo apellido 
y número de documento (único y no nulo). Los constructores y métodos getter/setter permiten la creación y 
manipulación de instancias de Cliente.

Uso: Se utiliza en aplicaciones que requieren operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre clientes 
en una base de datos, facilitando la persistencia y recuperación de datos mediante Hibernate.   
*/

@Entity
@Table(name = "Cliente")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private String primerApellido;
	private String segundoApellido;
	@Column(unique = true, nullable = false, length = 9)
	private String numeroDocumento;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Contrato> contratos;

	// Contructor

	public Cliente(String nombre, String primerApellido, String segundoApellido, String numeroDocumento) {
		super();
		this.nombre = nombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.numeroDocumento = numeroDocumento;
	}

	public Cliente() {
		super();
	}

	// Getters y Setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
}
