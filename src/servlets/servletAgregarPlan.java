package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Usuario;
import logica.CtrlConfiguracion;
import logica.ValidacionNegocio;

/**
 * Servlet implementation class servletAgregarPlan
 */
@WebServlet("/paciente/servletAgregarPlan")
public class servletAgregarPlan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletAgregarPlan() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	private HttpSession sesion;
    private Usuario user;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		sesion = request.getSession();		
		user = (Usuario)sesion.getAttribute("usuario");
		int idplan = Integer.parseInt(request.getParameter("planes"));
		String strnroafi= request.getParameter("nroafiliado");
		
		try {
			if(ValidacionNegocio.ValidarInteger(strnroafi)) {
				
				int nroafiliado = Integer.parseInt(strnroafi);
		
				CtrlConfiguracion controlador = new CtrlConfiguracion();			
				controlador.agregarPlan(user.getDni(),idplan,nroafiliado);
			
				request.getRequestDispatcher("/WEB-INF/confpersonal.jsp").forward(request, response);
			}else {
				servlet.NotificarMensaje(response,"configuracionPersonal","Nro de afiliado incorrecto, no debe poseer letras");
			}			
		}catch (SQLException e) {
			servlet.NotificarMensaje(response,"configuracionPersonal","Se ha producido un error: "+e.getMessage());
		}catch(NumberFormatException ne){		
			servlet.NotificarMensaje(response,"configuracionPersonal","Se ha producido un error: "+ne.getMessage());
		}
	}
}
