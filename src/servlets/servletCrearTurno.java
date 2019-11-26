package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
     

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datos.*;
import entidades.*;
import logica.*;
import servlets.*;


/**
 * Servlet implementation class servletPrincipal
 */
@WebServlet("/servletCrearTurno")
public class servletCrearTurno extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletCrearTurno() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		servlet.VerificarSesionYUsuario(request, response,Usuario.especialista);
		HttpSession sesion = request.getSession(false);
		
		Usuario especialista= (Usuario)(sesion.getAttribute("usuario"));
		String strFechaHora=request.getParameter("fecha")+" "+request.getParameter("hora");
		String strConsultorio = request.getParameter("cons");
		int estado = (Integer)sesion.getAttribute("estado");
		CtrlTurno controlador = new CtrlTurno();				
		if(controlador.AgregarNuevoTurno(strFechaHora,strConsultorio,especialista.getDni())) {
			
			//request.getRequestDispatcher("WEB-INF/esp_MisTurnosPend.jsp").forward(request, response);
			//Mejor asi, queda la url correcta en el navegador
			//Hay que verificar si esta en turnos disponibles o reservados
			//para redirigirlo a la pag correcta
			if(estado == 1){
				request.getRequestDispatcher("WEB-INF/esp_MisTurnosPend.jsp").forward(request, response);
			}
			if(estado == 2){
				servlet.RedirigirUrl(request, response, "servletVerTurnosPendientesEsp");
				}
			
		}else {
			java.util.Date fecha = Conversion.ConvertirStringAFechaHora(strFechaHora);
			int idConsultorio = Conversion.ConvertirStringAInteger(strConsultorio);	
			
			if(controlador.VerificarDisponibilidadConsultorio(idConsultorio, fecha)==false) {
			servlet.NotificarMensaje(response,"sevletEspecialistaTurnosDisponibles","El consultorio esta ocupado en ese horario");
			}
			else {
			servlet.NotificarMensaje(response,"sevletVerTurnosPendientesEsp","No se ha podido crear un turno");
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


