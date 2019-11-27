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

.card {
  width: 90px;                 /* Set width of cards */
  display: flex;                /* Children use Flexbox */
  flex-direction: column;       /* Rotate Axis */
  border: 2px solid #3cb371;    /* Set up Border CAMBIO EL BORDE========================*/
  border-radius: 4px;           /* Slightly Curve edges */
  overflow: hidden;             /* Fixes the corners */
  margin: 5px;                  /* Add space between cards */
  
  background-color:#bfefbb;
 
}
.card-header {
  color: #1e5b3a; /*LETRA CABECERA*/
  text-align: center;
  font-size: 12px;
  font-weight: 600;
  border-bottom: 2px solid #3cb371;/*RENGLON DE ABAJO*/
  background-color: #3cb371; /*CAMBIADO POR VERDE CABECERA =====================*/
  padding: 3px 10px;
}

.card-main {
  display: flex;              /* Children use Flexbox */
  flex-direction: column;     /* Rotate Axis to Vertical */
  justify-content: center;    /* Group Children in Center */
  align-items: center;        /* Group Children in Center (on cross axis) */
  padding: 15px 0;            /* Add padding to the top/bottom */
}

.main-description {
  color: #18a40f; /*CAMBIO COLOR TEXTO "DIPOSNIBLE""============================*/
  font-size: 12px;
  text-align: center;
  font-weight: 700;
}

/* IDs for additional colors*/
/* Colors from Google Material Design: https://material.io/guidelines/style/color.html*/

#or-border {
  border-color: #969696;
  background-color:#d3d3d3;
}

#or-header {
  background-color: #969696;
  border-color: #969696;
  color: #626262;
}

#or-color {
  color: #969696;
}

#red-border {
  border-color: #cb3234;
  background-color:#ea899a;
}

#red-header {
  background-color: #cb3234;
  border-color: #cb3234;
  color: #800000;
}

#red-color {
  color: #D32F2F;
}
#wt-border {
  border-color: #505050;
  background-color: #FFFFFF;
  
  border: 1px solid #505050; 
  border-bottom: 1px solid #FFFFFF;/*RENGLON DE ABAJO*/
}

#wt-header {


  background-color: #FFFFFF;
  border-color: #505050;
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







 .pagination {
        margin: 10px 0 5px;
    }
    .pagination li a {
        border: none;
        min-width: 30px;
        min-height: 30px;
        color: #999;
        margin: 0 2px;
        line-height: 30px;
        border-radius: 4px !important;
        text-align: center;
        padding: 0;
    }
    .pagination li a:hover {
        color: #666;
    }
    .pagination li.active a, .pagination li.active a.page-link {
        background: #59bdb3;
    }
    .pagination li.active a:hover {        
        background: #45aba0;
    }
    .pagination li:first-child a, .pagination li:last-child a {
        padding: 0 10px;
    }
    .pagination li.disabled a {
        color: #ccc;
    }
    .pagination li i {
        font-size: 17px;
        position: relative;
        top: 1px;
        margin: 0 2px;
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
		<strong>Elija su cita!</strong> Seleccione la fecha en la que desea asistir a su turno.
	</div><br></br>
	
<!-- VISTA DE DIAS TURNO  -->  
	            <div class="row">    	
				<div class="btn-group-justified" role="group" >
				  <button type="button" class="btn btn-default" style="width:90px; margin:5px;">Lunes</button>
				  <button type="button" class="btn btn-default" style="width:90px; margin:5px;">Martes</button>
				  <button type="button" class="btn btn-default" style="width:90px; margin:5px;">Miercoles</button>
				  <button type="button" class="btn btn-default" style="width:90px; margin:5px;">Jueves</button>
				  <button type="button" class="btn btn-default" style="width:90px; margin:5px;">Viernes</button>
				  <button type="button" class="btn btn-default" style="width:90px; margin:5px;">Sabado</button>
				  <button type="button" class="btn btn-default" style="width:90px; margin:5px;">Domingo</button>
				</div>
				</div>
				<br></br>
				
<!-- VISTA DE CUADRADOS DE SEMANA TURNOS -->

	<!-- CARDS DE LOS DIAS PUDIENDO SER {DISPONIBLE-INHBILITADO-OCUPADO} -->	
			<!-- CALCULO EL DIA EN EL QUE EMPIEZA -->
			<!-- BUSCO LA CANTIDAD DE DIAS PARAR RECORRER -->
			<% 
			
       		Usuario us = (Usuario)session.getAttribute("Especialista");
			int MESActual = (Integer)session.getAttribute("mes");
       		int ANIOActual = (Integer)session.getAttribute("anio");
       		
       		
			//BUSCO LOS TURNOS DEL ESPECIALISTA A PARTIR DE LA FECHA ACTUAL
			CtrlTurno controladorTurno = new CtrlTurno();
			java.sql.Date fechaVista = new java.sql.Date(new Date().getTime());
			ArrayList<Turno> turnosOcuDispo = controladorTurno.getProximosDeEspecialista(us,fechaVista); 
		
       		//Dia en el que empieza el mes
       		int DIASvacios=ValidacionNegocio.primerDia(MESActual, ANIOActual);
       	
       		//CANTIDAD de dias a recorrer
			int CANTdiasMES= ValidacionNegocio.numeroDeDiasMes(MESActual);
			%>

		<div class="row">
			<table>
			<tr>

			<%
			int cont = 0;
			for(int i=1; i<=DIASvacios; i++) {
			
			cont = DIASvacios;
			%>		
				<td>
				</td>		
			<%}%>
			
			<!-- AGREGO TARJETAS EN LOS DIAS DEL MES -->
			
			<%			
			for(int i=1; i<=CANTdiasMES; i++) {			
				if(cont==7){ %>
					</tr>
					<tr>
				<%cont = 1; } else cont++;%>				
				<td>
				<!-- DIFERENCIO ENTRE LAS DIFERENTES TARJETAS: -->

						<!-- NO AGREGO TARJETAS EN LOS DIAS QUE ESTAN VACIOS (EL MES TODAVIA NO EMPEZÓ) - GENERO ESPACIOS EN BLANCO -->
						<!-- COMPARO CON LA FECHA ACTUAL, CADA UNO DE LOS DIAS PARA VER CUALES NO HABILITAR -->
						<%
						String dateSTRING = i+"/"+MESActual+"/"+ANIOActual;
						if(ValidacionNegocio.EsMenorAFechaActual(dateSTRING)){%>						
												
						    	<!-- CARD NO HABILITADO POR SER MENOR A LA FECHA ACTUAL-->   
					        <div class="card" id="or-border">
					          <div class="card-header" id="or-header"><%=i %></div>
					          <div class="card-main">
					            <div class="main-description" id="or-color">Inhabilitado</div>
					          </div>
					        </div> 
						
							
						<%} else {%>
						<!-- VEO SI EXISTEN TURNOS PARA ESA FECHA -->
						
							<!-- no existe -->
								<!-- muestro tarjeta de inhabilitado -->
								
							<!-- existe -->
								<!-- estado == disponible -->
									<!-- muestro tarjeta de disponible -->
								<!-- estado == reservado -->
									<!-- muestro tarjeta de ocupado -->

						<% 
						Turno t = new Turno();
						t.setFechahora(Conversion.formatter1ddmmyy.parse(dateSTRING));
						
						if(!CtrlSolicitarTurno.contieneFechaTurno(turnosOcuDispo, t)){%>					
						
							<!-- CARD NO HABILITADO POR NO EXISTIR TURNOS -->   
						        <div class="card" id="or-border">
						          <div class="card-header" id="or-header"><%=i %></div>
						          <div class="card-main">
						            <div class="main-description" id="or-color">Inhabilitado</div>
						          </div>
						        </div> 
						
						
						<%} else{							
							if(CtrlSolicitarTurno.existenTurnoDisponible(turnosOcuDispo, t)){
								%>
								
								<!-- CARD DISPONIBLE -->
						        <div class="card">
						          <div class="card-header"><%=i %></div>
						          <div class="card-main">
						            <a href="#" class="main-description">Disponible</a>
						          </div>
						        </div>

								<%
							}else{
								%>
								
								 <!-- CARD OCUPADO -->
						        <div class="card" id="red-border">
						          <div class="card-header" id="red-header">22</div>
						          <div class="card-main">
						            <div class="main-description" id="red-color">Ocupado</div>
						          </div>
						        </div>
						        
								<%
							}
						}
					  }%>      
				</td>
			<%} %>
			</tr>
			</table>	
		</div>
	<br></br>
	
	<!-- BOTONES ANTERIOR - SIGUIENTE -->		
		<div class="text-center">
			<form action="servletVerOtroMes" method="post">
		    	<button type="submit" name="act" value="anterior" class="btn btn-outline-secondary">Anterior</button>
		    	<button type="submit" name="act" value="siguiente" class="btn btn-outline-secondary">Siguiente</button>
		    </form>
	    </div>  
	  <br></br>	
		</div>      
	</div>
 </div>
 </div>
</body>
</html>