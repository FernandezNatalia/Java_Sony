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
		// TODO Auto-generated method stub
		
		HttpSession sesion = request.getSession(false);
		
		if(sesion==null) {
			
			response.sendRedirect("index.html");	
			
		}else {
			Usuario especialista= (Usuario)(sesion.getAttribute("usuario"));
			
			if(especialista.getTipousuario()== Usuario.especialista) {
				
				String strFechaHora=request.getParameter("fecha")+" "+request.getParameter("hora");
				String strConsultorio = request.getParameter("cons");
				
				CtrlTurno controlador = new CtrlTurno();				
				if(controlador.AgregarNuevoTurno(strFechaHora,strConsultorio,especialista.getDni())) {
					
					request.getRequestDispatcher("WEB-INF/esp_MisTurnosPend.jsp").forward(request, response);
				}else {
					response.sendRedirect("err.html");
				}
			}else {
				
				sesion.invalidate();
				response.sendRedirect("index.html");				
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


