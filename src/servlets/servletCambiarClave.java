package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Usuario;
import logica.CtrlConfiguracion;

/**
 * Servlet implementation class servletCambiarClave
 */
@WebServlet({"/cambiarclave","/paciente/cambiarclave"})
public class servletCambiarClave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletCambiarClave() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession sesion = request.getSession(false);
		if(sesion==null) {
			response.sendRedirect("index.html");
		}
		else {
			String oldpass = request.getParameter("oldpass");
			String newpass = request.getParameter("newpass");
			String repnewpass = request.getParameter("rnewpass");
			
			Usuario usActual = (Usuario) sesion.getAttribute("usuario");
			
			
			CtrlConfiguracion controlador = new CtrlConfiguracion();
			if(controlador.CambioClave(oldpass,newpass,repnewpass,usActual)) {
				
				request.getRequestDispatcher("/WEB-INF/confpersonal.jsp").forward(request, response);
				
			}else {
				response.sendRedirect("err.html");
			}

		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
