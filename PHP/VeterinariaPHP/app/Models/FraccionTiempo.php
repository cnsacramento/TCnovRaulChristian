<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

/**
 * @property integer $id
 * @property integer $id_intervencion
 * @property string $hora_inicio
 * @property string $hora_fin
 * @property Intervencion $intervencion
 * @property Jornada[] $jornadas
 */
class FraccionTiempo extends Model
{
    /**
     * The table associated with the model.
     * 
     * @var string
     */
    protected $table = 'fraccion_tiempo';

    /**
     * Indicates if the IDs are auto-incrementing.
     * 
     * @var bool
     */
    public $incrementing = false;

    /**
     * @var array
     */
    protected $fillable = ['id_intervencion', 'hora_inicio', 'hora_fin'];

    /**
     * @return \Illuminate\Database\Eloquent\Relations\BelongsTo
     */
    public function intervencion()
    {
        return $this->belongsTo('App\Models\Intervencion', 'id_intervencion');
    }

    /**
     * @return \Illuminate\Database\Eloquent\Relations\HasMany
     */
    public function jornadas()
    {
        return $this->hasMany('App\Models\Jornada', 'id_fraccion_tiempo');
    }
}
