<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

/**
 * @property integer $id
 * @property integer $id_intervencion
 * @property string $id_restriccion_dia
 * @property string $fecha_inicio
 * @property string $fecha_fin
 * @property Intervencion $intervencion
 * @property TipoRestriccionDia $tipoRestriccionDia
 */
class Reserva extends Model
{
    /**
     * The table associated with the model.
     *
     * @var string
     */
    protected $table = 'reserva';

    /**
     * @var array
     */
    protected $fillable = ['id_intervencion', 'id_restriccion_dia', 'fecha_inicio', 'fecha_fin'];

    /**
     * @return \Illuminate\Database\Eloquent\Relations\BelongsTo
     */
    public function intervencion()
    {
        return $this->belongsTo('App\Models\Intervencion', 'id_intervencion');
    }

    /**
     * @return \Illuminate\Database\Eloquent\Relations\BelongsTo
     */
    public function tipoRestriccionDium()
    {
        return $this->belongsTo('App\Models\TipoRestriccionDia', 'id_restriccion_dia', 'tipo');
    }
}
