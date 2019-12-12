<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>Configuracion personal</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/confPersonal.css">


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jsDetallesTurno.js"></script>
<%    
   Usuario usActual = (Usuario) session.getAttribute("usuario");
   CtrlConfiguracion controlador = new CtrlConfiguracion();
%>
</head>
<body>
    <div class="container">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
						<h2>Configuracion personal</h2>
					</div>
					<% if(usActual.getTipousuario() == Usuario.especialista) {%>
					<a href="especialista?opcion=MenuEsp" class="btn btn-info" ><i class="material-icons">exit_to_app</i> <span>Volver al menu</span></a>
					<%}if (usActual.getTipousuario() == Usuario.paciente){ %>
					<a href="paciente?opcion=menuPaciente" class="btn btn-info"><i class="material-icons">exit_to_app</i> <span>Volver al menu</span></a>
					<%} %>
                </div>
            </div>
            <table class="table table-striped table-hover">
                <thead>
                    <tr>						
                         <th></th>
                         <th></th>
                         <th></th>						            
                    </tr>
                </thead>
                <tbody>
                    <tr>			
                        <td>Nombre</td>
                        <td><input type="text" class="form-control" value="<%=usActual.getNombre() %>" disabled></td>
						<td></td>      
                    </tr>
                    <tr>				
                        <td>Apellido</td>
                        <td><input type="text" class="form-control" value="<%=usActual.getApellido() %>" disabled></td>
						<td></td>
                    </tr>
					<tr>					
                        <td>Fecha de nacimiento</td>
                        <td><input type="text" class="form-control" value="<%=usActual.getFechanacimiento() %>" disabled></td>
						<td></td>
                    </tr>
                    <tr>				
                        <td>DNI</td>
                        <td><input type="text" class="form-control" value="<%=usActual.getDni() %>" disabled></td>
                        <td></td>						
                    </tr>
                    <tr>					
                        <td>Email</td>
                        <td><input type="text" class="form-control" value="<%=usActual.getEmail() %>" disabled></td>
						<td><a href="#cambiarMailModal" class="btn btn-default" data-toggle="modal">Cambiar</a></td>
                    </tr>
                    <tr>					
                        <td>Contraseña</td>
                        <td><input type="text" class="form-control" value="**************" disabled></td>
						<td><a href="#cambiarClaveModal" class="btn btn-default" data-toggle="modal">Cambiar</a></td>
                    </tr>  
                </tbody>
            </table>
			<div class="clearfix">           
        </div>
    </div>
    <%if(usActual.getTipousuario()==Usuario.paciente){ %>
    <div class="card">
    	<div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
						<h2>Obras sociales</h2>
					</div>
					<div class="col-sm-6">
					<%	Paciente pac = controlador.getPaciente(usActual.getDni());
						if(pac.getPlan() == null){ 
					%>
						<a href="#agregarPlanModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">playlist_add</i> <span>Añadir plan</span></a>	
					<%	} %>
					</div>
                </div>
            </div>
            <table class="table table-striped table-hover">
                <thead>
                    <tr>
						<th>Obra social</th>
                        <th>Plan</th>
                        <th>ID Afiliado</th>						            
                    </tr>
                </thead>
                <tbody>
                    <tr>
                   <% if(pac.getPlan() !=null) { %>
						<td><%=pac.getPlan().getObs().getRazonSocial()%></td>
                        <td><%=pac.getPlan().getNomplan()%></td>
                        <td><%=pac.getNroAfiliado()%></td>
						<td><a href="#borrarPlanModal" class="btn btn-default" data-toggle="modal">Eliminar</a></td>
					<%} %>
                    </tr>
                                
                </tbody>
            </table>
			<div class="clearfix">
                
        </div>
    </div>
<%} %>
	<!-- Edit Modal HTML -->
	<div id="agregarPlanModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="paciente" method="post">
					<div class="modal-header">						
						<h4 class="modal-title">Añadir plan</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">					
						<div class="form-group">
							<label>Plan</label>
							<br/>
							<div class="custom-select" style="width:200px;">
							<select name="planes">
							<%	ArrayList<Plan> planes = controlador.getAllPlanes();
								for(Plan pa : planes) {
							%>								
								<option value="<%=pa.getId()%>"><%=pa.getNomplan()+" "+pa.getObs().getRazonSocial() %></option>	
  							<%} %>							
							</select>
						</div>
						<script>VerDetallesTurno()</script>
						</div>
						<div class="form-group">
							<label>Numero de afiliado</label>
							<input type="text" class="form-control" name="nroafiliado" required>
						</div>					
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancelar">
						<input type="submit" class="btn btn-success" name="opcion" value="Agregar">
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- Cambiar mail Modal HTML -->
	<div id="cambiarMailModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="configuracionPersonal" method="post">
					<div class="modal-header">						
						<h4 class="modal-title">Cambiar Email</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">					
						<div class="form-group">
						</div>
						<div class="form-group">
							<label>Nuevo email</label>
							<input type="text" class="form-control" name="mail" required></textarea>
						</div>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancelar">
						<input type="submit" class="btn btn-info" value="Guardar">
						<input type="hidden" name="opcion" value="cambiarMail">
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<!-- Cambiar clave Modal HTML -->
	<div id="cambiarClaveModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="configuracionPersonal" method="post">
					<div class="modal-header">						
						<h4 class="modal-title">Cambiar contraseña</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">					
						<div class="form-group">			
						</div>
						<div class="form-group">
							<label>Contraseña anterior</label>
							<input type="password" class="form-control" name="oldpass" required></textarea>
						</div>
						<div class="form-group">
							<label>Nueva contraseña</label>
							<input type="password" class="form-control" name="newpass" required></textarea>
						</div>
						<div class="form-group">
							<label>Repetir nueva contraseña</label>
							<input type="password" class="form-control" name="rnewpass" required></textarea>
						</div>				
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancelar">
						<input type="submit" class="btn btn-info" value="Cambiar contraseña">
						<input type="hidden" name="opcion" value="cambiarClave">
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- Delete Modal HTML -->
	<div id="borrarPlanModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="paciente" method="post">
					<div class="modal-header">						
						<h4 class="modal-title">Eliminar plan</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">					
						<p>Esta seguro de que desea eliminar este plan?</p>
						<p class="text-warning"><small>Necesitará volver a agregarlo para solicitar turnos con este plan</small></p>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancelar">
						<input type="submit" class="btn btn-danger" name="opcion" value="Eliminar">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>                                		                            