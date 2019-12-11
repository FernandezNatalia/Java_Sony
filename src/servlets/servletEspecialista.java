package servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Turno;
import entidades.Usuario;

/**
 * Servlet implementation class servletEspecialista
 */
@WebServlet("/especialista")
public class servletEspecialista extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletEspecialista() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		try {
			
			servlet.VerificarSesionYUsuario(request, response, Usuario.especialista);
			
			String opcion = request.getParameter("opcion");	
			
			switch(opcion)
			{	
			case "MenuEsp":
				request.getRequestDispatcher("WEB-INF/menuEspecialista.html").forward(request, response);
				break;
			case "Turnos pendientes":
				servletVerTurnosPendientesEsp(request, response);	
				break;
			case "Gestionar pacientes":
				gestionarPacientes(request, response);
				break;	
			case "Configuracion personal":
				request.getRequestDispatcher("WEB-INF/confpersonal.jsp").forward(request, response);
				break;
			default:
				request.getRequestDispatcher("WEB-INF/menuEspecialista.html").forward(request, response);
				break;
			}
		}catch(IOException e) {
			servlet.ErrorEsp("MenuEsp", "Ha ocurrido un error. Causa: "+e.getMessage(), response);	
		}catch(Exception e1) {
			servlet.ErrorEsp("MenuEsp", "Ha ocurrido un error. Causa: "+e1.getMessage(), response);	
		}
	}

	public void gestionarPacientes(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			
			HttpSession sesion = request.getSession(false);
			if(sesion==null) {
				response.sendRedirect("index.html");
			}
			else {
				Usuario usActual = (Usuario) sesion.getAttribute("usuario");
				
				if(usActual.getTipousuario()==Usuario.especialista) {
					
					request.getRequestDispatcher("WEB-INF/esp_gestionarPacientes.jsp").forward(request, response);
				}
			}
		}catch(IOException e) {
			servlet.ErrorEsp("MenuEsp", "Ha ocurrido un error. Causa: "+e.getMessage(), response);	
		}
	}

	public void servletVerTurnosPendientesEsp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		HttpSession sesion = request.getSession(false);
		
		java.sql.Date diaActual = new java.sql.Date(new Date().getTime());
		sesion.setAttribute("fecha", diaActual);
		sesion.setAttribute("estado", Turno.reservado);
		sesion.setAttribute("botonEstado","Ver turnos disponibles");
		request.getRequestDispatcher("WEB-INF/esp_MisTurnosPend.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
