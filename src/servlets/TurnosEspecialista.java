package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datos.TurnoDatos;
import entidades.Turno;
import entidades.Usuario;
import logica.Conversion;
import logica.CtrlDetalleTurno;
import logica.CtrlTurno;
import logica.ValidacionNegocio;

/**
 * Servlet implementation class TurnosEspecialista
 */
@WebServlet("/MisTurnos")
public class TurnosEspecialista extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TurnosEspecialista() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		servlet.VerificarSesionYUsuario(request, response, Usuario.especialista);	
		String opcion = request.getParameter("opcion");	
		switchEspecialista(request, response, opcion);
	}

	String direcFallo;
	String mensaje;
	
	public void switchEspecialista(HttpServletRequest request, HttpServletResponse response, String opcion) throws ServletException {	
		direcFallo = "MenuEsp";
		mensaje= "Se ha producido un error";
		
		try {
			switch(opcion)
			{	
			case "verListado":
				request.getRequestDispatcher("WEB-INF/esp_MisTurnosPend.jsp").forward(request, response);
				break;
			case "crearTurno":
				agregarNuevoTurno(request, response);	
				break;
			case "finalizarTurno":
				finalizarTurno(request, response);		
				break;	
			case "eliminarTurno":
				eliminarTurno(request, response);
				break;
			case "EspecialistaCambioFecha":
				especialistaCambioFecha(request, response);
				break;
			case "EspecialistaTurnosDisponibles":
				espTurnosDisponibles(request, response);
				break;			
			case "detallesTurno":
				detallesTurno(request, response);
				break;
			case "BorrarPracticaTurno":
				quitarPracticaTurno(request, response);
				break;
			case "AgregarPracticaTurno":
				agregarPracticaTurno(request, response);
				break;
			default:
				request.getRequestDispatcher("WEB-INF/menuEspecialista.html").forward(request, response);
				break;
			}
		}catch(NumberFormatException | ServletException | IOException ne) {	
			servlet.ErrorEsp(direcFallo, mensaje, response);		
		}catch(Exception e1) {
			servlet.ErrorEsp(direcFallo, "Ha ocurrido un error. Causa: "+e1.getMessage(), response);	
		}
	}

	public void agregarPracticaTurno(HttpServletRequest request, HttpServletResponse response) throws ServletException {		
		
		String idTurnoStr = request.getParameter("idturno");
		String idPracticaStr = request.getParameter("idpractica");
		
		try {
			if(ValidacionNegocio.ValidarInteger(idTurnoStr) && ValidacionNegocio.ValidarInteger(idPracticaStr)) {
				int idTurno = Conversion.ConvertirStringAInteger(idTurnoStr);
				int idPractica = Conversion.ConvertirStringAInteger(idPracticaStr);
				
				HttpSession sesion = request.getSession(false);
				sesion.setAttribute("idturno", idTurno);
				
				CtrlDetalleTurno controlador = new CtrlDetalleTurno();
				if(controlador.AgregarPractica(idTurno, idPractica)) {
					request.getRequestDispatcher("WEB-INF/esp_detallesturno.jsp").forward(request, response);
				}
				else {
					servlet.ErrorEspMisTurnos("detallesTurno&idturno="+idTurnoStr,"No se ha podido agregar la practica",response);
				}
			}else {
				servlet.ErrorEspMisTurnos("detallesTurno&idturno="+idTurnoStr,"No se ha podido agregar la practica",response);
			}
		}catch(IOException ie) {
			servlet.ErrorEspMisTurnos("detallesTurno&idturno="+idTurnoStr,"No se ha podido eliminar la practica",response);
		}
	}

	public void quitarPracticaTurno(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		String idTurnoStr = request.getParameter("idturno");
		String idPracticaStr = request.getParameter("idpractica");
		
		try {
			if(ValidacionNegocio.ValidarInteger(idTurnoStr) && ValidacionNegocio.ValidarInteger(idPracticaStr)) {
				int idTurno = Conversion.ConvertirStringAInteger(idTurnoStr);
				int idPractica = Conversion.ConvertirStringAInteger(idPracticaStr);
				
				HttpSession sesion = request.getSession(false);
				sesion.setAttribute("idturno", idTurno);
				
				CtrlDetalleTurno controlador = new CtrlDetalleTurno();
				if(controlador.EliminarPracticaDeTurno(idTurno, idPractica)) {
					request.getRequestDispatcher("WEB-INF/esp_detallesturno.jsp").forward(request, response);
				}
				else {
					servlet.ErrorEspMisTurnos("detallesTurno&idturno="+idTurnoStr,"No se ha podido eliminar la practica",response);
				}
			}else {
				servlet.ErrorEspMisTurnos("detallesTurno&idturno="+idTurnoStr,"No se ha podido eliminar la practica",response);
			}
		}catch(IOException e) {
			servlet.ErrorEspMisTurnos("detallesTurno&idturno="+idTurnoStr,"No se ha podido eliminar la practica",response);
		}	
	}

	public void espTurnosDisponibles(HttpServletRequest request, HttpServletResponse response)throws ServletException {
		try {
			HttpSession sesion = request.getSession(false);
			
			if((Integer)sesion.getAttribute("estado") == Turno.reservado) {	
				
				sesion.setAttribute("estado",Turno.disponible);				
				sesion.setAttribute("botonEstado","Ver turnos reservados");	
				
			}else {
				sesion.setAttribute("estado", Turno.reservado);
				sesion.setAttribute("botonEstado","Ver turnos disponibles");
				}
			
			request.getRequestDispatcher("WEB-INF/esp_MisTurnosPend.jsp").forward(request, response);
			
		} catch (IOException e) {
			servlet.ErrorEspListado("Ha ocurrido un error al querer mostrar los turnos disponibles", response);
		}
	}

	public void detallesTurno(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			HttpSession sesion = request.getSession(false);
			
			String stridturno = request.getParameter("idturno");
			int idturno=0;
			
			if(ValidacionNegocio.ValidarInteger(stridturno)) {
				idturno = Integer.parseInt(stridturno);
			
				Usuario usActual = (Usuario) sesion.getAttribute("usuario");
				TurnoDatos td = new TurnoDatos();
				
				//Valido que el turno sea de ese especialista
				if(usActual.getDni()==td.getOne(idturno).getEspecialista().getDni()) {
					sesion.setAttribute("idturno", idturno);				
					request.getRequestDispatcher("WEB-INF/esp_detallesturno.jsp").forward(request, response);
				}else {
					servlet.ErrorEspListado("El turno no corresponde al especialista", response);
				}
			}else {
				servlet.ErrorEspListado("ID de turno incorrecto", response);
			}
		}catch(Exception e) {
			servlet.ErrorEspListado("Se ha producido un error. Causa: "+e.getMessage(), response);
		}
	}

	public void especialistaCambioFecha(HttpServletRequest request, HttpServletResponse response)throws ServletException{
		try {
			
			HttpSession sesion = request.getSession();
			String strFecha = request.getParameter("fechaDeseada");			
				
			if(ValidacionNegocio.ValidarFecha(strFecha)) {
				java.sql.Date fecha = Conversion.ConvertirStringAFechaSql(strFecha);
				sesion.setAttribute("fecha", fecha);
				
				request.getRequestDispatcher("WEB-INF/esp_MisTurnosPend.jsp").forward(request, response);
				
			}else {		
				servlet.ErrorEspListado("No se ha podido cambiar la fecha.", response);			
			}
		}catch(IOException e) {
			servlet.ErrorEspListado("No se ha podido cambiar la fecha.", response);	
		}
	}

	public void eliminarTurno(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {		
			String strIDTurno = request.getParameter("idturno");
			
			if(ValidacionNegocio.ValidarInteger(strIDTurno)){
				
				int idTurno = Conversion.ConvertirStringAInteger(strIDTurno);				
				CtrlTurno controlador = new CtrlTurno();	
				
				if(!controlador.EliminarTurno(idTurno)) {
					servlet.ErrorEspListado("Ha ocurrido un error, no se puede eliminar el turno", response);		
				}
				request.getRequestDispatcher("WEB-INF/esp_MisTurnosPend.jsp").forward(request, response);	
			}
		}catch(IOException e) {
			servlet.ErrorEspListado("Ha ocurrido un error, no se puede eliminar el turno", response);
		}
	}


	public void finalizarTurno(HttpServletRequest request, HttpServletResponse response)throws ServletException {
		
		try {
			String observacion=request.getParameter("observacion");
			String strIDTurno = request.getParameter("idturno");
			
			//Valido que el id sea integer
			if(ValidacionNegocio.ValidarInteger(strIDTurno)){
				int idTurno = Conversion.ConvertirStringAInteger(strIDTurno);				
				CtrlTurno controlador = new CtrlTurno();	
				
				//El controlador del turno se encarga de finalizarlo
				if(controlador.FinalizarTurno(observacion,idTurno)) {
					
					request.getRequestDispatcher("WEB-INF/esp_MisTurnosPend.jsp").forward(request, response);				
				}else {
					servlet.ErrorEspListado("Ha ocurrido un error, no se puede finalizar el turno", response);
				}			
			}
		}catch(IOException e) {
			servlet.ErrorEspListado("Ha ocurrido un error, no se puede finalizar el turno", response);
		}
	}

	public void agregarNuevoTurno(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		try{
			HttpSession sesion = request.getSession(false);
		
			Usuario especialista= (Usuario)(sesion.getAttribute("usuario"));
			String strFechaHora=request.getParameter("fecha")+" "+request.getParameter("hora");
			String strConsultorio = request.getParameter("cons");
			int estado = (Integer)sesion.getAttribute("estado");
			
			CtrlTurno controlador = new CtrlTurno();				
			if(controlador.AgregarNuevoTurno(strFechaHora,strConsultorio,especialista.getDni())) {
				
				//Mejor asi, queda la url correcta en el navegador
				//Hay que verificar si esta en turnos disponibles o reservados
				//para redirigirlo a la pag correcta
				if(estado == Turno.disponible){
					request.getRequestDispatcher("WEB-INF/esp_MisTurnosPend.jsp").forward(request, response);
				}
				if(estado == Turno.reservado){			
					request.getRequestDispatcher("WEB-INF/esp_MisTurnosPend.jsp").forward(request, response);		
				}
				
			}else {
				
				servlet.ErrorEspListado("El consultorio esta ocupado en ese horario", response);
			}
		}catch(IOException | ParseException | SQLException e) {
			servlet.ErrorEspListado("No se ha podido crear un turno. Causa: "+e.getMessage(), response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
