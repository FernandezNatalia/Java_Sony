<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Error 500</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" type="text/css" href="css/baseSolicitarTurno.css">
<link rel="stylesheet" type="text/css" href="css/menuPaciente.css">
</head>
	<body>
	<div class="container">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
						<h2>Lo sentimos...</h2>												
					</div>	
					<div class="col-sm-6">	
						<form action="servletPrincipal" method="get">
							<button type="submit" class="btn btn-info" ><i class="material-icons">exit_to_app</i> <span>Volver al menu</span>
							</button>
						</form>	
					</div>				
                </div>
            </div> 
			<div class="text-center">
				<br>
				<h3>Estado HTTP 500: Error interno del servidor</h3>
				<h5>Se produjo un error interno del servidor y fue imposible completar su solicitud. </br>
				El servidor pudo estar sobrecargado o hubo un error en la ejecución de un programa
				</h5>
				<br>
				<img src="images/img_error.jpg">
				<h2 style="color:red;">Se ha producido un error.</h2>
			</div>
		</div>
	</div>
	</body>
</html>