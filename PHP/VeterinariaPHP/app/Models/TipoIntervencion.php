<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

/**
 * @property integer $id
 * @property string $tipo
 * @property Intervencione[] $intervenciones
 */
class TipoIntervencion extends Model
{
    /**
     * The table associated with the model.
     * 
     * @var string
     */
    protected $table = 'tipos_intervencion';

    /**
     * @var array
     */
    protected $fillable = ['tipo'];

    /**
     * @return \Illuminate\Database\Eloquent\Relations\HasMany
     */
    public function intervenciones()
    {
        return $this->hasMany('App\Models\Intervencione', 'id_tipo_intervencion');
    }
}
