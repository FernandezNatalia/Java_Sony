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
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {	
			servlet.VerificarSesionYUsuario(request, response, Usuario.paciente);
			
			String op = request.getParameter("opcion");		
			switchPaciente(op, request, response);
			
		}catch(java.lang.NullPointerException | ServletException | IOException e) {
			
			servlet.ErrorPaciente("menuPaciente","Se ha producido un error. Causa: "+e.getMessage(), response);
			
		}
	}
	String direcFallo;
	String mensaje;
	
	public void switchPaciente(String opcion, HttpServletRequest request, HttpServletResponse response) throws ServletException{
		direcFallo = "menuPaciente";
		mensaje= "Se ha producido un error";
		
		try {	
			switch(opcion)
			{	
			case "menuPaciente":
				request.getRequestDispatcher("WEB-INF/menuPaciente.jsp").forward(request, response);
				break;
			case "misTurnos":
				opMenuListaTurnos(request, response);
				break;
			case "SolicitarTurno":			
				solicitarTurno(request, response);			
				break;
			case "ConfiguracionPersonal":
				response.sendRedirect("/TurnosWeb/configuracionPersonal");
				break;		
			case "Eliminar":
				eliminarPlan(request, response);
				break;
			case "Agregar":
				agregarPlan(request, response);
				break;
			case "verEspecialistas":			
				verEspecialistas(request, response);
				break;
			case "verCalendario":
				verCalendario(request, response);
				opcion = "";
				break;
			case "volverAlCalendario":
				opVerCalendario(request, response);
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
		}catch(NumberFormatException | ServletException | IOException ne) {

			servlet.ErrorPaciente(direcFallo, mensaje, response);		
		}
	}

	public void verEspecialistas(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		//Valida que haya una opcion seleccionada
		//Obtiene la especialidad seleccionada
		//Muestra todos los especialistas de esa especialidad		
		try {
			
			int codEspecialidad = Integer.parseInt(request.getParameter("opcionesEspecid"));				
			CtrlSolicitarTurno controlador = new CtrlSolicitarTurno();		

			Especialidad e = controlador.getOneEspecialidad(codEspecialidad);
			
			if(e == null) servlet.ErrorPaciente("SolicitarTurno","No se ha podido recuperar la especialidad", response);

			HttpSession s = request.getSession(false);
			s.setAttribute("espeSeleccionada", e);
			s.setAttribute("camino","especialista");
			
			opMenuSolicitarTurno(request, response);
			
		}catch(NumberFormatException | ServletException ne) {
			servlet.ErrorPaciente("SolicitarTurno","Por favor seleccione una opcion.", response);
		}
	}

	public void solicitarTurno(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		HttpSession sesion = request.getSession(false);
		sesion.setAttribute("camino","especialidad");
		
		opMenuSolicitarTurno(request, response);
	}
	
	public void reservarTurno(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		try {
			
			HttpSession sesion = request.getSession(false);
			Turno t = (Turno)sesion.getAttribute("TurnoNuevo");
			
			CtrlSolicitarTurno ctST = new CtrlSolicitarTurno();
			boolean exito = ctST.reservarTurno(t);
			
			if(exito)
			request.getRequestDispatcher("/WEB-INF/pac_TurnoReservado.jsp").forward(request, response);			
			else
			servlet.ErrorPaciente("ConfirmarHorario","No se ha podido confirmar el horario", response);
		
		} catch (IOException e) {
			servlet.ErrorPaciente("ConfirmarHorario","No se ha podido confirmar el horario", response);
		}
	}
	public void confirmarHorario(HttpServletRequest request, HttpServletResponse response)throws ServletException{	
		try {
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
		}catch (IOException | NumberFormatException e) {
				servlet.ErrorPaciente("SelectFecha","Se ha producido un error de conversion y/o ha ocurrido un error interno", response);
		}
	}
	public void verOtroMes(HttpServletRequest request, HttpServletResponse response)throws ServletException{
		try {

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
			servlet.ErrorPaciente("verCalendario","No se presiono ningun boton y/o accion invalida.", response);
		}
		
		sesion.setAttribute("mes", MES);
		sesion.setAttribute("anio", ANIO);
		
		opVerCalendario(request, response);
		
		}catch(IllegalStateException i) {
			servlet.ErrorPaciente("verCalendario","Se ha producido un erroren la pagina. Causa: "+i.getMessage(), response);
		}
	}
	public void verHorarios(HttpServletRequest request, HttpServletResponse response) throws ServletException{

		String fechaReserva = request.getParameter("Reserva");
		
		HttpSession sesion = request.getSession(false);
		sesion.setAttribute("fechaReserva", fechaReserva);
		
		opVerHorarios(request, response);
	}
	public void verCalendario(HttpServletRequest request, HttpServletResponse response)throws ServletException {
		
		try {
			int dniEspe = Integer.parseInt(request.getParameter("opEspecialistas"));				
			CtrlEspecialista ctrEsp = new CtrlEspecialista();

			Usuario us = (Usuario)ctrEsp.getOneEspecialista(dniEspe);
			
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
			
			opVerCalendario(request, response);
			
		} catch (SQLException e) {
			servlet.ErrorPaciente("verEspecialistas","Se ha producido un error. Causa: "+e.getMessage(), response);
		}catch (NumberFormatException et) {
			servlet.ErrorPaciente("verEspecialistas","Se ha producido un error con la conversion. Causa: "+et.getMessage(), response);
		}
	}
	public void agregarPlan(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
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
				servlet.ErrorPaciente("ConfiguracionPersonal","Nro de afiliado incorrecto, no debe poseer letras", response);
			}			
		}catch (SQLException |NumberFormatException | IOException e) {
			servlet.ErrorPaciente("ConfiguracionPersonal","Se ha producido un error", response);
		}
	}
	public void eliminarPlan(HttpServletRequest request, HttpServletResponse response) throws ServletException{

		HttpSession sesion = request.getSession();	
		Usuario user = (Usuario)sesion.getAttribute("usuario");
			
		CtrlConfiguracion controlador = new CtrlConfiguracion();
			
		try {
			controlador.eliminarPlan(user.getDni());
			request.getRequestDispatcher("/WEB-INF/confpersonal.jsp").forward(request, response);
				
		}catch (SQLException | IOException e) {
			servlet.ErrorPaciente("ConfiguracionPersonal","No se ha podido eliminar el plan", response);
		}
	}

	public void opVerHorarios(HttpServletRequest request, HttpServletResponse response)throws ServletException{
		try {			
			request.getRequestDispatcher("/WEB-INF/pac_VerHorarios.jsp").forward(request, response);
			
		} catch (IOException e) {			
			this.opVerCalendario(request, response);
		}
	}
	public void opVerCalendario(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		try {
			
			request.getRequestDispatcher("/WEB-INF/pac_calendario.jsp").forward(request, response);
			
		} catch (IOException e) {
			
			servlet.ErrorPaciente("verEspecialistas","Se ha producido un error. Causa: "+e.getMessage(), response);
		}
	}
	public void opMenuListaTurnos(HttpServletRequest request, HttpServletResponse response)throws ServletException{
		try {
			request.getRequestDispatcher("/WEB-INF/pac_ListadoTurnosPend.jsp").forward(request, response);
		} catch (IOException e) {
			servlet.ErrorPaciente("menuPaciente","Se ha producido un error al intentar redireccionar la pagina. Causa: "+e.getMessage(), response);
		}
	}
	public void opMenuSolicitarTurno(HttpServletRequest request, HttpServletResponse response) throws ServletException{	
		try {
			request.getRequestDispatcher("/WEB-INF/pac_SolicitarTurno.jsp").forward(request, response);
		} catch (IOException e) {
			
			servlet.ErrorPaciente("menuPaciente","Se ha producido un error al intentar redireccionar la pagina. Causa: "+e.getMessage(), response);
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
