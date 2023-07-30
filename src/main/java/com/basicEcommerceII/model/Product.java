package com.basicEcommerceII.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ct_articulos")

public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer cod_articulo;

	// @Column(length = 128)
//	public String codigo_sku;

	// @Column(length = 32)
//		public String codigo_base;

//		@Column(length = 32)
//		public String cod_variante;

//	@Column(length = 32)
//	public String cod_presentacion;

	@Column(length = 100, name = "nom_articulo")
	public String nombre_articulo;

	@Column(length = 1024, name = "nom_det_art")
	public String nombre_det_Art;

	@Column(length = 1050)
	public String image;

	@Column
	public double precio_unit_ing;

	@Column
	public float cantidad;

	@ManyToOne
	private User usuario;

	public Product() {

	}

	/**
	 * @param cod_articulo
	 * @param nombre_articulo
	 * @param nombre_det_Art
	 * @param image
	 * @param precio_unit_ing
	 * @param cantidad
	 * @param usuario
	 */
	public Product(Integer cod_articulo, String nombre_articulo, String nombre_det_Art, String image,
			double precio_unit_ing, float cantidad, User usuario) {
		super();
		this.cod_articulo = cod_articulo;
		this.nombre_articulo = nombre_articulo;
		this.nombre_det_Art = nombre_det_Art;
		this.image = image;
		this.precio_unit_ing = precio_unit_ing;
		this.cantidad = cantidad;
		this.usuario = usuario;
	}

	public Integer getCod_articulo() {
		return cod_articulo;
	}

	public void setCod_articulo(Integer cod_articulo) {
		this.cod_articulo = cod_articulo;
	}

	public String getNombre_articulo() {
		return nombre_articulo;
	}

	public void setNombre_articulo(String nombre_articulo) {
		this.nombre_articulo = nombre_articulo;
	}

	public String getNombre_det_Art() {
		return nombre_det_Art;
	}

	public void setNombre_det_Art(String nombre_det_Art) {
		this.nombre_det_Art = nombre_det_Art;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public double getPrecio_unit_ing() {
		return precio_unit_ing;
	}

	public void setPrecio_unit_ing(double precio_unit_ing) {
		this.precio_unit_ing = precio_unit_ing;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(cantidad);
		result = prime * result + ((cod_articulo == null) ? 0 : cod_articulo.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((nombre_articulo == null) ? 0 : nombre_articulo.hashCode());
		result = prime * result + ((nombre_det_Art == null) ? 0 : nombre_det_Art.hashCode());
		long temp;
		temp = Double.doubleToLongBits(precio_unit_ing);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Product other = (Product) obj;
		if (Float.floatToIntBits(cantidad) != Float.floatToIntBits(other.cantidad))
			return false;
		if (cod_articulo == null) {
			if (other.cod_articulo != null)
				return false;
		} else if (!cod_articulo.equals(other.cod_articulo))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (nombre_articulo == null) {
			if (other.nombre_articulo != null)
				return false;
		} else if (!nombre_articulo.equals(other.nombre_articulo))
			return false;
		if (nombre_det_Art == null) {
			if (other.nombre_det_Art != null)
				return false;
		} else if (!nombre_det_Art.equals(other.nombre_det_Art))
			return false;
		if (Double.doubleToLongBits(precio_unit_ing) != Double.doubleToLongBits(other.precio_unit_ing))
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
		return "Product [cod_articulo=" + cod_articulo + ", nombre_articulo=" + nombre_articulo + ", nombre_det_Art="
				+ nombre_det_Art + ", image=" + image + ", precio_unit_ing=" + precio_unit_ing + ", cantidad="
				+ cantidad + ", usuario=" + usuario + "]";
	}
	
	

}
