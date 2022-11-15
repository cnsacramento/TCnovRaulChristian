<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

/**
 * @property string $fecha
 * @property integer $id_fraccion_tiempo
 * @property FraccionTiempo $fraccionTiempo
 */
class Jornada extends Model
{
    /**
     * The table associated with the model.
     * 
     * @var string
     */
    protected $table = 'jornada';

    /**
     * The primary key for the model.
     * 
     * @var string
     */
    protected $primaryKey = 'fecha';

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
    protected $fillable = ['id_fraccion_tiempo'];

    /**
     * @return \Illuminate\Database\Eloquent\Relations\BelongsTo
     */
    public function fraccionTiempo()
    {
        return $this->belongsTo('App\Models\FraccionTiempo', 'id_fraccion_tiempo');
    }
}
