package ui.web.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.entities.Personal;
import business.logic.PersonalLogic;
import util.exception.ConsultaSQLException;
import util.exception.DataBaseConnectionException;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public TestServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Personal> personal;
		
		try {
			personal = new PersonalLogic().getAll();
			for(Personal per : personal) {
				response.getWriter().append(String.valueOf(per.getLegajo()));
				response.getWriter().append(per.getDni());
				response.getWriter().append(per.getNombre());
				response.getWriter().append(per.getApellido());
				response.getWriter().append(per.getDireccion());
				response.getWriter().append(per.getTelefono());
				response.getWriter().append(per.getTipo().toString());
				
			}
		} catch (ClassNotFoundException e) {
			response.getWriter().append("ClassNotFoundException");
		} catch (DataBaseConnectionException e) {
			response.getWriter().append("DataBaseConnectionException");
		} catch (ConsultaSQLException e) {
			response.getWriter().append("ConsultaSqlException");
		} catch (Exception e) {
			response.getWriter().append("Exception");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
