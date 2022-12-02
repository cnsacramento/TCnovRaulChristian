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
		@if(empty($especialidad))
			@if(empty($especialidad) && empty($veterinario))
			    <div class = "formularios">    
				    <div class="container">
				    	<form action="veterinarioServlet" method="POST">
				    		<a href="#tableEspecialidades">
					    		<input class="controls" type="text" name="dni" value="" placeholder="DNI" readonly="readonly"> 
								<input class="controls" type="text" name="nombre" value="" placeholder="Nombre" readonly="readonly"> 
								<input class="controls" type="text" name="apellidos" value="" placeholder="Apellidos" readonly="readonly"> 
								<input class="controls" type="text" name="telefono" value="" placeholder="Telefono" readonly="readonly"> 
								<input class="controls" type="text" name="especialidad" value="{{$especialidadAsignada}}" placeholder="seleccione una especialidad" required="required" readonly="readonly"> 
								<input class="controls" type="text" name="correo" value="" placeholder="Correo">   
	        					<input class="controls" type="password" name="contrasenia" value="" placeholder="Contrase�a">
								<input class="buttons" type="submit" name="boton" value="Crear Veterinario">
							</a>
						</form><br>
				    </div>
			    </div>
			@endif	
			@if(!empty($especialidadAsignada))
			    <div class = "formularios">    
				    <div class="container">
				    	<form action="veterinarioServlet" method="POST">
				    		<input class="controls" type="text" name="dni" value="" placeholder="DNI" pattern="[0-9]{7,8}[A-Za-z]"> 
							<input class="controls" type="text" name="nombre" value="" placeholder="Nombre"> 
							<input class="controls" type="text" name="apellidos" value="" placeholder="Apellidos"> 
							<input class="controls" type="text" name="telefono" value="" placeholder="Telefono"> 
							<a href="#tableEspecialidades">
								<input class="controls" type="text" name="especialidad" value="{{$_GET['especialidadAsignada']}}" placeholder="seleccione una especialidad" required="required" readonly="readonly"> 
							</a>
							<input class="controls" type="text" name="correo" value="" placeholder="Correo">   
        					<input class="controls" type="password" name="contrasenia" value="" placeholder="Contrase�a">
							<input class="buttons" type="submit" name="boton" value="Crear Veterinario">
						</form><br>
				    </div>
			    </div>
	   		@endif
	   		 
	   		@if(!empty($veterinario))
			    <div class = "formularios">    
				    <div class="container">
				    	<form action="veterinarioServlet" method="POST">
				    		<input class="controls" type="text" name="dni" value="{{$veterinario->dni}}" placeholder="DNI" pattern="[0-9]{7,8}[A-Za-z]" readonly="readonly"> 
							<input class="controls" type="text" name="nombre" value="{{$veterinario->nombre}}" placeholder="Nombre"> 
							<input class="controls" type="text" name="apellidos" value="{{$veterinario->apellidos}}" placeholder="Apellidos"> 
							<input class="controls" type="text" name="telefono" value="{{$veterinario->telefono}}" placeholder="Telefono"> 
							<input class="controls" type="text" name="especialidad" value="{{$veterinario->especialidadVeterinario->id}}" placeholder="seleccione una especialidad" required="required"> 
							<input class="buttons" type="submit" name="boton" value="Actualizar Veterinario">
						</form><br>
				    </div>
			    </div>
	   		@endif
	   		 
			<div class ="container">
			<div class ="container">
				<form action="veterinarioServlet" method="POST">
					<label for ="nombreEspecie"> </label> 
					<input class="controls" type="text" name="nombreVeterinario" value="" placeholder="Nombre"> 
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
							@foreach ($veterinarios as $veterinario)
								<tr>
				                    <td data-label="Dni">{{$veterinario->dni}}</td>
				                    <td data-label="Nombre">{{$veterinario->nombre}}</td>
				                    <td data-label="Apellidos">{{$veterinario->apellidos}}</td>
									<td data-label="Telefono">{{$veterinario->telefono}}</td>
				                    <td data-label="Peligrosa">{{$veterinario->especialidadVeterinario->nombre}}</td>
				                    <td data-label="Opciones">
										<div class="imagenes">
											<a href="VeterinarioServlet?metodo=editVeterinario&veterinarioId=${veterinario.getDni()}" id="btnOpciones"><img alt="editEspecie" src="css/images/edit.svg"></a>
											<a href="VeterinarioServlet?metodo=deleteVeterinario&veterinarioId=${veterinario.getDni()}" id="btnOpciones"><img alt="deleteEspecie" src="css/images/delete.svg"></a>
										</div>
									</td>
			                	</tr>
			            	@endforeach
			            </tbody>
			        </table>
			    </div>
			</div>	
		</div>
		@endif
	</div>
		<!-- Especialidad -->    
	
	
	<div class ="contenedor">			
			@if(empty($especialidad))
				<div class = "formularios">    
				    <div class="container">
				    	<form action="veterinarioServlet" method="POST">
							<input class="controls" type="text" name="nombre" value="" placeholder="Nombre"> 
							<input class="buttons" type="submit" name="boton" value="Crear Especialidad">
						</form>
				    </div>
			    </div>
			@endif
			@if(!empty($especialidad))
				<div class = "formularios">    
				    <div class="container">
				    	<form action="veterinarioServlet" method="POST">
							<input class="controls" type="text" name="id" value="{{$especialidad->id}}" placeholder="Nombre" readonly="readonly"> 
							<input class="controls" type="text" name="nombre" value="{{$especialidad->nombre}}" placeholder="Nombre"> 
							<input class="buttons" type="submit" name="boton" value="Editar Especialidad">
						</form>
				    </div>
				</div>
			@endif
   		 <div class ="container">
	
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
							@foreach ($especialidades as $especialidad)
								<tr>
				                    <td data-label="ID">{{$especialidad->id}}</td>
				                    <td data-label="Nombre">{{$especialidad->nombre}}</td>
				                    <td data-label="Opciones">
										<div class="imagenes">
											<a href="veterinarioServlet?metodo=editEspecialidad&especialidad=${especialidad.getId()}" id="btnOpciones"><img alt="editEspecie" src="css/images/edit.svg"></a>
											<a href="veterinarioServlet?metodo=deleteEspecialidad&especialidad=${especialidad.getId()}" id="btnOpciones"><img alt="deleteEspecie" src="css/images/delete.svg"></a>
											@if(empty($veterinario))
												<a href="veterinarioServlet?metodo=asignarEspecialidad&especialidad=${especialidad.getId()}" id="btnOpciones"><img alt="crearMascota" src="css/images/intervencion.svg"></a>
											@endif
										</div>
									</td>
			                	</tr>
			            	@endforeach
			            </tbody>
			        </table>
	    	</div>
	    </div>
	</div>
	</div>		
</body>
</html>