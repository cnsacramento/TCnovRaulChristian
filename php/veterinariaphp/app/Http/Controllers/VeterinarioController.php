<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Veterinario;
use App\Models\EspecialidadVeterinario;


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



}
