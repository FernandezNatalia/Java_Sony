package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Usuario;
import logica.CtrlConfiguracion;

/**
 * Servlet implementation class configuracionPersonal
 */
@WebServlet("/configuracionPersonal")
public class configuracionPersonal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public configuracionPersonal() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		servlet.VerificarSesion(request, response);
		
		String opcion = request.getParameter("opcion");
		switch(opcion) {
		case "configuracion":
			request.getRequestDispatcher("WEB-INF/confpersonal.jsp").forward(request, response);
			break;
		case "cambiarMail":
			cambiarMail(request, response);	
			break;
		case "cambiarClave":
			cambiarClave(request, response);	
			break;
		default:
			request.getRequestDispatcher("WEB-INF/confpersonal.jsp").forward(request, response);
			break;
		}
	}

	public void cambiarClave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion = request.getSession(false);
		
		String oldpass = request.getParameter("oldpass");
		String newpass = request.getParameter("newpass");
		String repnewpass = request.getParameter("rnewpass");
			
		Usuario usActual = (Usuario) sesion.getAttribute("usuario");
			
			
		CtrlConfiguracion controlador = new CtrlConfiguracion();
		if(controlador.CambioClave(oldpass,newpass,repnewpass,usActual)) {
				
			request.getRequestDispatcher("/WEB-INF/confpersonal.jsp").forward(request, response);
				
		}else {
			servlet.ErrorConfiguracion("No se ha podido cambiar la clave",response);
		}
	}

	public void cambiarMail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession sesion = request.getSession(false);
		Usuario usActual = (Usuario) sesion.getAttribute("usuario");
			
		String newmail = request.getParameter("mail");			
		CtrlConfiguracion controlador = new CtrlConfiguracion();
			
		if(controlador.CambioMail(newmail,usActual)) {
				
			request.getRequestDispatcher("/WEB-INF/confpersonal.jsp").forward(request, response);
		}else {
			servlet.ErrorConfiguracion("No se ha podido cambiar el email.",response);
		}
	}
}
