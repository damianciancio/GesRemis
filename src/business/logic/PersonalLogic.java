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
}
