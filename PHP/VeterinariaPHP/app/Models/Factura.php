<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

/**
 * @property integer $id
 * @property string $fecha
 * @property float $coste
 * @property string $detalles
 * @property Intervencione[] $intervenciones
 */
class Factura extends Model
{
    /**
     * @var array
     */
    protected $fillable = ['fecha', 'coste', 'detalles'];

    /**
     * @return \Illuminate\Database\Eloquent\Relations\HasMany
     */
    public function intervenciones()
    {
        return $this->hasMany('App\Models\Intervencione', 'id_factura');
    }
}
