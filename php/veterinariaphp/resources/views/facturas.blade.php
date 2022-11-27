<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Facturas</title>
    <link rel='stylesheet' type='text/css' media='screen' href='{{ asset('css/general.css') }}'>
    <link rel="stylesheet" type="text/css" media="screen" href="{{ asset('css/crud.css') }}">
    <meta name="description" content="Métodos para realizar el CRUD sobre facturas">
</head>

<body>
    <header>
        <nav>
            <a href="/" class="nav-link">Volver</a>
        </nav>
    </header>

    <div class="crud">
    <article>

    <h3 class="text-center">Crear factura</h3>

    <form action="/facturas/crear" method="post">
        @csrf
        <label for=""> <span>*Fecha:</span> <input type="date" name="fecha" id="fecha">
        </label> <label for=""> <span>Coste:</span> <input class="text-end" type="number"
                step="0.01" name="coste" id="coste">
        </label> <label for=""> <span>*Detalles:</span>
        <textarea name="detalles" id="detalles"></textarea></label>

        <input type="submit" name="btnFactura" id="btnFactura" value="Crear">
    </form>

    </article>

  <article>

   <h3 class="text-center">Eliminar factura</h3>
   <form action="/facturas/eliminar" method="post">
    @csrf
    <label for=""> <span>*ID:</span> <input class="text-end" type="number"
     name="id" id="id" value="@if(isset($factura)){{ $factura->id }}@endif" required>
    </label> <input type="submit" name="btnFactura" value="Borrar">
   </form>

  </article>

  <article>

   <h3 class="text-center">Editar factura</h3>

   <form action="/facturas/editar" method="post">
    @csrf
    <label for=""> <span>*ID:</span> <input class="text-end" type="number"
     name="id" id="id" value="@if(isset($factura)){{ $factura->id }}@endif" required>
    </label> <label for=""> <span>*Fecha:</span> <input type="date"
     name="fecha" id="fecha" value="@if(isset($factura)){{ explode(' ', $factura->fecha)[0] }}@endif">
    </label> <label for=""> <span>Coste:</span> <input type="number" step="0.01"
     name="coste" id="coste" value="@if(isset($factura)){{ $factura->coste }}@endif">
    </label> <label for=""> <span>*Detalles:</span>
    <textarea name="detalles" id="detalles">@if(isset($factura)){{ $factura->detalles }}@endif</textarea></label>

    <input type="submit" name="btnFactura" id="btnFactura" value="Editar">
   </form>

  </article>

  <article>

   <h3 class="text-center">Mostrar factura</h3>

   <form action="/facturas/mostrar" method="post">
    @csrf
    <label for=""> <span>*ID:</span> <input class="text-end" type="number"
     name="id" id="id" value="@if(isset($factura)){{ $factura->id }}@endif" required>
    </label> <input type="submit" name="btnFactura" value="Mostrar">

   </form>

   <h3 class="text-center">Mostrar listado</h3>

   <form action="/facturas/mostrar-todas" method="post">
    @csrf
    <input type="submit" name="btnFactura"
     value="Mostrar todas">

   </form>

  </article>

 </div>

 <div class="container">

  <table class="table">

   <caption>Facturas</caption>

   <thead>
    <tr>
     <th>ID</th>
     <th>Fecha</th>
     <th>Coste</th>
     <th>Detalles</th>
     <th></th>
    </tr>
   </thead>

   <tbody>
    @if(isset($facturas))
        @foreach($facturas as $factura)
        <tr>
        <td data-label="ID">{{ $factura->id }}</td>
        <td data-label="FECHA">{{ $factura->fecha }}</td>
        <td data-label="COSTE">{{ $factura->coste }}</td>
        <td data-label="DETALLES">{{ $factura->detalles }}</td>
        <td>
        <a href="/facturas/opciones?id={{ $factura->id }}">
            Opciones
        </a>
        </td>
        </tr>
        @endforeach
    @endif
   </tbody>

  </table>
 </div>


</body>

</html>
