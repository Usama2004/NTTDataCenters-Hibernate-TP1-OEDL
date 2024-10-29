package com.nttdata.nttdatacenters_hibernate_t1_OEDL;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Contrato {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDate fechaVigencia;
	private LocalDate fechaCaducidad;
	private Double precioMensual;

	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;
	
	public Contrato(LocalDate fechaVigencia, LocalDate fechaCaducidad, Double precioMensual, Cliente cliente) {
		super();
		this.fechaVigencia = fechaVigencia;
		this.fechaCaducidad = fechaCaducidad;
		this.precioMensual = precioMensual;
		this.cliente = cliente;
	}
		
	public Contrato() {
		super();
	}

	// Getters y Setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getFechaVigencia() {
		return fechaVigencia;
	}

	public void setFechaVigencia(LocalDate fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}

	public LocalDate getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(LocalDate fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public Double getPrecioMensual() {
		return precioMensual;
	}

	public void setPrecioMensual(Double precioMensual) {
		this.precioMensual = precioMensual;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
