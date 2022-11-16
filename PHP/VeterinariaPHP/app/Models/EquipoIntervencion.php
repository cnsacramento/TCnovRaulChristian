<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

/**
 * @property integer $id
 * @property string $dni_veterinario
 * @property Intervencione[] $intervenciones
 * @property Veterinario $veterinario
 */
class EquipoIntervencion extends Model
{
    /**
     * The table associated with the model.
     * 
     * @var string
     */
    protected $table = 'equipo_intervencion';

    /**
     * Indicates if the IDs are auto-incrementing.
     * 
     * @var bool
     */
    public $incrementing = false;

    /**
     * @var array
     */
    protected $fillable = ['dni_veterinario'];

    /**
     * @return \Illuminate\Database\Eloquent\Relations\HasMany
     */
    public function intervenciones()
    {
        return $this->hasMany('App\Models\Intervencione', 'id_equipo_intervencion');
    }

    /**
     * @return \Illuminate\Database\Eloquent\Relations\BelongsTo
     */
    public function veterinario()
    {
        return $this->belongsTo('App\Models\Veterinario', 'dni_veterinario', 'dni');
    }
}
