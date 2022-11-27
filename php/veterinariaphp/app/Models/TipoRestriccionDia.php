<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

/**
 * @property string $tipo
 * @property string $hora_apertura
 * @property string $hora_cierre
 * @property integer $intervalo_tiempo
 * @property Reserva[] $reservas
 */
class TipoRestriccionDia extends Model
{
    /**
     * The table associated with the model.
     * 
     * @var string
     */
    protected $table = 'tipo_restriccion_dia';

    /**
     * The primary key for the model.
     * 
     * @var string
     */
    protected $primaryKey = 'tipo';

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
    protected $fillable = ['hora_apertura', 'hora_cierre', 'intervalo_tiempo'];

    /**
     * @return \Illuminate\Database\Eloquent\Relations\HasMany
     */
    public function reservas()
    {
        return $this->hasMany('App\Models\Reserva', 'id_restriccion_dia', 'tipo');
    }
}
