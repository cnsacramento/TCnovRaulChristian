<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crear intervención</title>
    <link rel='stylesheet' type='text/css' media='screen' href='{{ asset('css/general.css') }}'>
    <link rel="stylesheet" type="text/css" media="screen" href="{{ asset('css/crud.css') }}">
    <meta name="description" content="Creacíon de la intervención">
</head>

<body>

	<div class="crud">

		<div class="column">

			@if(!$bloqueos["bloqueoFecha"])
				<article>

					<h3 class="text-center">Escoge la fecha</h3>

					<form action="/intervenciones/crear" method="post">
                        @csrf
						<input type="date" name="fecha" value="@if(isset($fecha)){{ $fecha }}@endif"
							min="{{ date("Y-m-d") }}"
							required> <input type="submit" name="fechaIntervencion"
							value="Escoger fecha">

					</form>

				</article>
			@endif

			@if(!$bloqueos["bloqueoHora"])
				<article>

					<h3 class="text-center">Escoge la hora</h3>

					<form action="/intervenciones/crear" method="post">
                        @csrf
						<div class="selectform">
							<label for=""> <span>Horas disponibles</span></label> <select
								name="hora">
								@foreach($horasLibres as $hora)
									<option value="{{ $hora }}">{{ $hora }}</option>
								@endforeach
							</select>
						</div>

						<input type="submit" name="fechaIntervencion" value="Escoger hora">
					</form>

				</article>
			@endif

			@if(!$bloqueos["bloqueoSesiones"])
				<article>

					<h3 class="text-center">Escoge la cantidad de sesiones</h3>

					<form action="/intervenciones/crear" method="post">
                        @csrf
						<div class="selectform">
							<label for=""> <span>Sesiones disponibles</span></label> <select
								name="numeroSesiones">
								@foreach($sesionesDisponibles as $sesion)
                                    <option value="{{ $sesion }}">{{ $sesion }}</option>
								@endforeach
							</select>
						</div>

						<input type="submit" name="fechaIntervencion"
							value="Escoger sesiones">
					</form>

				</article>
			@endif


			@if(!$bloqueos["bloqueoDatos"])
                <article>

					<h3 class="text-center">Rellena los datos</h3>

					<form action="/intervenciones/crear" method="post">
                        @csrf
						<label for=""> <span>*Asunto:</span> <input type="text"
							name="asunto" id="asunto" maxlength="30" required>
						</label> <label for=""> <span>*Descripción:</span> <textarea
								name="descripcion" id="descripcion" required></textarea>
						</label>

						<div class="selectform">
							<label for=""> <span>*Tipo intervención:</span></label> <select
								name="tipointervencion">
								@foreach($tiposIntervenciones as $tipoIntervencion)
									<option value="{{ $tipoIntervencion->id }}">{{ $tipoIntervencion->tipo }}</option>
								@endforeach
							</select>
						</div>

						<label for=""> <span>*ID mascota:</span> <input
							type="number" name="idmascota" id="idmascota">
						</label> <label for=""> <span>*Equipo:</span> <input type="text"
							name="equipo" id="equipo" required>

						</label> <input type="submit" name="fechaIntervencion" value="Continuar">
					</form>

				</article>
			@endif


			@if(!$bloqueos["bloqueoFactura"])

				<article>

					<h3 class="text-center">Crear factura</h3>

					<form action="/intervenciones/crear" method="post">
                        @csrf
						<label for=""> <span>*Fecha:</span> <input type="date" min="{{ date("Y-m-d") }}"
							name="fechafactura" id="fecha" value="{{ date("Y-m-d") }}">
						</label> <label for=""> <span>Coste:</span> <input
							class="text-end" type="number" step="0.01" name="coste"
							id="coste">
						</label> <label for=""> <span>*Detalles:</span> <textarea
								name="detalles" id="detalles"></textarea></label> <input type="submit"
							name="fechaIntervencion" id="fechaIntervencion"
							value="Crear factura">
					</form>

				</article>
			@endif

		</div>

	</div>
</body>

</html>
