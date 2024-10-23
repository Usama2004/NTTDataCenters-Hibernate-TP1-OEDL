package com.nttdata.nttdatacenters_hibernate_t1_OEDL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "primer_apellido", nullable = false)
    private String primerApellido;

    @Column(name = "segundo_apellido", nullable = false)
    private String segundoApellido;

    @Column(name = "numero_documento", unique = true, nullable = false, length = 9)
    private String numeroDocumento;

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

