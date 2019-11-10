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
import logica.ValidacionNegocio;
/**
 * Servlet implementation class servletFinalizarTurno
 */
@WebServlet("/servletFinalizarTurno")
public class servletFinalizarTurno extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletFinalizarTurno() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		HttpSession sesion = request.getSession(false);
		
		//Si el Usuario no tiene una sesion o es un paciente se lo manda a la pagina de inicio.
		
		if(sesion==null || Usuario.paciente == ((Usuario)(sesion.getAttribute("usuario"))).getTipousuario()) {			
			response.sendRedirect("index.html");			
		}else {
			
			String observacion=request.getParameter("observacion");
			String strIDTurno = request.getParameter("idturno");
			
			if(ValidacionNegocio.ValidarInteger(strIDTurno)){
				
				int idTurno = Conversion.ConvertirStringAInteger(strIDTurno);				
				CtrlTurno controlador = new CtrlTurno();	
				
				if(controlador.FinalizarTurno(observacion,idTurno)) {

					request.getRequestDispatcher("/WEB-INF/esp_MisTurnosPend.jsp").forward(request, response);
					
				}else {
					servlet.NotificarMensaje(response,"servletVerTurnosPendientesEsp","Ha ocurrido un error, no se puede finalizar el turno");
				}
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
