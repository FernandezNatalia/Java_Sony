<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entidades.*"%>
<%@page import="logica.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.*"%>    
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Turno seleccionado</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/menuPaciente.css">
<link rel="stylesheet" type="text/css" href="css/baseSolicitarTurno.css">
<link rel="stylesheet" type="text/css" href="css/TurnoReservadoListado.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
						<h2>Solicitar turno</h2>												
					</div>	
					<div class="col-sm-6">	
						<a href="paciente?opcion=menuPaciente" class="btn btn-info"><i class="material-icons">exit_to_app</i> <span>Volver al menu</span></a>
					</div>				
                </div>
            </div>   
            <br></br>
			<div class="text-center">
				<div class="panel panel-success"> 
					<div class="alert alert-warning alert-dismissible" role="alert">
						<strong>Reserva de turno:</strong> Verifique que los datos seleccionados son correctos.
					</div><br>
<%
	Especialista e = (Especialista)session.getAttribute("Especialista");
	Turno t = (Turno)session.getAttribute("TurnoNuevo");
%>
			<!-- CARD del turno -->
			<div class="login-form">    
			    <form action="paciente" method="post">
					<div class="avatar"><i class="material-icons">&#xE7FF;</i></div>
			    	<h4 class="modal-title" style="text-decoration: underline;"><%=e.getEspecialidad() %></h4>
			    	<div style="text-align:left;" >
			    	
			    	<p>Especialista: <%=e.getNombre()+" "+e.getApellido() %> </p>
			    	<p>Fecha: <%=Conversion.formatter1ddmmyy.format(t.getFechahora()) %> </p>
			    	<p>Hora: <%=t.getFechahora().getHours()+":"+Conversion.convertirMinutosConCero(t.getFechahora().getMinutes()) %></p> 
			    	<p>Direccion: <%=t.getConsultorio().getDireccion() %></p>
			    	
			    	</div>
			    	<br>
			    	<button type="submit" class="btn btn-info" name="opcion" value="ReservarTurno">
			    		Reservar
					</button>
			    </form>			
			</div>							
			<br></br>
		</div>
	</div>
</div>
</div>
</body>
</html>