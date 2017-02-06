package ui.web.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.entities.Personal;
import business.entities.Remis;
import business.logic.RemisLogic;

/**
 * Servlet implementation class ABMRemis
 */
@WebServlet("/ABMRemis")
public class ABMRemis extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ABMRemis() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Remis remis = new Remis();
		if (!request.getParameter("id").equals("")) {
			remis.setId(Integer.parseInt(request.getParameter("id")));
		}
		remis.setPatente(request.getParameter("patente"));
		remis.setAnioModelo(Integer.parseInt(request.getParameter("anioModelo")));
		remis.setFechaIncorporacion(Date.valueOf(request.getParameter("fechaIncorporacion")));
		if(!request.getParameter("fechaBaja").equals("")) 
			remis.setFechaBaja(Date.valueOf(request.getParameter("fechaBaja")));
		remis.setIdMarca(Integer.parseInt(request.getParameter("marca")));
		Personal choferActual = new Personal();
		choferActual.setLegajo(Integer.parseInt(request.getParameter("choferActual")));
		remis.setChoferActual(choferActual);
		remis.setEstado(request.getParameter("modo"));
		int id = new RemisLogic().save(remis);
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
