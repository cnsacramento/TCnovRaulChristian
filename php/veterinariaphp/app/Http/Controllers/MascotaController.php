<?php

namespace App\Http\Controllers;

use App\Models\Cliente;
use App\Models\Mascota;
use App\Models\EspecieMascota;
use Doctrine\DBAL\Exception;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class MascotaController extends Controller
{
    public function index(){
        $mascotas = Mascota::all();
        return view('mascotas', compact('mascotas'));
    }

    public function indexCrearMascotas(){
        $especies = EspecieMascota::all();
        session()->put("clienteDni", $_GET['clienteDni']);
        return view('crearMascota', compact('especies'));
    }

    public function saveEspecieMascota(Request $request){

        $especie = new EspecieMascota();
        $especie->nombre = $request->nombre;
        if($request->peligrosa == 1){
            $especie->peligrosa = true;
        }else{
            $especie->peligrosa = false;
        }
        $especie->save();

        $especies = EspecieMascota::all();
        return view('crearMascota', compact('especies'));
    }

    public function deleteEspecieMascota(Request $request){
        $id=$request->especieId;
        $especie = EspecieMascota::find($id);

        if($especie != null){
            try{
                $especie->delete();
            }catch(Exception $ex){}
        }
        $especies = EspecieMascota::all();
        return view('crearMascota', compact('especies'));
    }

    public function save(Request $request) {

        try
        {
            DB::beginTransaction();
            $mascota = new Mascota();
            $mascota->nombre = $request->nombre;
            $mascota->fecha_nacimiento = $request->fechaNacimiento;
            $mascota->peso = $request->peso;

            $cliente = Cliente::find($request->dniCliente);

            $mascota->cliente()->associate($request->cliente);
            $mascota->especieMascota()->associate($request->especie);
            $mascota->save();

            $mascota->refresh();
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
        $mascota = Mascota::find($id);

        if($mascota != null){
            try{
                $mascota->delete();
            }catch(Exception $ex){}
        }
        $mascotas = Mascota::all();
        return view('mascotas', compact('mascotas'));
    }

    public function update(Request $request){

        $id = $request->id;
        $mascota = Mascota::find($id);

        if($mascota != null){

            $mascota->nombre = $request->nombre;
            $mascota->fecha_nacimiento = $request->fechaNacimiento;
            $mascota->peso = $request->peso;

            $cliente = Cliente::find($request->dni);
            $mascota->cliente()->associate($cliente);
            $mascota->especieMascota()->associate($request->especie);

            $mascota->update();

            $mascotas = Mascota::all();
            return view('mascotas', compact('mascotas'));
        }else{
            $mascota = Mascotas::find($id);
            return view('mascotas');
        }

    }

    public function rellenarEdit(Request $request){
        $id=$request->id;
        $mascota = Mascota::find($id);

        $mascotas = Mascota::all();
        return view('mascotas', compact('mascotas', 'mascota'));
    }


    public function find(Request $request){

        $mascota = Mascota::find($request->idMascota);

        if($mascota != null){
            $mascotas = collect([$mascota]);
            return view('mascotas', compact('mascotas'));
        }else{

            $mascotas = Mascota::all();
            return view('mascotas', compact('mascotas'));
        }
    }

}