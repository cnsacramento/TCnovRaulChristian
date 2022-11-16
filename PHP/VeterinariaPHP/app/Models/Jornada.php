<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

/**
 * @property integer $id
 * @property string $hora_inicio
 * @property string $hora_fin
 * @property Sesione[] $sesiones
 */
class Jornada extends Model
{
    /**
     * Indicates if the IDs are auto-incrementing.
     * 
     * @var bool
     */
    public $incrementing = false;

    /**
     * @var array
     */
    protected $fillable = ['hora_inicio', 'hora_fin'];

    /**
     * @return \Illuminate\Database\Eloquent\Relations\HasMany
     */
    public function sesiones()
    {
        return $this->hasMany('App\Models\Sesione', 'id_jornada');
    }
}
