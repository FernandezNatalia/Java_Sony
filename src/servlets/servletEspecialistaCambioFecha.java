package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Usuario;
import logica.Conversion;
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

		servlet.VerificarSesionYUsuario(request, response,Usuario.especialista);
		
		HttpSession sesion = request.getSession();
		String strFecha = request.getParameter("fechaDeseada");			
			
		if(ValidacionNegocio.ValidarFecha(strFecha)) {
			java.sql.Date fecha = Conversion.ConvertirStringAFechaSql(strFecha);
			sesion.setAttribute("fecha", fecha);
			//servlet.RedirigirUrl(request, response, "servletVerTurnosPendientesEsp");
			request.getRequestDispatcher("WEB-INF/esp_MisTurnosPend.jsp").forward(request, response);
			
		}else {			
			servlet.NotificarMensaje(response,"servletVerTurnosPendientesEsp","No se ha podido cambiar la fecha.");
			
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
