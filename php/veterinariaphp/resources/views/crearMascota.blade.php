<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name = "viewport" content="width=device-width, initial-scale=1.0">
<title>CrearMascota</title>
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
		<form action="/mascotas/findEspecie" method="POST">
			@csrf
			<label for ="nombreEspecie"> </label> 
			<input class="controls" type="text" name="nombreEspecie" value="" placeholder="Nombre de la especie"> 
			<input class="buttons" type="submit" name="boton" value="Encontrar">
		</form><br>
	</div>

	<div class ="contenedor">
		@if(empty($especie))
			<div class = "formularios">    
			    <div class="container">
			    	<form action="/especies/save" method="POST">
						@csrf
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
		@endif
			
		@if(!empty($especie))
			<div class = "formularios">    
			    <div class="container">
			    	<form action="/cliente/especies/edit" method="POST">
					@csrf
			    		<label for ="id">ID: </label> 
						<input class="controls" type="text" name="especieIdEdit" value="{{$especie->id}}" placeholder="Nombre"> 
						<label for ="nombre">Nombre: </label> 
						<input class="controls" type="text" name="nombre" value="{{$especie->nombre}}" placeholder="Nombre"> 
						<label for ="peligrosa">Peligrosa: </label> 
						<select class="controls" name="peligrosa">
							<option>No</option>
							<option>Si</option>
						</select>
						<input class="buttons" type="submit" name="boton" value="Editar Especie">
					</form>
			    </div>
	    	</div>
		@endif
	
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
								@if(!empty($especies))
									@foreach($especies as $especie)
										<tr>
											<td data-label="ID">{{$especie->id}}</td>
											<td data-label="Nombre">{{$especie->nombre}}</td>
											<td data-label="Peligrosa">
												@if($especie->peligrosa == 1)
												si
												@else
												no
												@endif
											</td>
											<td data-label="Opciones">
												<div class="imagenes">
													<a href="/cliente/especies/edit?especieIdEdit={{$especie->id}}" id="btnOpciones"><img alt="e" src="{{asset('images/edit.svg')}}"></a>
													<a href="/cliente/especies/delete?especieIdDelete={{$especie->id}}" id="btnOpciones"><img alt="d" src="{{asset('images/delete.svg')}}"></a>
													<a href="/cliente/mascotas?especieId={{$especie->id}}&clienteDni={{session('clienteDni')}}" id="btnOpciones"><img alt="c" src="{{asset('images/intervencion.svg')}}"></a>
												</div>
											</td>
										</tr>
									@endforeach
								@endif
							</tbody>
						</table>
					</div>
					<div class="tablaIndividual">
						<table class="table">
							<caption>Mascotas de {{session('clienteDni')}}</caption>
							<thead>
								<tr>
									<th>ID</th>
									<th>Nombre</th>
									<th>Especie</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								@if(!empty($mascotas))
									@foreach ($mascotas as $mascota)
										<tr>
											<td data-label="ID">{{$mascota->id}}</td>
											<td data-label="Nombre">{{$mascota->nombre}}</td>
											<td data-label="Especie">{{$mascota->especieMascota->nombre}}</td>
											<td>
												<div class="imagenes">
													<a href="/clientes/mascotas/delete?id={{$mascota->id}}" id="btnOpciones"><img alt="d" src="{{asset('images/delete.svg')}}"></a>
												</div>
											</td>
										</tr>
									@endforeach
								@endif
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>

			@if(empty($_GET['especieId']))
				<div class = "formularios">    
					<div class="container">
						<form action="/mascotas/save" method="POST">
							<label for ="nombre">Nombre: </label> 
							<input class="controls" type="text" name="nombre" value="" placeholder="Nombre" readonly="readonly"> 
							<label for ="fechaNacimiento">Fecha nacimiento: </label> 
							<input class="controls" type="date" name="fechaNacimiento" value="" placeholder="ID" readonly="readonly"> 
							<label for ="nombreMascota">Peso: </label> 
							<input class="controls" type="text" name="peso" value="" placeholder="Peso" readonly="readonly"> 
							<label for ="especie"> Especie: </label> 
							<input class="controls" type="number" name="especie" value="" placeholder="ID_ESPECIE" required="required" readonly="readonly"> 
							<label for ="cliente"> Cliente: </label>
							<input class="controls" type="text" name="cliente" value="{{session('clienteDni')}}" placeholder="DNI_CLIENTE" required="required" readonly="readonly"> 
							<input class="buttons" type="submit" name="boton" value="Crear Mascota">
						</form><br>
					</div>
				</div>
			@endif
			
			@if(!empty($_GET['especieId']))
				<div class = "formularios">    
					<div class="container">
						<form action="/mascotas/save" method="POST">
						@csrf
							<label for ="nombre">Nombre: </label> 
						<input class="controls" type="text" name="nombre" value="" placeholder="Nombre" required="required"> 
						<label for ="fechaNacimiento">Fecha nacimiento: </label> 
						<input class="controls" type="date" name="fechaNacimiento" value="" placeholder="ID" required="required"> 
						<label for ="nombreMascota">Peso: </label> 
						<input class="controls" type="text" name="peso" value="" placeholder="Peso" required="required"> 
						<label for ="especie"> Especie: </label> 
						<input class="controls" type="number" name="especie" value="{{$_GET['especieId']}}" placeholder="ID_ESPECIE" required="required" readonly="readonly"> 
						<label for ="cliente"> Cliente: </label>
						<input class="controls" type="text" name="cliente" value="{{session('clienteDni')}}" placeholder="DNI_CLIENTE" required="required" readonly="readonly"> 
						<input class="buttons" type="submit" name="boton" value="Crear Mascota">
					</form><br>
			    </div>
		    </div>
	    @endif
	</div>
</body>
</html>