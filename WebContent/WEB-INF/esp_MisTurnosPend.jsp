<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="entidades.*"%>
<%@page import="logica.*"%>
<%@page import="datos.*"%>
<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%@page import="java.text.*"%>
<%@ page errorPage="/error.jsp" %>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Mis turnos</title>
<!--===============================================================================================-->
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/esp_TurnosPend.css">
<!--===============================================================================================-->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/esp_TurnosPend.js"></script>
<!--===============================================================================================-->
<% 		
		//Aca se guarda una variable de sesion para determinar a que servlet se vuelve al 
		//presionar el boton volver en detalles del turno
		session.setAttribute("detallesturnobotonvolver", "listadopendesp");

		Usuario us= (Usuario)session.getAttribute("usuario");
		java.sql.Date fechaVista = (java.sql.Date) session.getAttribute("fecha");
		int estado = (Integer)session.getAttribute("estado");
%>
<!--===============================================================================================-->
</head>
<body>
    <div class="container">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
						<h2>Mis <b>turnos</b></h2>
					</div>
					<div class="col-sm-6">
						<a href="especialista?opcion=MenuEsp" class="btn btn-info" ><i class="material-icons">exit_to_app</i> <span>Volver al menu</span></a>
						<a href="#addEmployeeModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Agregar nuevo turno</span></a>
					</div>
                </div>
            </div>
            <table class="table table-striped table-hover">
                <thead>
                    <tr>			
                        <th>Fecha y hora</th>
						<th>Dni paciente</th>
                        <th>Nombre y Apellido</th>
						<th>Consultorio</th>
                        <th>Estado</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                <% 
                CtrlTurno tl = new CtrlTurno();	
            	ArrayList<Turno> lt=tl.getProximosDeEspecialista(us,fechaVista,estado);
            	
                for (Turno tur : lt) {             
                %>
                    <tr>
			            <% 
		           		String strfechahora = Conversion.formatohhmmFechaLarga.format(tur.getFechahora());
		           		String str = strfechahora.substring(0,6) + strfechahora.substring(6,7).toUpperCase() + strfechahora.substring(7);
			             %>
                        <td><%=str%></td>
                        <%if (tur.getEstado() == Turno.reservado) {%>
                        	<td><%=tur.getPaciente().getDni()%></td>
                        	<td><%=tur.getPaciente().getNombre()+" "+tur.getPaciente().getApellido() %></td>
						<%}
                        if (tur.getEstado() == Turno.disponible) {%>
							<td>--</td>
							<td>--</td>
						<%} %>
							<td><%=tur.getConsultorio().getDesc()%></td>                       
                        <%if (tur.getEstado() == Turno.reservado) {%>
                        	<td>Ocupado</td>
                        	<td>
                        	<%
                        	String comprarF = Conversion.formatter1ddmmyy.format(tur.getFechahora()).toString();
                        	//Si el turno es mayor a la fecha actual entonces no se debe poder finalizar
                        	if(!ValidacionNegocio.EsMayorAFechaActual(comprarF)){
                        	%>
                            <a href="#finTurnoModal<%=tur.getIdturno()%>" class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Finalizar turno">check_circle</i></a>
                            <%} %>
                            <a href="MisTurnos?opcion=detallesTurno&idturno=<%=tur.getIdturno() %>" class="more" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Mas información">more_horiz</i></a>                           
                           	<%
                           	//Si el turno es menor a la fecha actual entonces no se puede enviar un mail para cancelarlo
                           	if(!ValidacionNegocio.EsMenorAFechaActual(comprarF)){%>             
                            <a href="#cancelarTurnoModal<%=tur.getIdturno()%>" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Cancelar">&#xE872;</i></a>
                        	<%} %>
                        <%}if (tur.getEstado() == Turno.disponible) {%>
                        	<td>Disponible</td>
                        	<td>
                            <a href="#eliminarTurnoModal<%=tur.getIdturno()%>" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Eliminar turno">&#xE872;</i></a>	
                        <%} %>
                        </td>
                    </tr>
                    <%}%>					
                </tbody>
            </table>
			<a href="#verSemanaTurno" class="btn btn-success" data-toggle="modal"><span>Ver hasta fecha</span></a>
			<a href="MisTurnos?opcion=EspecialistaTurnosDisponibles" class="btn btn-success"><span><%=(String)session.getAttribute("botonEstado") %></span></a>
        </div>
    </div>
    
<!--===============================================================================================-->
<!--========================================VENTANAS MODALES ======================================-->
<!--===============================================================================================-->

<!-- Edit Modal -->
	<div id="addEmployeeModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="MisTurnos" method = "post">
					<div class="modal-header">						
						<h4 class="modal-title">Nuevo turno</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">					
						<div class="form-group">
							<%
								Date date = new Date();
								String fecmin = Conversion.formatoddmmyy.format(date);						
							%>
							<label>Fecha</label>
							<input type="date" class="form-control" min="<%=fecmin%>" name="fecha" required>
						</div>
						<div class="form-group">
							<label>Hora</label>
							<input type="time" class="form-control" name="hora" required>
						</div>
						<div class="form-group">
							<label>Consultorio</label>
							<br/>
							<div class="custom-select" name="consultorio" style="width:200px;">
							<select name="cons">
								<option value="0" disabled="disabled">     --      </option>
								<% for(Consultorio con : tl.getAllConsultorios()){%>
 									<option value="<%=con.getIdconsultorio()%>"><%=con.getDesc() %></option>
 								<% }%>
							</select>
						</div>
						<script>VerConsultorios();</script>
						</div>		
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancelar">
						<input type="submit" class="btn btn-success" value="Agregar">
						<input type="hidden" name="opcion" value="crearTurno">
					</div>
				</form>
			</div>
		</div>
	</div>
<!-- finalizar turno Modal -->
	<% for (Turno tur : lt) {
     if (tur.getEstado() == Turno.reservado) {%>	
	<div id="finTurnoModal<%=tur.getIdturno()%>" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="MisTurnos" method="post">
					<div class="modal-header">						
						<h4 class="modal-title">Finalizar turno</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">					
						<div class="form-group">
						</div>
						<div class="form-group">
							<label>Observación</label>
							<input type="text" class="form-control" name="observacion" required></textarea>
							<input type="hidden" name="idturno" value="<%=tur.getIdturno()%>">
						</div>			
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancelar">
						<input type="submit" class="btn btn-info" value="Guardar">
						<input type="hidden" name="opcion" value="finalizarTurno">
					</div>
				</form>
			</div>
		</div>
	</div>		
<!-- Cancelar turno -->
	<div id="cancelarTurnoModal<%=tur.getIdturno()%>" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="servletCancelarTurno" method="post">
					<div class="modal-header">						
						<h4 class="modal-title">Cancelar Turno</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">					
						<p>Esta seguro de que desea cancelar este turno?</p>
						<p class="text-warning"><small>Se creara un turno disponible con el mismo horario</small></p>
						<input type="hidden" name="idturno" value="<%=tur.getIdturno()%>">
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Volver">
						<input type="submit" class="btn btn-danger" value="Cancelar turno">
					</div>
				</form>
			</div>
		</div>
	</div>	
	<%}} %>
	<% for (Turno tur : lt) {%>
    <% if (tur.getEstado() == Turno.disponible) {%>
<!-- Modal Eliminar turno -->
	<div id="eliminarTurnoModal<%=tur.getIdturno()%>" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="MisTurnos" method="post">
					<div class="modal-header">						
						<h4 class="modal-title">Eliminar turno</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">					
						<p>Esta seguro de que desea eliminar este turno?</p>
						<p class="text-warning"><small>Esta acción no se puede deshacer.</small></p>
						<input type="hidden" name="idturno" value="<%=tur.getIdturno()%>">
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancelar">
						<input type="submit" class="btn btn-danger" value="Eliminar">
						<input type="hidden" name="opcion" value="eliminarTurno">
					</div>
				</form>
			</div>
		</div>
	</div>
<%}} %>
<!-- verSemanaTurno Modal -->
	<div id="verSemanaTurno" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="MisTurnos" method="post">
					<div class="modal-header">						
						<h4 class="modal-title">Ver hasta la fecha</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">					
						<div class="form-group">
						</div>
					<div class="form-group">
						<label>Ingrese una fecha (yyyy-mm-dd)</label>
						<input type="date" class="form-control" min="<%=fecmin%>" name="fechaDeseada" required></textarea>
					</div>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancelar">
						<input type="submit" class="btn btn-info" value="Aceptar">
						<input type="hidden" name="opcion" value="EspecialistaCambioFecha">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>                                		                            