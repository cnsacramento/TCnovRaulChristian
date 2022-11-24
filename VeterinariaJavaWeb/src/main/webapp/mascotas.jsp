<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name = "viewport" content="width=device-width, initial-scale=1.0">
<title>Mascotas</title>
    <link rel='stylesheet' type='text/css' media='screen' href='css/general.css'>
    <link rel='stylesheet' type='text/css' media='screen' href='css/mascotas.css'>

</head>
<body>	
	<header>
	  	<nav>
            <a href="panelVeterinario.jsp" class="nav-link">Volver</a>
        </nav>
	</header>
	
	<div class ="container">
		<form action="mascotasServlet" method="POST">
			<label for ="idMascota"> </label> 
			<input class="controls" type="text" name="idMascota" value="" placeholder="ID"> 
			<input class="buttons" type="submit" name="boton" value="Buscar">
		</form><br>
	</div>
	<div class ="contenedor">
		<div class = "formularios">
		    <c:if test="${not empty mascota}">
		    	<form action="mascotasServlet" method="POST">
		    		<label for ="nombre">ID: </label> 
					<input class="controls" type="text" name="id" value="${mascota.getId()}" placeholder="ID" readonly="readonly"> 
					<label for ="nombre">Nombre: </label> 
					<input class="controls" type="text" name="nombre" value="${mascota.getNombre()}" placeholder="Nombre" required="required"> 
					<label for ="fechaNacimiento">Fecha nacimiento: </label> 
					<input class="controls" type="date" name="fechaNacimiento" value="${fechaNacimiento}" placeholder="Fecha_Nacimiento" required="required"> 
					<label for ="nombreMascota">Peso: </label> 
					<input class="controls" type="text" name="peso" value="${mascota.getPeso()}" placeholder="Peso" required="required"> 
					<label for ="especie"> Especie: </label> 
					<input class="controls" type="text" name="especie" value="${mascota.getEspecieMascota().getId()}" placeholder="ID_ESPECIE" required="required"> 
					<label for ="cliente"> Cliente: </label>
					<input class="controls" type="text" name="cliente" value="${mascota.getCliente().getDni()}" placeholder="DNI_CLIENTE" required="required"> 
					<input class="buttons" type="submit" name="boton" value="Editar">
				</form><br>
		    </c:if>
		    <c:if test="${empty mascota}">
		    	<form action="mascotasServlet" method="POST">
		    		<label for ="nombre">ID: </label> 
					<input class="controls" type="text" name="nombre" value="${mascota.getId()}" placeholder="ID" readonly="readonly"> 
					<label for ="nombre">Nombre: </label> 
					<input class="controls" type="text" name="nombre" value="${mascota.getNombre()}" placeholder="Nombre" required="required" readonly="readonly"> 
					<label for ="fechaNacimiento">Fecha nacimiento: </label> 
					<input class="controls" type="date" name="fechaNacimiento" value="${mascota.getFechaNacimiento()}" placeholder="Fecha_Nacimiento" required="required" readonly="readonly"> 
					<label for ="nombreMascota">Peso: </label> 
					<input class="controls" type="text" name="peso" value="${mascota.getPeso()}" placeholder="Peso" required="required" readonly="readonly"> 
					<label for ="especie"> Especie: </label> 
					<input class="controls" type="text" name="especie" value="${mascota.getEspecieMascota().getId()}" placeholder="ID_ESPECIE" required="required" readonly="readonly"> 
					<label for ="cliente"> Cliente: </label>
					<input class="controls" type="text" name="cliente" value="${mascota.getCliente().getDni()}" placeholder="DNI_CLIENTE" required="required" readonly="readonly"> 
					<input class="buttons" type="submit" name="boton" value="Editar">
				</form><br>
		    </c:if>
		</div>
	
		<div class="container">
	        <table class="table">
	            <caption>Mascotas</caption>
	            <thead>
	                <tr>
	                    <th>ID</th>
	                    <th>Nombre</th>
	                    <th>Fecha Nacimiento</th>
	                    <th>Peso</th>
	                    <th>Especie</th>
	                    <th>Dueño</th>
	                    <th></th>
	                </tr>
	            </thead>
	            <tbody>
	            	<c:forEach var ="mascota" items="${mascotas}">
						<tr>
		                    <td data-label="ID">${mascota.getId()}</td>
		                    <td data-label="Nombre">${mascota.getNombre()}</td>
		                    <td data-label="Fecha Nacimiento">${mascota.getFechaNacimiento()}</td>
		                    <td data-label="Peso">${mascota.getPeso()}</td>
		                    <td data-label="Especie">${mascota.getEspecieMascota().getNombre()}</td>
							<td data-label="DNI Dueño">${mascota.getCliente().getDni()}</td>
							<td data-label="Opciones">
								<div class="imagenes">
									<a href="MascotasServlet?metodo=edit&id=${mascota.getId()}" id="btnOpciones"><img alt="editMascota" src="css/images/edit.svg"></a>
									<a href="MascotasServlet?metodo=delete&id=${mascota.getId()}" id="btnOpciones"><img alt="deleteMascota" src="css/images/delete.svg"></a>
									<a href="MascotasServlet?metodo=intervencion&id=${mascota.getId()}" id="btnOpciones"><img alt="Intervencion" src="css/images/intervencion.svg"></a>
								</div>
							</td>
	                	</tr>
	            	</c:forEach>
	            </tbody>
	        </table>
	    </div>
	    
	    <div class = "formularios">    
		    <div class="container">
		    	<form action="mascotasServlet" method="POST">
					<label for ="nombre">Nombre: </label> 
					<input class="controls" type="text" name="nombre" value="" placeholder="Nombre" required="required"> 
					<label for ="fechaNacimiento">Fecha nacimiento: </label> 
					<input class="controls" type="date" name="fechaNacimiento" value="" placeholder="ID" required="required"> 
					<label for ="nombreMascota">Peso: </label> 
					<input class="controls" type="text" name="peso" value="" placeholder="Peso" required="required"> 
					<label for ="especie"> Especie: </label> 
					<input class="controls" type="number" name="especie" value="" placeholder="ID_ESPECIE" required="required"> 
					<label for ="cliente"> Cliente: </label>
					<input class="controls" type="text" name="cliente" value="" placeholder="DNI_CLIENTE" required="required"> 
					<input class="buttons" type="submit" name="boton" value="Crear Mascota">
				</form><br>
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