package com.bufete.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "clientes")


public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // si prefieres SEQUENCE en PostgreSQL usa:
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientes_seq")
    // @SequenceGenerator(name = "clientes_seq", sequenceName = "clientes_id_seq", allocationSize = 1)
 
    private Long id;
    private String telefono;
    
    
   

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Column(nullable = false, unique = true, length = 10)
    private String cedula;

    @Column(nullable = false, length = 100)
    private String nombres;

    @Column(nullable = false, length = 100)
    private String apellidos;

    @Column(nullable = false)
    private Integer edad;

    @Column(name = "estado_civil", nullable = false, length = 20)
    private String estadoCivil;

    @Column(columnDefinition = "TEXT")
    private String observaciones;
}
