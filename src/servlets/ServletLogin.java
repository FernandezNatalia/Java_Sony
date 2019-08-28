package servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Usuario;
import logica.UsuarioLogico;
import logica.ValidacionIngresoDatos;

@WebServlet("/servletPrincipal")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletLogin() {
        super();
    }

    HttpSession sesion;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());		
			
		if((request.getSession()).isNew()) {
			response.sendRedirect("index.html");
		}else {
			
			try {
				int dni = Integer.parseInt(request.getParameter("dni"));
				String pass = request.getParameter("pass");//Validar tipo de datos con metodos EsNro,..
			
				UsuarioLogico usLog = new UsuarioLogico();
				
				if(usLog.Autenticacion(dni,pass))
				{
					Usuario usActual = usLog.getOne(dni);
					
					sesion = request.getSession();					
					sesion.setAttribute("usuario",usActual);
					
					UsuarioLogico usLogi = new UsuarioLogico();
					String path = usLogi.getPathMenuUsuario(usActual);//.getTipousuario());
					
					request.getRequestDispatcher(path).forward(request, response);
					
				}else {
					//Informo que el usuario y/o contraseña son incorrectos
					
					 response.setContentType("text/html"); 
					 PrintWriter out = response.getWriter();
					 out.println("<html>");
					 out.println("<script type=\"text/javascript\">");				 
					 out.println("alert('Usuario y/o contraseña incorrectos');");
					 out.println("window.location.href = \"index.html\";");
					 out.println("</script>");
					 out.println("</html>");
				}
				}catch(NumberFormatException ne){
					//Se produce una excepcion porque los campos estan vacios, o el dni es un string.
					
					response.setContentType("text/html"); 
					PrintWriter out = response.getWriter();
					out.println("<html>");
					out.println("<script type=\"text/javascript\">");			 
					out.println("alert('Ingreso incorrecto, vuelva a intentar.');");
					out.println("window.location.href = \"index.html\";");
					out.println("</script>");
					out.println("</html>");
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
