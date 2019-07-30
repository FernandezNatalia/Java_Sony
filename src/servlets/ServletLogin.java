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
		try {
			
			UsuarioLogico usLog = new UsuarioLogico();	
			Usuario nuevo = new Usuario();
			UsuarioDatos usdat = new UsuarioDatos();
			nuevo.setApellido("Garcia");
			nuevo.setNombre("Pepe");
			nuevo.setDni(15577);
			//nuevo.setFechanacimiento(new Date("2018-01-01"));
			nuevo.setEmail("aa@mail.com");
			nuevo.setPassword("caca");
			nuevo.setTipousuario(1);
		
			usdat.add(nuevo);
			
		}
		catch(Exception ex) {
			 ex.printStackTrace();
		}
		
			//response.sendRedirect("err.html");}//No logre hacer andar esto :c
			
		
		/*	
		if(usActual != null)
		{
		
		
	
		
		
		
		
		
		
		
		
			if(usActual.getTipousuario() == Usuario.especialista) 
			{////////////////////////////////////////////////////////
				//Menu especialista
				//0) Crear turno
					//Ingresa dia-horarios-duracion-consultorio
					//Validar que este libre
				//1) Listar turnos para ese dia
				//2) Finalizar turnos
				
			///////////////////////////////////////////////////////
				
				response.sendRedirect("menuEspecialista.html");
				
				
			}else
			{////////////////////////////////////////////////////////
				//Menu paciente
				//0) Ver turnos reservados
					//Listar Turnos
				//1) Cancelar turno
					//Listar turnos
					//Elegir uno - cambiar el estado
					//Recibe mail
				//2) Solicitar turno
					//Listar turnos - Elige turno
					//Ingresa Practica que desea
					//Elige plan de la obra social
					//Mostrar costo
					//Confirmar turno
					//Recibe mail
			 ////////////////////////////////////////////////////////
				//response.sendRedirect("menuPaciente.html");
				
			}
		}
		else 
		{		
			PrintWriter out = response.getWriter();
			out.println("<html><body>Usuario y/o contrase�a incorrectos</body></html>");
		}
		
		/*
			
		/*	PrintWriter salida = response.getWriter();
			salida.println("<html><body>");
			Connection mico = Conexion.getConnection();
			
			try {
				
				
				Connection con =  Conexion.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rset = stmt.executeQuery("SELECT nombre FROM alumnos");
				
				while (rset.next())
				{
					salida.println(rset.getString("nombre") + "<br>");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			salida.println("holaaaa");
			salida.println("</html></body>");
			
			
		
	*/
	
		
	}
}


