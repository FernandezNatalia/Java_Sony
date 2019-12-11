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
@WebServlet("/detallePaciente")
public class detallePaciente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public detallePaciente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		servlet.VerificarSesionYUsuario(request, response, Usuario.especialista);
		
		try {	
			String dnipstr = request.getParameter("dnipaciente");
			
			if(dnipstr==null) {
				servlet.NotificarMensaje(response, "index.html", "Es null dnipaciente");
			}
					
			int dnipac = Integer.parseInt(dnipstr);
					
			CtrlConfiguracion controlador = new CtrlConfiguracion();
			Paciente pac = controlador.getPaciente(dnipac);
				
			HttpSession sesion = request.getSession(false);
			sesion.setAttribute("pacseleccionado", pac);
			request.getRequestDispatcher("WEB-INF/esp_detallePaciente.jsp").forward(request, response);

		}catch(Exception e) {
			servlet.ErrorEsp("Gestionar pacientes", "Ha ocurrido un errror", response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}


