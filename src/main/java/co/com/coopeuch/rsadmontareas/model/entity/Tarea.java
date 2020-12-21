package co.com.coopeuch.rsadmontareas.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tareas")
public class Tarea implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long identificador;
	
	@Column(nullable=false)
	private String descripcion;
	
	@Column(name="fechacreacion")
	@Temporal(TemporalType.DATE)	
	private Date fechaCreacion;
	
	@Column(nullable=false)
	private boolean vigente;

	@PrePersist
	public void prePersist() {
		fechaCreacion =  new Date();
	}
	
	public Long getidentificador() {
		return identificador;
	}

	public void setidentificador(Long i) {
		this.identificador = i;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public boolean getVigente() {
		return vigente;
	}

	public void setVigente(boolean activo) {
		this.vigente = activo;
	}

}
