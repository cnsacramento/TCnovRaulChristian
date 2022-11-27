<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Clientes</title>
    <link rel='stylesheet' type='text/css' media='screen' href='{{ asset('css/general.css') }}'>
    <link rel="stylesheet" type="text/css" media="screen" href="{{ asset('css/crud.css') }}">
    <meta name="description" content="Métodos para realizar el CRUD sobre cliente">
</head>

<body>
    <header>
        <nav>
            <a href="/" class="nav-link">Volver</a>
        </nav>
    </header>

    <div class="crud">
        <article>

            <h3 class="text-center">Agregar cliente</h3>

            <form action="/clientes/agregar" method="post">
                @csrf
                <label for=""> <span>*DNI:</span> <input type="text" name="dni" id="dni"
                        maxlength="9" pattern="[0-9]{7,8}[A-Za-z]" required>
                </label> <label for=""> <span>*Nombre:</span> <input type="text" name="nombre"
                        id="nombre" required>
                </label> <label for=""> <span>*Apellidos:</span> <input type="text" name="apellidos"
                        id="apellidos" required>
                </label> <label for=""> <span>Dirección:</span> <input type="text" name="direccion"
                        id="direccion">
                </label> <label for=""> <span>Correo:</span> <input type="text" name="correo"
                        id="correo">
                </label> <label for=""> <span>*Teléfono:</span> <input type="text" name="telefono"
                        id="telefono" required>

                </label> <input type="submit" name="agregar" value="Agregar">
            </form>

        </article>

        <article>

            <h3 class="text-center">Borrar cliente</h3>
            <form action="/clientes/borrar" method="post">
                @csrf
                <label for=""> <span>*DNI Cliente:</span> <input type="text" name="dni" id="dni"
                        value="@if(isset($cliente)){{ $cliente->dni }}@endif" required>
                </label> <input type="submit" name="borrar" value="Borrar">
            </form>

        </article>

        <article>

            <h3 class="text-center">Editar cliente</h3>

            <form action="/clientes/editar" method="post">
                @csrf
                <label for=""> <span>*DNI:</span> <input type="text" name="dni" id="dni"
                        maxlength="9" pattern="[0-9]{7,8}[A-Za-z]" value="@if(isset($cliente)){{ $cliente->dni }}@endif" required>
                </label> <label for=""> <span>*Nombre:</span> <input type="text" name="nombre"
                        id="nombre" value="@if(isset($cliente)){{ $cliente->nombre }}@endif" required>
                </label> <label for=""> <span>*Apellidos:</span> <input type="text" name="apellidos"
                        id="apellidos" value="@if(isset($cliente)){{ $cliente->apellidos }}@endif" required>
                </label> <label for=""> <span>Dirección:</span> <input type="text" name="direccion"
                        id="direccion" value="@if(isset($cliente)){{ $cliente->direccion }}@endif">
                </label> <label for=""> <span>Correo:</span> <input type="text" name="correo" id="correo"
                        value="@if(isset($cliente)){{ $cliente->correo }}@endif">
                </label> <label for=""> <span>*Teléfono:</span> <input type="text" name="telefono"
                        id="telefono" value="@if(isset($cliente)){{ $cliente->telefono }}@endif" required></label>

                <input type="submit" name="editar" id="editar" value="Editar">
            </form>

        </article>

        <article>

            <h3 class="text-center">Mostrar cliente</h3>

            <form action="/clientes/find" method="post">
                @csrf
                <label for=""> <span>*DNI:</span> <input type="text" name="dni" id="dni"
                        maxlength="9" value="@if(isset($cliente)){{ $cliente->dni }}@endif" pattern="[0-9]{7,8}[A-Za-z]" required>
                </label> <input type="submit" name="mostrar" value="Mostrar">

            </form>

            <h3 class="text-center">Mostrar listado clientes</h3>

            <form action="/clientes/mostrartodos" method="post">
                @csrf
                <input type="submit" name="mostrartodos" value="Mostrar todos">

            </form>

        </article>

    </div>

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
                    <th></th>
                </tr>
            </thead>

            <tbody>

                @if (isset($clientes))
                    @foreach ($clientes as $cliente)
                        <tr>
                            <td data-label="DNI">{{ $cliente->dni }}</td>
                            <td data-label="Nombre">{{ $cliente->nombre }}</td>
                            <td data-label="Apellidos">{{ $cliente->apellidos }}</td>
                            <td data-label="Dirección">{{ $cliente->direccion }}</td>
                            <td data-label="Correo">{{ $cliente->correo }}</td>
                            <td data-label="Teléfono">{{ $cliente->telefono }}</td>
                            <td>
                                <a href="/clientes/opciones?dni={{ $cliente->dni }}">
                                    Opciones
                                </a>
                            </td>
                            <td>
                                <a href="mascotas?metodo=save&clienteDni={{ $cliente->dni }}&especieId=">
                                    Mascotas
                                </a>
                            </td>
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
