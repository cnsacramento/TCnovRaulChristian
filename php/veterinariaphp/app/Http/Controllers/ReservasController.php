<?php

namespace App\Http\Controllers;

use App\Models\Reserva;
use App\Models\TipoIntervencion;
use App\Models\TipoRestriccionDia;
use Illuminate\Http\Request;

class ReservasController extends Controller
{
    public function index() {

        $reservasList = Reserva::all();
        $tipoRestriccionDiaList = TipoRestriccionDia::all();

        return view('reserva', compact('reservasList', 'tipoRestriccionDiaList'));
    }


    public function mostrar(Request $request)
    {
        $id = $request->id;
        $reserva = Reserva::find($id);
        $reservasList = collect([$reserva]);
        return view('reserva', compact('reservasList'));

    }



    public function eliminarReserva(Request $request) {

        $id = $request->id;
        $reserva = Reserva::find($id);
        $reserva->delete();
        $reserva->refresh();
        $reservasList = Reserva::all();

        return view('reserva', compact('reservasList'));

    }


    public function mostrarTodasReservas(Request $request) {

        $reservasList = Reserva::all();
        return view('reserva', compact('reservasList'));
    }

    public function crearRestriccion(Request $request) {

        $tipo = $request->tipo;
        $horaApertura = $request->horaApertura;
        $horaCierre = $request->horaCierre;
        $intervalo = $request->intervalo;

        $tipoRestriccionDia = new TipoRestriccionDia();
        $tipoRestriccionDia->tipo = $tipo;
        $tipoRestriccionDia->hora_apertura = $horaApertura;
        $tipoRestriccionDia->hora_cierre = $horaCierre;
        $tipoRestriccionDia->intervalo_tiempo = $intervalo;

        $tipoRestriccionDia->save();
        $tipoRestriccionDia->refresh();
        $tipoRestriccionDiaList = collect([$tipoRestriccionDia]);
        return view('reserva', compact('tipoRestriccionDiaList'));

    }

    public function editarRestriccion(Request $request) {

        $tipo = $request->tipo;
        $horaApertura = $request->horaApertura;
        $horaCierre = $request->horaCierre;
        $intervalo = $request->intervalo;

        $tipoRestriccionDia = TipoRestriccionDia::find($tipo);
        $tipoRestriccionDia->hora_apertura = $horaApertura;
        $tipoRestriccionDia->hora_cierre = $horaCierre;
        $tipoRestriccionDia->intervalo_tiempo = $intervalo;

        $tipoRestriccionDia->save();
        $tipoRestriccionDia->refresh();
        $tipoRestriccionDiaList = collect([$tipoRestriccionDia]);
        return view('reserva', compact('tipoRestriccionDiaList'));

    }

    public function eliminarRestriccionDia(Request $request) {

        $tipo = $request->tipo;
        $tipoRestriccionDia = TipoRestriccionDia::find($tipo);
        $tipoRestriccionDia->delete();
        $tipoRestriccionDia->refresh();
        $tipoRestriccionDiaList = TipoRestriccionDia::all();

        return view('reserva', compact('tipoRestriccionDiaList'));

    }

    public function mostrarTodasRestricciones(Request $request) {

        $tipoRestriccionDiaList = TipoRestriccionDia::all();
        return view('reserva', compact('tipoRestriccionDiaList'));
    }

    public function opcionesRestriccionDia(Request $request) {

        $tipo = $request->tipo;
        $tipoRestriccionDia = TipoRestriccionDia::find($tipo);


        $tipoRestriccionDiaList = collect([$tipoRestriccionDia]);

        return view('reserva', compact('tipoRestriccionDia','tipoRestriccionDiaList'));
    }
}
