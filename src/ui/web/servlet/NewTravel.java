package ui.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NewTravel
 */
@WebServlet("/NewTravel")
public class NewTravel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewTravel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String html = request.getParameter("direccionOrigen");
		html += "\n";
		html += request.getParameter("direccionDestino");
		html += "\n";
		html += request.getParameter("CPOrigen");
		html += "\n";
		html += request.getParameter("CPDestino");
		html += "\n";
		html += request.getParameter("latOrigen");
		html += request.getParameter("lngOrigen");
		html += "\n";
		html += request.getParameter("latDestino");
		html += request.getParameter("lngDestino");
		html += "\n";
		html += request.getParameter("fechaViaje");
		html += request.getParameter("horaViaje");
		
		response.getWriter().append(html);
		
		
	}

}
