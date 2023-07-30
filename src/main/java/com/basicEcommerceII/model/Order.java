package com.basicEcommerceII.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "cpt_ordenes")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String numero;

    @Column
    private Date fechadecreacion;

    @Column
    private Date fecharecibida;

    @Column
    private double Total;	
    

    @ManyToOne
    private User usuario;

    
   
	
	@OneToMany(mappedBy = "orden")
	private List<OrderDetail> detalle;


    public Order() {
    }

	/**
	 * @param id
	 * @param número
	 * @param fechadecreacion
	 * @param fecharecibida
	 * @param total
	 * @param usuario
	 * @param detalle
	 */

	

	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 * @param numero
	 * @param fechadecreacion
	 * @param fecharecibida
	 * @param total
	 * @param usuario
	 * @param detalle
	 */
	public Order(Integer id, String numero, Date fechadecreacion, Date fecharecibida, double total, User usuario,
			List<OrderDetail> detalle) {
		super();
		this.id = id;
		this.numero = numero;
		this.fechadecreacion = fechadecreacion;
		this.fecharecibida = fecharecibida;
		Total = total;
		this.usuario = usuario;
		this.detalle = detalle;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getFechadecreacion() {
		return fechadecreacion;
	}

	public void setFechadecreacion(Date fechadecreacion) {
		this.fechadecreacion = fechadecreacion;
	}

	public Date getFecharecibida() {
		return fecharecibida;
	}

	public void setFecharecibida(Date fecharecibida) {
		this.fecharecibida = fecharecibida;
	}

	public double getTotal() {
		return Total;
	}

	public void setTotal(double total) {
		Total = total;
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	public List<OrderDetail> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<OrderDetail> detalle) {
		this.detalle = detalle;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", numero=" + numero + ", fechadecreacion=" + fechadecreacion + ", fecharecibida="
				+ fecharecibida + ", Total=" + Total + ", usuario=" + usuario + ", detalle=" + detalle + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(Total);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((detalle == null) ? 0 : detalle.hashCode());
		result = prime * result + ((fechadecreacion == null) ? 0 : fechadecreacion.hashCode());
		result = prime * result + ((fecharecibida == null) ? 0 : fecharecibida.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		Order other = (Order) obj;
		if (Double.doubleToLongBits(Total) != Double.doubleToLongBits(other.Total))
			return false;
		if (detalle == null) {
			if (other.detalle != null)
				return false;
		} else if (!detalle.equals(other.detalle))
			return false;
		if (fechadecreacion == null) {
			if (other.fechadecreacion != null)
				return false;
		} else if (!fechadecreacion.equals(other.fechadecreacion))
			return false;
		if (fecharecibida == null) {
			if (other.fecharecibida != null)
				return false;
		} else if (!fecharecibida.equals(other.fecharecibida))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	
}