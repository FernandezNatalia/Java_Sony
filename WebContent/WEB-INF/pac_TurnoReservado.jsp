<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Turno registrado</title>
<link href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round" rel="stylesheet">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" 
integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">


<link rel="stylesheet" type="text/css" href="css/baseSolicitarTurno.css">
<link rel="stylesheet" type="text/css" href="css/menuPaciente.css">
<link rel="stylesheet" type="text/css" href="css/modalTurnoReservadoEstatico.css">
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
						<a href="paciente?opcion=menuPaciente" class="btn btn-info"><i class="material-icons">exit_to_app</i> <span>Volver al menú</span></a>
					</div>				
                </div>
            </div>  


		<!-- Modal turno registrado -->
		<div class="modal-dialog modal-confirm" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<div class="icon-box">
						<i class="material-icons">&#xE876;</i>
					</div>
				</div>
				<div class="modal-body text-center">
					<h4>Reservado!</h4>	
					<p>El turno ha sido reservado exitosamente.</p></div>
			</div>
		</div>
	</div>
</div>


</body>
</html>