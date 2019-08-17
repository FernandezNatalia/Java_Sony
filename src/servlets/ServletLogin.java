package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datos.UsuarioDatos;
import entidades.*;
import logica.*;


/**
 * Servlet implementation class servletPrincipal
 */
@WebServlet("/servletPrincipal")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doPost(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		try {
		int dni = 0;
		dni = Integer.parseInt(request.getParameter("dni"));
		String pass = request.getParameter("pass");
		//int ndni = Integer.parseInt(dni);
		UsuarioLogico usLog = new UsuarioLogico();	
		Usuario usActual = new Usuario();
		
		usActual.setTipousuario(-1);
		usActual = usLog.Logear(dni, pass);
		
		
		
			if(dni==0 || pass == "" ) {
				response.setContentType("text/html"); 
				 PrintWriter out = response.getWriter();
				 out.println("<html>");
				 out.println("<script type=\"text/javascript\">");
				 
				 out.println("alert('Ingrese dni y contraseña');");
				 out.println("window.location.href = \"index.html\";");
				 out.println("</script>");
				 out.println("</html>");
			}
			 TurnoLogico tl = new TurnoLogico();
			 
			 
			 
			 
			 

			if(usActual.getTipousuario() == 2) {
				HttpSession sesion = request.getSession(true);
				sesion.setAttribute("usuario", usActual);
				
				
				request.getRequestDispatcher("WEB-INF/esp_MisTurnosPend.jsp").forward(request, response);
				
				 
						//response.sendRedirect("menuEspecialista.html");
			}
			if(usActual.getTipousuario() == 1) {
				response.sendRedirect("menuPaciente.html");
			}
			
			if(usActual.getTipousuario() != 1 && usActual.getTipousuario()!=2) {
				 response.setContentType("text/html"); 
				 PrintWriter out = response.getWriter();
				 out.println("<html>");
				 out.println("<script type=\"text/javascript\">");
				 
				 out.println("alert('Usuario o contraseña incorrectos');");
				 out.println("window.location.href = \"index.html\";");
				 out.println("</script>");
				 out.println("</html>");
				
			}
		
		}
		catch(SQLException e) {
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


