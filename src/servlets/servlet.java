package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

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
}