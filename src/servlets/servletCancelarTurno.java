package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Especialista;
import entidades.Turno;
import entidades.Usuario;
import logica.Conversion;
import logica.CtrlTurno;
import logica.Emailer;
/**
 * Servlet implementation class servletCancelarTurno
 */
@WebServlet({"/servletCancelarTurno","/paciente/servletCancelarTurno"})
public class servletCancelarTurno extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletCancelarTurno() {
        super();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		Usuario us = (Usuario)(sesion.getAttribute("usuario"));
		
		servlet.VerificarSesion(request, response);
		Integer IDTurno = Conversion.ConvertirStringAInteger(request.getParameter("idturno"));
	
		//Llamo al controlador, se elimina el turno y se redirecciona al listado.		
		CtrlTurno controlador = new CtrlTurno();			
		if(controlador.CancelarTurno(IDTurno)) {		
			Emailer em = new Emailer();
			Turno turno = controlador.getOne(IDTurno);
			
			//Pongo la fecha mas presentable antes de meterla en el cuerpo del mail
			//Formateo la fecha del turno
			String fechayhoramin = Conversion.formatoEEEEdeMMMMyyyyHHmm.format(turno.getFechahora());
            String fechayhora = fechayhoramin.substring(0, 1).toUpperCase() + fechayhoramin.substring(1);
            
			if(us.getTipousuario() == Usuario.especialista){
				
				String cuerpomail = "Su turno con el especialita " + turno.getEspecialista().getNombre() + " " 
									+ turno.getEspecialista().getApellido() + ", reservado para el dia " 
									+ fechayhora + " ha sido cancelado por el especialista. \n\n Lamentamos las molestias ocasionadas,"
											+ "\n\n Departamento de "+ ((Especialista)turno.getEspecialista()).getEspecialidad();
	
				
				em.send(turno.getPaciente().getEmail(), "Turno cancelado", cuerpomail);
				request.getRequestDispatcher("WEB-INF/esp_MisTurnosPend.jsp").forward(request, response);	
			}
			if(us.getTipousuario() == Usuario.paciente) {
				
				String cuerpomail = "Estimado/a ,\n Su turno con el paciente \n DNI: "+us.getDni()+"\n Nombre y apellido: "+us.getNombre()+" "+us.getApellido()+							
									"\n Fecha: "+fechayhora+"\n ha sido cancelado.\n\n Lamentamos las molestias ocasionadas.";
				
				em.send(turno.getEspecialista().getEmail(), "Turno cancelado", cuerpomail);
				servletPaciente.opMenuListaTurnos(request, response);
			}
			
		}else {	
			if(us.getTipousuario() == Usuario.especialista)			
				servlet.ErrorEspListado("No se ha podido cancelar el turno", response);
			if(us.getTipousuario() == Usuario.paciente)
				request.getRequestDispatcher("/WEB-INF/pac_ListadoTurnosPend.jsp").forward(request, response);		
		}						
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
