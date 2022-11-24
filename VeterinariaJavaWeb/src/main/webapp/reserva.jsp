<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Facturas</title>
<link rel='stylesheet' type='text/css' media='screen'
	href='css/general.css'>
<link rel='stylesheet' type='text/css' media='screen'
	href='css/clientes.css'>
<meta name="description"
	content="Métodos para realizar el CRUD sobre facturas">
</head>

<body>
	<header>
		<nav>
			<a href="panelVeterinario.jsp" class="nav-link">Volver</a>
		</nav>
	</header>

	<div class="crud">
		<article>

			<h3 class="text-center">Crear reserva</h3>

			<form action="ReservasServlet" method="post">

				<label for=""> <span>*ID:</span> <input class="text-end" type="number"
					name="id" id="id" value="${reserva.getId()}" required>
				</label> <label for=""> <span>*Fecha Inicio:</span> <input type="date"
					name="fechaInicio" id="fechaInicio" value="${reserva.getFechaInicio()}"></label>
					<label for=""> 
					<span>*Fecha Fin:</span> <input type="date" name="fechaFin" id="fechaFin" value="${reserva.getFechaFin()}">
				</label> 
				
				<label for=""> <span>ID Intervención:</span> <input type="number"
					name="idIntervencion" id="idIntervencion" value="${reserva.getIntervencion().getId()}">
				</label> <label for=""> <span>Restricción día:</span> 
				<textarea name="tipoRestriccion" id="tiporestriccion">${reserva.getTipoRestriccionDia().getTipo()}</textarea></label>

				<input type="submit" name="btnReserva" id="btnReserva" value="Editar">
			</form>

		</article>

		<article>

			<h3 class="text-center">Eliminar reserva</h3>
			<form action="FacturasServlet" method="post">

				<label for=""> <span>*ID:</span> <input class="text-end" type="number"
					name="id" id="id" value="${reserva.getId()}" required>
				</label> <input type="submit" name="btnReserva" value="Borrar">
			</form>

		</article>

		<article>

			<h3 class="text-center">Editar reserva</h3>

			<form action="ReservasServlet" method="post">

				<label for=""> <span>*ID:</span> <input class="text-end" type="number"
					name="id" id="id" value="${reserva.getId()}" required>
				</label> <label for=""> <span>*Fecha Inicio:</span> <input type="date"
					name="fechaInicio" id="fechaInicio" value="${reserva.getFechaInicio()}"></label>
					<label for=""> 
					<span>*Fecha Fin:</span> <input type="date" name="fechaFin" id="fechaFin" value="${reserva.getFechaFin()}">
				</label> 
				
				<label for=""> <span>ID Intervención:</span> <input type="number"
					name="idIntervencion" id="idIntervencion" value="${reserva.getIntervencion().getId()}">
				</label> <label for=""> <span>Restricción día:</span> 
				<textarea name="tipoRestriccion" id="tipoRestriccion">${reserva.getTipoRestriccionDia().getTipo()}</textarea></label>

				<input type="submit" name="btnReserva" id="btnReserva" value="Editar">
			</form>

		</article>

		<article>

			<h3 class="text-center">Buscar reserva</h3>

			<form action="ReservasServlet" method="post">

				<label for=""> <span>*ID:</span> <input class="text-end" type="number"
					name="id" id="id" value="${reserva.getId()}" required>
				</label> <input type="submit" name="btnReserva" value="Mostrar">

			</form>
			
			<h3 class="text-center">Mostrar listado</h3>

			<form action="ReservasServlet" method="post">

				<input type="submit" name="btnReserva"
					value="Mostrar todas">

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
						<td>
							<a href="FacturasServlet?id=${reserva.getId()}">
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