<?php

namespace App\Http\Controllers;

use App\Models\Cliente;
use App\Models\Mascota;
use Doctrine\DBAL\Exception;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class MascotaController extends Controller
{
    public function save(Request $request) {

        DB::beginTransaction();
        try
        {
            $id = $request->id ?? 0;
            $nombre = $request->nombre ?? 0;
            $fechaNacimiento = $request->fechaNacimiento ?? 0;
            $peso = $request->peso ?? "";
            $dniCliente = $request->dniCliente ?? "";
            $especie = $request->especie ?? 0;

            $mascota = Mascota::find($id);
            $mascota->nombre = $nombre;
            $mascota->fechaNacimiento = $fechaNacimiento;
            $mascota->peso = $peso;

            $cliente = Cliente::find($dniCliente);

            $mascota->cliente()->associate($cliente);
            $mascota->especie = $especie;
            $mascota->save();

            $cliente->refresh();
            DB::commit();
        }
        catch (Exception $e)
        {
            DB::rollBack();
        }

    }
}
