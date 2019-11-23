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
<script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>  
<style>
@import url('https://fonts.googleapis.com/css?family=Open+Sans|Roboto');
html, body{
	margin: 0;
	padding: 0;
	background-color: #bfefbb;
}
body{
	width: 100%;
	height: 100%;
	font-family: sans-serif;
	letter-spacing: 0.03em;
	line-height: 1.6;
	font-family: 'Open Sans', sans-serif;
}
.title{
	text-align: center;
	font-size: 40px;
	color: #6a6a6a;
	margin-top: 50px;
	font-weight: 100;
	font-family: 'Roboto', sans-serif;
}
.container{
	width: 100%;
	max-width: 1200px;
	height: 430px;
	display: flex;
	flex-wrap: wrap;
	justify-content: center;
	margin: auto;
}
.container .card{
	width: auto;
	height: 250px;
	border-radius: 8px;
	box-shadow: 0 2px 2px rgba(0, 0, 0, 0.2);
	overflow: hidden;
	margin: 10px;
	text-align: center;
	transition: all 0.25s;
	background-color: #ffffbf;
}
.container .card:hover{
	transform: translateY(-15px);
	box-shadow: 0 12px 16px rgba(0, 0, 0, 0.2);
}
.container .card img{
	width: 330px;
	height: 220px;
}
.container .card h4{
	font-weight: 600;
}
.container .card p{
	padding: 0 1rem;
	font-size: 14px;
	font-weight: 30;
}
.container .card a {
	font-weight: 500;
	text-decoration: none;
	color: #3498db;
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

<script>
function ObtenerIDTurno(idDeTurno)
{
	document.getElementById("IDTURNO").value=idDeTurno; 
}
</script>
</head>
<body>
<% 		
			Usuario us= (Usuario)session.getAttribute("usuario");
			CtrlTurno turLog = new CtrlTurno();	
			CtrlEspecialista eLog = new CtrlEspecialista();
			ArrayList<Turno> turnos = turLog.getTurnosPendientesPaciente(us);
			
			SimpleDateFormat formatohhmm = new SimpleDateFormat("HH:mm"); 		
	   		SimpleDateFormat formatoyymmaa = new SimpleDateFormat("yyyy-MM-dd");
%>  
	     
<div class="container">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
						<h2>Mis turnos pendientes</h2>												
					</div>					
                </div>
            </div>                
    <div class="container">
    <table class="table table-striped table-hover">
    	<thead>
       <% for (Turno tur : turnos) {%>
        <div class="card">
        	<br>
            <h4><%=eLog.getEspecialidad(tur.getEspecialista().getDni())%></h4>
            <p align="left">
            	Fecha: <%=formatoyymmaa.format(tur.getFechahora())%> <br>
            	Hora: <%=formatohhmm.format(tur.getFechahora())%> <br><br>
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