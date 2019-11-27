package servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Usuario;
import logica.Conversion;

/**
 * Servlet implementation class servletVerSemanas
 */
@WebServlet({ "/servletVerSemanas", "/paciente/servletVerSemanas" })
public class servletVerSemanas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletVerSemanas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		servlet.VerificarSesionYUsuario(request, response,Usuario.paciente);


		//DATOS JARKODEADOS
		Usuario us = new Usuario();
		us.setDni(40100300);
		

		java.sql.Date fechaVista = new java.sql.Date(new Date().getTime());
		
		//convierto la fecha al mes actual
		SimpleDateFormat formatoMES = new SimpleDateFormat("MM",  new Locale("ES", "ES"));			
   		int MES = Integer.parseInt(formatoMES.format(fechaVista));
   		
   		//convierto la fecha al anio actual
   		SimpleDateFormat formatoANIO = new SimpleDateFormat("yyyy",  new Locale("ES", "ES"));			
   		int ANIO = Integer.parseInt(formatoANIO.format(fechaVista));
   		
   		//los agrego a la sesion
   		HttpSession sesion = request.getSession(false);
   		sesion.setAttribute("mes", MES);
		sesion.setAttribute("anio", ANIO);
		sesion.setAttribute("Especialista", us);
		
		request.getRequestDispatcher("/WEB-INF/pac_calendario.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
