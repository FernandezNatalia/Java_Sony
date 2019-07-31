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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		try {
		int dni = Integer.parseInt(request.getParameter("dni"));
		String contra = request.getParameter("pass");
		//int ndni = Integer.parseInt(dni);
		UsuarioLogico usLog = new UsuarioLogico();	
		Usuario usActual = new Usuario();
		
		usActual.setTipousuario(-1);
		usActual = usLog.Logear(111, "doktorbob");
		
		
		
			
			if(usActual.getTipousuario() == 2) {
				response.sendRedirect("menuEspecialista.html");
			}
			if(usActual.getTipousuario() == 1) {
				response.sendRedirect("menuPaciente.html");
			}
			
			if(usActual.getTipousuario() != 1 && usActual.getTipousuario()!=2) {
				response.sendRedirect("err.html");
			}
		
		}
		catch(Exception ex) {
			
			System.out.println("ERRSLoginservlet");
		}
		
			
	
	
		
	}
}


