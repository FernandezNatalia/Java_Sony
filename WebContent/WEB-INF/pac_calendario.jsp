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
<link rel="stylesheet" type="text/css" href="css/baseSolicitarTurno.css">
<link rel="stylesheet" type="text/css" href="css/menuPaciente.css">
<link rel="stylesheet" type="text/css" href="css/Calendario.css">

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
            
<!-- SELECCION DE ITEMS -->
	
	<div class="text-center">
	<div class="panel panel-success"> 
	<div class="alert alert-info alert-dismissible" role="alert">
		<strong>Elija su cita!</strong> Seleccione la fecha en la que desea asistir a su turno.
	</div><br>
	
<!-- NOMBRE DEL MES -->

<%
	Usuario us = (Usuario)session.getAttribute("Especialista");
	int MESActual = (Integer)session.getAttribute("mes");
	int ANIOActual = (Integer)session.getAttribute("anio");
			
	//Pongo la primer letra en mayusucula, y obtengo el nombre del mes
	String MESNombre = (Conversion.NombreDeMes(MESActual)).substring(0, 1).toUpperCase() + (Conversion.NombreDeMes(MESActual)).substring(1);;
%>
<h3><%=MESNombre %></h3>

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

			//BUSCO LOS TURNOS DEL ESPECIALISTA A PARTIR DE LA FECHA ACTUAL
			CtrlTurno controladorTurno = new CtrlTurno();
			java.sql.Date fechaVista = new java.sql.Date(new Date().getTime());
			ArrayList<Turno> turnosOcuDispo = controladorTurno.getProximosDeEspecialista(us,fechaVista); 
		
       		//Dia en el que empieza el mes
       		int DIASvacios=ValidacionNegocio.primerDia(MESActual, ANIOActual);
       	
       		//CANTIDAD de dias a recorrer
			int CANTdiasMES= ValidacionNegocio.numeroDeDiasMes(MESActual, ANIOActual);
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
						Date turFecha = Conversion.formatter1ddmmyy.parse(dateSTRING);
						t.setFechahora(turFecha);
						
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
								String fechaDisponible = ANIOActual+"-"+MESActual+"-"+i;
								%>	
								<!-- CARD DISPONIBLE -->
						        <div class="card">
						          <div class="card-header"><%=i %></div>
						          <div class="card-main">
						          	<form action="paciente" method="post">
						          		<input type="hidden" name="opcion" value="SelectFecha"></input>
		    							<button type="submit" name="Reserva" value="<%=fechaDisponible %>" class="btn-disponible">Disponible</button>
									</form>
						          </div>
						        </div>

								<%
							}else{
								%>
								 <!-- CARD OCUPADO -->
						        <div class="card" id="red-border">
						          <div class="card-header" id="red-header"><%=i %></div>
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
			<form action="paciente" method="post">
			<input type="hidden" name="opcion" value="VerOtroMes"></input>
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