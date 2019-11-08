<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="entidades.*"%>
<%@page import="logica.*"%>
<%@page import="datos.*"%>
<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%@page import="java.text.*"%>
<%@ page errorPage="/err.html" %>

<%
//Aca se guarda una variable de sesion para determinar a que servlet se vuelve al presionar el boton volver en detalles del turno
session.setAttribute("detallesturnobotonvolver", "listadopendesp");
%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Mis turnos</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style type="text/css">
    body {
        color: #566787;
		background: #f5f5f5;
		font-family: 'Varela Round', sans-serif;
		font-size: 13px;
	}
	.table-wrapper {
        background: #fff;
        padding: 20px 25px;
        margin: 30px 0;
		border-radius: 3px;
        box-shadow: 0 1px 1px rgba(0,0,0,.05);
        min-width : 540px
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
    .pagination {
        float: right;
        margin: 0 0 5px;
    }
    .pagination li a {
        border: none;
        font-size: 13px;
        min-width: 30px;
        min-height: 30px;
        color: #999;
        margin: 0 2px;
        line-height: 30px;
        border-radius: 2px !important;
        text-align: center;
        padding: 0 6px;
    }
    .pagination li a:hover {
        color: #666;
    }	
    .pagination li.active a, .pagination li.active a.page-link {
        background: #03A9F4;
    }
    .pagination li.active a:hover {        
        background: #0397d6;
    }
	.pagination li.disabled i {
        color: #ccc;
    }
    .pagination li i {
        font-size: 16px;
        padding-top: 6px
    }
    .hint-text {
        float: left;
        margin-top: 10px;
        font-size: 13px;
    }    
	/* Custom checkbox */
	.custom-checkbox {
		position: relative;
	}
	.custom-checkbox input[type="checkbox"] {    
		opacity: 0;
		position: absolute;
		margin: 5px 0 0 3px;
		z-index: 9;
	}
	.custom-checkbox label:before{
		width: 18px;
		height: 18px;
	}
	.custom-checkbox label:before {
		content: '';
		margin-right: 10px;
		display: inline-block;
		vertical-align: text-top;
		background: white;
		border: 1px solid #bbb;
		border-radius: 2px;
		box-sizing: border-box;
		z-index: 2;
	}
	.custom-checkbox input[type="checkbox"]:checked + label:after {
		content: '';
		position: absolute;
		left: 6px;
		top: 3px;
		width: 6px;
		height: 11px;
		border: solid #000;
		border-width: 0 3px 3px 0;
		transform: inherit;
		z-index: 3;
		transform: rotateZ(45deg);
	}
	.custom-checkbox input[type="checkbox"]:checked + label:before {
		border-color: #03A9F4;
		background: #03A9F4;
	}
	.custom-checkbox input[type="checkbox"]:checked + label:after {
		border-color: #fff;
	}
	.custom-checkbox input[type="checkbox"]:disabled + label:before {
		color: #b8b8b8;
		cursor: auto;
		box-shadow: none;
		background: #ddd;
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
	/*the container must be positioned relative:*/
.custom-select {
  position: relative;
  font-family: Arial;
}

.custom-select select {
  display: none; /*hide original SELECT element:*/
}

.select-selected {
  background-color: Purple;
}

/*style the arrow inside the select element:*/
.select-selected:after {
  position: absolute;
  content: "";
  top: 14px;
  right: 10px;
  width: 0;
  height: 0;
  border: 6px solid transparent;
  border-color: #fff transparent transparent transparent;
}

/*point the arrow upwards when the select box is open (active):*/
.select-selected.select-arrow-active:after {
  border-color: transparent transparent #fff transparent;
  top: 7px;
}

/*style the items (options), including the selected item:*/
.select-items div,.select-selected {
  color: #ffffff;
  padding: 8px 16px;
  border: 1px solid transparent;
  border-color: transparent transparent rgba(0, 0, 0, 0.1) transparent;
  cursor: pointer;
  user-select: none;
}

/*style items (options):*/
.select-items {
  position: absolute;
  background-color: DodgerBlue;
  top: 100%;
  left: 0;
  right: 0;
  z-index: 99;
}

/*hide the items when the select box is closed:*/
.select-hide {
  display: none;
}

.select-items div:hover, .same-as-selected {
  background-color: rgba(0, 0, 0, 0.1);
}

</style>
<script type="text/javascript">


$(document).ready(function(){
	// Activate tooltip
	$('[data-toggle="tooltip"]').tooltip();
	
	// Select/Deselect checkboxes
	var checkbox = $('table tbody input[type="checkbox"]');
	$("#selectAll").click(function(){
		if(this.checked){
			checkbox.each(function(){
				this.checked = true;                        
			});
		} else{
			checkbox.each(function(){
				this.checked = false;                        
			});
		} 
	});
	checkbox.click(function(){
		if(!this.checked){
			$("#selectAll").prop("checked", false);
		}
	});
});
</script>
<% 		
		Usuario us= (Usuario)session.getAttribute("usuario");
		CtrlTurno tl = new CtrlTurno();	
	
		java.sql.Date fechaVista = (java.sql.Date) session.getAttribute("fecha");
		
		int estado = (Integer)session.getAttribute("estado");
		
    	ArrayList<Turno> lt=tl.getProximosDeEspecialista(us,fechaVista,estado);
%>
</head>
<body>
    <div class="container">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
						<h2>Mis <b>turnos</b></h2>
					</div>
					<div class="col-sm-6">
						
						<a href="servletPrincipal" class="btn btn-info" ><i class="material-icons">exit_to_app</i> <span>Volver al menú</span></a>
						<a href="#addEmployeeModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Agregar nuevo turno</span></a>
						
					</div>
                </div>
            </div>
            <table class="table table-striped table-hover">
                <thead>
                    <tr>			
                        <th>Fecha y hora</th>
						<th>Dni paciente</th>
                        <th>Nombre y Apellido</th>
						<th>Consultorio</th>
                        <th>Estado</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                <% for (Turno tur : lt) {%>
                 
                    <tr>
			            <% 
			            SimpleDateFormat formatohhmm = new SimpleDateFormat("HH:mm  dd/MM/yyyy");
		           		ConsultorioDatos cd = new ConsultorioDatos();
			             %>
                        <td><%=formatohhmm.format(tur.getFechahora())%></td>
                        
                        <%if (tur.getEstado() == Turno.reservado) {%>
                        <td><%=tur.getPaciente().getDni()%></td>
                        <td><%=tur.getPaciente().getNombre()+" "+tur.getPaciente().getApellido() %></td>
						<%}if (tur.getEstado() == Turno.disponible) {%>
						<td>--</td>
						<td>--</td>
						<%} %>
						<td><%=tur.getConsultorio().getDesc()%></td>                       
                        <%if (tur.getEstado() == Turno.reservado) {%>
                        <td>Ocupado</td>
                        <td>
                            <a href="#finTurnoModal<%=tur.getIdturno()%>" class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Finalizar turno">check_circle</i></a>
                            <a href="detallesTurno?idturno=<%=tur.getIdturno() %>" class="more" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Mas información">more_horiz</i></a>
                            <a href="#cancelarTurnoModal<%=tur.getIdturno()%>" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Cancelar">&#xE872;</i></a>
                        <%}if (tur.getEstado() == Turno.disponible) {%>
                        <td>Disponible</td>
                        <td>
                            <a href="#eliminarTurnoModal<%=tur.getIdturno()%>" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Eliminar turno">&#xE872;</i></a>	
                        <%} %>
                        </td>
                    </tr>

                    <%}%>					
					
                </tbody>
            </table>
			<a href="#verSemanaTurno" class="btn btn-success" data-toggle="modal"><span>Ver semana completa</span></a>
			<a href="sevletEspecialistaTurnosDisponibles" class="btn btn-success"><span><%=(String)session.getAttribute("botonEstado") %></span></a>
        </div>
    </div>
	<!-- Edit Modal HTML -->
	<div id="addEmployeeModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="servletCrearTurno" method = "post">
					<div class="modal-header">						
						<h4 class="modal-title">Nuevo turno</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">					
						<div class="form-group">
						<%
							DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							Date date = new Date();
							String fecmin = dateFormat.format(date);						
						%>
							<label>Fecha</label>
							<input type="date" class="form-control" min="<%=fecmin%>" name="fecha" required>
						</div>
						<div class="form-group">
							<label>Hora</label>
							<input type="time" class="form-control" name="hora" required>
						</div>
						<div class="form-group">
							<label>Consultorio</label>
							<br/>
							<div class="custom-select" name="consultorio" style="width:200px;">
							<select name="cons">
							<%ConsultorioDatos cd = new ConsultorioDatos();
							ArrayList<Consultorio> consultorios = cd.getAll();
							%>
								<option value="0" disabled="disabled">     --      </option>
								<% for(Consultorio con : consultorios){%>
 								<option value="<%=con.getIdconsultorio()%>"><%=con.getDesc() %></option>
 								<% }%>
							</select>
						</div>
						<script>
var x, i, j, selElmnt, a, b, c;
/*look for any elements with the class "custom-select":*/
x = document.getElementsByClassName("custom-select");
for (i = 0; i < x.length; i++) {
  selElmnt = x[i].getElementsByTagName("select")[0];
  /*for each element, create a new DIV that will act as the selected item:*/
  a = document.createElement("DIV");
  a.setAttribute("class", "select-selected");
  a.innerHTML = selElmnt.options[selElmnt.selectedIndex].innerHTML;
  x[i].appendChild(a);
  /*for each element, create a new DIV that will contain the option list:*/
  b = document.createElement("DIV");
  b.setAttribute("class", "select-items select-hide");
  for (j = 1; j < selElmnt.length; j++) {
    /*for each option in the original select element,
    create a new DIV that will act as an option item:*/
    c = document.createElement("DIV");
    c.innerHTML = selElmnt.options[j].innerHTML;
    c.addEventListener("click", function(e) {
        /*when an item is clicked, update the original select box,
        and the selected item:*/
        var y, i, k, s, h;
        s = this.parentNode.parentNode.getElementsByTagName("select")[0];
        h = this.parentNode.previousSibling;
        for (i = 0; i < s.length; i++) {
          if (s.options[i].innerHTML == this.innerHTML) {
            s.selectedIndex = i;
            h.innerHTML = this.innerHTML;
            y = this.parentNode.getElementsByClassName("same-as-selected");
            for (k = 0; k < y.length; k++) {
              y[k].removeAttribute("class");
            }
            this.setAttribute("class", "same-as-selected");
            break;
          }
        }
        h.click();
    });
    b.appendChild(c);
  }
  x[i].appendChild(b);
  a.addEventListener("click", function(e) {
      /*when the select box is clicked, close any other select boxes,
      and open/close the current select box:*/
      e.stopPropagation();
      closeAllSelect(this);
      this.nextSibling.classList.toggle("select-hide");
      this.classList.toggle("select-arrow-active");
    });
}
function closeAllSelect(elmnt) {
  /*a function that will close all select boxes in the document,
  except the current select box:*/
  var x, y, i, arrNo = [];
  x = document.getElementsByClassName("select-items");
  y = document.getElementsByClassName("select-selected");
  for (i = 0; i < y.length; i++) {
    if (elmnt == y[i]) {
      arrNo.push(i)
    } else {
      y[i].classList.remove("select-arrow-active");
    }
  }
  for (i = 0; i < x.length; i++) {
    if (arrNo.indexOf(i)) {
      x[i].classList.add("select-hide");
    }
  }
}
/*if the user clicks anywhere outside the select box,
then close all select boxes:*/
document.addEventListener("click", closeAllSelect);
</script>
						</div>
											
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancelar">
						<input type="submit" class="btn btn-success" value="Agregar">
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- finturno Modal HTML -->
	
	<% for (Turno tur : lt) {
     if (tur.getEstado() == 2) {%>	
	<div id="finTurnoModal<%=tur.getIdturno()%>" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="finalizarturno" method="post">
					<div class="modal-header">						
						<h4 class="modal-title">Finalizar turno</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">					
						<div class="form-group">
						
							
						</div>
						<div class="form-group">
							<label>Observación</label>
							<input type="text" class="form-control" name="observacion" required></textarea>
							<input type="hidden" name="idturno" value="<%=tur.getIdturno()%>">
						</div>
				
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancelar">
						<input type="submit" class="btn btn-info" value="Guardar">

					</div>
				</form>
			</div>
		</div>
	</div>
	
	
	<!-- Modal Cancelar turno -->
	<div id="cancelarTurnoModal<%=tur.getIdturno()%>" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="cancelarturno" method="post">
					<div class="modal-header">						
						<h4 class="modal-title">Cancelar Turno</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">					
						<p>Esta seguro de que desea cancelar este turno?</p>
						<p class="text-warning"><small>Se creara un turno disponible con el mismo horario</small></p>
						<input type="hidden" name="idturno" value="<%=tur.getIdturno()%>">
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Volver">
						<input type="submit" class="btn btn-danger" value="Cancelar turno">
					</div>
				</form>
			</div>
		</div>
	</div>	
	<%}} %>
	<% for (Turno tur : lt) {%>
    <% if (tur.getEstado() == 1) {%>
	<!-- Modal Eliminar turno -->
	<div id="eliminarTurnoModal<%=tur.getIdturno()%>" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="eliminarturno" method="post">
					<div class="modal-header">						
						<h4 class="modal-title">Eliminar turno</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">					
						<p>Esta seguro de que desea eliminar este turno?</p>
						<p class="text-warning"><small>Esta acción no se puede deshacer.</small></p>
						<input type="hidden" name="idturno" value="<%=tur.getIdturno()%>">
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancelar">
						<input type="submit" class="btn btn-danger" value="Eliminar">
					</div>
				</form>
			</div>
		</div>
	</div>
<%}} %>

		
		<!-- verSemanaTurno Modal HTML -->
	<div id="verSemanaTurno" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="servletEspecialistaCambioFecha" method="post">
					<div class="modal-header">						
						<h4 class="modal-title">Ver proximas semanas</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">					
						<div class="form-group">
						</div>

					<div class="form-group">
						<label>Ingrese una fecha (yyyy-mm-dd)</label>
						<input type="text" class="form-control" name="fechaDeseada" required></textarea>
					</div>

					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancelar">
						<input type="submit" class="btn btn-info" value="Aceptar">
					</div>
				</form>
			</div>
		</div>
	</div>
	
		
</body>
</html>                                		                            