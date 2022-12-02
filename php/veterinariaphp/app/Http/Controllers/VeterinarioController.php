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
}
