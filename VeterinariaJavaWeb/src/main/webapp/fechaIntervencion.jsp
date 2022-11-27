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
<title>Solicitud Intervención</title>
<link rel='stylesheet' type='text/css' media='screen'
	href='css/general.css'>
<link rel='stylesheet' type='text/css' media='screen'
	href='css/clientes.css'>
<meta name="description" content="Solicitud de intervención">
</head>
<body>

	<div class="crud">

		<div class="column">

			<c:if test="${!bloqueoFecha}">
				<article>

					<h3 class="text-center">Escoge la fecha</h3>

					<form action="IntervencionesServlet" method="post">

						<input type="date" name="fecha" value="${fecha}"
							min='<%=new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date())%>'
							required> <input type="submit" name="fechaIntervencion"
							value="Escoger fecha">

					</form>

					<a href="IntervencionesServlet">Volver</a>
				</article>
			</c:if>

			<c:if test="${!bloqueoHora}">
				<article>

					<h3 class="text-center">Escoge la hora</h3>

					<form action="IntervencionesServlet" method="post">

						<div class="selectform">
							<label for=""> <span>Horas disponibles</span></label> 
							<select	name="hora">
								<c:forEach var="hora" items="${horasLibres}">
									<option value="${hora}">${hora}</option>
								</c:forEach>
							</select>
						</div>
						
						<input type="submit" name="fechaIntervencion" value="Escoger hora">
					</form>

					<a href="IntervencionesServlet">Volver</a>
				</article>
			</c:if>
			
			<c:if test="${!bloqueoHora}">
				<article>

					<h3 class="text-center">Escoge la cantidad de sesiones</h3>

					<form action="IntervencionesServlet" method="post">

						<div class="selectform">
							<label for=""> <span>Sesiones disponibles</span></label> 
							<select	name="hora">
								<c:forEach var="sesion" items="${sesionesDisponibles}">
									<option value="${sesion}">${sesionesDisponibles}</option>
								</c:forEach>
							</select>
						</div>
						
						<input type="submit" name="fechaIntervencion" value="Escoger sesiones">
					</form>

				</article>
			</c:if>


			<c:if test="${!bloqueoDatos}">
				<article>

					<h3 class="text-center">Rellena los datos</h3>

					<form action="IntervencionesServlet" method="post">

						<label for=""> <span>*Asunto:</span> <input type="text"
							name="asunto" id="asunto" maxlength="30" required>
						</label> <label for=""> <span>*Descripción:</span> <textarea
								name="descripcion" id="descripcion" required></textarea>
						</label>

						<div class="selectform">
							<label for=""> <span>*Tipo intervención:</span></label>
							 <select
								name="tipointervencion">
								<c:forEach var="tipoIntervencion"
									items="${tipoIntervencionList}">
									<option value="${tipoIntervencion.getId()}">${tipoIntervencion.getTipo()}</option>
								</c:forEach>
							</select>
						</div>

						<label for=""> <span>*ID mascota:</span> <input
							type="number" name="idmascota" id="idmascota">
						</label> <label for=""> <span>*Equipo:</span> <input type="text"
							name="equipo" id="equipo" required>

						</label> <input type="submit" name="fechaIntervencion" value="Continuar">
					</form>

				</article>
			</c:if>


			<c:if test="${!bloqueoFactura}">
				<article>

					<h3 class="text-center">Crear factura</h3>

					<form action="IntervencionesServlet" method="post">

						<label for=""> <span>*Fecha:</span> <input type="date"
							name="fechafactura" id="fecha">
						</label> <label for=""> <span>Coste:</span> <input
							class="text-end" type="number" step="0.01" name="coste"
							id="coste">
						</label> <label for=""> <span>*Detalles:</span> <textarea
								name="detalles" id="detalles"></textarea></label> <input type="submit"
							name="fechaIntervencion" id="fechaIntervencion"
							value="Crear factura">
					</form>

				</article>
			</c:if>

		</div>

	</div>


</body>
</html>