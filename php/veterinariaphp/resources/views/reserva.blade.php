<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reserva</title>
    <link rel='stylesheet' type='text/css' media='screen' href='{{ asset('css/general.css') }}'>
    <link rel="stylesheet" type="text/css" media="screen" href="{{ asset('css/crud.css') }}">
    <meta name="description" content="Métodos para realizar el CRUD sobre reservas">
</head>

<body>
    <header>
        <nav>
            <a href="/" class="nav-link">Volver</a>
        </nav>
    </header>

    <div class="crud">

		<article>

			<h3 class="text-center">Buscar reserva</h3>

			<form action="/reservas/mostrar" method="post">
                @csrf
				<label for=""> <span>*ID:</span> <input class="text-end"
					type="number" name="id" id="id" value="@if(isset($reserva)){{ $reserva->id }}@endif" required>
				</label> <input type="submit" name="btnReserva" value="Mostrar">

			</form>

			<h3 class="text-center">Eliminar reserva</h3>
			<form action="/reservas/eliminar" method="post">
                @csrf
				<label for=""> <span>*ID:</span> <input class="text-end"
					type="number" name="id" id="id" value="@if(isset($reserva)){{ $reserva->id }}@endif" required>
				</label> <input type="submit" name="btnReserva" value="Borrar">
			</form>

			<h3 class="text-center">Mostrar listado</h3>

			<form action="/reservas/mostrartodas" method="post">
                @csrf
				<input type="submit" name="btnReserva" value="Mostrar todas">

			</form>

		</article>

		<article>

			<h3 class="text-center">Crear restriccion</h3>

			<form action="/tiporestricciondia/crear" method="post">
                @csrf
				<label for=""> <span>*Tipo:</span> <input type="text" class="text-end"
					name="tipo" id="tipo" required></label> <label for=""> <span>*Hora
						apertura:</span> <input type="time" step="2" name="horaApertura"
					id="horaApertura" ></label>
				<label for=""> <span>*Hora cierre:</span> <input type="time"
					step="2" name="horaCierre" id="horaCierre" required
					></label> <label
					for=""> <span>Intervalo tiempo:</span> <input type="number"
					name="intervalo" id="intervalo" required></label>

				<input type="submit" name="btnRestriccion" id="btnRestriccion"
					value="Crear">
			</form>

			<h3 class="text-center">Editar restriccion</h3>

			<form action="/tiporestricciondia/editar" method="post">
                @csrf
				<label for=""> <span>Tipo:</span> <input type="text" class="text-end"
					name="tipo" id="tipo" value="@if(isset($tipoRestriccionDia)){{ $tipoRestriccionDia->tipo }}@endif" required></label>
				<label for=""> <span>*Hora apertura:</span> <input
					type="time" step="2" name="horaApertura" id="horaApertura"
					value="@if(isset($tipoRestriccionDia)){{ $tipoRestriccionDia->hora_apertura }}@endif" required></label> <label
					for=""> <span>*Hora cierre:</span> <input type="time"
					step="2" name="horaCierre" id="horaCierre"
					value="@if(isset($tipoRestriccionDia)){{ $tipoRestriccionDia->hora_cierre }}@endif" required></label> <label
					for=""> <span>Intervalo tiempo:</span> <input type="number"
					name="intervalo" id="intervalo"
					value="@if(isset($tipoRestriccionDia)){{ $tipoRestriccionDia->intervalo_tiempo }}@endif" required></label> <input
					type="submit" name="btnRestriccion" id="btnRestriccion"
					value="Editar">
			</form>
		</article>

		<article>

			<h3 class="text-center">Buscar restricción</h3>

			<form action="/tiporestricciondia/buscar" method="post">
                @csrf
				<label for=""> <span>*Tipo:</span> <input class="text-end"
					type="text" name="tipo" id="tipo"
					value="@if(isset($tipoRestriccionDia)){{ $tipoRestriccionDia->tipo }}@endif" required>
				</label> <input type="submit" name="btnRestriccion" value="Mostrar">

			</form>

			<h3 class="text-center">Eliminar restriccion</h3>
			<form action="/tiporestricciondia/eliminar" method="post">
                @csrf
				<label for=""> <span>*ID:</span> <input class="text-end"
					type="text" name="tipo" id="tipo"
					value="@if(isset($tipoRestriccionDia)){{ $tipoRestriccionDia->tipo }}@endif" required>
				</label> <input type="submit" name="btnRestriccion" value="Borrar">
			</form>

			<h3 class="text-center">Mostrar listado</h3>

			<form action="/tiporestricciondia/mostrartodas" method="post">
                @csrf
				<input type="submit" name="btnRestriccion" value="Mostrar todas">
			</form>

		</article>

	</div>

	<div class="container">

		<table class="table">

			<caption>Reservas</caption>

			<thead>
				<tr>
					<th>ID</th>
					<th>Fecha</th>
					<th>Hora Inicio</th>
					<th>Hora Fin</th>
					<th>Id intervención</th>
					<th>Id restricción día</th>
				</tr>
			</thead>

			<tbody>
                @if(isset($reservasList))
                    @foreach($reservasList as $reserva)
                        <tr>
                            <td data-label="ID">{{ $reserva->id }}</td>
                            <td data-label="FECHA">{{ explode(" ", $reserva->fecha_inicio)[0] }}</td>
                            <td data-label="HORA INICIO">{{ explode(" ", $reserva->fecha_inicio)[1] }}</td>
                            <td data-label="HORA FIN">{{ explode(" ", $reserva->fecha_fin)[1] }}</td>
                            <td data-label="INTERVENCIÓN">{{ $reserva->intervencion->id }}</td>
                            <td data-label="RESTRICCIÓN DÍA">{{ $reserva->id_restriccion_dia }}</td>

                        </tr>
                    @endforeach
                @endif
			</tbody>

		</table>

		<table class="table">

			<caption>Tipos de restricciones</caption>

			<thead>
				<tr>
					<th>Tipo</th>
					<th>Hora apertura</th>
					<th>Hora cierre</th>
					<th>Intervalo tiempo</th>
				</tr>
			</thead>

			<tbody>
                @if(isset($tipoRestriccionDiaList))
                    @foreach($tipoRestriccionDiaList as $tipoRestriccionDia)
                        <tr>
                            <td data-label="TIPO">{{ $tipoRestriccionDia->tipo }}</td>
                            <td data-label="HORA APERTURA">{{ $tipoRestriccionDia->hora_apertura }}</td>
                            <td data-label="HORA CIERRE">{{ $tipoRestriccionDia->hora_cierre }}</td>
                            <td data-label="INTERVALO DE TIEMPO">{{ $tipoRestriccionDia->intervalo_tiempo }}</td>
                        </tr>
                    @endforeach
                @endif
			</tbody>

		</table>
	</div>

</body>

</html>
