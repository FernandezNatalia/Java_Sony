package servlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logica.*;

/**
 * Servlet implementation class servletEspecialista
 */
@WebServlet("/servletEspecialista")
public class servletEspecialista extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletEspecialista() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		int opcion = Integer.parseInt(request.getParameter("opcion"));
		
		switch(opcion)
		{
		case 1: crearTurno(); break;
		case 2: listarTurnos(); break;
		case 3: FinalizarTurnos(); break;
		default: break;
		}
		
		
	}
	
	public static void crearTurno()
	{
		//Ingresa parametros para crear turno
	}
	public static void listarTurnos()
	{
		TurnoLogico turnos = new TurnoLogico();
		turnos.getAll();
	}
	public static void FinalizarTurnos()
	{
		listarTurnos();
	    //Ingresa turno que desea observar - getOne()
		//Cambiar estado
	}
	
}
