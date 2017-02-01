package ui.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import business.entities.Personal;
import business.logic.PersonalLogic;
import data.PersonalData;
import utils.security.Security;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (null == request.getSession().getAttribute("usuarioActual")) {
			String usuario = request.getParameter("usuario");
			String contrasenia = Security.MD5(request.getParameter("contrasenia"));
			Personal per = new Personal();
			per.setUsuario(usuario);
			per.setContrasenia(contrasenia);
			if (per.getUsuario() == null) {
				response.sendRedirect("login.jsp");
			} else {
				Personal p = new PersonalLogic().login(per);
				if (p == null) {
					response.getWriter().append("invalido");
				} else {
					if (p.isHabilitado()) {
						HttpSession session = request.getSession();
						session.setAttribute("usuarioActual", p);
					} else {
						//usuario no habilitado
					}
				}
			} 
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
