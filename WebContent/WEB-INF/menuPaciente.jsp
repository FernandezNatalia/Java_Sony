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

<script src="https://kit.fontawesome.com/bdd89edb33.js"></script>
<link href="https://fonts.googleapis.com/css?family=Lato:100,300,400,700,900" rel="stylesheet"/>

<style>
body {
	color: #566787;
	background: #e4e4e4;
	font-family: 'Raleway', sans-serif;
	font-size: 13px;
}
.table-wrapper {

	background: url(images/pincelPaciente.jpeg) repeat top center;
	padding: 20px 25px;
	margin: auto auto;
	border-radius: 3px;
	width: 500px;
}
.table-title {
	padding-bottom: 15px;
	background: #505050;
	color: #fff;
	padding: 16px 30px;
	margin: -20px -25px 10px;
	border-radius: 8px 8px 0 0;
}
.table-footer{
	padding-bottom: 0px;
	background: #505050;
	color: #fff;
	padding: 16px 0px;
	margin: 15px -25px -20px;
	border-radius: 0 0 8px 8px;
}
/* */
.table-footer .btn-group {
	float: right;
}
.table-footer .btn {
	color: #fff;
	float: right;
	font-size: 13px;
	border: none;
	min-width: 150px;
	margin-left: 20px;
	margin-right: 20px;
	border-radius: 2px;
	border: none;
	outline: none !important;
}
.table-footer .btn i {
	float: right;
	font-size: 21px;
	margin-right: 5px;
}
.table-footer .btn span {
	float: left;
	margin-top: 2px;
}
/* */
.table-title h2 {
	margin: 20px 0 0;
	font-size: 24px;
}
table.table tr th, table.table tr td {
	border-color: #6400A0;
	padding: 12px 15px;
	vertical-align: middle;
}
.main-section{
	width:80%;
	margin:0 auto;
	text-align: center;
	padding: 0px 5px;
}
.dashbord{
	width:95%;
	display: inline-block;
	background-color:#34495E;
	color:#fff;
	margin-top: 50px; 
}
.icon-section i{
	font-size: 30px;
	padding:10px;
	border:1px solid #fff;
	border-radius:50%;
	margin-top:-25px;
	margin-bottom: 10px;
	background-color:#34495E;
}
.icon-section p{
	margin:0px;
	font-size: 20px;
	padding-bottom: 10px;
}
.detail-section{
	background-color: #2F4254;
	padding: 5px 0px;
}
.dashbord .detail-section:hover{
	background-color: #5a5a5a;
	cursor: pointer;
}
.detail-section a{
	color:#fff;
	text-decoration: none;
}
.dashbord-green .icon-section,.dashbord-green .icon-section i{
	background-color: #00c29c;
}
.dashbord-green .detail-section{
	background-color: #009e7f;
}
.dashbord-orange .icon-section,.dashbord-orange .icon-section i{
	background-color: #2e8b57;
}
.dashbord-orange .detail-section{
	background-color: #297a4d;
}
.dashbord-red .icon-section,.dashbord-red .icon-section i{
	background-color:#ff6482;
}
.dashbord-red .detail-section{
	background-color:#ff476b;
}

.btn-opciones{
	background-color: transparent; 
	border: transparent; 

  	font-size: 12px;
  	text-align: center;
  	font-weight: 700;
}
/* Modal styles */
.modal .modal-dialog {
	max-width: 400px;
}
.modal .modal-header, .modal .modal-body, .modal .modal-footer {
	padding: 20px 30px;
}
.modal .modal-content {
	border-radius: 3px;
}
.modal .modal-footer {
	background: #ecf0f1;
	border-radius: 0 0 3px 3px;
}
.modal .modal-title {
	display: inline-block;
}
.modal .form-control {
	border-radius: 2px;
	box-shadow: none;
	border-color: #dddddd;
}
.modal textarea.form-control {
	resize: vertical;
}
.modal .btn {
	border-radius: 2px;
	min-width: 100px;
}
.modal form label {
	font-weight: normal;
}

</style>
</head>
<body>
<br><br>
    <div class="container">
        <div class="table-wrapper">
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
						<a href="#saliendoModal" class="btn btn-info" data-toggle="modal"><i class="material-icons">exit_to_app</i> <span>Cerrar sesi�n</span></a>
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
						<h4 class="modal-title">Cerrar sesi�n</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">					
						<p>Esta seguro de que desea cerrar la sesi�n?</p>						
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancelar">
						<input type="submit" class="btn btn-danger" value="Cerrar sesi�n">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>                                		                            