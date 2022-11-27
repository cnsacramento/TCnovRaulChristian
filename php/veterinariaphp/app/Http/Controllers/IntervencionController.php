<?php

namespace App\Http\Controllers;

use App\Models\Intervencion;
use App\Models\TipoIntervencion;
use Illuminate\Http\Request;

class IntervencionController extends Controller
{

    public function index() {

        $intervenciones = Intervencion::all();
        $tiposIntervenciones = TipoIntervencion::all();

        return view('intervenciones', compact('intervenciones', 'tiposIntervenciones'));
    }


    public function opciones(Request $request) {

        $id = $request->id;
        $intervencion = Intervencion::find($id);

        $intervenciones = collect([$intervencion]);
        $tiposIntervenciones = TipoIntervencion::all();

        return view('intervenciones', compact('intervencion','intervenciones','tiposIntervenciones'));
    }

    public function crearTipoIntervencion(Request $request) {

        $tipo = $request->tipo;
        $tipoIntervencion = new TipoIntervencion();
        $tipoIntervencion->tipo = $tipo;
        $tipoIntervencion->save();
        $tipoIntervencion->refresh();

        $tiposIntervenciones = TipoIntervencion::all();

        return view('intervenciones', compact('tiposIntervenciones'));
    }

    public function editarTipoIntervencion(Request $request) {

        $id = $request->id;
        $tipo = $request->tipo;

        $tipoIntervencion = TipoIntervencion::find($id);
        $tipoIntervencion->tipo = $tipo;
        $tipoIntervencion->save();
        $tipoIntervencion->refresh();

        $tiposIntervenciones = TipoIntervencion::all();

        return view('intervenciones', compact('tiposIntervenciones'));
    }

    public function eliminarTipoIntervencion(Request $request) {

        $id = $request->id;
        $tipoIntervencion = TipoIntervencion::find($id);
        $tipoIntervencion->delete();

        $tiposIntervenciones = TipoIntervencion::all();
        return view('intervenciones', compact('tiposIntervenciones'));
    }

    public function mostrarTipoIntervencion(Request $request) {

        $id = $request->id;
        $tipoIntervencion = TipoIntervencion::find($id);

        $tiposIntervenciones = collect([$tipoIntervencion]);

        return view('intervenciones', compact('tiposIntervenciones'));
    }

    public function mostrarTiposIntervenciones() {

        $tiposIntervenciones = TipoIntervencion::all();

        return view('intervenciones', compact('tiposIntervenciones'));

    }

    public function opcionesTipoIntervencion(Request $request) {

        $id = $request->id;
        $tipoIntervencionOpciones = TipoIntervencion::find($id);

        $tiposIntervenciones = collect([$tipoIntervencionOpciones]);

        return view('intervenciones', compact('tipoIntervencionOpciones','tiposIntervenciones'));
    }
}
