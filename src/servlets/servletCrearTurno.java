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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		try {
		//String dnipacstr=request.getParameter("dnipac"); Mejor no asignar el paciente aca, alto quilombo y el medico tendria que saber el dni
		String datestr=request.getParameter("fecha");
		String horastr=request.getParameter("hora");
		String fechayhorastr = datestr + " " + horastr;
		HttpSession sesion = request.getSession(false);
		if(sesion==null) {
			response.sendRedirect("index.html");
		}
		else {
			Usuario especialista= (Usuario)(sesion.getAttribute("usuario"));
			if(especialista.getTipousuario()!=2) {
				//El usuario no es especialista, vuelve al login y le expiramos la sesion por rata
				sesion.invalidate();
				response.sendRedirect("index.html");
				
			}
			if(especialista.getTipousuario()==2) {
		int idconsultorio =  Integer.parseInt(request.getParameter("cons"));
		ConsultorioDatos cd = new ConsultorioDatos();
		TurnoDatos td = new TurnoDatos();
		Turno turno = new Turno();
		
		
		turno.setEstado(1);
		
		try {
			Consultorio consultorio = cd.getOne(idconsultorio);
			turno.setConsultorio(consultorio);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		turno.setEspecialista(especialista);
		
		 String fechabonita = "yyyy-MM-dd hh:mm";
		
         SimpleDateFormat formato = new SimpleDateFormat(fechabonita);
        
		try {
			Date fechaturno = formato.parse(fechayhorastr);
			turno.setFechahora(fechaturno);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		try {
			td.add(turno);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//request.getRequestDispatcher("/servletVerTurnosPendientesEsp").forward(request, response);
		response.setContentType("text/html"); 
		 PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<script>");
		out.println("window.location.replace(\"servletVerTurnosPendientesEsp\");");
		 out.println("</script>");
		 out.println("</html>");
		
		}
		
		
		
		
		}
		
		}
		catch(Exception e) {
			e.printStackTrace();
			response.setContentType("text/html"); 
			 PrintWriter out = response.getWriter();
			out.println("<html>");
			 out.println("Error");
			 out.println("</html>");
		}
		//catch(Exception ex) {
			
		//	response.sendRedirect("err.html");
		//	ex.printStackTrace();
		//}
		
			
	
	
		
	}
}


