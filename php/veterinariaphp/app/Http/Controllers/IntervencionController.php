<?php

namespace App\Http\Controllers;

use App\Models\Factura;
use App\Models\Intervencion;
use App\Models\Mascota;
use App\Models\Reserva;
use App\Models\TipoIntervencion;
use App\Models\TipoRestriccionDia;
use App\Models\Veterinario;
use Doctrine\DBAL\Exception;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class IntervencionController extends Controller
{

    public function index() {

        $intervenciones = Intervencion::all();
        $tiposIntervenciones = TipoIntervencion::all();

        return view('intervenciones', compact('intervenciones', 'tiposIntervenciones'));
    }

    public function crear(Request $request) {

        switch($request->fechaIntervencion) {

            case "Crear":
                $bloqueos = $this->desbloquear("bloqueoFecha");
                return view("fechaIntervencion", compact('bloqueos'));

            case "Escoger fecha":

                $fechaSeleccionada = $request->fecha;
                session(["fecha" => $fechaSeleccionada]);
                $bloqueos = $this->desbloquear("bloqueoHora");
                $fecha = $request->fecha;
                $strFecha = strtotime($fecha);
                $fechaApertura = date("Y-m-d H:i:s", mktime( 8, 0, 0, date('n', $strFecha), date('j', $strFecha), date('Y', $strFecha) ));
                $fechaCierre = date("Y-m-d H:i:s", mktime( 14, 0, 0, date('n', $strFecha), date('j', $strFecha), date('Y', $strFecha) ));

                $reservas = $this->encontrarCitasDeUnDia($fechaApertura, $fechaCierre);
                $sesiones = [
                    8 => false,
                    9 => false,
                    10 => false,
                    11 => false,
                    12 => false,
                    13 => false,
                    14 => false,
                ];

                foreach($reservas as $reserva) {
                    $fechaInicio = $reserva->fecha_inicio;
                    $strFechaInicio = strtotime($fechaInicio);
                    $fechaFin = $reserva->fecha_fin;
                    $strFechaFin = strtotime($fechaFin);
                    $cantidadSesiones = date('G', $strFechaFin) - date('G', $strFechaInicio);
                    $hora = date('G', $strFechaInicio);

                    for($i = 0; $i < $cantidadSesiones; $i++) {
                        $sesiones[$hora] = true;
                        $hora++;
                    }
                }

                session(['sesiones' => $sesiones]);


                $horasLibres = [];

                foreach($sesiones as $key => $value) {
                    if(!$value) {
                        array_push($horasLibres, $key);
                    }
                }

                session(['horasLibres' => $horasLibres]);

                return view('fechaIntervencion', compact('bloqueos', 'horasLibres'));

            case "Escoger hora":
                $hora = $request->hora;
                $sesiones = session('sesiones');

                $cantidadSesiones = 1;
                $sesionesDisponibles = [];

                foreach($sesiones as $key => $value) {

                    if($key > $hora) {
                        if($value) {
                            break;
                        } else {
                            array_push($sesionesDisponibles, $cantidadSesiones++);
                        }
                    }
                }

                session(['hora' => $hora]);
                $bloqueos = $this->desbloquear('bloqueoSesiones');

                return view('fechaIntervencion', compact('sesionesDisponibles', 'bloqueos'));


            case "Escoger sesiones":

                $numeroSesiones = $request->numeroSesiones;
                session(['numeroSesiones' => $numeroSesiones]);
                $bloqueos = $this->desbloquear('bloqueoDatos');
                $tiposIntervenciones = TipoIntervencion::all();

                return view('fechaIntervencion', compact('tiposIntervenciones', 'bloqueos'));

            case "Continuar":

                $asunto = $request->asunto;
                $descripcion = $request->descripcion;
                $tipoIntervencion = $request->tipointervencion;
                $idMascota = $request->idmascota;
                $equipo = $request->equipo;

                session(['asunto' => $asunto]);
                session(['descripcion' => $descripcion]);
                session(['tipoIntervencion' => $tipoIntervencion]);
                session(['idMascota'=> $idMascota]);
                session(['equipo'=> $equipo]);

                $bloqueos = $this->desbloquear("bloqueoFactura");

                return view("fechaIntervencion", compact('bloqueos'));

            case "Crear factura":



                try{
                    DB::beginTransaction();
                    $intervencion = new Intervencion();

                    $asunto = $request->session()->get('asunto');
                    $descripcion = $request->session()->get('descripcion');

                    $idTipoIntervencion = $request->session()->get('tipoIntervencion');
                    $tipoIntervencion = TipoIntervencion::find($idTipoIntervencion);

                    $idMascota = $request->session()->get('idMascota');
                    $mascota = Mascota::find($idMascota);

                    $equipo = $request->session()->get('equipo');
                    $equipoExplode = explode(",", $equipo);

                    $veterinarios = [];

                    foreach($equipoExplode as $veterinario) {
                        array_push($veterinarios, $veterinario);
                    }

                    $factura = new Factura();
                    $factura->fecha = $request->fechafactura;
                    $factura->coste = $request->coste;
                    $factura->detalles = $request->detalles;
                    $factura->save();

                    $tipoRestriccionDia = TipoRestriccionDia::find("laboral");


                    $reserva = new Reserva();

                    $fecha = $request->session()->get('fecha');
                    $hora = $request->session()->get('hora');

                    $fechaInicio = $fecha." ".$hora;

                    $numeroSesiones = $request->session()->get('numeroSesiones');
                    $horaFin = $hora + $numeroSesiones;

                    $fechaFin = $fecha." ".$horaFin;

                    $reserva->fecha_inicio = $fechaInicio;
                    $reserva->fecha_fin = $fechaFin;
                    $reserva->tipoRestriccionDium()->associate($tipoRestriccionDia);

                    $intervencion->asunto = $asunto;
                    $intervencion->descripcion = $descripcion;
                    $intervencion->tipoIntervencion()->associate($tipoIntervencion);
                    $intervencion->factura()->associate($factura);
                    $intervencion->mascota()->associate($mascota);
                    //$intervencion->reservas()->save($reserva); // No funciona
                    $intervencion->save();
                    $intervencion->refresh();

                    $reserva->intervencion()->associate($intervencion);
                    $reserva->save();

                    foreach($veterinarios as $veterinario) {
                        $veterinarioEncontrado = Veterinario::find($veterinario);
                        $intervencion->veterinarios()->save($veterinarioEncontrado);
                    }

                    // TODO eliminar de sesion los datos

                    $tiposIntervenciones = TipoIntervencion::all();

                    DB::commit();

                    return view('intervenciones', compact('tiposIntervenciones'));
                }catch(Exception $e){
                    DB::rollBack();
                }


            default:
                break;

        }


        $tiposIntervenciones = TipoIntervencion::all();

        return view('intervenciones', compact('tiposIntervenciones'));
    }


    public function opciones(Request $request) {

        $id = $request->id;
        $intervencion = Intervencion::find($id);

        $intervenciones = collect([$intervencion]);
        $tiposIntervenciones = TipoIntervencion::all();

        return view('intervenciones', compact('intervencion','intervenciones','tiposIntervenciones'));
    }


    public function crearTipoIntervencion(Request $request) {

        $tipo = $request->tipo;

        $tipoIntervencion = new TipoIntervencion();
        $tipoIntervencion->tipo = $tipo;
        $tipoIntervencion->save();
        $tipoIntervencion->refresh();

        $tiposIntervenciones = TipoIntervencion::all();

        return view('intervenciones', compact('tiposIntervenciones'));
    }


    public function editarTipoIntervencion(Request $request) {

        $id = $request->id;
        $tipo = $request->tipo;

        $tipoIntervencion = TipoIntervencion::find($id);
        $tipoIntervencion->tipo = $tipo;
        $tipoIntervencion->save();
        $tipoIntervencion->refresh();

        $tiposIntervenciones = TipoIntervencion::all();

        return view('intervenciones', compact('tiposIntervenciones'));
    }

    public function eliminarTipoIntervencion(Request $request) {

        $id = $request->id;
        $tipoIntervencion = TipoIntervencion::find($id);
        $tipoIntervencion->delete();

        $tiposIntervenciones = TipoIntervencion::all();
        return view('intervenciones', compact('tiposIntervenciones'));
    }

    public function mostrarTipoIntervencion(Request $request) {

        $id = $request->id;
        $tipoIntervencion = TipoIntervencion::find($id);

        $tiposIntervenciones = collect([$tipoIntervencion]);

        return view('intervenciones', compact('tiposIntervenciones'));
    }

    public function mostrarTiposIntervenciones() {

        $tiposIntervenciones = TipoIntervencion::all();

        return view('intervenciones', compact('tiposIntervenciones'));

    }

    public function opcionesTipoIntervencion(Request $request) {

        $id = $request->id;
        $tipoIntervencionOpciones = TipoIntervencion::find($id);

        $tiposIntervenciones = collect([$tipoIntervencionOpciones]);

        return view('intervenciones', compact('tipoIntervencionOpciones','tiposIntervenciones'));
    }


    private function desbloquear(string $bloqueo): array {

        switch ($bloqueo) {
            case "bloqueoFecha":
                return $bloqueos = [
                    "bloqueoFecha" => false,
                    "bloqueoHora" => true,
                    "bloqueoSesiones" => true,
                    "bloqueoDatos" => true,
                    "bloqueoFactura" => true,
                ];
            case "bloqueoHora":
                return $bloqueos = [
                    "bloqueoFecha" => true,
                    "bloqueoHora" => false,
                    "bloqueoSesiones" => true,
                    "bloqueoDatos" => true,
                    "bloqueoFactura" => true,
                ];
            case "bloqueoSesiones":
                return $bloqueos = [
                    "bloqueoFecha" => true,
                    "bloqueoHora" => true,
                    "bloqueoSesiones" => false,
                    "bloqueoDatos" => true,
                    "bloqueoFactura" => true,
                ];
            case "bloqueoDatos":
                return $bloqueos = [
                    "bloqueoFecha" => true,
                    "bloqueoHora" => true,
                    "bloqueoSesiones" => true,
                    "bloqueoDatos" => false,
                    "bloqueoFactura" => true,
                ];
            case "bloqueoFactura":
                return $bloqueos = [
                    "bloqueoFecha" => true,
                    "bloqueoHora" => true,
                    "bloqueoSesiones" => true,
                    "bloqueoDatos" => true,
                    "bloqueoFactura" => false,
                ];
            default:
                return array();
        }
    }

    private function encontrarCitasDeUnDia(string $fechaApertura, string $fechaCierre) {

        return Reserva::whereBetween('fecha_inicio', [$fechaApertura, $fechaCierre])->get();

    }
}
