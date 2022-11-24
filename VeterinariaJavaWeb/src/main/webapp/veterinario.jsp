<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<title>Veterinario</title>
    <link rel='stylesheet' type='text/css' media='screen' href='css/general.css'>
    <link rel='stylesheet' type='text/css' media='screen' href='css/mascotas.css'>
</head>
<body>
	<header>
	  	<nav>
            <a href="panelVeterinario.jsp" class="nav-link">Volver</a>
        </nav>
	</header>
	
		<!-- VETERINARIO -->    	
	
	<div class ="contenedor">	
		<c:if test="${empty especialidad}">
		    <div class = "formularios">    
			    <div class="container">
			    	<form action="veterinarioServlet" method="POST">
						<label for ="nombre">Nombre: </label> 
						<input class="controls" type="text" name="nombre" value="" placeholder="Nombre" readonly="readonly"> 
						<label for ="apellidos">Apellidos: </label> 
						<input class="controls" type="text" name="apellidos" value="" placeholder="Apellidos" readonly="readonly"> 
						<label for ="telefono">Telefono: </label> 
						<input class="controls" type="text" name="telefono" value="" placeholder="Telefono" readonly="readonly"> 
						<label for ="especialidad">Especialidad: </label>
						<a href="#tableEspecialidades">
							<input class="controls" type="text" name="especialidad" value="${especialidad}" placeholder="seleccione una especialidad" required="required" readonly="readonly"> 
						</a>
						<input class="buttons" type="submit" name="boton" value="Crear Veterinario">
					</form><br>
			    </div>
		    </div>
	    </c:if>
	    
	    <c:if test="${not empty especialidad}">
		    <div class = "formularios">    
			    <div class="container">
			    	<form action="veterinarioServlet" method="POST">
						<label for ="nombre">Nombre: </label> 
						<input class="controls" type="text" name="nombre" value="" placeholder="Nombre"> 
						<label for ="apellidos">Apellidos: </label> 
						<input class="controls" type="text" name="apellidos" value="" placeholder="Apellidos"> 
						<label for ="telefono">Telefono: </label> 
						<input class="controls" type="text" name="telefono" value="" placeholder="Telefono"> 
						<label for ="especialidad">Especialidad: </label>
						<input class="controls" type="text" name="especialidad" value="${especialidad}" placeholder="seleccione una especialidad" required="required" readonly="readonly"> 
						<input class="buttons" type="submit" name="boton" value="Crear Veterinario">
					</form><br>
			    </div>
		    </div>
   		 </c:if>
   		 		<div class ="container">
		<div class ="container">
			<form action="veterinarioServlet" method="POST">
				<label for ="nombreEspecie"> </label> 
				<input class="controls" type="text" name="nombreEspecie" value="" placeholder="Nombre de la especie"> 
				<input class="buttons" type="submit" name="boton" value="Encontrar">
			</form><br>
		</div>
		<div class="container">
				<div class="wrapper">
			        <table class="table">
			            <caption>Veterinarios:</caption>
			            <thead>
			                <tr>
			                    <th>DNI</th>
			                    <th>Nombre</th>
			                    <th>Apellidos</th>
								<th>Telefono</th>
                			    <th>Especialidad</th>
			                    <th></th>
			                </tr>
			            </thead>
			            <tbody>
			            	<c:forEach var ="veterinario" items="${veterinarios}">
								<tr>
				                    <td data-label="Dni">${veterinario.getDni()}</td>
				                    <td data-label="Nombre">${veterinario.getNombre()}</td>
				                    <td data-label="Apellidos">${veterinario.getApellidos()}</td>
									<td data-label="Telefono">${veterinario.getTelefono()}</td>
				                    <td data-label="Peligrosa">${veterinario.getEspecialidadVeterinario().getNombre()}</td>
				                    <td data-label="Opciones">
										<div class="imagenes">
											<a href="VeterinarioServlet?metodo=editEspecie&especieId=${especie.getId()}" id="btnOpciones"><img alt="editEspecie" src="css/images/edit.svg"></a>
											<a href="VeterinarioServlet?metodo=deleteEspecie&especieId=${especie.getId()}" id="btnOpciones"><img alt="deleteEspecie" src="css/images/delete.svg"></a>
											<a href="VeterinarioServlet?metodo=save&especieId=${especie.getId()}&clienteDni=${clienteDni}" id="btnOpciones"><img alt="crearMascota" src="css/images/intervencion.svg"></a>
										</div>
									</td>
			                	</tr>
			            	</c:forEach>
			            </tbody>
			        </table>
		    	</div>
		    </div>	
		</div>
	</div>
	
		<!-- Especialidad -->    
	
	
	<div class ="contenedor">			
			<c:if test="${empty especialidad}">
				<div class = "formularios">    
				    <div class="container">
				    	<form action="veterinarioServlet" method="POST">
							<label for ="nombre">Nombre: </label> 
							<input class="controls" type="text" name="nombre" value="" placeholder="Nombre"> 
							<input class="buttons" type="submit" name="boton" value="Crear Especie">
						</form>
				    </div>
			    </div>
			</c:if>
			<c:if test="${not empty especialidad}">
				<div class = "formularios">    
				    <div class="container">
				    	<form action="veterinarioServlet" method="POST">
				    		<label for ="nombre">ID: </label> 
							<input class="controls" type="text" name="id" value="${especialidad.getId()}" placeholder="Nombre" readonly="readonly"> 
							<label for ="nombre">Nombre: </label> 
							<input class="controls" type="text" name="nombre" value="${especialidad.getNombre()}" placeholder="Nombre"> 
							<input class="buttons" type="submit" name="boton" value="Editar Especie">
						</form>
				    </div>
		    	</div>
			</c:if>
		
   		 <div class ="container">
			<form action="veterinarioServlet" method="POST">
				<input class="controls" type="text" name="nombreEspecie" value="" placeholder="Nombre de la especie"> 
				<input class="buttons" type="submit" name="boton" value="Encontrar">
			</form>
	
		<div class="container">
			<div class="wrapper">
		        <table class="table" id="tableEspecialidades">
		            <caption>Seleccione la especialidad:</caption>
		            <thead>
		                <tr>
		                    <th>ID</th>
		                    <th>Nombre</th>
		                    <th></th>
		                </tr>
		            </thead>
		            <tbody>
		            	<c:forEach var ="especialidad" items="${especialidades}">
							<tr>
			                    <td data-label="ID">${especialidad.getId()}</td>
			                    <td data-label="Nombre">${especialidad.getNombre()}</td>
			                    <td data-label="Opciones">
									<div class="imagenes">
										<a href="veterinarioServlet?metodo=editEspecialidad&especialidad=${especialidad.getId()}" id="btnOpciones"><img alt="editEspecie" src="css/images/edit.svg"></a>
										<a href="veterinarioServlet?metodo=deleteEspecialidad&especialidad=${especialidad.getId()}" id="btnOpciones"><img alt="deleteEspecie" src="css/images/delete.svg"></a>
										<a href="veterinarioServlet?metodo=save&especialidad=${especialidad.getId()}" id="btnOpciones"><img alt="crearMascota" src="css/images/intervencion.svg"></a>
									</div>
								</td>
		                	</tr>
		            	</c:forEach>
		            </tbody>
		        </table>
	    	</div>
	    </div>
	</div>
	</div>		
	
    <c:if test="${not empty mensaje}">
	    <script>
	            alert("${mensaje}");
	    </script>
	</c:if>
</body>
</html>