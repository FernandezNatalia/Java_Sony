package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Usuario;

public class servlet {
	
public static void NotificarMensaje(HttpServletResponse response,String  pagRedireccionada,String mensaje) throws IOException {			
		
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<script type=\"text/javascript\">");
		out.println("alert('"+mensaje+"');");
		out.println("window.location.replace(\""+pagRedireccionada+"\");");
		out.println("</script>");
		out.println("</html>");
	}

public static void ErrorPaciente(String direcFallo, String mensaje, HttpServletResponse response) throws ServletException {

	try {
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<script type=\"text/javascript\">");
		out.println("alert('"+mensaje+"');");
		out.println("window.location.replace(\"paciente?opcion="+direcFallo+"\");");
		out.println("</script>");
		out.println("</html>");
		
	} catch (IOException e) {		
		throw new ServletException(e);		
	}
}
public static void ErrorConfiguracion(String mensaje, HttpServletResponse response) throws ServletException {

	try {
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<script type=\"text/javascript\">");
		out.println("alert('"+mensaje+"');");
		out.println("window.location.replace(\"configuracionPersonal?opcion=configuracion\");");
		out.println("</script>");
		out.println("</html>");
		
	} catch (IOException e) {		
		throw new ServletException(e);		
	}
}
public static void ErrorEspListado(String mensaje, HttpServletResponse response) throws ServletException {

	try {
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<script type=\"text/javascript\">");
		out.println("alert('"+mensaje+"');");
		out.println("window.location.replace(\"MisTurnos?opcion=verListado\");");
		out.println("</script>");
		out.println("</html>");
		
	} catch (IOException e) {		
		throw new ServletException(e);		
	}
}
public static void ErrorEspMisTurnos(String opcion,String mensaje, HttpServletResponse response) throws ServletException {

	try {
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<script type=\"text/javascript\">");
		out.println("alert('"+mensaje+"');");
		out.println("window.location.replace(\"MisTurnos?opcion="+opcion+"\");");
		out.println("</script>");
		out.println("</html>");
		
	} catch (IOException e) {		
		throw new ServletException(e);		
	}
}
public static void ErrorEsp(String direcFallo, String mensaje, HttpServletResponse response) throws ServletException {

	try {
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<script type=\"text/javascript\">");
		out.println("alert('"+mensaje+"');");
		out.println("window.location.replace(\"especialista?opcion="+direcFallo+"\");");
		out.println("</script>");
		out.println("</html>");
		
	} catch (IOException e) {		
		throw new ServletException(e);		
	}
}

public static void VerificarSesion(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	//Si el Usuario no tiene una sesion se lo manda a la pagina de inicio.
	
	HttpSession sesion = request.getSession(false);	
	if(sesion==null) request.getRequestDispatcher("index.html").forward(request, response);
	
	}
public static void VerificarUsuario(HttpServletRequest request,HttpServletResponse response,int tipoUsuario) throws IOException, ServletException{
	//Si el Usuario es un tipo de usuario incorrecto se lo manda a la pagina de inicio.
	
	HttpSession sesion = request.getSession();
	if(((Usuario)(sesion.getAttribute("usuario")))==null) {
		request.getRequestDispatcher("index.html").forward(request, response);
	}
	if(tipoUsuario != ((Usuario)(sesion.getAttribute("usuario"))).getTipousuario()) {
		sesion.invalidate();
		request.getRequestDispatcher("index.html").forward(request, response);
		}	
	}

public static void VerificarSesionYUsuario(HttpServletRequest request, HttpServletResponse response, int tipoUsuario) throws ServletException, IOException {	
	//Si el Usuario no tiene una sesion o es un paciente se lo manda a la pagina de inicio.
	
	VerificarSesion(request, response);
	VerificarUsuario(request, response, tipoUsuario);
	}

public static void RedirigirUrl(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
	response.setContentType("text/html"); 
	PrintWriter out = response.getWriter();
	out.println("<html>");
	out.println("<script type=\"text/javascript\">");
	out.println("window.location.replace(\""+url+"\");");
	out.println("</script>");
	out.println("</html>");
}
}




