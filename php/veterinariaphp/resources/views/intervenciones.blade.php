<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Intervención</title>
    <link rel='stylesheet' type='text/css' media='screen' href="{{ asset('css/general.css') }}">
    <link rel="stylesheet" type="text/css" media="screen" href="{{ asset('css/crud.css') }}">
    <meta name="description" content="Métodos para realizar el CRUD sobre intervención y tipos de intervenciones">
</head>

<body>
    <header>
        <nav>
            <a href="/" class="nav-link">Volver</a>
        </nav>
    </header>

    <div class="crud">

        <article>

            <h3 class="text-center">Crear intervención</h3>

            <form action="/intervenciones/crear" method="post">
                @csrf
                <input type="submit" name="fechaIntervencion" value="Crear">
            </form>

            <h3 class="text-center">Editar intervención</h3>

            <form action="/intervenciones/editar-intervencion" method="post">
                @csrf
                <label for=""> <span>*ID Intervención:</span> <input type="number" name="id"
                        id="id" value="@if(isset($intervencion)){{ $intervencion->id }}@endif" required>
                </label> <label for=""> <span>*Asunto:</span> <input type="text" name="asunto"
                        id="asunto" maxlength="30" value="@if(isset($intervencion)){{ $intervencion->asunto }}@endif" required>
                </label> <label for=""> <span>*Descripción:</span>
                    <textarea name="descripcion" id="descripcion">
                        @if(isset($intervencion)) {{ $intervencion->descripcion }}@endif
                    </textarea>
                </label>

                <div class="selectform">
                    <label for=""> <span>*Tipo intervención:</span></label>
                    <select name="tipointervencion">
                        @foreach ($tiposIntervenciones as $tipoIntervencion)
                            <option value="{{ $tipoIntervencion->tipo }}">{{ $tipoIntervencion->tipo }}</option>
                        @endforeach
                    </select>
                </div>

                <label for=""> <span>*ID mascota:</span> <input type="number" name="idmascota" id="idmascota"
                        value="@if(isset($intervencion)){{ $intervencion->id_mascota }}@endif">
                </label> <label for=""> <span>*ID factura:</span> <input type="text" name="factura"
                        id="factura" value="@if(isset($intervencion)){{ $intervencion->id_factura }}@endif">
                </label>
                 <input type="submit" name="editar" id="editar" value="Editar">
            </form>

            <h3 class="text-center">Eliminar intervención</h3>
            <form action="/intervenciones/eliminar" method="post">
                @csrf
                <label for=""> <span>*ID Intervención:</span> <input type="number" name="id"
                        id="id" value="@if(isset($intervencion)){{ $intervencion->id }}@endif"
                        required>
                </label> <input type="submit" name="eliminar" value="Eliminar">
            </form>

            <h3 class="text-center">Mostrar Intervención</h3>

            <form action="/intervenciones/mostrar-intervencion" method="post">
                @csrf
                <label for=""> <span>*ID:</span> <input type="number" name="id" id="id"
                        value="@if(isset($intervencion)){{ $intervencion->id }}@endif" required>
                </label> <input type="submit" name="mostrar" value="Mostrar">

            </form>

            <h3 class="text-center">Mostrar listado</h3>

            <form action="/intervenciones/mostrar-intervenciones" method="post">
                @csrf
                <input type="submit" name="mostrartodas" value="Mostrar todos">

            </form>

        </article>

        <article>

            <h3 class="text-center">Crear tipo intervención</h3>

            <form action="/intervenciones/crear-tipo" method="post">
                @csrf
                <label for=""> <span>*Tipo:</span> <input type="text" name="tipo" id="tipo"
                        maxlength="30" required>
                </label> <input type="submit" name="btntipo" value="Crear">
            </form>

            <h3 class="text-center">Editar tipo intervención</h3>
            <form action="/intervenciones/editar-tipo" method="post">
                @csrf
                <label for=""> <span>*ID Tipo Intervención:</span> <input type="number" name="id"
                        id="id" value="@if(isset($tipoIntervencionOpciones)){{ $tipoIntervencionOpciones->id }}@endif"
                        required>
                </label> <label for=""> <span>*Tipo:</span>
                    <input type="text" value="@if(isset($tipoIntervencionOpciones)){{ $tipoIntervencionOpciones->tipo }}@endif" name="tipo" id="tipo" maxlength="30" required>
                </label> <input type="submit" name="btntipo" value="Editar">
            </form>

            <h3 class="text-center">Eliminar tipo intervención</h3>
            <form action="/intervenciones/eliminar-tipo" method="post">
                @csrf
                <label for=""> <span>*ID Tipo Intervención:</span> <input type="number" name="id"
                        id="id" value="@if(isset($tipoIntervencionOpciones)){{ $tipoIntervencionOpciones->id }}@endif"
                        required>
                </label> <input type="submit" name="btntipo" value="Eliminar">
            </form>

            <h3 class="text-center">Mostrar tipo intervención</h3>

            <form action="/intervenciones/mostrar-tipo" method="post">
                @csrf
                <label for=""> <span>*ID:</span> <input type="number" name="id" id="id"
                        value="@if(isset($tipoIntervencionOpciones)){{ $tipoIntervencionOpciones->id }}@endif" required>
                </label> <input type="submit" name="btntipo" value="Mostrar">

            </form>

            <h3 class="text-center">Mostrar listado</h3>

            <form action="/intervenciones/mostrar-tipos" method="post">
                @csrf
                <input type="submit" name="btntipo" value="Mostrar todos">

            </form>

        </article>

    </div>

    <div class="container">

        <table class="table">

            <caption>Intervenciones</caption>

            <thead>
                <tr>
                    <th>ID</th>
                    <th>Asunto</th>
                    <th>Descripción</th>
                    <th>Tipo</th>
                    <th>Mascota</th>
                    <th>Factura</th>
                    <th>Equipo</th>
                    <th></th>
                </tr>
            </thead>

            <tbody>
                @if (isset($intervenciones))
                    @foreach ($intervenciones as $intervencion)
                        <tr>
                            <td data-label="ID">{{ $intervencion->id }}</td>
                            <td data-label="Asunto">{{ $intervencion->asunto }}</td>
                            <td data-label="Descripcion">{{ $intervencion->descripcion }}</td>
                            <td data-label="Tipo">{{ $intervencion->tipoIntervencion->tipo }}</td>
                            <td data-label="Mascota">{{ $intervencion->id_mascota }}</td>
                            <td data-label="Factura">{{ $intervencion->id_factura }}</td>
                            <td data-label="Equipo">
                                @foreach ($intervencion->veterinarios as $veterinario)
                                    <span>{{ $veterinario->dni }}</span>
                                @endforeach
                            </td>
                            <td><a href="/intervenciones/opciones?id={{ $intervencion->id }}">Opciones
                                </a></td>
                        </tr>
                    @endforeach
                @endif
            </tbody>

        </table>
    </div>


    <div class="container">

        <table class="table">

            <caption>Tipo de intervenciones</caption>

            <thead>
                <tr>
                    <th>ID</th>
                    <th>Tipo</th>
                    <th></th>
                </tr>
            </thead>

            <tbody>
                @if (isset($tiposIntervenciones))
                    @foreach ($tiposIntervenciones as $tipoIntervencion)
                        <tr>
                            <td data-label="ID">{{ $tipoIntervencion->id }}</td>
                            <td data-label="Tipo">{{ $tipoIntervencion->tipo }}</td>
                            <td><a href="/intervenciones/opciones-tipo?id=@if(isset($tipoIntervencion)){{ $tipoIntervencion->id }}@endif">Opciones</a></td>
                        </tr>
                    @endforeach
                @endif
            </tbody>

        </table>
    </div>

    <!--
    <c:if test="${not empty mensaje}">
        <script>
            alert("${mensaje}");
        </script>
    </c:if>
-->
</body>

</html>
