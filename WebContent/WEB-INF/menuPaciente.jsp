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
<link rel="stylesheet" type="text/css" href="css/menuPacDecor.css">
<link rel="stylesheet" type="text/css" href="css/baseMenuPacEsp.css">
<link href="https://fonts.googleapis.com/css?family=Lato:100,300,400,700,900" rel="stylesheet"/>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/menuPacienteJS.js"></script>
<script src="https://kit.fontawesome.com/bdd89edb33.js"></script>
</head>
<body>
<br><br>
    <div class="container">
        <div class="table-wrapper" style="background: url(images/pincelPaciente.jpeg) repeat top center;">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
						<h2>Menu del paciente</h2><br>
					</div>
					<div class="col-sm-6">
   					</div>
                </div>
            </div>     
			<div class="main-section">
			<form action="paciente" method="post">
				<div class="dashbord dashbord-green">
					<div class="icon-section">
						<i class="fa fa-tasks" aria-hidden="true"></i><br>		
					</div>
					<div class="detail-section">
						<button type="submit" class="btn-opciones btn-block" name="opcion" value="misTurnos">Mis Turnos pendientes</button>	
					</div>
				</div>
				<div class="dashbord dashbord-orange">
					<div class="icon-section">
						<i class="fa fa-bell" aria-hidden="true"></i><br>
					</div>
					<div class="detail-section">
						<button type="submit" class="btn-opciones btn-block" name="opcion" value="SolicitarTurno">Solicitar turno</button> 
					</div>
				</div>
				<div class="dashbord dashbord-red">
					<div class="icon-section">
						<i class="fa fa-users" aria-hidden="true"></i><br>			
					</div>
					<div class="detail-section">
						<button type="submit" class="btn-opciones btn-block" name="opcion" value="ConfiguracionPersonal">Configuracion personal</button>
					</div>
				</div>
			</form>
			</div>
		<br></br>	
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
	<br>
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