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

			<h3 class="text-center">Editar factura</h3>

			<form action="FacturasServlet" method="post">

				<label for=""> <span>*ID:</span> <input class="text-end" type="number"
					name="id" id="id" value="${factura.getId()}" required>
				</label> <label for=""> <span>*Fecha:</span> <input type="date"
					name="fecha" id="fecha" value='${factura.getFecha().toString().split(" ")[0]}'>
				</label> <label for=""> <span>Coste:</span> <input type="number" step="0.01"
					name="coste" id="coste" value="${factura.getCoste()}">
				</label> <label for=""> <span>*Detalles:</span> 
				<textarea name="detalles" id="detalles">${factura.getDetalles()}</textarea></label>

				<input type="submit" name="btnFactura" id="btnFactura" value="Editar">
			</form>

		</article>
		
		<article>

			<h3 class="text-center">Eliminar factura</h3>
			<form action="FacturasServlet" method="post">

				<label for=""> <span>*ID:</span> <input class="text-end" type="number"
					name="id" id="id" value="${factura.getId()}" required>
				</label> <input type="submit" name="btnFactura" value="Borrar">
			</form>

		</article>

		
		<article>

			<h3 class="text-center">Mostrar factura</h3>

			<form action="FacturasServlet" method="post">

				<label for=""> <span>*ID:</span> <input class="text-end" type="number"
					name="id" id="id" value="${factura.getId()}" required>
				</label> <input type="submit" name="btnFactura" value="Mostrar">

			</form>
			
			<h3 class="text-center">Mostrar listado</h3>

			<form action="FacturasServlet" method="post">

				<input type="submit" name="btnFactura"
					value="Mostrar todas">

			</form>

		</article>

	</div>

	<div class="container">

		<table class="table">

			<caption>Facturas</caption>

			<thead>
				<tr>
					<th>ID</th>
					<th>Fecha</th>
					<th>Coste</th>
					<th>Detalles</th>
					<th></th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="factura" items="${facturasList}">
					<tr>
						<td data-label="ID">${factura.getId()}</td>
						<td data-label="FECHA">${factura.getFecha()}</td>
						<td data-label="COSTE">${factura.getCoste()}</td>
						<td data-label="DETALLES">${factura.getDetalles()}</td>
						<td>
							<a href="FacturasServlet?id=${factura.getId()}">
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