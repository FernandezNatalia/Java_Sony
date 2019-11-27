<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="entidades.*"%>
<%@page import="logica.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.*"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Solicitar turno</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600" rel="stylesheet">



<link rel="stylesheet" type="text/css" href="css/pac_SolicitarTurno.css">


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>




<style type="text/css">
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
						<a href="#" class="btn btn-info" ><i class="material-icons">exit_to_app</i> <span>Volver al menu</span></a>
					</div>				
                </div>
            </div>   
            <br></br>
            
	<!-- SELECCION DE ITEMS -->
	
	<div class="text-center">
	<div class="panel panel-success"> 
     	<div class="alert alert-info alert-dismissible" role="alert">
  			<strong>Elija sus preferencias!</strong> Seleccione la especialidad y el especialista que desee.
		</div>   
		<br></br>  
          
    <!-- ELECCION DE ESPECIALIDADES -->   
        
            <div class="row">
            <div class="form-group" style="width:400px;">
            <label>ESPECIALIDAD: </label>
	            <select name="opcionesEspecid" id="opcionesEspecid" required="true" class="form-control input-sm">
	            <option value=""></option>
	            <% 	
	            	CtrlSolicitarTurno controlador = new CtrlSolicitarTurno();
	            	ArrayList<Especialidad> especialidades = controlador.getAllEspecialidades();
	            	
	            	for(Especialidad e : especialidades){           	
	            %>    			
				<option value="<%=e.getCodEspecialidad()%>"><%=e.getNombre() %></option>	
	  			<%} %>					
	    		</select>
    		</div>
            </div>
     <br>   
     
     <!-- ELECCION DE ESPECIALISTAS -->   
       
            <div class="row">
            <div class="form-group" style="width:400px;">
            <label>ESPECIALISTA: </label>
	            <select name="productoId" id="productoId" required="true" class="form-control input-sm" placeholder="Producto">
				<option value="0">Sin preferencias</option>
     			<option value="1">Huerma Alfonsina</option>
				<option value="2">Maria Lopez</option>
				<option value="3">Matias Recarto</option>
    		</select>
    		</div>
            </div>
     	<br></br> 

	<!-- BOTON PARA VER CALENDARIO -->
	
		<form action="servletVerSemanas" method="post">
			<button type="submit" class="btn btn-info">
		  		Buscar turno
			</button>
		</form>	
	<br></br>
	</div>
 	</div>
 </div>
 </div>
</body>
</html>