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

<style>
html, body{
	margin: 0;
	padding: 0;
	background-color: #bdbdbd;
}
body {
  font-family: 'Open Sans', sans-serif;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.row {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0 25px;
}



/*Estilos sacados del menu paciente*/
.table-wrapper {
	background: #fff;
	padding: 20px 25px;
	margin: 30px 0;
	border-radius: 3px;
	box-shadow: 0 1px 1px rgba(0,0,0,.05);
	min-width : 540px;
	max-width: auto
}
.table-title {
	padding-bottom: 15px;
	background: #505050;
	color: #fff;
	padding: 16px 30px;
	margin: -20px -25px 10px;
	border-radius: 3px 3px 0 0;
}
.table-title h2 {
	margin: 5px 0 0;
	font-size: 24px;
}
.table-title .btn-group {
	float: right;
}
.table-title .btn {
	color: #fff;
	float: right;
	font-size: 13px;
	border: none;
	min-width: 50px;
	border-radius: 2px;
	border: none;
	outline: none !important;
	margin-left: 10px;
}
.table-title .btn i {
	float: left;
	font-size: 21px;
	margin-right: 5px;
}
.table-title .btn span {
	float: left;
	margin-top: 2px;
}
table.table tr th, table.table tr td {
	border-color: #e9e9e9;
	padding: 12px 15px;
	vertical-align: middle;
}
table.table tr th:first-child {
	width: 200px;
}
table.table tr th:last-child {
	width: 135px;
}
table.table-striped tbody tr:nth-of-type(odd) {
	background-color: #fcfcfc;
}
table.table-striped.table-hover tbody tr:hover {
	background: #f5f5f5;
}
table.table th i {
	font-size: 13px;
	margin: 0 5px;
	cursor: pointer;
}
table.table td:last-child i {
	opacity: 0.9;
	font-size: 22px;
	margin: 0 5px;
}
table.table td a {
	font-weight: bold;
	color: #566787;
	display: inline-block;
	text-decoration: none;
	outline: none !important;
}
table.table td a:hover {
	color: #2196F3;
}
table.table td a.edit {
	color: #FFC107;
}
table.table td a.more{
	color: green
}
table.table td a.delete {
	color: #F44336;
}
table.table td i {
	font-size: 19px;
}
table.table .avatar {
	border-radius: 50%;
	vertical-align: middle;
	margin-right: 10px;
}
</style>

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
						<form action="servletPrincipal" method="get">
							<button type="submit" class="btn btn-info" ><i class="material-icons">exit_to_app</i> <span>Volver al menu</span>
							</button>
						</form>	
					</div>				
                </div>
            </div>   
            <br></br>
            
<!-- LISTADO DE HORARIOS -->
<%

Usuario us = (Usuario)session.getAttribute("Especialista");

CtrlTurno controladorTurno = new CtrlTurno();
String strFecha = (String)session.getAttribute("fechaReserva");
java.sql.Date sqlFechaDispo = Conversion.ConvertirStringAFechaSql(strFecha);

ArrayList<Turno> turnosHorariosDispo = controladorTurno.getTurnosDisponiblesAFecha(us,sqlFechaDispo); 

%>
	<div class="text-center">
		<div class="panel panel-success"> 
			<div class="alert alert-info alert-dismissible" role="alert">
				<strong>Ya casi!</strong> Seleccione un horario en el que desee asistir a su turno.
			</div><br>
			<div class="row">	
				<div class="list-group">
				
				  <a href="#" class="list-group-item list-group-item-action active">Horarios disponibles</a>
				  
				  <!-- Listo cada horario con disponibilidad -->
				  <% for(Turno t : turnosHorariosDispo){
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
				<button type="submit" class="btn btn-info" name="opcion" value="verCalendario">
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