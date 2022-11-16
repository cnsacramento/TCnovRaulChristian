<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

/**
 * @property string $dni
 * @property string $cuenta_veterinaria
 * @property integer $id_especialidad
 * @property string $nombre
 * @property string $apellidos
 * @property string $telefono
 * @property EquipoIntervencion $equipoIntervencion
 * @property CuentasVeterinario $cuentasVeterinario
 * @property EspecialidadesVeterinario $especialidadesVeterinario
 */
class Veterinario extends Model
{
    /**
     * The primary key for the model.
     * 
     * @var string
     */
    protected $primaryKey = 'dni';

    /**
     * The "type" of the auto-incrementing ID.
     * 
     * @var string
     */
    protected $keyType = 'string';

    /**
     * Indicates if the IDs are auto-incrementing.
     * 
     * @var bool
     */
    public $incrementing = false;

    /**
     * @var array
     */
    protected $fillable = ['cuenta_veterinaria', 'id_especialidad', 'nombre', 'apellidos', 'telefono'];

    /**
     * @return \Illuminate\Database\Eloquent\Relations\HasOne
     */
    public function equipoIntervencion()
    {
        return $this->hasOne('App\Models\EquipoIntervencion', 'dni_veterinario', 'dni');
    }

    /**
     * @return \Illuminate\Database\Eloquent\Relations\BelongsTo
     */
    public function cuentasVeterinario()
    {
        return $this->belongsTo('App\Models\CuentasVeterinario', 'cuenta_veterinaria', 'correo');
    }

    /**
     * @return \Illuminate\Database\Eloquent\Relations\BelongsTo
     */
    public function especialidadesVeterinario()
    {
        return $this->belongsTo('App\Models\EspecialidadesVeterinario', 'id_especialidad');
    }
}
