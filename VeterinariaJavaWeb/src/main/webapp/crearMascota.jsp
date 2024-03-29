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
			<label for ="nombreEspecie"> </label> 
			<input class="controls" type="text" name="nombreEspecie" value="" placeholder="Nombre de la especie"> 
			<input class="buttons" type="submit" name="boton" value="Encontrar">
		</form><br>
	</div>
	<div class ="contenedor">
		<c:if test="${empty especie}">
			<div class = "formularios">    
			    <div class="container">
			    	<form action="mascotasServlet" method="POST">
						<label for ="nombre">Nombre: </label> 
						<input class="controls" type="text" name="nombre" value="" placeholder="Nombre"> 
						<label for ="nombre">Peligrosa: </label> 
						<select class="controls" name="peligrosa">
							<option>No</option>
							<option>Si</option>
						</select>
						<input class="buttons" type="submit" name="boton" value="Crear Especie">
					</form>
			    </div>
		    </div>
		</c:if>
			
		<c:if test="${not empty especie}">
			<div class = "formularios">    
			    <div class="container">
			    	<form action="mascotasServlet" method="POST">
			    		<label for ="nombre">ID: </label> 
						<input class="controls" type="text" name="id" value="${especie.getId()}" placeholder="Nombre"> 
						<label for ="nombre">Nombre: </label> 
						<input class="controls" type="text" name="nombre" value="${especie.getNombre()}" placeholder="Nombre"> 
						<label for ="nombre">Peligrosa: </label> 
						<select class="controls" name="peligrosa">
							<option>No</option>
							<option>Si</option>
						</select>
						<input class="buttons" type="submit" name="boton" value="Editar Especie">
					</form>
			    </div>
	    	</div>
		</c:if>
		
	    <div class="container">
			<div class="wrapper">
				<div class="tablas">
					<div class="tablaIndividual">
				        <table class="table">
		            <caption>Seleccione la especie:</caption>
		            <thead>
		                <tr>
		                    <th>ID</th>
		                    <th>Nombre</th>
		                    <th>Peligrosa</th>
		                    <th></th>
		                </tr>
		            </thead>
		            <tbody>
		            	<c:forEach var ="especie" items="${especies}">
							<tr>
			                    <td data-label="ID">${especie.getId()}</td>
			                    <td data-label="Nombre">${especie.getNombre()}</td>
			                    <td data-label="Peligrosa">${especie.getPeligrosa()}</td>
			                    <td data-label="Opciones">
									<div class="imagenes">
										<a href="MascotasServlet?metodo=editEspecie&especieId=${especie.getId()}" id="btnOpciones"><img alt="editEspecie" src="css/images/edit.svg"></a>
										<a href="MascotasServlet?metodo=deleteEspecie&especieId=${especie.getId()}" id="btnOpciones"><img alt="deleteEspecie" src="css/images/delete.svg"></a>
										<a href="MascotasServlet?metodo=save&especieId=${especie.getId()}&clienteDni=${clienteDni}" id="btnOpciones"><img alt="crearMascota" src="css/images/intervencion.svg"></a>
									</div>
								</td>
		                	</tr>
		            	</c:forEach>
		            </tbody>
		        </table>
					</div>
					<div class="tablaIndividual">
						<table class="table">
							<caption>Mascotas del Cliente</caption>
							<thead>
								<tr>
									<th>ID</th>
									<th>Nombre</th>
									<th>Especie</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${not empty mascotas}">	
									<c:forEach var ="mascota" items="${mascotas}">
										<tr>
											<td data-label="ID">${mascota.getId()}</td>
											<td data-label="Nombre">${mascota.getNombre()}</td>
											<td data-label="Especie">${mascota.getEspecieMascota().getNombre()}</td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
	    	</div>
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