<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="entidades.*"%>
<%@page import="logica.*"%>
<%@page import="datos.*"%>
<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%@page import="java.text.*"%>
<html lang="es">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Detalles del turno</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/detalleTurnoStyle.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jsDetallesTurno.js"></script>
</head>
<% 
   int idturno = (int) session.getAttribute("idturno");
   String volvercommand = (String) session.getAttribute("detallesturnobotonvolver");
     
   CtrlDetalleTurno controlador = new CtrlDetalleTurno();
   Turno turno = controlador.getOneTurno(idturno);
%>
<body>
    <div class="container">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
						<h2>Detalles del turno</h2>
					</div>
					<div class="col-sm-6">
						<%
						if(volvercommand.equals("listadopendesp")){
						%>
						<a href="servletVerTurnosPendientesEsp" class="btn btn-info"><i class="material-icons">exit_to_app</i> <span>Volver al listado</span></a>
						<%} else{%>
						<a href="javascript:history.back()" class="btn btn-info"><i class="material-icons">exit_to_app</i> <span>Volver al listado</span></a>
						<%} %>

					</div>
                </div>
            </div>
            <table class="table table-striped table-hover">
                <thead>
                    <tr>						
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Nombre del paciente</td>
                        <td><%=turno.getPaciente().getNombre() + " " + turno.getPaciente().getApellido() %></td>						
                    </tr>
                    <tr>
                        <td>Fecha y hora del turno</td>
                        <%SimpleDateFormat formatohhmm = new SimpleDateFormat("HH:mm EEEEE dd 'de' MMMMM yyyy"); %>
                        <td><%=formatohhmm.format(turno.getFechahora()) %></td>
                    </tr>
                     <tr>
                        <td>Observacion</td>
                        <%if(turno.getEstado()==6) {%><td><%=turno.getObservacion() %></td><%}
                        if(turno.getEstado()==2){%> <td>--</td><%}%>
                    </tr>
                     <tr>
                        <td>Estado</td>
                        <%if(turno.getEstado()==6) {%><td>Finalizado</td><%}
                        if(turno.getEstado()==2){%><td>Reservado</td><%}%>
                    </tr>
                    <%Paciente paci = (Paciente)(turno.getPaciente()); 
                    if(paci.getPlan() != null){%>
                    <tr>
                        <td>Plan</td>                       
                        <td><%=paci.getPlan().getNomplan()+" "+paci.getPlan().getObs().getRazonSocial() %></td>
                    </tr>
                    <%} %>
                    <tr>
                        <td>Consultorio</td>
                        <td><%=turno.getConsultorio().getDesc() %></td>
                    </tr>
                </tbody>
            </table>			
    	</div>
    </div>
    <div class="container">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
						<h2>Practicas del turno</h2>
					</div>
					<div class="col-sm-6">
	
						<a href="#addPracticaModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">playlist_add</i> <span>Añadir práctica</span></a>	
					</div>
                </div>
            </div>
            <table class="table table-striped table-hover">
                <thead>
                    <tr>						
                        <th>Nombre de práctica</th>
                        <th>Valor</th>
                        <%if(paci.getPlan() != null){ %>
                        <th>Valor cubierto</th>
                        <%} %>
                         <th></th>
                    </tr>
                </thead>
                <tbody>
                <% double total = 0; %>
                <% for (Practica prac : turno.getPracticas()) {%>
                    <tr>
                        <td><%=prac.getDesc() %></td>                       
                        <% double valorDescuento = controlador.getValorPractica(prac,paci.getPlan());
                           total = total + valorDescuento ;
                        %>                       
                        <td><%=prac.getValor() %></td>
                        <%if(paci.getPlan() != null){ %>
                        <td><%=valorDescuento %></td>
                        <%} %>  
						<td><a href="#borrarPracticaModal<%=prac.getId() %>" class="btn btn-default" data-toggle="modal">Eliminar</a></td>
                    </tr>
						<!-- Borrar practica Modal HTML -->
							<div id="borrarPracticaModal<%=prac.getId() %>" class="modal fade">
								<div class="modal-dialog">
									<div class="modal-content">
										<form action="servletQuitarPracticaTurno" method="post">
											<div class="modal-header">						
												<h4 class="modal-title">Eliminar práctica</h4>
												<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
											</div>
											<div class="modal-body">					
												<p>Esta seguro de que desea eliminar esta práctica del turno?</p>
												<input type="hidden" name="idturno" value="<%=turno.getIdturno()%>">
												<input type="hidden" name="idpractica" value="<%=prac.getId() %>">
											</div>
											<div class="modal-footer">
												<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancelar">
												<input type="submit" class="btn btn-danger" value="Eliminar">
											</div>
										</form>
									</div>
								</div>
							</div>
               <%} %>
                <tr>
                <td><b>Total de consulta : </b></td>
                <td></td>
                <td><b>$ <%=total %></b></td>
                <td></td>
                </tr>
                </tbody>
            </table>			
    	</div>
    </div>
<!-- Agregar Practica HTML -->
	<div id="addPracticaModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="servletAgregarPracticaTurno" method="post">
					<div class="modal-header">						
						<h4 class="modal-title">Añadir práctica</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">					
						
						<div class="form-group">
							<label>Práctica</label>
							<br/>
							<input type="hidden" name="idturno" value="<%=turno.getIdturno()%>">
							<div class="custom-select" style="width:200px;">
							<select name="idpractica">							
								<option value="0" disabled="disabled">     --      </option>
								<%for(Practica pract : ((Especialista)(turno.getEspecialista())).getPracticas()){%>
 								<option value="<%=pract.getId() %>"><%=pract.getDesc() %></option>
  								<%} %>
							</select>
						</div>
						<script>VerDetallesTurno()</script>
						</div>										
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancelar">
						<input type="submit" class="btn btn-success" value="Agregar">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>                                		                                                     		                                                  