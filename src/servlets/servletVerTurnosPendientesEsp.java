package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.*;
import logica.*;

/**
 * Servlet implementation class servletRegistro
 */
@WebServlet("/servletVerTurnosPendientesEsp")
public class servletVerTurnosPendientesEsp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletVerTurnosPendientesEsp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			
			HttpSession sesion = request.getSession(false);
			
			if(sesion==null) {
				
				response.sendRedirect("index.html");
			
			}else {
				
				Usuario usActual = (Usuario)sesion.getAttribute("usuario");				
				if(usActual.getTipousuario()== Usuario.especialista) {	
					
					java.sql.Date diaActual = new java.sql.Date(new Date().getTime());
					sesion.setAttribute("fecha", diaActual);
					sesion.setAttribute("estado", Turno.reservado);
					sesion.setAttribute("botonEstado","Ver turnos disponibles");
					request.getRequestDispatcher("WEB-INF/esp_MisTurnosPend.jsp").forward(request, response);
					
				}else {
					sesion.invalidate();
					response.sendRedirect("index.html");
					}
				}
			}catch(Exception ex) {
				ex.printStackTrace();
				}
		}
	}
