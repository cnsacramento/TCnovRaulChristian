<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name = "viewport" content="width=device-width, initial-scale=1.0">
<title>Mascotas</title>

    <link rel='stylesheet' type='text/css' media='screen' href="{{ asset('css/general.css') }}">
    <link rel='stylesheet' type='text/css' media='screen' href="{{ asset('css/mascotas.css')}}">
</head>
<body>	
	<header>
	  	<nav>
		  <a href="/" class="nav-link">Volver</a>
        </nav>
	</header>
	
	<div class ="container">
		<form action="/mascotas/find" method="POST">
			@csrf
			<label for ="idMascota"> </label> 
			<input class="controls" type="text" name="idMascota" value="" placeholder="ID"> 
			<input class="buttons" type="submit" name="boton" value="Buscar">
		</form><br>
	</div>
	<div class ="contenedor">
		<div class = "formularios">
			@if(!empty($mascota))
		    	<form action="/mascotas/edit" method="POST">
					@csrf
		    		<label for ="nombre">ID: </label> 
					<input class="controls" type="text" name="id" value="{{$mascota->id}}" placeholder="ID" readonly="readonly"> 
					<label for ="nombre">Nombre: </label> 
					<input class="controls" type="text" name="nombre" value="{{$mascota->nombre}}" placeholder="Nombre" required="required"> 
					<label for ="fechaNacimiento">Fecha nacimiento: </label> 
					<input class="controls" type="date" name="fechaNacimiento" value="{{explode(' ', $mascota->fecha_nacimiento)[0]}}" placeholder="Fecha_Nacimiento" required="required"> 
					<label for ="nombreMascota">Peso: </label> 
					<input class="controls" type="text" name="peso" value="{{$mascota->peso}}" placeholder="Peso" required="required"> 
					<label for ="especie"> Especie: </label> 
					<input class="controls" type="text" name="especie" value="{{$mascota->id_especie}}" placeholder="ID_ESPECIE" required="required"> 
					<label for ="cliente"> Cliente: </label>
					<input class="controls" type="text" name="dni" value="{{$mascota->cliente->dni}}" placeholder="DNI_CLIENTE" required="required"> 
					<input class="buttons" type="submit" name="boton" value="Editar">
				</form><br>
		    @endif

			@if(empty($mascota))
		    	<form action="/mascotas/edit" method="POST">
				@csrf
				<label for ="nombre">ID: </label> 
					<input class="controls" type="text" name="id" value="" placeholder="ID" readonly="readonly"> 
					<label for ="nombre">Nombre: </label> 
					<input class="controls" type="text" name="nombre" value="" placeholder="Nombre" required="required" readonly="readonly"> 
					<label for ="fechaNacimiento">Fecha nacimiento: </label> 
					<input class="controls" type="date" name="fechaNacimiento" value="" placeholder="Fecha_Nacimiento" required="required" readonly="readonly"> 
					<label for ="nombreMascota">Peso: </label> 
					<input class="controls" type="text" name="peso" value="" placeholder="Peso" required="required" readonly="readonly"> 
					<label for ="especie"> Especie: </label> 
					<input class="controls" type="text" name="especie" value="" placeholder="ID_ESPECIE" required="required" readonly="readonly"> 
					<label for ="cliente"> Cliente: </label>
					<input class="controls" type="text" name="cliente" value="" placeholder="DNI_CLIENTE" required="required" readonly="readonly"> 
					<input class="buttons" type="submit" name="boton" value="Editar">
				</form><br>
			@endif
		</div>
	
		<div class="container">
			<div class = "wrapper">
		        <table class="table">
		            <caption>Mascotas</caption>
		            <thead>
		                <tr>
		                    <th>ID</th>
		                    <th>Nombre</th>
		                    <th>Nacimiento</th>
		                    <th>Peso</th>
		                    <th>Especie</th>
		                    <th>Dueño</th>
		                    <th></th>
		                </tr>
		            </thead>
		            <tbody>
					@if(!empty($mascotas))
		            	@foreach ($mascotas as $mascota)
							<tr>
			                    <td data-label="ID">{{$mascota->id}}</td>
			                    <td data-label="Nombre">{{$mascota->nombre}}</td>
			                    <td data-label="Nacimiento">{{explode(" ", $mascota->fecha_nacimiento)[0]}}</td>
			                    <td data-label="Peso">{{$mascota->peso}}</td>
			                    <td data-label="Especie">{{$mascota->especieMascota->nombre}}</td>
								<td data-label="DNI Dueño">{{$mascota->cliente->dni}}</td>
								<td data-label="Opciones">
									<div class="imagenes">
										<a href="/mascotas/edit?id={{ $mascota->id }}" id="btnOpciones"><img alt="e" src="{{asset('images/edit.svg') }}"></a>
										<a href="/mascotas/delete?id={{$mascota->id}}" id="btnOpciones"><img alt="d" src="{{asset('images/delete.svg')}}"></a>
										<a href="/mascotas/intevencion?id={{$mascota->id}}" id="btnOpciones"><img alt="i" src="{{asset('images/intervencion.svg')}}"></a>
									</div>
								</td>
		                	</tr>
		            	@endforeach
					@endif
		            </tbody>
		        </table>
			</div>
	    </div>
	    
	    <div class = "formularios">    
		    <div class="container">
				<form action="mascotas/save" method="POST">
					@csrf
					<label for ="nombre">Nombre: </label> 
					<input class="controls" type="text" name="nombre" value="" placeholder="Nombre" required="required"> 
					<label for ="fechaNacimiento">Fecha nacimiento: </label> 
					<input class="controls" type="date" name="fechaNacimiento" value="" placeholder="" required="required"> 
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

	@if(isset($mensaje)){ 
	    <script>
	            alert("${mensaje}");
		</script>
	@endif
</body>
</html>