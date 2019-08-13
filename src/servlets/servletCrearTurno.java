package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		try {
		String dnipacstr=request.getParameter("dnipac");
		String datestr=request.getParameter("fecha");
		String horastr=request.getParameter("hora");
		String fechayhorastr = datestr + " " + horastr;
		
		HttpSession sesion = request.getSession(false);
		Usuario especialista= (Usuario)(sesion.getAttribute("usuario"));
		String aaa= (String)sesion.getAttribute("usmail");
		
		 String fechabonita = "yyyy-MM-dd hh:mm";
         
         SimpleDateFormat formato = new SimpleDateFormat(fechabonita);
         
		// Date fechaturno = formato.parse(fechayhorastr);
		 
		
		
		if(dnipacstr.equalsIgnoreCase("")) { //Se crea un turno sin paciente asignado
			Turno turno = new Turno();
			turno.setEstado(1);
			//turno.setFechahora(fechaturno);
			turno.setEspecialista(especialista);
		}
		
		response.setContentType("text/html"); 
		 PrintWriter out = response.getWriter();
		out.println("<html>");
		 out.println(aaa);
		 out.println("</html>");
		
		}
		catch(NullPointerException e) {
			response.setContentType("text/html"); 
			 PrintWriter out = response.getWriter();
			out.println("<html>");
			 out.println("Error, no se puede conectar a la base de datos");
			 out.println("</html>");
		}
		//catch(Exception ex) {
			
		//	response.sendRedirect("err.html");
		//	ex.printStackTrace();
		//}
		
			
	
	
		
	}
}


