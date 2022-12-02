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
        $mascotas = Mascota::where('dni_cliente', session()->get("clienteDni"))->get();
        return view('crearMascota', compact('especies', 'mascotas'));
    }

    public function findEspecie(Request $request){
        $especie = $request->nombreEspecie;
        if(!Empty($especie)){
            $especies = EspecieMascota::where('nombre', "like", "%$especie%")->get();
        }else{
            $especies = EspecieMascota::all();
        }

        $mascotas = Mascota::where('dni_cliente', session()->get("clienteDni"))->get();
        return view('crearMascota', compact('especies', 'mascotas'));
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

        $mascotas = Mascota::where('dni_cliente', session()->get("clienteDni"))->get();
        $especies = EspecieMascota::all();
        return view('crearMascota', compact('especies', 'mascotas'));
    }

    public function deleteEspecieMascota(Request $request){
        $id=$request->especieIdDelete;
        $especie = EspecieMascota::find($id);

        if($especie != null){
            try{
                $especie->delete();
            }catch(Exception $ex){}
        }
        $mascotas = Mascota::where('dni_cliente', session()->get("clienteDni"))->get();
        $especies = EspecieMascota::all();
        return view('crearMascota', compact('especies', 'mascotas'));
    }

    public function editRellenarCampos(Request $request){
        $id=$request->especieIdEdit;
        $especie = EspecieMascota::find($id);

        $mascotas = Mascota::where('dni_cliente', session()->get("clienteDni"))->get();
        $especies = EspecieMascota::all();
        return view('crearMascota', compact('especies', 'mascotas', 'especie'));
    }
    
    public function editEspecieMascota(Request $request){
        $id = $request->especieIdEdit;
        $especie = EspecieMascota::find($id);

        if($especie != null){

            $especie->nombre = $request->nombre;
            if($request->peligrosa == 1){
                $especie->peligrosa = true;
            }else{
                $especie->peligrosa = false;
            }

            $especie->update();
        }

        $mascotas = Mascota::where('dni_cliente', session()->get("clienteDni"))->get();
        $especies = EspecieMascota::all();
        return view('crearMascota', compact('especies', 'mascotas'));
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
            $especie = EspecieMascota::find($request->especie);
            $mascota->cliente()->associate($request->cliente);
            $mascota->especieMascota()->associate($especie);
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

    public function deleteMascotaByCliente(Request $request){
        $id=$request->id;
        $mascota = Mascota::find($id);

        if($mascota != null){
            try{
                $mascota->delete();
            }catch(Exception $ex){}
        }
        $mascotas = Mascota::where('dni_cliente', session()->get("clienteDni"))->get();
        $especies = EspecieMascota::all();
        return view('crearMascota', compact('especies', 'mascotas'));
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