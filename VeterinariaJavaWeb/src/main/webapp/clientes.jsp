<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name = "viewport" content="width=device-width, initial-scale=1.0">
	<title>Clientes</title>
    <link rel='stylesheet' type='text/css' media='screen' href='css/general.css'>
 	<link rel='stylesheet' type='text/css' media='screen' href='css/clientes.css'>
	<meta name="description" content="Métodos para realizar el CRUD sobre cliente">
</head>

<body>	
	<header>
	  	<nav>
            <a href="panelVeterinario.jsp" class="nav-link">Volver</a>
        </nav>
	</header>
	
	<div class ="container">
		<form action="ClientesServlet" method="POST">
			<label for ="idCliente"> </label> 
			<input class="controls" type="text" name="idCliente" value="" placeholder="ID"> 
			<input class="buttons" type="submit" name="boton" value="Buscar">
		</form><br>
	</div>
	<!-- 
	<div class ="contenedor">
		<div class = "formularios">
		    <c:if test="${not empty cliente}">
		    	<form action="ClienteServlet" method="POST">
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
		    <c:if test="${empty cliente}">
		    	<form action="ClientesServlet" method="POST">
		    	
		    		<label for ="dni">DNI: </label> 
					<input class="controls" type="text" name="nombre" value="${cliente.getDni()}" placeholder="DNI" required readonly> 
					
					<label for ="nombre">Nombre: </label> 
					<input class="controls" type="text" name="nombre" value="${cliente.getNombre()}" placeholder="Nombre" required readonly> 
					
					<label for ="apellidos">Apellidos: </label> 
					<input class="controls" type="text" name="apellidos" value="${cliente.Apellidos()}" placeholder="Apellidos" required readonly> 
					
					<label for ="direccion">Dirección: </label> 
					<input class="controls" type="text" name="direccion" value="${cliente.getDirección()}" placeholder="Dirección completa" required> 
					
					<label for ="especie">Correo: </label> 
					<input class="controls" type="text" name="correo" value="${cliente.getCorreo()}" placeholder="Correo/email" required readonly> 
					
					<label for ="cliente">Teléfono: </label>
					<input class="controls" type="text" name="cliente" value="${cliente.getTelefono()}" placeholder="+xx xxxxxxxxx" required readonly> 
					
					<input class="buttons" type="submit" name="boton" value="Editar">
				</form><br>
		    </c:if>
		</div>
		
		 -->
	
		<div class="container">
		
	        <table class="table">
	        
	            <caption>Clientes</caption>
	            
	            <thead>
	                <tr>
	                    <th>DNI</th>
	                    <th>Nombre</th>
	                    <th>Apellidos</th>
	                    <th>Dirección</th>
	                    <th>Correo</th>
	                    <th>Teléfono</th>
	                    <th></th>
	                </tr>
	            </thead>
	            
	            <tbody>
	            	<c:forEach var="cliente" items="${clientesList}">
						<tr>
		                    <td data-label="DNI">${cliente.getDni()}</td>
		                    <td data-label="Nombre">${cliente.getNombre()}</td>
		                    <td data-label="Apellidos">${cliente.getApellidos()}</td>
		                    <td data-label="Dirección">${cliente.getDireccion()}</td>
		                    <td data-label="Correo">${cliente.getCorreo()}</td>
							<td data-label="Teléfono">${cliente.getTelefono()}</td>
							<td data-label="Opciones">
								<div class="imagenes">
									<a href="ClientesServlet?metodo=edit&id=${cliente.getDni()}" id="btnOpciones"><img alt="Lápiz de edición" src="css/images/edit.svg"></a>
									<a href="ClientesServlet?metodo=delete&id=${cliente.getDni()}" id="btnOpciones"><img alt="Papelera" src="css/images/delete.svg"></a>
									<a href="ClientesServlet?metodo=intervencion&id=${cliente.getDni()}" id="btnOpciones"><img alt="Bandera" src="css/images/intervencion.svg"></a>
								</div>
							</td>
	                	</tr>
	            	</c:forEach>
	            </tbody>
	            
	        </table>
	    </div>
	    
<!-- 
	    <div class = "formularios">    
		    <div class="container">
		    	<form action="ClientesServlet" method="POST">
		    	
		    		<label for ="dni">DNI: </label> 
					<input class="controls" type="text" name="nombre" value="${cliente.getDni()}" placeholder="DNI" required readonly> 
					
					<label for ="nombre">Nombre: </label> 
					<input class="controls" type="text" name="nombre" value="${cliente.getNombre()}" placeholder="Nombre" required readonly> 
					
					<label for ="apellidos">Apellidos: </label> 
					<input class="controls" type="text" name="apellidos" value="${cliente.Apellidos()}" placeholder="Apellidos" required readonly> 
					
					<label for ="direccion">Dirección: </label> 
					<input class="controls" type="text" name="direccion" value="${cliente.getDirección()}" placeholder="Dirección completa" required> 
					
					<label for ="especie">Correo: </label> 
					<input class="controls" type="text" name="correo" value="${cliente.getCorreo()}" placeholder="Correo/email" required readonly> 
					
					<label for ="cliente">Teléfono: </label>
					<input class="controls" type="text" name="cliente" value="${cliente.getTelefono()}" placeholder="+xx xxxxxxxxx" required readonly> 
					
					<input class="buttons" type="submit" name="boton" value="Editar">
				</form><br>
		    </div>
	    </div>
    </div>
    
-->
    <c:if test="${not empty mensaje}">
	    <script>
	            alert("${mensaje}");
	    </script>
	</c:if>
</body>
</html>