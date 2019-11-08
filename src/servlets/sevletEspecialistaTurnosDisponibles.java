package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Turno;

/**
 * Servlet implementation class sevletEspecialistaTurnosDisponibles
 */
@WebServlet("/sevletEspecialistaTurnosDisponibles")
public class sevletEspecialistaTurnosDisponibles extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sevletEspecialistaTurnosDisponibles() {
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
				if((Integer)sesion.getAttribute("estado") == Turno.reservado) {
					
					sesion.setAttribute("estado",Turno.disponible);				
					sesion.setAttribute("botonEstado","Ver turnos reservados");
					
				}else {					
					sesion.setAttribute("estado", Turno.reservado);
					sesion.setAttribute("botonEstado","Ver turnos disponibles");
				}				
				request.getRequestDispatcher("WEB-INF/esp_MisTurnosPend.jsp").forward(request, response);								
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
