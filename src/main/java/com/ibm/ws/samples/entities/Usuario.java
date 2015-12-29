/**
 * 
 */
package com.ibm.ws.samples.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author lentiummmx
 *
 */
@Entity
@Table(name="Usuario")
@SequenceGenerator(name = "id_usuario_sequence_generator", sequenceName = "usuario_seq", allocationSize = 1)
public class Usuario extends AuditEntity {

	/**
	 * the serialVersionUID
	 */
	private static final long serialVersionUID = -1096045812579051766L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="id_usuario_sequence_generator")
	@Basic(optional = false)
	private Long id;
	
	@Column(name="nombre_usuario")
	private String nombreUsuario;
	
	private String contrasena;
	
	private String nombre;
	
	@Column(name="apellido_paterno")
	private String apellidoPaterno;
	
	@Column(name="apellido_materno")
	private String apellidoMaterno;
	
	@Column(name="fecha_nacimiento")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaNacimiento;
	
	@Column(name="correo_electronico")
	private String correoElectronico;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "usuario_rol",
			joinColumns = { @JoinColumn(name = "id_usuario", referencedColumnName = "id") },
			inverseJoinColumns = { @JoinColumn(name = "id_rol", referencedColumnName = "id") })
	private Set<Rol> roles;

	/* (non-Javadoc)
	 * @see com.ibm.ws.samples.entities.BaseEntity#getId()
	 */
	@Override
	Long getId() {
		return this.id;
	}

	/* (non-Javadoc)
	 * @see com.ibm.ws.samples.entities.BaseEntity#setId(java.lang.Long)
	 */
	@Override
	void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nombreUsuario
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	/**
	 * @param nombreUsuario the nombreUsuario to set
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	/**
	 * @return the contrasena
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * @param contrasena the contrasena to set
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
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
	 * @return the apellidoPaterno
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	/**
	 * @param apellidoPaterno the apellidoPaterno to set
	 */
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	/**
	 * @return the apellidoMaterno
	 */
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	/**
	 * @param apellidoMaterno the apellidoMaterno to set
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	/**
	 * @return the fechaNacimiento
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * @return the correoElectronico
	 */
	public String getCorreoElectronico() {
		return correoElectronico;
	}

	/**
	 * @param correoElectronico the correoElectronico to set
	 */
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	/**
	 * @return the roles
	 */
	public Set<Rol> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

}
