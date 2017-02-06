package business.entities;

import java.util.Date;

public class Remis extends Entity {
	private int id;
	private String patente;
	private Date fechaIncorporacion;
	private Date fechaBaja;
	private int anioModelo;
	private int idMarca;
	private String descMarca;
	private String descModelo;
	private Personal choferActual;
	private Date fechaDesdeChoferActual;
	
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
	public String getDescMarca(){
		return descMarca;
	}
	public void setDescMarca(String marca){
		this.descMarca = marca;
	}
	public String getDescModelo() {
		return descModelo;
	}
	public void setDescModelo(String descModelo) {
		this.descModelo = descModelo;
	}
	public Personal getChoferActual(){
		return choferActual;
	}
	public void setChoferActual(Personal choferActual){
		this.choferActual = choferActual;
	}
	public Date getFechaDesdeChoferActual(){
		return fechaDesdeChoferActual;
	}
	public void setFechaDesdeChoferActual(Date fecha){
		this.fechaDesdeChoferActual = fecha;
	}
}
