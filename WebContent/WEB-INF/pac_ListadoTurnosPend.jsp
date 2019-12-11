<!DOCTYPE html>
<%@page import="entidades.*"%>
<%@page import="logica.*"%>
<%@page import="datos.*"%>
<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%@page import="java.text.*"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<title>Turnos</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

<link rel="stylesheet" type="text/css" href="css/menuPaciente.css">
<link rel="stylesheet" type="text/css" href="css/CardsPacienteTurnos.css">
<link rel="stylesheet" type="text/css" href="css/pac_Listado_Turnos.css">

<script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>  
<script type="text/javascript" src="js/modalObtenerID.js"></script>

</head>
<body>     
<div class="container">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
						<h2>Mis turnos pendientes</h2>												
					</div>	
					<div class="col-sm-6">	
						<a href="paciente?opcion=menuPaciente" class="btn btn-info"><i class="material-icons">exit_to_app</i> <span>Volver al menú</span></a>
					</div>				
                </div>
            </div>                
    <div class="container">
    <table class="table table-striped table-hover">
    	<thead>
     <% 
       ArrayList<Turno> turnos = (ArrayList<Turno>)request.getAttribute("ListadoTurnos");
       for (Turno tur : turnos) {       
     %>
        <div class="card">
        	<br>
            <h4><%=((Especialista)tur.getEspecialista()).getEspecialidad() %></h4>
            <p align="left">
            	Fecha: <%=Conversion.formatoddmmyy.format(tur.getFechahora())%> <br>
            	Hora: <%=Conversion.formatohhmm.format(tur.getFechahora())%> <br><br>
           		Especialista: <%=tur.getEspecialista().getNombre()+" "+tur.getEspecialista().getApellido()%> <br>
            	Consultorio: <%=tur.getConsultorio().getDesc()%></p>
            <br>	
            <a href="#cancelarTurnoModal" onclick="ObtenerIDTurno(<%=tur.getIdturno()%>);"class="trigger-btn" data-toggle="modal">Cancelar</a>
        </div>
        <%} %>
      	</thead>
    </table>
    </div>  
 </div>
 </div> 
   
<!--  Cancelar turno modal -->
<div class="modal fade" id="cancelarTurnoModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">    
      <div class="modal-dialog">
        <div class="modal-content">
        	<form action="servletCancelarTurno" method="post">
          	<div class="modal-header">
          		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
              	<h4>Cancelar turno</h4>
           </div>
           <div class="modal-body">
             ¿Está seguro que desea cancelar este turno?             
       	   </div>
           <div class="modal-footer">          
          	<input type="submit" class="btn btn-danger" value="Eliminar"> 
          	<input type="hidden" id="IDTURNO" name="idturno" />               
           </div>    
           	</form>     
      </div>
  	 </div>  
</div>    
</body>
</html>