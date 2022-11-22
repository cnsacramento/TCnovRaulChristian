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

	<c:if test="${not empty mensaje}">
	    <script>
	        window.addEventListener("load", function () {
	            alert("${mensaje}");
	        });
	    </script>
	</c:if>
	
	<header id = "header">
	    <div class="container">
	        <a href="index.html">
	            <h3 class="nombreEmpresa">VETERINARIA CRRC</h3>
	        </a>
	        <div class="nav">
	            <a href="panelVeterinario.jsp" class="nav-link">Volver</a>
	        </div>
	    </div>
	</header>

	<div class="container">
        <table class="table">
            <caption>Consolas</caption>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Fecha Nacimiento</th>
                    <th>Peso</th>
                    <th>Especie</th>
                    <th>Dueño</th>
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
                	</tr>
            	</c:forEach>
            </tbody>
        </table>
    </div>
	
	
	
</body>
</html>