package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Usuario;
import logica.Conversion;
import logica.CtrlTurno;
import logica.ValidacionNegocio;

/**
 * Servlet implementation class servletEliminarTurno
 */
@WebServlet("/servletEliminarTurno")
public class servletEliminarTurno extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletEliminarTurno() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		servlet.VerificarSesionYUsuario(request, response,Usuario.especialista);		
		String strIDTurno = request.getParameter("idturno");
		
		if(ValidacionNegocio.ValidarInteger(strIDTurno)){
			
			int idTurno = Conversion.ConvertirStringAInteger(strIDTurno);				
			CtrlTurno controlador = new CtrlTurno();	
			
			if(controlador.EliminarTurno(idTurno)) {
				request.getRequestDispatcher("/WEB-INF/esp_MisTurnosPend.jsp").forward(request, response);				
			}else {
				servlet.NotificarMensaje(response,"servletVerTurnosPendientesEsp","Ha ocurrido un error, no se puede finalizar el turno");
			}
		}
		
		
		request.getRequestDispatcher("/WEB-INF/esp_MisTurnosPend.jsp").forward(request, response);
	}	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
