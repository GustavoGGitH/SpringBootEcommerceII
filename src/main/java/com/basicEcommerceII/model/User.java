package com.basicEcommerceII.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gl_usuarios")

public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	
	@Column(length = 20)
	public String usuario;

	@Column(length = 40)
	public String nombre;

	@Column(length = 60)
	public String password;

	@Column(length = 250)
	public String mail;

	@Column(length = 6)
	public String tipo;
	
	@Column(length = 150)
	public String direccion;
	
	@Column(length = 20)
	public String telefono;

	/*
	@OneToMany(mappedBy="usuario")
	public List<Product> products;*/
	
	/*@OneToMany(mappedBy="usuario")
	public List<Order> ordenes;*/

	/*@OneToMany(mappedBy = "usuario", cascade = CascadeType.PERSIST)
	private List<Order> ordenes;*/


	/**
	 * 
	 */
	public User() {

	}

	/**
	 * @param id
	 * @param usuario
	 * @param nombre
	 * @param password
	 * @param mail
	 * @param tipo
	 * @param direccion
	 * @param telefono
	 */
	public User(Integer id, String usuario, String nombre, String password, String mail, String tipo, String direccion,
			String telefono) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.nombre = nombre;
		this.password = password;
		this.mail = mail;
		this.tipo = tipo;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", usuario=" + usuario + ", nombre=" + nombre + ", password=" + password + ", mail="
				+ mail + ", tipo=" + tipo + ", direccion=" + direccion + ", telefono=" + telefono + "]";
	}





	/**
	 * @param id
	 * @param username
	 * @param nombre
	 * @param password
	 * @param mail
	 * @param tipo
	 * @param direccion
	 * @param telefono
	 */
	

}