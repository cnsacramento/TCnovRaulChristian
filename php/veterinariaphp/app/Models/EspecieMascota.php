<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

/**
 * @property integer $id
 * @property string $nombre
 * @property boolean $peligrosa
 * @property Mascota[] $mascotas
 */
class EspecieMascota extends Model
{
    /**
     * The table associated with the model.
     *
     * @var string
     */
    protected $table = 'especie_mascota';

    /**
     * @var array
     */
    protected $fillable = ['nombre', 'peligrosa'];

    /**
     * @return \Illuminate\Database\Eloquent\Relations\HasMany
     */
    public function mascotas()
    {
        return $this->hasMany('App\Models\Mascota', 'id_especie');
    }
}
