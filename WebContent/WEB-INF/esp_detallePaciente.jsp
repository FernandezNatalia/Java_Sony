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
<!--===============================================================================================-->
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/detalleTurnoStyle.css">
<!--===============================================================================================-->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jsDetallesTurno.js"></script>
<!--===============================================================================================-->
<% 
   Paciente paciente = (Paciente) session.getAttribute("pacseleccionado");
   session.setAttribute("detallesturnobotonvolver", "detallePaciente?dnipaciente=" + String.valueOf(paciente.getDni()));
%>
<!--===============================================================================================-->
</head>
<body>
    <div class="container">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
						<h2>Detalles del paciente</h2>
					</div>
					<div class="col-sm-6">						
						<a href="especialista?opcion=Gestionar pacientes" class="btn btn-info"><i class="material-icons">exit_to_app</i> <span>Volver al listado</span></a>					
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
                        <td><%=paciente.getNombre() + " " + paciente.getApellido() %></td>						
                    </tr>
                    <tr>
                        <td>Fecha de nacimiento</td>
                        <td><%=Conversion.formatoEEEEdeMMMMyyyy.format(paciente.getFechanacimiento()) %></td>
                    </tr>
                     <tr>
                        <td>DNI</td>
                        <td><%=paciente.getDni() %></td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td><%=paciente.getEmail() %></td>
                    </tr>
                     <tr>
                    <%if(paciente.getPlan() != null){%>
                    <tr>
                        <td>Plan</td>                       
                        <td><%=paciente.getPlan().getNomplan()+" "+paciente.getPlan().getObs().getRazonSocial() %></td>
                    </tr>
                    <%} else{%>
                    <tr>
                        <td>Plan</td>                       
                        <td>Sin obra social/Particular</td>
                    </tr>
                    <%} %>
                </tbody>
            </table>			
    </div>
    </div>
    <div class="container">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
						<h2>Historial de turnos</h2>
					</div>
					<div class="col-sm-6">
					</div>
                </div>
            </div>
            <div class="card mb-3">
          <div class="card-header">
            </div>
          <div class="card-body">
            <div class="table-responsive">
              <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                  <tr>
                    <th>Fecha y hora</th>                  
                    <th>Consultorio</th>
                    <th>Observación</th>
                    <th>Detalle</th>
                  </tr>
                </thead>
                <tfoot>
                  <tr>
                    <th>Fecha y hora</th>                
                    <th>Consultorio</th>
                    <th>Observación</th>
                    <th>Detalle</th>
                  </tr>
                </tfoot>
                <tbody>
                <%
                Usuario especialistausr = (Usuario) session.getAttribute("usuario");
                CtrlEspecialista crl = new CtrlEspecialista();
                Especialista especialista = crl.getOneEspecialista(especialistausr.getDni());
                
                CtrlTurno ctrltur = new CtrlTurno();
                ArrayList<Turno> turnospac = ctrltur.getAtencionesPaciente(paciente,especialista);
                
                for (Turno turno : turnospac) {
                %>
                    <tr>
                    	<td><%=turno.getFechahora() %></td>                                                                                           
                        <td><%=turno.getConsultorio().getDesc() %></td>
                        <td><%=turno.getObservacion() %>
						<td><a href="MisTurnos?opcion=detallesTurno&idturno=<%=turno.getIdturno() %>" class="btn btn-default" data-toggle="modal">Ver detalle</a></td>
                    </tr>
               <%} %>
                </tbody>
            </table>			
    	</div>
    </div>
<!--===============================================================================================-->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
  <script src="vendor/datatables/jquery.dataTables.js"></script>
  <script src="vendor/datatables/dataTables.bootstrap4.js"></script>
  <script src="js/demo/datatables-demo.js"></script>
<!--===============================================================================================-->
</body>
</html>                                		                                                     		                                                  