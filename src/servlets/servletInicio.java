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
import logica.CtrlUsuario;
import logica.ValidacionNegocio;

@WebServlet("/servletPrincipal")
public class servletInicio extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public servletInicio() {
        super();
    }

    HttpSession sesion;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());		

		if(sesion == null) {
			response.sendRedirect("index.html");
		}else {
			
			Usuario usActual = (Usuario) sesion.getAttribute("usuario");
			CtrlUsuario usLogi = new CtrlUsuario();
			
			String path = usLogi.getPathMenuUsuario(usActual);//.getTipousuario());
			request.getRequestDispatcher(path).forward(request, response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	try {		
		String strdni = request.getParameter("dni");
		String pass = request.getParameter("pass");

		CtrlUsuario usLog = new CtrlUsuario();

		if(ValidacionNegocio.ValidarInteger(strdni) && ValidacionNegocio.ValidarInteger(pass))
		{
			int dni = Integer.parseInt(strdni);
			if(usLog.Autenticacion(dni,pass))
			{
				Usuario usActual = usLog.getOne(dni);
				sesion = request.getSession();					
				sesion.setAttribute("usuario",usActual);

				doGet(request, response);
			}else
				response.sendRedirect("err.html");
		}
		else {
			//Informo que el usuario y/o contraseña son incorrectos
			response.sendRedirect("err.html");
		}
		}
		catch(NumberFormatException ne){		
			//Se produce una excepcion porque los campos estan vacios, o el dni es un string.
			response.sendRedirect("err.html"); //PARAMETROS INFORMANDO
	}
	}

}