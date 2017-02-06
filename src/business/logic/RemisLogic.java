package business.logic;

import java.util.ArrayList;

import business.entities.Remis;
import data.RemisesData;
import util.exception.ConsultaSQLException;
import util.exception.DataBaseConnectionException;

public class RemisLogic {
	public ArrayList<Remis> getAll() throws ClassNotFoundException, DataBaseConnectionException, ConsultaSQLException, Exception{
		return (new RemisesData()).getAll();
	}
	
	public Remis getOne(Remis buscado){
		return (new RemisesData().getOne(buscado));
	}
}
