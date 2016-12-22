package business.entities;

import java.util.Date;

public class Remis {
	private int id;
	private String patente;
	private Date fechaIncorporacion;
	private Date fechaBaja;
	private int anioModelo;
	private int idMarca;
	private int descModelo;
	private Modelo modelo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	public Date getFechaIncorporacion() {
		return fechaIncorporacion;
	}
	public void setFechaIncorporacion(Date fechaIncorporacion) {
		this.fechaIncorporacion = fechaIncorporacion;
	}
	public Date getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	public int getAnioModelo() {
		return anioModelo;
	}
	public void setAnioModelo(int anioModelo) {
		this.anioModelo = anioModelo;
	}
	public int getIdMarca() {
		return idMarca;
	}
	public void setIdMarca(int idMarca) {
		this.idMarca = idMarca;
	}
	public int getDescModelo() {
		return descModelo;
	}
	public void setDescModelo(int descModelo) {
		this.descModelo = descModelo;
	}
	public Modelo getModelo() {
		return modelo;
	}
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
}
