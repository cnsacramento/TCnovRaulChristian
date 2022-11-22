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
    
    <c:if test="${not empty mensaje}">
	    <script>
	        window.addEventListener("load", function () {
	            alert("${mensaje}");
	        });
	    </script>
	</c:if>
</body>
</html>