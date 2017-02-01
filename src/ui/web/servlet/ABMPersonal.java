package ui.web.servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.entities.Estado;
import business.entities.Personal;
import business.logic.PersonalLogic;

/**
 * Servlet implementation class ABMPersonal
 */
@WebServlet("/ABMPersonal")
public class ABMPersonal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ABMPersonal() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Personal per = new Personal();
		per.setNombre(request.getParameter("nombre"));
		per.setApellido(request.getParameter("apellido"));
		per.setDni(request.getParameter("dni"));
		per.setDireccion(request.getParameter("direccion"));
		per.setTelefono(request.getParameter("telefono"));
		per.setFechaIncorporacion(Date.valueOf(request.getParameter("fechaIncorporacion")));
		per.setTipo(request.getParameter("tipo"));
		per.setUsuario(request.getParameter("usuario"));
		per.setContrasenia(request.getParameter("contrasenia"));
		PersonalLogic pl = new PersonalLogic();
		per.setEstado(Estado.NEW);
		int id = pl.save(per);
		response.getWriter().append(String.valueOf(id));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
