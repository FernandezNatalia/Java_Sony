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
	
			servlet.VerificarSesionYUsuario(request, response, Usuario.especialista);
			HttpSession sesion = request.getSession(false);
			
			java.sql.Date diaActual = new java.sql.Date(new Date().getTime());
			sesion.setAttribute("fecha", diaActual);
			sesion.setAttribute("estado", Turno.reservado);
			sesion.setAttribute("botonEstado","Ver turnos disponibles");
			request.getRequestDispatcher("WEB-INF/esp_MisTurnosPend.jsp").forward(request, response);			
	}	
}
