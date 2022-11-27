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

