<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name = "viewport" content="width=device-width, initial-scale=1.0">
<title>CrearMascota</title>
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
		    <div class="container">
		    	<form action="mascotasServlet" method="POST">
					<label for ="nombre">Nombre: </label> 
					<input class="controls" type="text" name="nombre" value="" placeholder="Nombre"> 
					<select class="controls" name="peligrosa">
						<option>No</option>
						<option>Si</option>
					</select>
					<input class="buttons" type="submit" name="boton" value="Crear Especie">
				</form><br>
		    </div>
	    </div>
	
	
		<div class="container">
	        <table class="table">
	            <caption>Seleccione la especie:</caption>
	            <thead>
	
	                <tr>
	                    <th>ID</th>
	                    <th>Nombre</th>
	                    <th>Peligrosa</th>
	                </tr>
	            </thead>
	            <tbody>
	            	<c:forEach var ="especie" items="${especies}">
						<tr onclick="MascotasServlet?metodo=save?&clienteDni=${clienteDni}&especieId=${especie.getId()}">
		                    <td data-label="ID">${especie.getId()}</td>
		                    <td data-label="Nombre">${especie.getNombre()}</td>
		                    <td data-label="Peligrosa">${especie.getPeligrosa()}</td>
		                    <td data-label="Opciones">
								<div class="imagenes">
									<a href="MascotasServlet?metodo=editEspecie&id=${especie.getId()}" id="btnOpciones"><img alt="editEspecie" src="css/images/edit.svg"></a>
									<a href="MascotasServlet?metodo=deleteEspecie&id=${especie.getId()}" id="btnOpciones"><img alt="deleteEspecie" src="css/images/delete.svg"></a>
									<a href="MascotasServlet?metodo=setEspecie&id=${especie.getId()}" id="btnOpciones"><img alt="crearMascota" src="css/images/intervencion.svg"></a>
								</div>
							</td>
	                	</tr>
	            	</c:forEach>
	            </tbody>
	        </table>
	    </div>
		
		<c:if test="${empty especieId}">
		    <div class = "formularios">    
			    <div class="container">
			    	<form action="mascotasServlet" method="POST">
						<label for ="nombre">Nombre: </label> 
						<input class="controls" type="text" name="nombre" value="" placeholder="Nombre" readonly="readonly"> 
						<label for ="fechaNacimiento">Fecha nacimiento: </label> 
						<input class="controls" type="date" name="fechaNacimiento" value="" placeholder="ID" readonly="readonly"> 
						<label for ="nombreMascota">Peso: </label> 
						<input class="controls" type="text" name="peso" value="" placeholder="Peso" readonly="readonly"> 
						<label for ="especie"> Especie: </label> 
						<input class="controls" type="number" name="especie" value="" placeholder="ID_ESPECIE" required="required" readonly="readonly"> 
						<label for ="cliente"> Cliente: </label>
						<input class="controls" type="text" name="cliente" value="${clienteDni}" placeholder="DNI_CLIENTE" required="required" readonly="readonly"> 
						<input class="buttons" type="submit" name="boton" value="Crear Mascota">
					</form><br>
			    </div>
		    </div>
	    </c:if>
	    
	    <c:if test="${not empty especieId}">
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
						<input class="controls" type="number" name="especie" value="${especieId}" placeholder="ID_ESPECIE" required="required" readonly="readonly"> 
						<label for ="cliente"> Cliente: </label>
						<input class="controls" type="text" name="cliente" value="${clienteDni}" placeholder="DNI_CLIENTE" required="required" readonly="readonly"> 
						<input class="buttons" type="submit" name="boton" value="Crear Mascota">
					</form><br>
			    </div>
		    </div>
	    </c:if>
	</div>
    <c:if test="${not empty mensaje}">
	    <script>
	            alert("${mensaje}");
	    </script>
	</c:if>
</body>
</html>