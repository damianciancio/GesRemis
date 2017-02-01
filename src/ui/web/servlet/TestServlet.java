package ui.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.entities.Estado;
import business.entities.Personal;
import business.entities.TipoPersonal;
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
		
		/*ArrayList<Personal> personal;
		
		try {
			Personal p = new Personal();
			p.setApellido("Garcia");
			p.setNombre("Gustavo Ariel");
			p.setContrasenia("perrosputo");
			p.setDireccion("La flora 23");
			p.setDni("23484903");
			Calendar today = Calendar.getInstance();
			today.set(Calendar.HOUR_OF_DAY, 0);
			p.setFechaIncorporacion(today.getTime());
			p.setTelefono("4235252");
			p.setTipo(TipoPersonal.Administrativo);
			p.setUsuario("ggarcia");
			PersonalLogic pl = new PersonalLogic();
			p.setEstado(Estado.NEW);
			response.getWriter().append(String.valueOf(pl.save(p)));
			response.getWriter().append("<br>");
			personal = pl.getAll();
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
		}*/
		response.sendRedirect("altaPersonal.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
