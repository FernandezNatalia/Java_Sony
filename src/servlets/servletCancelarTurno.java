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
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession(false);
		if(sesion==null) {			
			response.sendRedirect("index.html");	
			
		}else {
			Usuario especialista= (Usuario)(sesion.getAttribute("usuario"));
			
			if(especialista.getTipousuario()== Usuario.especialista) {				
				Integer IDTurno = Conversion.ConvertirStringAInteger(request.getParameter("idturno"));
	
				//Llamo al controlador, se elimina el turno y se redirecciona al listado.
				CtrlTurno controlador = new CtrlTurno();			
				if(controlador.EliminarTurno(IDTurno))
					request.getRequestDispatcher("WEB-INF/esp_MisTurnosPend.jsp").forward(request, response);				
				else {					
					servlet.NotificarMensaje(response,"servletVerTurnosPendientesEsp","No se ha podido cancelar el turno");
				}				
			}else {			
				//El usuario no es especialista, le invalido la sesion.
				sesion.invalidate();
				response.sendRedirect("index.html");				
			}			
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
