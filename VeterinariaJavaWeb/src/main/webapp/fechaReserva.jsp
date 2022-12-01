<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Creación de reserva</title>
<link rel='stylesheet' type='text/css' media='screen'
	href='css/general.css'>
<link rel='stylesheet' type='text/css' media='screen'
	href='css/clientes.css'>
<meta name="description" content="Solicitud de reserva">
</head>
<body>

	<div class="crud">

		<div class="column">

			<c:if test="${!bloqueoFecha}">
				<article>

					<h3 class="text-center">Escoge la fecha</h3>

					<form action="ReservasServlet" method="post">

						<input type="date" name="fecha" value="${fecha}"
							min='<%=new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date())%>'
							required> <input type="submit" name="fechaReserva"
							value="Escoger fecha">
					</form>

				</article>
			</c:if>

			<c:if test="${!bloqueoHora}">
				<article>

					<h3 class="text-center">Escoge la hora</h3>

					<form action="ReservasServlet" method="post">

						<div class="selectform">
							<label for=""> <span>Horas disponibles</span></label> <select
								name="hora">
								<c:forEach var="hora" items="${horasLibres}">
									<option value="${hora}">${hora}</option>
								</c:forEach>
							</select>
						</div>

						<input type="submit" name="fechaReserva" value="Escoger hora">
					</form>

				</article>
			</c:if>

			<c:if test="${!bloqueoSesiones}">
				<article>

					<h3 class="text-center">Escoge la cantidad de sesiones</h3>

					<form action="ReservasServlet" method="post">

						<div class="selectform">
							<label for=""> <span>Sesiones disponibles</span></label> <select
								name="numeroSesiones">
								<c:forEach var="sesion" items="${sesionesDisponibles}">
									<option value="${sesion}">${sesion}</option>
								</c:forEach>
							</select>
						</div>

						<input type="submit" name="fechaReserva"
							value="Escoger sesiones">
					</form>

				</article>
			</c:if>

		</div>

	</div>



</body>
</html>