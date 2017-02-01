package business.entities;

import java.util.Date;

public class Viaje extends Entity {
	private int id;
	private int legajoChofer;
	private Personal chofer;
	private Date fecDesdeChoferEnRemis;
	private ChoferRemis choferRemisViaje;
	private Date fechaHoraInicio;
	private Date fechaHoraFin;
	private String direccionOrigen;
	private String direccionDestino;
	private float saldo;
	private String nombreApellidoCliente;
	private Date fechaHoraSolicitada;
	private Date fechaHoraSolicitud;
	private int legajoRecepcionista;
	private Personal recepcionista;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLegajoChofer() {
		return legajoChofer;
	}
	public void setLegajoChofer(int legajoChofer) {
		this.legajoChofer = legajoChofer;
	}
	public Personal getChofer() {
		return chofer;
	}
	public void setChofer(Personal chofer) {
		this.chofer = chofer;
	}
	public Date getFecDesdeChoferEnRemis() {
		return fecDesdeChoferEnRemis;
	}
	public void setFecDesdeChoferEnRemis(Date fecDesdeChoferEnRemis) {
		this.fecDesdeChoferEnRemis = fecDesdeChoferEnRemis;
	}
	public ChoferRemis getChoferRemisViaje() {
		return choferRemisViaje;
	}
	public void setChoferRemisViaje(ChoferRemis choferRemisViaje) {
		this.choferRemisViaje = choferRemisViaje;
	}
	public Date getFechaHoraInicio() {
		return fechaHoraInicio;
	}
	public void setFechaHoraInicio(Date fechaHoraInicio) {
		this.fechaHoraInicio = fechaHoraInicio;
	}
	public Date getFechaHoraFin() {
		return fechaHoraFin;
	}
	public void setFechaHoraFin(Date fechaHoraFin) {
		this.fechaHoraFin = fechaHoraFin;
	}
	public String getDireccionOrigen() {
		return direccionOrigen;
	}
	public void setDireccionOrigen(String direccionOrigen) {
		this.direccionOrigen = direccionOrigen;
	}
	public String getDireccionDestino() {
		return direccionDestino;
	}
	public void setDireccionDestino(String direccionDestino) {
		this.direccionDestino = direccionDestino;
	}
	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	public String getNombreApellidoCliente() {
		return nombreApellidoCliente;
	}
	public void setNombreApellidoCliente(String nombreApellidoCliente) {
		this.nombreApellidoCliente = nombreApellidoCliente;
	}
	public Date getFechaHoraSolicitada() {
		return fechaHoraSolicitada;
	}
	public void setFechaHoraSolicitada(Date fechaHoraSolicitada) {
		this.fechaHoraSolicitada = fechaHoraSolicitada;
	}
	public Date getFechaHoraSolicitud() {
		return fechaHoraSolicitud;
	}
	public void setFechaHoraSolicitud(Date fechaHoraSolicitud) {
		this.fechaHoraSolicitud = fechaHoraSolicitud;
	}
	public int getLegajoRecepcionista() {
		return legajoRecepcionista;
	}
	public void setLegajoRecepcionista(int legajoRecepcionista) {
		this.legajoRecepcionista = legajoRecepcionista;
	}
	public Personal getRecepcionista() {
		return recepcionista;
	}
	public void setRecepcionista(Personal recepcionista) {
		this.recepcionista = recepcionista;
	}
}
