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
 * @property Intervencion[] $intervencions
 * @property Cliente $cliente
 * @property EspecieMascota $especiemascota
 */
class Mascota extends Model
{
    /**
     * The table associated with the model.
     *
     * @var string
     */
    protected $table = 'mascota';

    /**
     * @var array
     */
    protected $fillable = ['dni_cliente', 'id_especie', 'nombre', 'fecha_nacimiento', 'peso'];

    /**
     * @return \Illuminate\Database\Eloquent\Relations\HasMany
     */
    public function intervencions()
    {
        return $this->hasMany('App\Models\Intervencion', 'id_mascota');
    }

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
    public function especieMascotum()
    {
        return $this->belongsTo('App\Models\EspecieMascota', 'id_especie');
    }

}
