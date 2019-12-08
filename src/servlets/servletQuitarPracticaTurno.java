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
import logica.CtrlDetalleTurno;
import logica.ValidacionNegocio;

/**
 * Servlet implementation class servletQuitarPracticaTurno
 */
@WebServlet("/servletQuitarPracticaTurno")
public class servletQuitarPracticaTurno extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletQuitarPracticaTurno() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		servlet.VerificarUsuario(request, response, Usuario.especialista);
		
		String idTurnoStr = request.getParameter("idturno");
		String idPracticaStr = request.getParameter("idpractica");
		
		if(ValidacionNegocio.ValidarInteger(idTurnoStr) && ValidacionNegocio.ValidarInteger(idPracticaStr)) {
			int idTurno = Conversion.ConvertirStringAInteger(idTurnoStr);
			int idPractica = Conversion.ConvertirStringAInteger(idPracticaStr);
			
			CtrlDetalleTurno controlador = new CtrlDetalleTurno();
			if(controlador.EliminarPracticaDeTurno(idTurno, idPractica)) {
				
				//Se elimin� la practica del turno			
				//A�ado el turno actual a la sesion para que se muestre el detalle del mismo actualizado.
				
				HttpSession sesion = request.getSession(false);
				sesion.setAttribute("idturno", idTurno);
				
				request.getRequestDispatcher("WEB-INF/esp_detallesturno.jsp").forward(request, response);
			}
		}
		servlet.NotificarMensaje(response,"detallesTurno?idturno="+idTurnoStr,"No se ha podido eliminar la practica");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
