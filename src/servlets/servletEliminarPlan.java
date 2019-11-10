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
import logica.CtrlConfiguracion;;

/**
 * Servlet implementation class servletEliminarPlan
 */
@WebServlet("/paciente/servletEliminarPlan")
public class servletEliminarPlan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletEliminarPlan() {
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
	private HttpSession sesion;
    private Usuario user;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		sesion = request.getSession();		
		user = (Usuario)sesion.getAttribute("usuario");
		
		CtrlConfiguracion controlador = new CtrlConfiguracion();
		
		try {
			controlador.eliminarPlan(user.getDni());
			request.getRequestDispatcher("/WEB-INF/confpersonal.jsp").forward(request, response);
			
		}catch (SQLException e) {
			servlet.NotificarMensaje(response,"configuracionPersonal","No se ha podido eliminar el plan: "+e.getMessage());
		}

		
	}

}
