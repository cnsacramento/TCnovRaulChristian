<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

/**
 * @property integer $id
 * @property string $fecha
 * @property float $coste
 * @property string $detalles
 * @property Intervencion[] $intervencions
 */
class Factura extends Model
{

    public $timestamps = false;
    /**
     * The table associated with the model.
     *
     * @var string
     */
    protected $table = 'factura';

    /**
     * @var array
     */
    protected $fillable = ['fecha', 'coste', 'detalles'];

    /**
     * @return \Illuminate\Database\Eloquent\Relations\HasMany
     */
    public function intervencions()
    {
        return $this->hasMany('App\Models\Intervencion', 'id_factura');
    }
}
