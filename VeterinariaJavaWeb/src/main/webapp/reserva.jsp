<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Reservas</title>
<link rel='stylesheet' type='text/css' media='screen'
	href='css/general.css'>
<link rel='stylesheet' type='text/css' media='screen'
	href='css/clientes.css'>
<meta name="description"
	content="Métodos para realizar el CRUD sobre Reservas">
</head>

<body>
	<header>
		<nav>
			<a href="panelVeterinario.jsp" class="nav-link">Volver</a>
		</nav>
	</header>

	<div class="crud">
	
		<article>
		
		<h3 class="text-center">Editar reserva</h3>

			<form action="ReservasServlet" method="post">

				<label for=""> <span>*ID:</span> <input class="text-end"
					type="number" name="id" id="id" value="${reserva.getId()}" required>
				</label> 
				<label for=""> <span>Restricción día:</span> <textarea
						name="tipoRestriccion" 
						id="tipoRestriccion" required>${reserva.getTipoRestriccionDia().getTipo()}</textarea></label>

				<input type="submit" name="fechaReserva" id="fechaReserva"
					value="Editar">
			</form>

			<h3 class="text-center">Buscar reserva</h3>

			<form action="ReservasServlet" method="post">

				<label for=""> <span>*ID:</span> <input class="text-end"
					type="number" name="id" id="id" value="${reserva.getId()}" required>
				</label> <input type="submit" name="btnReserva" value="Mostrar">

			</form>
			
			<h3 class="text-center">Eliminar reserva</h3>
			<form action="ReservasServlet" method="post">

				<label for=""> <span>*ID:</span> <input class="text-end"
					type="number" name="id" id="id" value="${reserva.getId()}" required>
				</label> <input type="submit" name="btnReserva" value="Borrar">
			</form>

			<h3 class="text-center">Mostrar listado</h3>

			<form action="ReservasServlet" method="post">

				<input type="submit" name="btnReserva" value="Mostrar todas">

			</form>

		</article>

		<article>

			<h3 class="text-center">Crear restriccion</h3>

			<form action="ReservasServlet" method="post">

				<label for=""> <span>*Tipo:</span> <input type="text" class="text-end"
					name="tipo" id="tipo" required></label> <label for=""> <span>*Hora
						apertura:</span> <input type="time" step="2" name="horaApertura"
					id="horaApertura" ></label>
				<label for=""> <span>*Hora cierre:</span> <input type="time"
					step="2" name="horaCierre" id="horaCierre"
					></label> <label
					for=""> <span>Intervalo tiempo:</span> <input type="number"
					name="intervalo" id="intervalo"></label>

				<input type="submit" name="btnRestriccion" id="btnRestriccion"
					value="Crear">
			</form>

			<h3 class="text-center">Editar restriccion</h3>

			<form action="ReservasServlet" method="post">

				<label for=""> <span>Tipo:</span> <input type="text" class="text-end"
					name="tipo" id="tipo" value="${tipoRestriccionDia.getTipo()}"></label>
				<label for=""> <span>*Hora apertura:</span> <input
					type="time" step="2" name="horaApertura" id="horaApertura"
					value="${tipoRestriccionDia.getHoraApertura()}"></label> <label
					for=""> <span>*Hora cierre:</span> <input type="time"
					step="2" name="horaCierre" id="horaCierre"
					value="${tipoRestriccionDia.getHoraCierre()}"></label> <label
					for=""> <span>Intervalo tiempo:</span> <input type="number"
					name="intervalo" id="intervalo"
					value="${tipoRestriccionDia.getIntervaloTiempo()}"></label> <input
					type="submit" name="btnRestriccion" id="btnRestriccion"
					value="Editar">
			</form>
		</article>

		<article>

			<h3 class="text-center">Buscar restricción</h3>

			<form action="ReservasServlet" method="post">

				<label for=""> <span>*Tipo:</span> <input class="text-end"
					type="text" name="tipo" id="tipo"
					value="${tipoRestriccionDia.getTipo()}" required>
				</label> <input type="submit" name="btnRestriccion" value="Mostrar">

			</form>
			
			<h3 class="text-center">Eliminar restriccion</h3>
			<form action="ReservasServlet" method="post">

				<label for=""> <span>*ID:</span> <input class="text-end"
					type="text" name="tipo" id="tipo"
					value="${tipoRestriccionDia.getTipo()}" required>
				</label> <input type="submit" name="btnRestriccion" value="Borrar">
			</form>

			<h3 class="text-center">Mostrar listado</h3>

			<form action="ReservasServlet" method="post">

				<input type="submit" name="btnRestriccion" value="Mostrar todas">

			</form>

		</article>

	</div>

	<div class="container">

		<table class="table">

			<caption>Reservas</caption>

			<thead>
				<tr>
					<th>ID</th>
					<th>Fecha Inicio</th>
					<th>Fecha Fin</th>
					<th>Id intervención</th>
					<th>Id restricción día</th>
					<th></th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="reserva" items="${reservasList}">
					<tr>
						<td data-label="ID">${reserva.getId()}</td>
						<td data-label="FECHA INICIO">${reserva.getFechaInicio()}</td>
						<td data-label="FECHA FIN">${reserva.getFechaFin()}</td>
						<td data-label="INTERVENCIÓN">${reserva.getIntervencion().getId()}</td>
						<td data-label="RESTRICCIÓN DÍA">${reserva.getTipoRestriccionDia().getTipo()}</td>
						<td><a href="ReservasServlet?id=${reserva.getId()}">
								Opciones </a></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>

		<table class="table">

			<caption>Tipos de restricciones</caption>

			<thead>
				<tr>
					<th>Tipo</th>
					<th>Hora apertura</th>
					<th>Hora cierre</th>
					<th>Intervalo tiempo</th>
					<th></th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="tipoRestriccionDia"
					items="${tipoRestriccionDiaList}">
					<tr>
						<td data-label="TIPO">${tipoRestriccionDia.getTipo()}</td>
						<td data-label="HORA APERTURA">${tipoRestriccionDia.getHoraApertura()}</td>
						<td data-label="HORA CIERRE">${tipoRestriccionDia.getHoraCierre()}</td>
						<td data-label="INTERVALO DE TIEMPO">${tipoRestriccionDia.getIntervaloTiempo()}</td>
						<td><a
							href="ReservasServlet?restriccion=${tipoRestriccionDia.getTipo()}">
								Opciones </a></td>
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