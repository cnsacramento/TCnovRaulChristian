<?php

namespace App\Http\Controllers;

use App\Models\Cliente;
use Doctrine\DBAL\Exception;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class ClientesController extends Controller
{


    public function index() {

        $clientes = $this->findAll();

        return view('clientes', compact('clientes'));
    }


    public function save(Request $request)
    {

        $cliente = new Cliente();
        $cliente->dni = $request->dni;
        $cliente->nombre = $request->nombre;
        $cliente->apellidos = $request->apellidos;
        $cliente->direccion = $request->direccion;
        $cliente->correo = $request->correo;
        $cliente->telefono = $request->telefono;
        $cliente->save();

        $cliente->refresh();
        $clientes = collect([$cliente]);
        return view('clientes', compact('clientes'));

    }

    public function delete(Request $request)
    {
        $dni = $request->dni;
        $cliente = Cliente::find($dni);
        $cliente->delete();
        $cliente->refresh();

        $clientes = Cliente::all();
        return view('clientes', compact('clientes'));
    }

    public function update(Request $request)
    {

        $dni = $request->dni;
        $cliente = Cliente::find($dni);

        if($cliente == null) {
            return view('clientes');
        }

        $cliente->dni = $request->dni;
        $cliente->nombre = $request->nombre;
        $cliente->apellidos = $request->apellidos;
        $cliente->direccion = $request->direccion;
        $cliente->correo = $request->correo;
        $cliente->telefono = $request->telefono;
        $cliente->save();

        $cliente->refresh();
        $clientes = collect([$cliente]);
        return view('clientes', compact('clientes'));
    }


    public function findById(Request $request)
    {
        $dni = request('dni');
        $cliente = Cliente::find($dni);
        $clientes = collect([$cliente]);
        return view('clientes', compact('clientes'));
 
    }


    public function findAll()
    {
        return Cliente::all();
    }

    public function opciones(Request $request) {

        $dni = $request->dni;
        $cliente = Cliente::find($dni);

        $clientes = collect([$cliente]);

        return view('clientes', compact('cliente','clientes'));
    }

}
