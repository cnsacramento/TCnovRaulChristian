<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Intervención</title>
<link rel='stylesheet' type='text/css' media='screen'
	href='css/general.css'>
<link rel='stylesheet' type='text/css' media='screen'
	href='css/clientes.css'>
<meta name="description"
	content="Métodos para realizar el CRUD sobre intervención y tipos de intervenciones">
</head>

<body>
	<header>
		<nav>
			<a href="panelVeterinario.jsp" class="nav-link">Volver</a>
		</nav>
	</header>

	<div class="crud">
		<article>

			<h3 class="text-center">Crear intervención</h3>

			<form action="IntervencionServlet" method="post">

				<label for=""> <span>*Asunto:</span> <input type="text"
					name="asunto" id="asunto" maxlength="30" required>
				</label> <label for=""> <span>*Descripción:</span> <input type="text"
					name="descripcion" id="descripcion" required>
				</label> 
				
				<div class="selectform">
					<label for=""> <span>*Tipo intervención:</span></label> 
					<select name="tipointervencion">
						<c:forEach var="tipo" items="${tipointervencionList}">
							<option value="${tipointervencionList.getTipo()}">${tipointervencionList.getNombre()}</option>
						</c:forEach>
					</select>
				</div>
				 
				</label> <label for=""> <span>*ID mascota:</span> <input type="text"
					name="idmascota" id="idmascota">
				</label> <label for=""> <span>*ID factura:</span> <input type="text"
					name="correo" id="correo">
				</label> <label for=""> <span>*Equipo:</span> <input type="text"
					name="telefono" id="telefono" required>

				</label> <input type="submit" name="crear" value="Continuar">
			</form>

		</article>

		<article>

			<h3 class="text-center">Eliminar intervención</h3>
			<form action="IntervencionesServlet" method="post">

				<label for=""> <span>*ID Intervención:</span> <input type="text"
					name="dni" id="dni" value="${intervencion.getId()}" required>
				</label> <input type="submit" name="eliminar" value="Eliminar">
			</form>

		</article>

		<article>

			<h3 class="text-center">Editar intervención</h3>

			<form action="IntervencionesServlet" method="post">

				
				<label for=""> <span>*Asunto:</span> <input type="text"
					name="asunto" id="asunto" maxlength="30" required>
				</label> <label for=""> <span>*Descripción:</span> <input type="text"
					name="descripcion" id="descripcion" required>
				</label> 
				
				<div class="selectform">
					<label for=""> <span>*Tipo intervención:</span></label> 
					<select name="tipointervencion">
						<c:forEach var="tipo" items="${tipointervencionList}">
							<option value="${tipointervencionList.getTipo()}">${tipointervencionList.getNombre()}</option>
						</c:forEach>
					</select>
				</div>
				
				<label for=""> <span>*ID mascota:</span> <input type="text"
					name="idmascota" id="idmascota">
				</label> <label for=""> <span>*ID factura:</span> <input type="text"
					name="correo" id="correo">
				</label> <label for=""> <span>*Equipo:</span> <input type="text"
					name="equipo" id="equipo" required></label>

				<input type="submit" name="editar" id="editar" value="Editar">
			</form>

		</article>

		<article>

			<h3 class="text-center">Mostrar Intervención</h3>

			<form action="IntervencionesServlet" method="post">

				<label for=""> <span>*ID:</span> <input type="text"
					name="id" id="id" value="${intervencion.getId()}" required>
				</label> <input type="submit" name="mostrar" value="Mostrar">

			</form>
			
			<h3 class="text-center">Mostrar listado</h3>

			<form action="IntervencionesServlet" method="post">

				<input type="submit" name="mostrartodas" value="Mostrar todos">

			</form>

		</article>

	</div>

	<div class="container">

		<table class="table">

			<caption>Intervenciones</caption>

			<thead>
				<tr>
					<th>ID</th>
					<th>Asunto</th>
					<th>Descripción</th>
					<th>Tipo</th>
					<th>Mascota</th>
					<th>Factura</th>
					<th>Equipo</th>
					<th></th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="intervencion" items="${intervencionesList}">
					<tr>
						<td data-label="DNI">${intervencion.getId()}</td>
						<td data-label="Asunto">${intervencion.getAsunto()}</td>
						<td data-label="Descripcion">${intervencion.getDescripcion()}</td>
						<td data-label="Mascota">${intervencion.getMascota().getId()}</td>
						<td data-label="Factura">${intervencion.getFactura().getId()}</td>
						<td data-label="Equipo">${intervencion.getVeterinarios()}</td>
						<td>
							<a href="IntervencionesServlet?id=${intervencion.getId()}">
								Opciones
							</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>

		</table>
	</div>

	<c:if test="${not empty mensaje}">
		<script>
			alert("${mensaje}");
		</script>
	</c:if>
</body>
</html>