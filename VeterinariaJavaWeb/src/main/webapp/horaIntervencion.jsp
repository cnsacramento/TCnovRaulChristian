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
<meta name="description"
	content="Solicitud de intervención">
</head>
<body>

	<div class="crud column">
		<article>

			<h3 class="text-center">Crear intervención</h3>

			<form action="IntervencionesServlet" method="post">

				<label for="">Escoge la fecha</label> 
				
				<input type="date" name="fecha"
					min='<%=new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date())%>'
					required>
					
				 <input type="submit" name="enviar" value="Enviar">

			</form>
			
			<a href="IntervencionesServlet">Volver</a>
		</article>


	</div>


</body>
</html>