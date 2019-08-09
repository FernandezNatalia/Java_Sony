package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

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
			//Verificar que no coincidan contraseñas
			int dni = Integer.parseInt(request.getParameter("dni"));
			UsuarioLogico ul = new UsuarioLogico();
			String psw1 = request.getParameter("password");
			String psw2 = request.getParameter("confirm_password");
			
			
				//verificar que no exista usuario
				if(ul.ExisteUsuario(dni)) {
					response.setContentType("text/html"); 
					PrintWriter out = response.getWriter();
					out.println("<html>");
					out.println("<script type=\"text/javascript\">");
				 
					out.println("alert('Ya existe un usuario con ese dni');");
					out.println("window.location.href = \"registro.html\";");
					out.println("</script>");
					out.println("</html>");
					//response.sendRedirect("registro.html"); 
				}
				else {
					Usuario usn = new Usuario();
					usn.setApellido(request.getParameter("apellido"));
					usn.setEmail(request.getParameter("email"));
					usn.setDni(dni);
					usn.setNombre(request.getParameter("nombre"));
					usn.setPassword(request.getParameter("password"));
					usn.setTipousuario(1);
					String fnacstr = request.getParameter("fechanac");
				
					usn.setFechanacimiento(Date.valueOf(fnacstr));
					//usn.setFechanacimiento(fechanacimiento);
				 	ul.Registro(usn);
					 response.setContentType("text/html"); 
					 PrintWriter out = response.getWriter();
					 out.println("<html>");
					 out.println("<script type=\"text/javascript\">");
				 
					 out.println("alert('Usuario creado correctamente');");
					 out.println("window.location.href = \"index.html\";");
					 out.println("</script>");
					 out.println("</html>");
				 
				}
			
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

}
