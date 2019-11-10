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

import entidades.Usuario;
import logica.ValidacionNegocio;

/**
 * Servlet implementation class servletEspecialistaCambioFecha
 */
@WebServlet("/servletEspecialistaCambioFecha")
public class servletEspecialistaCambioFecha extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletEspecialistaCambioFecha() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		HttpSession sesion = request.getSession();
		if(sesion==null) {
			response.sendRedirect("index.html");
		}
		else {
			String strFecha = request.getParameter("fechaDeseada");			
			
			if(ValidacionNegocio.ValidarFecha(strFecha)) {
				
				java.sql.Date fecha = ValidacionNegocio.ConvertirStringAFechaSql(strFecha);
				sesion.setAttribute("fecha", fecha);
				request.getRequestDispatcher("WEB-INF/esp_MisTurnosPend.jsp").forward(request, response);
				
			}else {
				servlet.NotificarMensaje(response,"servletVerTurnosPendientesEsp","No se ha podido cambiar la fecha.");
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
