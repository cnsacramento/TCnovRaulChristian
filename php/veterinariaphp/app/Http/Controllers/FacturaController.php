<?php

namespace App\Http\Controllers;

use App\Models\Factura;
use Illuminate\Http\Request;

class FacturaController extends Controller
{

    public function index() {

        $facturas = Factura::all();

        return view('facturas', compact('facturas'));
    }

    public function save(Request $request)
    {

        $factura = new Factura();
        $factura->fecha = $request->fecha;
        $factura->coste = $request->coste;
        $factura->detalles = $request->detalles;

        $factura->save();
        $factura->refresh();
        $facturas = collect([$factura]);

        return view('facturas', compact('facturas'));

    }

    public function delete(Request $request)
    {
        $id = $request->id;
        $factura = Factura::find($id);
        $factura->delete();
        $factura->refresh();

        $facturas = Factura::all();
        return view('facturas', compact('facturas'));
    }

    public function update(Request $request)
    {
        $id = $request->id;

        $factura = Factura::find($id);
        $factura->fecha = $request->fecha;
        $factura->coste = $request->coste;
        $factura->detalles = $request->detalles;

        $factura->refresh();
        $facturas = collect([$factura]);

        return view('facturas', compact('facturas'));
    }


    public function findById(Request $request)
    {
        $id = $request->id;
        $factura = Factura::find($id);
        $facturas = collect([$factura]);
        return view('facturas', compact('facturas'));
    }

    public function opciones(Request $request) {

        $id = $request->id;
        $factura = Factura::find($id);
        $facturas = collect([$factura]);

        return view('facturas', compact('factura', 'facturas'));
    }
}
