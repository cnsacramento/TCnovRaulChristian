<?php

namespace App\Http\Controllers;

use App\Models\Cliente;
use Doctrine\DBAL\Exception;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class ClienteController extends Controller
{

    public function findById(Request $request)
    {

        $id = $request->id ?? 0;

        Cliente::find($id); // Devolver el cliente

    }

    public function deleteByDni(Request $request)
    {

        $id = $request->id ?? 0;
        Cliente::deleted($id);
    }

    public function save(Request $request)
    {

        $dni = $request->dni ?? 0;
        $nombre = $request->nombre ?? 0;
        $apellidos = $request->apellidos ?? 0;
        $correo = $request->correo ?? "";
        $direccion = $request->direccion ?? "";
        $telefono = $request->telefono ?? 0;

        $cliente = new Cliente();
        $cliente->dni = $dni;
        $cliente->nombre = $nombre;
        $cliente->apellidos = $apellidos;
        $cliente->direccion = $direccion;
        $cliente->correo = $correo;
        $cliente->telefono = $telefono;
        $cliente->save();
    }

    public function findAll()
    {
        Cliente::all(); // Devolver es una coleccion
    }

    public function findByNames(Request $request)
    {

        $nombre = $request->nombre ?? 0;
        Cliente::where('nombre', "=", $nombre)->take(10)->get()->orderBy('nombre', 'desc');
    }


    public function update(Request $request)
    {

        DB::transaction(function () use ($request)
        {
            $dni = $request->dni ?? 0;
            $nombre = $request->nombre ?? 0;
            $apellidos = $request->apellidos ?? 0;
            $correo = $request->correo ?? "";
            $direccion = $request->direccion ?? "";
            $telefono = $request->telefono ?? 0;

            $cliente = Cliente::find($dni);
            $cliente->nombre = $nombre;
            $cliente->apellidos = $apellidos;
            $cliente->direccion = $direccion;
            $cliente->correo = $correo;
            $cliente->telefono = $telefono;
            $cliente->save();

            $cliente->refresh();

            if ($cliente->nombre != $nombre)
            {
                DB::rollBack();
            }

        });
    }

    public function updateConTryCatch(Request $request)
    {
        DB::beginTransaction();
        try
        {
            $dni = $request->dni ?? 0;
            $nombre = $request->nombre ?? 0;
            $apellidos = $request->apellidos ?? 0;
            $correo = $request->correo ?? "";
            $direccion = $request->direccion ?? "";
            $telefono = $request->telefono ?? 0;

            $cliente = Cliente::find($dni);
            $cliente->nombre = $nombre;
            $cliente->apellidos = $apellidos;
            $cliente->direccion = $direccion;
            $cliente->correo = $correo;
            $cliente->telefono = $telefono;
            $cliente->save();

            $cliente->refresh();
            DB::commit();
        }
        catch (Exception $e)
        {
            DB::rollBack();
        }
    }



}
