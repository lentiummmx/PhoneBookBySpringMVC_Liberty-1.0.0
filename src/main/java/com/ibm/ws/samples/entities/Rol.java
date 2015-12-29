/**
 * 
 */
package com.ibm.ws.samples.entities;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author lentiummmx
 *
 */
@Entity
@Table(name = "Rol")
@SequenceGenerator(name = "id_rol_sequence_generator", sequenceName = "role_seq", allocationSize = 1)
public class Rol extends AuditEntity {
	
	/**
	 * the serialVersionUID
	 */
	private static final long serialVersionUID = 4694090689901168809L;

	public static final String ROLE_PREFIX = "ROLE_";
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="id_rol_sequence_generator")
	@Basic(optional = false)
	private Long id;
	
	private String nombre;
	
	private String descripcion;

	@Override
	Long getId() {
		return this.id;
	}

	@Override
	void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
