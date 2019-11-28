<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Turnos - Menu paciente</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/menuPacienteJS.js"></script>
<link rel="stylesheet" type="text/css" href="css/menuPacienteStyle.css">
</head>
<body>
    <div class="container">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
						<h2>Menu del paciente</h2>
					</div>
					<div class="col-sm-6">
   					</div>
                </div>
            </div>          
			<form action="paciente" method="post">
				<button style="width: 350px; margin: 11px;" class="btn btn-default" data-dismiss="modal" name="opcion" value="misTurnos">Mis turnos</button>
    			<button style="width: 350px; margin: 11px;" class="btn btn-default" data-dismiss="modal" name="opcion" value="SolicitarTurno">Solicitar turno</button>   	
				<button style="width: 350px; margin: 11px;" class="btn btn-default" data-dismiss="modal" name="opcion" value="ConfiguracionPersonal">Configuracion personal</button>   				
			</form>			
			<div class="table-footer">
				<div class="row">
                   	<div class="col-sm-6">
           			</div>
					<div class="col-sm-6">
						<a href="#saliendoModal" class="btn btn-info" data-toggle="modal"><i class="material-icons">exit_to_app</i> <span>Cerrar sesión</span></a>
					</div>
                </div>
            </div>
		</div>
	</div>
<!-- Cerrar sesion -->
	<div id="saliendoModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="servletCerrarSesion" method="post">
					<div class="modal-header">						
						<h4 class="modal-title">Cerrar sesión</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">					
						<p>Esta seguro de que desea cerrar la sesión?</p>						
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancelar">
						<input type="submit" class="btn btn-danger" value="Cerrar sesión">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>                                		                            