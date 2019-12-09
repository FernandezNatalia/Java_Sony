package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.*;
import entidades.*;
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
		//3)Configuracion Personal
		//4) Planes
/**
 * Servlet implementation class servletPaciente
 */
@WebServlet("/paciente")
public class servletPaciente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletPaciente() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		String op = request.getParameter("opcion");		
		switchPaciente(op, request, response);
		
	}catch(java.lang.NullPointerException e) {
		
		request.getRequestDispatcher("/err.html").forward(request, response);
	}
	
	}

	public void switchPaciente(String opcion, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		switch(opcion)
		{	
		case "menuPaciente":
			request.getRequestDispatcher("WEB-INF/menuPaciente.jsp").forward(request, response);
			break;
			
		//Opciones base del menu
		case "misTurnos":
			request.getRequestDispatcher("/WEB-INF/pac_ListadoTurnosPend.jsp").forward(request, response);
			break;
		case "SolicitarTurno":
			
			HttpSession sesion = request.getSession(false);
			sesion.setAttribute("camino","especialidad");

			request.getRequestDispatcher("/WEB-INF/pac_SolicitarTurno.jsp").forward(request, response);
			
			break;
		case "ConfiguracionPersonal":
			response.sendRedirect("/TurnosWeb/configuracionPersonal");
			break;		
		//Opciones que pertenecen a alguna opcion del menu
		case "Eliminar":
			eliminarPlan(request, response);
			break;
		case "Agregar":
			agregarPlan(request, response);
			break;
		case "verEspecialistas":
			
			//Validar que haya una opcion seleccionada
			//Obtener la especialidad seleccionada
			//Mostrar todos los especialistas de esa especialidad
			
			
			int codEspecialidad = Integer.parseInt(request.getParameter("opcionesEspecid"));				
			CtrlSolicitarTurno controlador = new CtrlSolicitarTurno();
			
			Especialidad e = controlador.getOneEspecialidad(codEspecialidad);
			
			
			//======================================
			if(e == null) {
				request.getRequestDispatcher("/err.html").forward(request, response);
			}
			//======================================
			
			
			HttpSession s = request.getSession(false);
			s.setAttribute("espeSeleccionada", e);
			s.setAttribute("camino","especialista");

			request.getRequestDispatcher("/WEB-INF/pac_SolicitarTurno.jsp").forward(request, response);
			
			
			
			break;
		case "verCalendario":
			verCalendario(request, response);
			opcion = "";
			break;
		case "SelectFecha":
			verHorarios(request, response);
			break;
		case "VerOtroMes":
			verOtroMes(request, response);
			break;
		case "ConfirmarHorario":
			confirmarHorario(request, response);
			break;
		case "ReservarTurno":
			reservarTurno(request, response);
			break;
		default: 
			switchPaciente("menuPaciente", request, response);
			break;
		}
	}

	public void reservarTurno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		servlet.VerificarSesionYUsuario(request, response,Usuario.paciente);
		
		HttpSession sesion = request.getSession(false);
		Turno t = (Turno)sesion.getAttribute("TurnoNuevo");
		
		CtrlSolicitarTurno ctST = new CtrlSolicitarTurno();
		boolean exito = ctST.reservarTurno(t);
		
		if(exito)
		request.getRequestDispatcher("/WEB-INF/pac_TurnoReservado.jsp").forward(request, response);
		else
			//===============================
			request.getRequestDispatcher("/err.html").forward(request, response);
		//===============================
		//===============================
		//===============================
		
	}

	public void confirmarHorario(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		servlet.VerificarSesionYUsuario(request, response,Usuario.paciente);
		int idTurno = Integer.parseInt(request.getParameter("idTurnoHorario"));
		
		CtrlTurno controlador = new CtrlTurno();
		
		Turno tActual = controlador.getOne(idTurno);
		if(tActual != null) {
			HttpSession sesion = request.getSession(false);
			
			//Busco el paciente en la sesion y le asigno los datos correspondientes al turno actual.
			Usuario usActual = (Usuario) sesion.getAttribute("usuario");
			
			tActual.setEstado(Turno.reservado);
			tActual.setPaciente(usActual);
		
			
			sesion.setAttribute("TurnoNuevo",tActual);
			
			request.getRequestDispatcher("/WEB-INF/pac_ConfirmarReservaTurno.jsp").forward(request, response);
		}
	}

	public void verOtroMes(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		servlet.VerificarSesionYUsuario(request, response,Usuario.paciente);
		
		HttpSession sesion = request.getSession(false);

		int MES = (Integer)sesion.getAttribute("mes");
		int ANIO = (Integer)sesion.getAttribute("anio");
		

		//Verifico cual opcion se apreto ( ANTERIOR / SIGUIENTE ) 		
		String act = request.getParameter("act");
		
		
		java.sql.Date fechaVista = new java.sql.Date(new Date().getTime());  		
		int MESActual = Conversion.getNroDelMes(fechaVista);
		
		if (act.equals("anterior")) {
		    //Se presiono el boton anterior
			
				//Valido que el anio sea igual al actual
				if(MES == 1) {
					
					MES = 12;
					ANIO--;
					
				}else if(MESActual != MES) {
					//Valido que no quiera ir a un mes anterior
					
		   			MES--;
		   			
		   		}
				
		} else if (act.equals("siguiente")) {
		    //Se presiono el boton siguiente
			
			if(MES == 12) {
				MES = 1;
				ANIO++;
			}else {
				MES++;
			}

		} else {
		    //No se presiono ninguno o se entro por otro lado
			servlet.NotificarMensaje(response,"paciente/servletVerSemanas","No se pudo actualizar la pagina");
		}
		
		sesion.setAttribute("mes", MES);
		sesion.setAttribute("anio", ANIO);
		
		request.getRequestDispatcher("/WEB-INF/pac_calendario.jsp").forward(request, response);
	}

	public void verHorarios(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		servlet.VerificarSesionYUsuario(request, response,Usuario.paciente);
		String fechaReserva = request.getParameter("Reserva");
		
		HttpSession sesion = request.getSession(false);
		sesion.setAttribute("fechaReserva", fechaReserva);
		
		request.getRequestDispatcher("/WEB-INF/pac_VerHorarios.jsp").forward(request, response);
	}
	Usuario us;
	public void verCalendario(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		servlet.VerificarSesionYUsuario(request, response,Usuario.paciente);
		
		//Si es verdadero significa que esta eligiendo los horarios y que no es la primera vez que carga la pagina
		//if (us != null) request.getRequestDispatcher("/WEB-INF/pac_calendario.jsp").forward(request, response);
		
		try {
			int dniEspe = Integer.parseInt(request.getParameter("opEspecialistas"));				
			CtrlEspecialista ctrEsp = new CtrlEspecialista();

			us = (Usuario)ctrEsp.getOneEspecialista(dniEspe);
			
			java.sql.Date fechaVista = new java.sql.Date(new Date().getTime());
			
			//convierto la fecha al mes actual	
			int MES = Conversion.getNroDelMes(fechaVista);
			
			//convierto la fecha al anio actual		
			int ANIO = Conversion.getNroAnio(fechaVista);
			
			//los agrego a la sesion
			HttpSession sesion = request.getSession(false);
			sesion.setAttribute("mes", MES);
			sesion.setAttribute("anio", ANIO);
			sesion.setAttribute("Especialista", us);
			
			request.getRequestDispatcher("/WEB-INF/pac_calendario.jsp").forward(request, response);
			
		} catch (SQLException e) {
			//===========================================
			request.getRequestDispatcher("/err.html").forward(request, response); //e.printStackTrace();
			//===========================================
		}catch (NumberFormatException et) {
			//===========================================
			request.getRequestDispatcher("/err.html").forward(request, response);//e.printStackTrace();
			//===========================================
		}
	}

	public void agregarPlan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		servlet.VerificarSesionYUsuario(request, response,Usuario.paciente);
		
		HttpSession sesion = request.getSession();		
		Usuario user = (Usuario)sesion.getAttribute("usuario");
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

	public void eliminarPlan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		servlet.VerificarSesionYUsuario(request, response,Usuario.paciente);
		HttpSession sesion = request.getSession();	
		Usuario user = (Usuario)sesion.getAttribute("usuario");
			
		CtrlConfiguracion controlador = new CtrlConfiguracion();
			
		try {
			controlador.eliminarPlan(user.getDni());
			request.getRequestDispatcher("/WEB-INF/confpersonal.jsp").forward(request, response);
				
		}catch (SQLException e) {
			servlet.NotificarMensaje(response,"configuracionPersonal","No se ha podido eliminar el plan: "+e.getMessage());
		}
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
