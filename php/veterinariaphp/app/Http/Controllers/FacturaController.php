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
}
