<?php

namespace App\Http\Controllers;

use App\Models\Cliente;
use App\Models\Mascota;
use Doctrine\DBAL\Exception;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class MascotaController extends Controller
{

    public function index(){
        $mascotas = Mascota::all();
        return view('mascotas', compact('mascotas'));
    }


    public function save(Request $request) {

        try
        {
            DB::beginTransaction();
            $mascota = new Mascota();
            $mascota->nombre = $request->nombre;
            $mascota->fechaNacimiento = $request->fechaNacimiento;
            $mascota->peso = $request->peso;

            $cliente = Cliente::find($request->dniCliente);

            $mascota->cliente()->associate($request->cliente);
            $mascota->especieMascotum()->associate($request->especie);
            $mascota->save();

            $cliente->refresh();
            DB::commit();
        }
        catch (Exception $e)
        {
            DB::rollBack();
        }
        $mascotas = Mascota::all();
        return view('mascotas', compact('mascotas'));
    }

    public function delete(Request $request){
        $id=$request->id;
        $mascota = Mascotas::find($id);

        if($mascota != null){
            $mascota->delete();
        }
    }

    public function update(){

        $id = $request->id;
        $mascota = Mascota::find($id);

        if($mascota != null){

            $mascota->nombre = $request->nombre;
            $mascota->fechaNacimiento = $request->fechaNacimiento;
            $mascota->peso = $request->peso;

            $cliente = Cliente::find($request->dniCliente);
            $especie = EspecieMascota::find($request->especie);
            $mascota->cliente()->associate($cliente);
            $mascota->especie()->associate($especie);

            $mascota->update();

            $mascotas = Mascota::all();
            return view('mascotas', compact('mascotas'));
        }else{
            $mascota = Mascotas::find($id);
            return view('mascotas');
        }

    }

}