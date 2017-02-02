package business.logic;

import java.util.ArrayList;

import business.entities.Personal;
import data.PersonalData;
import util.exception.ConsultaSQLException;
import util.exception.DataBaseConnectionException;

public class PersonalLogic {
	public ArrayList<Personal> getAll() throws ClassNotFoundException, DataBaseConnectionException, ConsultaSQLException, Exception{
		return new PersonalData().getAll();
	}
	public Personal save (Personal p){
		try {
			p.setLegajo(new PersonalData().save(p));
		} catch (Exception e) {
		}
		return p;
	}
	public Personal login(Personal per) {
		return new PersonalData().login(per);
	}
	
	public Personal getOne(Personal buscado) {
		return new PersonalData().getOne(buscado);
	}
}
