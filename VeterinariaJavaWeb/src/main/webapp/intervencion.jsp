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

			<form action="IntervencionesServlet" method="post">
				<input type="submit" name="fechaIntervencion" value="Crear">
			</form>

		</article>

		<article>

			<h3 class="text-center">Editar intervención</h3>

			<form action="IntervencionesServlet" method="post">

				<label for=""> <span>*ID Intervención:</span> <input
					type="number" name="id" id="id" value="${intervencion.getId()}"
					required>
				</label> <label for=""> <span>*Asunto:</span> <input type="text"
					name="asunto" id="asunto" maxlength="30"
					value="${intervencion.getAsunto()}" required>
				</label> <label for=""> <span>*Descripción:</span> <textarea
						name="descripcion" id="descripcion">${intervencion.getDescripcion()}</textarea>
				</label>

				<div class="selectform">
					<label for=""> <span>*Tipo intervención:</span></label> <select
						name="tipointervencion">
						<c:forEach var="tipoIntervencion" items="${tipoIntervencionList}">
							<option value="${tipoIntervencion.getId()}">${tipoIntervencion.getTipo()}</option>
						</c:forEach>
					</select>
				</div>

				<label for=""> <span>*ID mascota:</span> <input
					type="number" name="idmascota" id="idmascota"
					value="${intervencion.getMascota().getId()}">
				</label> <label for=""> <span>*ID factura:</span> <input type="text"
					name="factura" id="factura"
					value="${intervencion.getFactura().getId()}">
				</label> <label for=""> <span>*Equipo:</span> <input type="text"
					name="equipo" id="equipo"></label> <input type="submit"
					name="editar" id="editar" value="Editar">
			</form>

		</article>

		<article>

			<h3 class="text-center">Eliminar intervención</h3>
			<form action="IntervencionesServlet" method="post">

				<label for=""> <span>*ID Intervención:</span> <input
					type="number" name="id" id="id" value="${intervencion.getId()}"
					required>
				</label> <input type="submit" name="eliminar" value="Eliminar">
			</form>

			<h3 class="text-center">Mostrar Intervención</h3>

			<form action="IntervencionesServlet" method="post">

				<label for=""> <span>*ID:</span> <input type="number"
					name="id" id="id" value="${intervencion.getId()}" required>
				</label> <input type="submit" name="mostrar" value="Mostrar">

			</form>

			<h3 class="text-center">Mostrar listado</h3>

			<form action="IntervencionesServlet" method="post">

				<input type="submit" name="mostrartodas" value="Mostrar todos">

			</form>

		</article>

		<article>

			<h3 class="text-center">Crear tipo intervención</h3>

			<form action="IntervencionesServlet" method="post">

				<label for=""> <span>*Tipo:</span> <input type="text"
					name="tipo" id="tipo" maxlength="30" required>
				</label> <input type="submit" name="btntipo" value="Crear">
			</form>

			<h3 class="text-center">Editar tipo intervención</h3>
			<form action="IntervencionesServlet" method="post">

				<label for=""> <span>*ID Tipo Intervención:</span> <input
					type="number" name="id" id="id" value="${tipoIntervencion.getId()}"
					required>
				</label> <label for=""> <span>*Tipo:</span> <input type="text"
					name="tipo" id="tipo" maxlength="30" required>
				</label> <input type="submit" name="btntipo" value="Editar">
			</form>

			<h3 class="text-center">Eliminar tipo intervención</h3>
			<form action="IntervencionesServlet" method="post">

				<label for=""> <span>*ID Tipo Intervención:</span> <input
					type="number" name="id" id="id" value="${tipoIntervencion.getId()}"
					required>
				</label> <input type="submit" name="btntipo" value="Eliminar">
			</form>

		</article>


		<article>

			<h3 class="text-center">Mostrar tipo intervención</h3>

			<form action="IntervencionesServlet" method="post">

				<label for=""> <span>*ID:</span> <input type="number"
					name="id" id="id" value="${tipoIntervencion.getId()}" required>
				</label> <input type="submit" name="btntipo" value="Mostrar">

			</form>

			<h3 class="text-center">Mostrar listado</h3>

			<form action="IntervencionesServlet" method="post">

				<input type="submit" name="btntipo" value="Mostrar todos">

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
						<td data-label="ID">${intervencion.getId()}</td>
						<td data-label="Asunto">${intervencion.getAsunto()}</td>
						<td data-label="Descripcion">${intervencion.getDescripcion()}</td>
						<td data-label="Tipo">${intervencion.getTipoIntervencion().getTipo()}</td>
						<td data-label="Mascota">${intervencion.getMascota().getId()}</td>
						<td data-label="Factura">${intervencion.getFactura().getId()}</td>
						<td data-label="Equipo"><c:forEach var="veterinario"
								items="${intervencion.getVeterinarios()}">
								<span>${veterinario.getDni()}</span>
							</c:forEach></td>
						<td><a
							href="IntervencionesServlet?id=${intervencion.getId()}">Opciones
						</a></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>
	</div>


	<div class="container">

		<table class="table">

			<caption>Tipo de intervenciones</caption>

			<thead>
				<tr>
					<th>ID</th>
					<th>Tipo</th>
					<th></th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="tipointervencion" items="${tipointervencionList}">
					<tr>
						<td data-label="ID">${tipointervencion.getId()}</td>
						<td data-label="Tipo">${tipointervencion.getTipo()}</td>
						<td><a
							href="IntervencionesServlet?idtipo=${tipointervencion.getId()}">Opciones</a></td>
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