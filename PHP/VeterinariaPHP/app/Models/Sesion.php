<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

/**
 * @property integer $id_jornada
 * @property integer $id_intervencion
 * @property string $hora_inicio
 * @property string $hora_fin
 * @property Intervencione $intervencione
 * @property Jornada $jornada
 */
class Sesion extends Model
{
    /**
     * The table associated with the model.
     * 
     * @var string
     */
    protected $table = 'sesiones';

    /**
     * @var array
     */
    protected $fillable = ['hora_inicio', 'hora_fin'];

    /**
     * @return \Illuminate\Database\Eloquent\Relations\BelongsTo
     */
    public function intervencione()
    {
        return $this->belongsTo('App\Models\Intervencione', 'id_intervencion');
    }

    /**
     * @return \Illuminate\Database\Eloquent\Relations\BelongsTo
     */
    public function jornada()
    {
        return $this->belongsTo('App\Models\Jornada', 'id_jornada');
    }
}
