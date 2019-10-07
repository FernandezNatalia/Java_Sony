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
    <title>Card con efecto hover - Magtimus</title>
    <!--  <link rel="stylesheet" href="css/estilos.css">-->
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<style>
@import url('https://fonts.googleapis.com/css?family=Open+Sans|Roboto');
html, body{
    margin: 0;
    padding: 0;
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
    width: 330px;
    height: 310px;
    border-radius: 8px;
    box-shadow: 0 2px 2px rgba(0, 0, 0, 0.2);
    overflow: hidden;
    margin: 20px;
    text-align: center;
    transition: all 0.25s;
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
    font-size: 16px;
    font-weight: 300;
}

.container .card a {
    font-weight: 500;
    text-decoration: none;
    color: #3498db;
}
</style>

</head>
<body>
    <% 		
		Usuario us= (Usuario)session.getAttribute("usuario");
		TurnoLogico turLog = new TurnoLogico();	
		EspecialistaLogico eLog = new EspecialistaLogico();
		ArrayList<Turno> turnos = turLog.getTurnosPendientesPaciente(us);
		
		SimpleDateFormat formatohhmm = new SimpleDateFormat("HH:mm"); 		
   		SimpleDateFormat formatoyymmaa = new SimpleDateFormat("yyyy-MM-dd");
     %>
    <h1 class="title">Mis turnos pendientes</h1>
    <input type="submit" value="Ver turnos asistidos"><br>
    <div class="container">
       <% for (Turno tur : turnos) {%>
        <div class="card">
            <h3><%=eLog.getEspecialidad(tur.getEspecialista().getDni())%></h3>
            <p align="left">Fecha: <%=formatoyymmaa.format(tur.getFechahora())%> <br>
            Hora: <%=formatohhmm.format(tur.getFechahora())%> <br><br><br>
            Especialista: <%=tur.getEspecialista().getNombre()+" "+tur.getEspecialista().getApellido()%> <br>
            Consultorio: <%=tur.getConsultorio().getDesc()%></p> <br>
            <a href="#">Cancelar</a>
        </div>
        <%} %>
    </div>    
</body>
</html>