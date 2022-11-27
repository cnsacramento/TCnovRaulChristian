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

    public function opcionesTipoIntervencion(Request $request) {

        $id = $request->id;
        $tipoIntervencion = TipoIntervencion::find($id);

        $tiposIntervenciones = collect([$tipoIntervencion]);

        return view('intervenciones', compact('tipoIntervencion','tiposIntervenciones'));
    }
}
