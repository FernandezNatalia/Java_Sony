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
import logica.Conversion;
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

		servlet.VerificarSesion(request, response);
		
		Usuario usActual = (Usuario) sesion.getAttribute("usuario");
		CtrlUsuario usLogi = new CtrlUsuario();
		String path = usLogi.getPathMenuUsuario(usActual);//.getTipousuario());
		request.getRequestDispatcher(path).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {		
		String strdni = request.getParameter("dni");
		String pass = request.getParameter("pass");

		CtrlUsuario usLog = new CtrlUsuario();

		if(ValidacionNegocio.ValidarInteger(strdni) && ValidacionNegocio.ValidarInteger(pass))
		{
			int dni = Conversion.ConvertirStringAInteger(strdni);
			if(usLog.Autenticacion(dni,pass))
			{
				Usuario usActual = usLog.getOne(dni);
				sesion = request.getSession();					
				sesion.setAttribute("usuario",usActual);

				doGet(request, response);
			}else
				servlet.NotificarMensaje(response,"index.html","Usuario y/o contrase�a incorrectos");;
		}
		else {
			servlet.NotificarMensaje(response,"index.html","Datos ingresados incorrectos");
		}
		}
		catch(NumberFormatException ne){		
			servlet.NotificarMensaje(response,"index.html","Algun/os campos se encuentran vacios o son incorrectos");
	}
	}

}