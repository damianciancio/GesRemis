package business.logic;

import java.util.ArrayList;

import business.entities.Marca;
import data.MarcaData;
public class MarcasLogic {
	public ArrayList<Marca> getAll(){
		return new MarcaData().getAll();
	}
}
