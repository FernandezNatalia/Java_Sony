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
import logica.CtrlTurno;
/**
 * Servlet implementation class servletCancelarTurno
 */
@WebServlet("/servletCancelarTurno")
public class servletCancelarTurno extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletCancelarTurno() {
        super();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					
		servlet.VerificarSesionYUsuario(request, response,Usuario.especialista);
		Integer IDTurno = Conversion.ConvertirStringAInteger(request.getParameter("idturno"));
	
		//Llamo al controlador, se elimina el turno y se redirecciona al listado.
		
		CtrlTurno controlador = new CtrlTurno();			
		if(controlador.CancelarTurno(IDTurno))
			request.getRequestDispatcher("WEB-INF/esp_MisTurnosPend.jsp").forward(request, response);				
		else {		
			servlet.NotificarMensaje(response,"servletVerTurnosPendientesEsp","No se ha podido cancelar el turno");			
		}						
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
