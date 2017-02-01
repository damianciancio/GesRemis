package business.entities;

import java.util.Date;

public class Personal extends Entity {
	private int legajo;
	private String dni;
	private String apellido;
	private String nombre;
	private String direccion;
	private String telefono;
	private Date fechaIncorporacion;
	private TipoPersonal tipo;
	private String usuario;
	private String contrasenia;
	private boolean habilitado;
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public int getLegajo() {
		return legajo;
	}
	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	public Date getFechaIncorporacion() {
		return fechaIncorporacion;
	}
	public void setFechaIncorporacion(Date fechaIncorporacion) {
		this.fechaIncorporacion = fechaIncorporacion;
	}
	public TipoPersonal getTipo() {
		return tipo;
	}
	public void setTipo(TipoPersonal tipo) {
		this.tipo = tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = TipoPersonal.valueOf(tipo);
	}
	public boolean isHabilitado() {
		return this.habilitado;
	}
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	public void setHabilitado(int bool) {
		this.setHabilitado(1 == bool);
	}
	public void setEstado(String estado) {
		if (estado.equals("NEW")) {
			this.setEstado(Estado.NEW);
		} else 
			if (estado.equals("MODIFIED")) {
			this.setEstado(Estado.MODIFIED);
		} else if (estado.equals("DELETED")) {
			this.setEstado(Estado.DELETED);
		} else {
			this.setEstado(Estado.UNMODIFIED);
		}

		}
	}
