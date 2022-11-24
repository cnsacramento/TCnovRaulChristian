<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width">
		<title>Panel Veterinario</title>
		<meta name="description" content="Panel de acciones para el veterinario"> 
		<link rel="stylesheet" href="css/panel-veterinario.css" type="text/css">
	</head>
	
	<body>
	
		<header class="header-panel-veterinario">
			<a href="index.jsp">Cerrar sesión</a>
		</header>
		
		<section class="section-panel">

			<h1 class="h1-panel">${usuario.getCorreo()}</h1>
			
			<a href="ClientesServlet">
				<div class="container-panel">
					
					<img src="../css/imagenes/cliente.png" alt="">
					
					<p>Cliente</p>
					
				</div>
			</a>
			
			<a href="MascotasServlet">
				<div class="container-panel">
					
					<img src="../css/imagenes/cliente.png" alt="">
					
					<p>Mascota</p>
					
				</div>
			</a>
			
			<a href="IntervencionesServlet">
				<div class="container-panel">
					
					<img src="../css/imagenes/cliente.png" alt="">
					
					<p>Intervención</p>
					
				</div>
			</a>
			
			<a href="VeterinarioServlet?metodo=">
				<div class="container-panel">
					
					<img src="../css/imagenes/cliente.png" alt="">
					
					<p>Veterinario</p>
					
				</div>
			</a>
		
		</section>
		
	</body>
	
</html>