<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Veterinario;
use App\Models\EspecialidadVeterinario;
use App\Models\CuentaVeterinario;
use Illuminate\Support\Facades\DB;



class VeterinarioController extends Controller
{
    public function index(){
        $veterinarios = Veterinario::all();
        $especialidades = EspecialidadVeterinario::all();
        $especialidadAsignada ="";
        return view('veterinario', compact('veterinarios', 'especialidades', 'especialidadAsignada'));
    }

    //ESPECIALIDAD

    public function crearEspecialidad(Request $request){
        $nombre = $request->nombre;
        
        if(!Empty($nombre)){
            $especialidad = new EspecialidadVeterinario();
            $especialidad->nombre = $nombre;
            $especialidad->save();
        }

        $veterinarios = Veterinario::all();
        $especialidades = EspecialidadVeterinario::all();
        $especialidadAsignada ="";
        return view('veterinario', compact('veterinarios', 'especialidades', 'especialidadAsignada'));
    }

    //editar

    public function editarEspecialidad(Request $request){

        $id = $request->id;

        if(!Empty($id)){
            $especialidad = EspecialidadVeterinario::find($id);
            $especialidad->nombre = $request->nombre;
            $especialidad->update();

        }
        $veterinarios = Veterinario::all();
        $especialidades = EspecialidadVeterinario::all();
        $especialidadAsignada ="";
        return view('veterinario', compact('veterinarios', 'especialidades', 'especialidadAsignada'));
        
    }

    public function rellenarCampos(Request $request){
        $id = $request->especialidad;

        if(!Empty($id)){
            $especialidad = EspecialidadVeterinario::find($id);
            $veterinarios = Veterinario::all();
            $especialidades = EspecialidadVeterinario::all();
            $especialidadAsignada ="";
            return view('veterinario', compact('veterinarios', 'especialidades', 'especialidadAsignada', 'especialidad'));
        }else{
            $veterinarios = Veterinario::all();
            $especialidades = EspecialidadVeterinario::all();
            $especialidadAsignada ="";
            return view('veterinario', compact('veterinarios', 'especialidades', 'especialidadAsignada'));
        }
        
    }

    //borrar

    public function borrarEspecialidad(Request $request){
        $id = $request->especialidad;

        if(!Empty($id)){
            $especialidad = EspecialidadVeterinario::find($id);
            $especialidad->delete();
        }
        $veterinarios = Veterinario::all();
        $especialidades = EspecialidadVeterinario::all();
        $especialidadAsignada ="";
        return view('veterinario', compact('veterinarios', 'especialidades', 'especialidadAsignada'));
    }

    //asignar

    public function asignarEspecialidad(Request $request){
        $veterinarios = Veterinario::all();
        $especialidades = EspecialidadVeterinario::all();
        $especialidadAsignada = $request->especialidad;
        return view('veterinario', compact('veterinarios', 'especialidades', 'especialidadAsignada'));
    }

    //VETERINARIO

    public function crearVeterinario(Request $request){
        $dni = $request->dni;

        try
        {
            if(Veterinario::find($dni) == null){

                DB::beginTransaction();
                $cuenta = new CuentaVeterinario();
                $cuenta->correo = $request->correo;
                $cuenta->contrasenia = password_hash($request->contrasenia, PASSWORD_BCRYPT);
                $cuenta->save();

                $veterinario = new Veterinario();
                $veterinario->dni = $dni;
                $veterinario->nombre = $request->nombre;
                $veterinario->apellidos = $request->apellidos;
                $veterinario->telefono = $request->telefono;
                $especialidad = EspecialidadVeterinario::find($request->especialidad);
                $veterinario->especialidadVeterinario()->associate($especialidad);
                $veterinario->cuentaVeterinario()->associate($cuenta);
                $veterinario->save();

                $veterinario->refresh();
                $cuenta->refresh();
                DB::commit();
            }
        }
        catch (Exception $e)
        {
            DB::rollBack();
        }

        $veterinarios = Veterinario::all();
        $especialidades = EspecialidadVeterinario::all();
        $especialidadAsignada ="";
        return view('veterinario', compact('veterinarios', 'especialidades', 'especialidadAsignada'));
    }

    //editar

    public function editarVeterinario(Request $request){

        $dni = $request->dni;

        if(Veterinario::find($dni) != null){
            $veterinario = Veterinario::find($dni);
            $veterinario->dni = $dni;
            $veterinario->nombre = $request->nombre;
            $veterinario->apellidos = $request->apellidos;
            $veterinario->telefono = $request->telefono;
            $especialidad = EspecialidadVeterinario::find($request->especialidad);
            $veterinario->especialidadVeterinario()->associate($especialidad);
            $veterinario->update();
        }

        $veterinarios = Veterinario::all();
        $especialidades = EspecialidadVeterinario::all();
        $especialidadAsignada ="";
        return view('veterinario', compact('veterinarios', 'especialidades', 'especialidadAsignada'));
        
    }

    public function rellenarCamposVeterinario(Request $request){
        $dni = $request->dni;

        if(Veterinario::find($dni) != null){
            $veterinario = Veterinario::find($dni);
            $veterinarios = Veterinario::all();
            $especialidades = EspecialidadVeterinario::all();
            $especialidadAsignada ="";
            return view('veterinario', compact('veterinarios', 'especialidades', 'especialidadAsignada', 'veterinario'));
        }else{
            $veterinarios = Veterinario::all();
            $especialidades = EspecialidadVeterinario::all();
            $especialidadAsignada ="";
            return view('veterinario', compact('veterinarios', 'especialidades', 'especialidadAsignada'));
        }
        
    }

    //borrar

    public function borrarVeterinario(Request $request){
        $dni = $request->dni;

        if(Veterinario::find($dni) != null){
            $veterinario = Veterinario::find($dni);
            $cuentaVeterinario = CuentaVeterinario::find($veterinario->cuentaVeterinario);
            $veterinario->delete();
            $cuentaVeterinario->delete();
        }
        $veterinarios = Veterinario::all();
        $especialidades = EspecialidadVeterinario::all();
        $especialidadAsignada ="";
        return view('veterinario', compact('veterinarios', 'especialidades', 'especialidadAsignada'));
    }


    //find

    public function findByNombre(Request $request){
        $nombre = $request->nombre;

        if(!Empty($nombre)){
            $veterinarios = Veterinario::where('nombre', 'like', "%$nombre%")->get();
        }else{
            $veterinarios = Veterinario::all();
        }
        $especialidades = EspecialidadVeterinario::all();
        $especialidadAsignada ="";
        return view('veterinario', compact('veterinarios', 'especialidades', 'especialidadAsignada'));
    }

}
