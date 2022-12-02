<?php

namespace App\Http\Controllers;
use App\Models\CuentaVeterinario;

use Illuminate\Http\Request;

class LoginController extends Controller
{
    public function login(Request $request){
        $correo = $request->correo;
        $cuenta = CuentaVeterinario::find($correo);
        if($cuenta != null){
            if($cuenta->correo == $request->correo && password_verify($request->contrasenia, $cuenta->contrasenia)){
                session()->put("usuario", $cuenta);
                return view("panel_veterinario");
            }else{
                return view("index");
            }
        }else{
            return view("index");
        }
    }

    public function cerrarSesion(Request $request){
        session_reset();
        return view("index");
    }
}
