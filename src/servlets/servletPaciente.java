package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.*;
import entidades.*;

/**
 * Servlet implementation class servletPaciente
 */
@WebServlet({"/paciente/*"})
public class servletPaciente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletPaciente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private HttpSession sesion;
    private Usuario user;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		//Menu paciente
		//0) Ver turnos reservados
			//Listar Turnos
		//1) Cancelar turno
			//Listar turnos
			//Elegir uno - cambiar el estado
			//Recibe mail
		//2) Solicitar turno
			//Listar turnos - Elige turno
			//Ingresa Practica que desea
			//Elige plan de la obra social
			//Mostrar costo
			//Confirmar turno
			//Recibe mail
		//3)Configuracion Personal
		//4) Planes
		
		String path = request.getPathInfo();
		
		//Obtengo el usuario, recuperando su session.
		sesion = request.getSession();		
		user = (Usuario)sesion.getAttribute("usuario");
		switch(path)
		{
			case "/verTurnos": 	
				request.getRequestDispatcher("/WEB-INF/pac_ListadoTurnosPend.jsp").forward(request, response);
				break;
			case "/cancelarTurno": 				
				//cancelarTurno(request,response);  
				break;
			case "/solicitarTurno": 
				solicitarTurno(request,response);  
				break;
			case "/planes": 
				//planes(request,response); 
				break;
			case "/configuracion": 	
				response.sendRedirect("/TurnosWeb/configuracionPersonal");
				//request.getRequestDispatcher("/WEB-INF/confpersonal.jsp").forward(request, response);
				break;	
			default: 
				request.getRequestDispatcher("/err.html").forward(request, response);
				//REDIRECCIONAR MENSAJE ERROR 
				break;
		}		
	}
	
	
	// LOS METODOS QUE ESTAN ABAJO TENDRIAN QUE ESTAR EN CAPA LOGICA

	public void solicitarTurno(HttpServletRequest request, HttpServletResponse response) {
		
		//Como no ingresa datos, no los valido.
		
		
		//FILTRAR POR ELECCION de practica
			//especialista
			//horarios
				//ELIGE UN TURNO	
					//elige obra social
					//muestra costo
		//Confirma - se envia un mail

		
	}

	
	
	
	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
