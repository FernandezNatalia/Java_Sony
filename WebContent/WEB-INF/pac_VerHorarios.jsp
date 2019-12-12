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
<title>Ver horarios</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600" rel="stylesheet">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css" href="css/menuPaciente.css">
<link rel="stylesheet" type="text/css" href="css/baseSolicitarTurno.css">

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
            
<!-- LISTADO DE HORARIOS -->
	<div class="text-center">
		<div class="panel panel-success"> 
			<div class="alert alert-info alert-dismissible" role="alert">
				<strong>Ya casi!</strong> Seleccione un horario en el que desee asistir a su turno.
			</div><br>
			<div class="row">	
				<div class="list-group">
				
				  <a href="#" class="list-group-item list-group-item-action active">Horarios disponibles</a>
				  
				  <!-- Listo cada horario con disponibilidad -->
				  <% 
				  ArrayList<Turno> turnosHorariosDispo = (ArrayList<Turno>)request.getAttribute("turnosConHorariosDispo");
				  
				  for(Turno t : turnosHorariosDispo){
					  String mins = Conversion.convertirMinutosConCero(t.getFechahora().getMinutes());
				  %>
				  	<form action="paciente" method="post">
				  	<input type="hidden" name="opcion" value="ConfirmarHorario"></input>
						<button type="submit" class="list-group-item list-group-item-action" style="text-align:center;"
								name="idTurnoHorario" value="<%=t.getIdturno() %>">
					  		<%=t.getFechahora().getHours()+":"+mins %>
						</button>
					</form>	
				  <%}%>
				  
				</div>
			</div>
			<br></br>
			<form action="paciente" method="post">
				<button type="submit" class="btn btn-info" name="opcion" value="volverAlCalendario">
			  		Volver al calendario
				</button>
			</form>	
			<br></br>
		</div>
	</div>
</div>
</div>


</body>
</html>