<!DOCTYPE html>
<html lang="{{ str_replace('_', '-', app()->getLocale()) }}">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width">
    <title>Panel Veterinario</title>
    <meta name="description" content="Panel de acciones para el veterinario">
    <link rel="stylesheet" href="css/panel-veterinario.css" type="text/css">
</head>

<body>

    <header class="header-panel-veterinario">
        <a href="/login/cerrarSesion">Cerrar sesión</a>
    </header>

    <section class="section-panel">

        <h1 class="h1-panel">{{ session()->get('usuario')->correo }}</h1>

        <a href="clientes">
            <div class="container-panel">

                <img src="images/cliente.png" alt="">

                <p>Cliente</p>

            </div>
        </a>

        <a href="mascotas">
            <div class="container-panel">

                <img src="images/mascota.png" alt="">

                <p>Mascota</p>

            </div>
        </a>

        <a href="intervenciones">
            <div class="container-panel">

                <img src="images/intervencion.png" alt="">

                <p>Intervención</p>

            </div>
        </a>

        <a href="veterinario">
            <div class="container-panel">

                <img src="images/veterinario.png" alt="">

                <p>Veterinario</p>

            </div>
        </a>

        <a href="facturas">
            <div class="container-panel">

                <img src="images/factura.png" alt="">

                <p>Facturas</p>

            </div>
        </a>

        <a href="reservas">
            <div class="container-panel">

                <img src="images/reserva.png" alt="">

                <p>Reservas</p>

            </div>
        </a>

    </section>

</html>
