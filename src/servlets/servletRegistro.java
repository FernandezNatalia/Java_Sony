package servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
		//response.getWriter().append("Served at: ").append(request.getContextPath());		
		
		String dni = request.getParameter("dni");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String email = request.getParameter("email");
		String fechaNacimiento = request.getParameter("fechanac");
		String password = request.getParameter("password");
		
		//=============VALIDO TODOS LOS DATOS Y REGISTRO==================
		
		boolean validado=ValidacionNegocio.ValidarDatosDeRegistro(dni,nombre,apellido,email,fechaNacimiento,password);
		
		if(validado) {
			
			Date fechaDate = ValidacionNegocio.ConvertirStringAFecha(fechaNacimiento);					
			Usuario usNuevo = new Usuario(Integer.parseInt(dni),nombre,apellido,email,fechaDate,password,1);
			
			CtrlUsuario userLogico = new CtrlUsuario();			
			if(userLogico.RegistrarNuevoUsuario(usNuevo)) {
				response.sendRedirect("index.html");
			}else {
				servlet.NotificarMensaje(response,"registro.html","Ya existe el usuario que se desea ingresar.");
			}	
		}else {
			servlet.NotificarMensaje(response,"registro.html","Los datos ingresados son incorrectos.");			
		}		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
