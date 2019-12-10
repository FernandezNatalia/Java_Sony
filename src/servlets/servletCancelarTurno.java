package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Turno;
import entidades.Usuario;
import logica.Conversion;
import logica.CtrlTurno;
import util.Emailer;
/**
 * Servlet implementation class servletCancelarTurno
 */
@WebServlet({"/servletCancelarTurno","/paciente/servletCancelarTurno"})
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
		HttpSession sesion = request.getSession();
		Usuario us = (Usuario)(sesion.getAttribute("usuario"));
		
		servlet.VerificarSesion(request, response);
		Integer IDTurno = Conversion.ConvertirStringAInteger(request.getParameter("idturno"));
	
		//Llamo al controlador, se elimina el turno y se redirecciona al listado.		
		CtrlTurno controlador = new CtrlTurno();			
		if(controlador.CancelarTurno(IDTurno)) {		
			
			if(us.getTipousuario() == Usuario.especialista){
				Emailer em = new Emailer();
				CtrlTurno ctr = new CtrlTurno();
				Turno turno = ctr.getOne(IDTurno);
				em.send(turno.getPaciente().getEmail(), "Turno cancelado", "Su turno ha sido cancelado");
				servlet.RedirigirUrl(request, response, "servletVerTurnosPendientesEsp");
			}
			if(us.getTipousuario() == Usuario.paciente)
				servletPaciente.opMenuListaTurnos(request, response);
			
		}else {	
			if(us.getTipousuario() == Usuario.especialista)			
				servlet.NotificarMensaje(response,"servletVerTurnosPendientesEsp","No se ha podido cancelar el turno");	
			if(us.getTipousuario() == Usuario.paciente)
				request.getRequestDispatcher("/WEB-INF/pac_ListadoTurnosPend.jsp").forward(request, response);		
		}						
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
