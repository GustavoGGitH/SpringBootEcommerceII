package com.basicEcommerceII.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cpf_detail_order")
public class OrderDetail {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

	
	@Column
	private String nombre;
	
	@Column
	private double cantidad;
	
	@Column
	private double precio;
	
	@Column
	private double total;
	
	
	@ManyToOne
/*	@JoinColumn(name = "orden_id", insertable = false, updatable = false) */
	private Order orden;
	
	@ManyToOne
	@JoinColumn(name = "cod_articulo") 
	private Product productos;
	

	/**
	 * 
	 */
	public OrderDetail() {

	}


	/**
	 * @param id
	 * @param nombre
	 * @param cantidad
	 * @param precio
	 * @param total
	 * @param orden
	 * @param productos
	 */
	public OrderDetail(Integer id, String nombre, double cantidad, double precio, double total, Order orden,
			Product productos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.precio = precio;
		this.total = total;
		this.orden = orden;
		this.productos = productos;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public double getCantidad() {
		return cantidad;
	}


	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public double getTotal() {
		return total;
	}


	public void setTotal(double total) {
		this.total = total;
	}


	public Order getOrden() {
		return orden;
	}


	public void setOrden(Order orden) {
		this.orden = orden;
	}


	public Product getProductos() {
		return productos;
	}


	public void setProductos(Product productos) {
		this.productos = productos;
	}


	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", nombre=" + nombre + ", cantidad=" + cantidad + ", precio=" + precio
				+ ", total=" + total + ", orden=" + orden + ", productos=" + productos + "]";
	}





	
	
	
}
