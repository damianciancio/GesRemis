package business.entities;

public class Entity {
	private Estado estado;

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
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
