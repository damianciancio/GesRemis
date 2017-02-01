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
	public int save (Personal p){
		int i = 0;
		try {
			i = new PersonalData().save(p);
		} catch (Exception e) {
		}
		return i;
	}
}
