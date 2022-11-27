<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

/**
 * @property integer $id
 * @property string $tipo
 * @property Intervencion[] $intervencions
 */
class TipoIntervencion extends Model
{

    public $timestamps = false;
    /**
     * The table associated with the model.
     *
     * @var string
     */
    protected $table = 'tipo_intervencion';

    /**
     * @var array
     */
    protected $fillable = ['tipo'];

    /**
     * @return \Illuminate\Database\Eloquent\Relations\HasMany
     */
    public function intervencions()
    {
        return $this->hasMany('App\Models\Intervencion', 'id_tipo_intervencion');
    }
}
