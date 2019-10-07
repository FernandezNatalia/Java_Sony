package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Usuario;
import logica.UsuarioLogico;
import logica.ValidacionIngresoDatos;
import logica.ValidacionNegocio;

/**
 * Servlet implementation class cambiaremail
 */
@WebServlet("/cambiaremail")
public class cambiaremail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cambiaremail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
				try {
				HttpSession sesion = request.getSession(false);
				String newmail = request.getParameter("mail");
				
				if(sesion==null) {
					response.sendRedirect("index.html");
				}
				else {
				Usuario usActual = (Usuario) sesion.getAttribute("usuario");
				//Verificar que el mail sea valido
				ValidacionNegocio v = new ValidacionNegocio();
				if(!v.ValidarEmail(newmail)) {
					 response.setContentType("text/html"); 
					 PrintWriter out = response.getWriter();
					 out.println("<html>");
					 out.println("<script type=\"text/javascript\">");
					 out.println("alert('Mail invalido');");
					 out.println("window.location.replace(\"configuracionPersonal\");");
					 out.println("window.location.href = \"configuracionPersonal\";");
					 out.println("</script>");
					 out.println("</html>");
					
					
				}
				else {
				UsuarioLogico ul = new UsuarioLogico();
				ul.cambiarMail(usActual, newmail);
				response.setContentType("text/html"); 
						 PrintWriter out = response.getWriter();
						out.println("<html>");
						 out.println("<script type=\"text/javascript\">");
						 

						 out.println("window.location.replace(\"configuracionPersonal\");");
						 out.println("window.location.href = \"configuracionPersonal\";");
						 out.println("</script>");
						 out.println("</html>");
					}
				}
				}
				catch(Exception e) {e.printStackTrace();}
	}

}
