package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entidades.*;
import logica.*;

/**
 * Servlet implementation class servletRegistro
 */
@WebServlet("/servletRegistro")
public class servletRegistro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletRegistro() {
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
		try {
			//Verificar que no exista usuario
			int dni = Integer.parseInt(request.getParameter("dni"));
			UsuarioLogico ul = new UsuarioLogico();
			if(ul.ExisteUsuario(dni)) {
				 System.out.println("<script type=\"text/javascript\">");
				 System.out.println("alert('Ups ');");
				 System.out.println("</script>");
				 response.sendRedirect("registro.html"); 
			}
			
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

}
