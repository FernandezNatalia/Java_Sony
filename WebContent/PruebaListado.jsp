<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entidades.*"%>
<%@page import="logica.*"%>
<%@page import="datos.*"%>
<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%@page import="java.text.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insertar título aquí</title>
</head>
<body>

Bienvenido!
<%

UsuarioLogico userLogico = new UsuarioLogico();
Usuario user = userLogico.getOne(40100300);
%>
<h1><%=user.getApellido()%></h1>


<%

TurnoLogico tl = new TurnoLogico();	
ArrayList<Turno> turnos=tl.getProximosDeEspecialista(user);

for (Turno tur : turnos){%>
<%
SimpleDateFormat formatoddmmyy = new SimpleDateFormat("yyyy-MM-dd");
SimpleDateFormat formatohhmm = new SimpleDateFormat("HH:mm");

%>
	<h1><%=tur.getEstado()%></h1>
	<h1><%=tur.getPaciente().getNombre()%></h1>
	<h1><%=formatoddmmyy.format(tur.getFecha())%></h1>
	<h1><%=formatohhmm.format(tur.getHora())%></h1>
<%}


%>






</body>
</html>