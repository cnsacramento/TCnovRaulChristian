<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

/**
 * @property integer $id
 * @property string $dni_cliente
 * @property integer $id_especie
 * @property string $nombre
 * @property string $fecha_nacimiento
 * @property float $peso
 * @property Cliente $cliente
 * @property EspeciesMascotum $especiesMascotum
 * @property Intervencione[] $intervenciones
 */
class Mascota extends Model
{
    /**
     * @var array
     */
    protected $fillable = ['dni_cliente', 'id_especie', 'nombre', 'fecha_nacimiento', 'peso'];

    /**
     * @return \Illuminate\Database\Eloquent\Relations\BelongsTo
     */
    public function cliente()
    {
        return $this->belongsTo('App\Models\Cliente', 'dni_cliente', 'dni');
    }

    /**
     * @return \Illuminate\Database\Eloquent\Relations\BelongsTo
     */
    public function especiesMascotum()
    {
        return $this->belongsTo('App\Models\EspeciesMascotum', 'id_especie');
    }

    /**
     * @return \Illuminate\Database\Eloquent\Relations\HasMany
     */
    public function intervenciones()
    {
        return $this->hasMany('App\Models\Intervencione', 'id_mascota');
    }
}
