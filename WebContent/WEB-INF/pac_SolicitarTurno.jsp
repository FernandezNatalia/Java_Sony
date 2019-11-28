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

<link rel="stylesheet" type="text/css" href="css/menuPaciente.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
						<form action="servletPrincipal" method="get">
							<button type="submit" class="btn btn-info" ><i class="material-icons">exit_to_app</i> <span>Volver al menu</span>
							</button>
						</form>	
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
	
		<form action="paciente" method="post">
			<button type="submit" class="btn btn-info" name="opcion" value="verCalendario">
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