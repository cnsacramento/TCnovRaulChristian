<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Clientes</title>
<link rel='stylesheet' type='text/css' media='screen'
	href='css/general.css'>
<link rel='stylesheet' type='text/css' media='screen'
	href='css/clientes.css'>
<meta name="description"
	content="M�todos para realizar el CRUD sobre cliente">
</head>

<body>
	<header>
		<nav>
			<a href="panelVeterinario.jsp" class="nav-link">Volver</a>
		</nav>
	</header>

	<div class="crud">
		<article>

			<h3 class="text-center">Agregar cliente</h3>

			<form action="ClientesServlet" method="post">

				<label for=""> <span>*DNI:</span> <input type="text"
					name="dni" id="dni" maxlength="9" pattern="[0-9]{7,8}[A-Za-z]"
					required>
				</label> <label for=""> <span>*Nombre:</span> <input type="text"
					name="nombre" id="nombre" required>
				</label> <label for=""> <span>*Apellidos:</span> <input type="text"
					name="apellidos" id="apellidos" required>
				</label> <label for=""> <span>Direcci�n:</span> <input type="text"
					name="direccion" id="direccion">
				</label> <label for=""> <span>Correo:</span> <input type="text"
					name="correo" id="correo">
				</label> <label for=""> <span>*Tel�fono:</span> <input type="text"
					name="telefono" id="telefono" required>

				</label> <input type="submit" name="agregar" value="Agregar">
			</form>

		</article>

		<article>

			<h3 class="text-center">Borrar cliente</h3>
			<form action="ClientesServlet" method="post">

				<label for=""> <span>*DNI Cliente:</span> <input type="text"
					name="dni" id="dni" required>
				</label> <input type="submit" name="borrar" value="Borrar">
			</form>

		</article>

		<article>

			<h3 class="text-center">Editar cliente</h3>

			<form action="ClientesServlet" method="post">

				<label for=""> <span>*DNI:</span> <input type="text"
					name="dni" id="dni" maxlength="9" pattern="[0-9]{7,8}[A-Za-z]"
					required>
				</label> <label for=""> <span>*Nombre:</span> <input type="text"
					name="nombre" id="nombre" required>
				</label> <label for=""> <span>*Apellidos:</span> <input type="text"
					name="apellidos" id="apellidos" required>
				</label> <label for=""> <span>Direcci�n:</span> <input type="text"
					name="direccion" id="direccion">
				</label> <label for=""> <span>Correo:</span> <input type="text"
					name="correo" id="correo">
				</label> <label for=""> <span>*Tel�fono:</span> <input type="text"
					name="telefono" id="telefono" required></label>

				<input type="submit" name="editar" id="editar" value="Editar">
			</form>

		</article>

		<article>

			<h3 class="text-center">Mostrar cliente</h3>

			<form action="ClientesServlet" method="post">

				<label for=""> <span>*DNI:</span> <input type="text"
					name="dni" id="dni" maxlength="9" pattern="[0-9]{7,8}[A-Za-z]"
					required>
				</label> <input type="submit" name="mostrar" value="Mostrar">

			</form>

		</article>

		<article>

			<h3 class="text-center">Mostrar listado clientes</h3>

			<form action="ClientesServlet" method="post">

				<input type="submit" name="mostrartodos"
					value="Mostrar todos">

			</form>

		</article>

	</div>

	<div class="container">

		<table class="table">

			<caption>Clientes</caption>

			<thead>
				<tr>
					<th>DNI</th>
					<th>Nombre</th>
					<th>Apellidos</th>
					<th>Direcci�n</th>
					<th>Correo</th>
					<th>Tel�fono</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="cliente" items="${clientesList}">
					<tr>
						<td data-label="DNI">${cliente.getDni()}</td>
						<td data-label="Nombre">${cliente.getNombre()}</td>
						<td data-label="Apellidos">${cliente.getApellidos()}</td>
						<td data-label="Direcci�n">${cliente.getDireccion()}</td>
						<td data-label="Correo">${cliente.getCorreo()}</td>
						<td data-label="Tel�fono">${cliente.getTelefono()}</td>
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