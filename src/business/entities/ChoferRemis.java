package business.entities;

import java.util.Date;

public class ChoferRemis extends Entity {
	private int idChofer;
	private Personal chofer;
	private int idRemis;
	private Remis remis;
	private Date fecha_desde;
	private Date fecha_hasta;
	
	public int getIdChofer() {
		return idChofer;
	}
	public void setIdChofer(int idChofer) {
		this.idChofer = idChofer;
	}
	public Personal getChofer() {
		return chofer;
	}
	public void setChofer(Personal chofer) {
		this.chofer = chofer;
	}
	public int getIdRemis() {
		return idRemis;
	}
	public void setIdRemis(int idRemis) {
		this.idRemis = idRemis;
	}
	public Remis getRemis() {
		return remis;
	}
	public void setRemis(Remis remis) {
		this.remis = remis;
	}
	public Date getFecha_desde() {
		return fecha_desde;
	}
	public void setFecha_desde(Date fecha_desde) {
		this.fecha_desde = fecha_desde;
	}
	public Date getFecha_hasta() {
		return fecha_hasta;
	}
	public void setFecha_hasta(Date fecha_hasta) {
		this.fecha_hasta = fecha_hasta;
	}
}
