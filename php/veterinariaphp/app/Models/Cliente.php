<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

/**
 * @property string $dni
 * @property string $nombre
 * @property string $apellidos
 * @property string $direccion
 * @property string $correo
 * @property string $telefono
 * @property Mascota[] $mascotas
 */
class Cliente extends Model
{
    /**
     * The table associated with the model.
     *
     * @var string
     */
    protected $table = 'cliente';

    /**
     * The primary key for the model.
     *
     * @var string
     */
    protected $primaryKey = 'dni';

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
    protected $fillable = ['nombre', 'apellidos', 'direccion', 'correo', 'telefono'];

    /**
     * @return \Illuminate\Database\Eloquent\Relations\HasMany
     */
    public function mascotas()
    {
        return $this->hasMany('App\Models\Mascota', 'dni_cliente', 'dni');
    }
}
