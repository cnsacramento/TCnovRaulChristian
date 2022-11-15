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
 * @property EquipoIntervencion[] $equipoIntervencions
 * @property CuentaVeterinarium $cuentaVeterinarium
 * @property EspecialidadVeterinario $especialidadVeterinario
 */
class Veterinario extends Model
{
    /**
     * The table associated with the model.
     * 
     * @var string
     */
    protected $table = 'veterinario';

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
     * @return \Illuminate\Database\Eloquent\Relations\HasMany
     */
    public function equipoIntervencions()
    {
        return $this->hasMany('App\Models\EquipoIntervencion', 'dni_veterinario', 'dni');
    }

    /**
     * @return \Illuminate\Database\Eloquent\Relations\BelongsTo
     */
    public function cuentaVeterinarium()
    {
        return $this->belongsTo('App\Models\CuentaVeterinarium', 'cuenta_veterinaria', 'correo');
    }

    /**
     * @return \Illuminate\Database\Eloquent\Relations\BelongsTo
     */
    public function especialidadVeterinario()
    {
        return $this->belongsTo('App\Models\EspecialidadVeterinario', 'id_especialidad');
    }
}
