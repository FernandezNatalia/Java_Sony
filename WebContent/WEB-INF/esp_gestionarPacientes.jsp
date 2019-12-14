<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="entidades.*"%>
<%@page import="logica.*"%>
<%@page import="datos.*"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Gestionar pacientes</title>
<!--===============================================================================================-->
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/detalleTurnoStyle.css">
<!--===============================================================================================-->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jsDetallesTurno.js"></script>
<!--===============================================================================================-->
</head>
<body>
    <div class="container">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
						<h2>Gestionar Pacientes</h2>
					</div>
					<div class="col-sm-6">
						<a href="especialista?opcion=MenuEsp" class="btn btn-info"><i class="material-icons">exit_to_app</i> <span>Volver al menu</span></a>
					</div>
        		</div>
        	</div>
		       <div class="card mb-3">
		          <div class="card-header">
		          </div>
		          <div class="card-body">
		            <div class="table-responsive">
		              <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
		                <thead>
		                  <tr>
		                    <th>Nombre y apellido</th>
		                    <th>DNI</th>
		                    <th>Email</th>
		                    <th>Fecha de nacimiento</th>
		                    <th>Detalle</th>
		                  </tr>
		                </thead>
		                <tfoot>
		                  <tr>
		                    <th>Nombre y apellido</th>
		                    <th>DNI</th>
		                    <th>Email</th>
		                    <th>Fecha de nacimiento</th>
		                    <th>Detalle</th>
		                  </tr>
		                </tfoot>
		                <tbody>
			                <%
			                CtrlUsuario ctrlus = new CtrlUsuario();
			            	Usuario us= (Usuario)session.getAttribute("usuario");
			            	ArrayList<Usuario> pacientes = ctrlus.getPacientesEspecialista(us);
			                
			                for(Usuario pac : pacientes){
			                %>
			                  <tr>
			                  <form action="detallePaciente" method="get">
			                    <td><%=pac.getNombre() + " " + pac.getApellido() %></td>
			                    <td><%=pac.getDni() %></td>
			                    <td><%=pac.getEmail() %></td>
			                    <td><%=pac.getFechanacimiento() %></td>
			                    <input type="hidden" name="dnipaciente" value="<%=pac.getDni() %>"/>
			                    <td><input type="submit" class="btn btn-default btn-block" value="Ver detalle"/></td>
			                  </form>
			                  </tr>
			                 <%} %>
		                </tbody>
		              </table>
		            </div>
		       		</div>
		          </div>     
		        </div>
		    </div>
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>
<script src="vendor/datatables/jquery.dataTables.js"></script>
<script src="vendor/datatables/dataTables.bootstrap4.js"></script>
<script src="js/demo/datatables-demo.js"></script>
</body>
</html>          