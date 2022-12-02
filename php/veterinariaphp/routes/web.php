<?php

use Illuminate\Support\Facades\Route;
use Illuminate\Support\Facades\Auth;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('welcome');
});

Auth::routes();

Route::get('/home', [App\Http\Controllers\HomeController::class, 'index'])->name('home');

// Panel Veterinario

Route::get('/', function() {
    return view('panel_veterinario');
});

// Clientes

Route::get('/clientes', [App\Http\Controllers\ClientesController::class, 'index']);

Route::post('/clientes/agregar', [App\Http\Controllers\ClientesController::class, 'save']);

Route::post('/clientes/borrar', [App\Http\Controllers\ClientesController::class, 'delete']);

Route::post('/clientes/editar', [App\Http\Controllers\ClientesController::class, 'update']);

Route::post('/clientes/find', [App\Http\Controllers\ClientesController::class, 'findById']);

Route::post('/clientes/mostrartodos', [App\Http\Controllers\ClientesController::class, 'index']);

Route::get('/clientes/opciones', [App\Http\Controllers\ClientesController::class, 'opciones']);

// Intervenciones

Route::get('/intervenciones', [App\Http\Controllers\IntervencionController::class, 'index']);

Route::post('/intervenciones/mostrar-intervenciones', [App\Http\Controllers\IntervencionController::class, 'index']);

Route::get('/intervenciones/opciones', [App\Http\Controllers\IntervencionController::class, 'opciones']);

// Tipos Intervenciones

Route::post('/intervenciones/crear-tipo', [App\Http\Controllers\IntervencionController::class, 'crearTipoIntervencion']);

Route::post('/intervenciones/editar-tipo', [App\Http\Controllers\IntervencionController::class, 'editarTipoIntervencion']);

Route::post('/intervenciones/eliminar-tipo', [App\Http\Controllers\IntervencionController::class, 'eliminarTipoIntervencion']);

Route::post('intervenciones/mostrar-tipo', [App\Http\Controllers\IntervencionController::class, 'mostrarTipoIntervencion']);

Route::post('intervenciones/mostrar-tipos', [App\Http\Controllers\IntervencionController::class, 'mostrarTiposIntervenciones']);

Route::get('/intervenciones/opciones-tipo', [App\Http\Controllers\IntervencionController::class, 'opcionesTipoIntervencion']);

// Facturas

Route::get('/facturas', [App\Http\Controllers\FacturaController::class, 'index']);

Route::post('/facturas/crear', [App\Http\Controllers\FacturaController::class, 'save']);

Route::post('/facturas/editar', [App\Http\Controllers\FacturaController::class, 'update']);

Route::post('/facturas/eliminar', [App\Http\Controllers\FacturaController::class, 'delete']);

Route::post('/facturas/mostrar', [App\Http\Controllers\FacturaController::class, 'findById']);

Route::post('/facturas/mostrar-todas', [App\Http\Controllers\FacturaController::class, 'index']);

Route::get('/facturas/opciones', [App\Http\Controllers\FacturaController::class, 'opciones']);

// Mascotas

Route::get('/mascotas', [App\Http\Controllers\MascotaController::class, 'index']);

Route::post('/mascotas/save', [App\Http\Controllers\MascotaController::class, 'save']);

Route::get('/mascotas/delete', [App\Http\Controllers\MascotaController::class, 'delete']);

Route::get('/mascotas/edit', [App\Http\Controllers\MascotaController::class, 'rellenarEdit']);

Route::post('/mascotas/edit', [App\Http\Controllers\MascotaController::class, 'update']);

Route::post('/mascotas/find', [App\Http\Controllers\MascotaController::class, 'find']);


//Cliente-mascotas

Route::post('/cliente/mascotas/save', [App\Http\Controllers\MascotaController::class, 'save']);

Route::get('/cliente/mascotas', [App\Http\Controllers\MascotaController::class, 'indexCrearMascotas']);

Route::post('/mascotas/findEspecie', [App\Http\Controllers\MascotaController::class, 'findEspecie']);

Route::post('/especies/save', [App\Http\Controllers\MascotaController::class, 'saveEspecieMascota']);

Route::get('/cliente/especies/delete', [App\Http\Controllers\MascotaController::class, 'deleteEspecieMascota']);

Route::get('/cliente/especies/edit', [App\Http\Controllers\MascotaController::class, 'editRellenarCampos']);

Route::post('/cliente/especies/edit', [App\Http\Controllers\MascotaController::class, 'editEspecieMascota']);

Route::get('/clientes/mascotas/delete', [App\Http\Controllers\MascotaController::class, 'deleteMascotaByCliente']);

//Veterinario

Route::get('/veterinario', [App\Http\Controllers\VeterinarioController::class, 'index']);

Route::post('/veterinario/crearEspecialidad', [App\Http\Controllers\VeterinarioController::class, 'crearEspecialidad']);

Route::post('/veterinario/editarEspecialidad', [App\Http\Controllers\VeterinarioController::class, 'editarEspecialidad']);

Route::get('/veterinario/editarEspecialidad', [App\Http\Controllers\VeterinarioController::class, 'rellenarCampos']);

Route::get('/veterinario/borrarEspecialidad', [App\Http\Controllers\VeterinarioController::class, 'borrarEspecialidad']);

Route::get('/veterinario/asignarEspecialidad', [App\Http\Controllers\VeterinarioController::class, 'asignarEspecialidad']);







