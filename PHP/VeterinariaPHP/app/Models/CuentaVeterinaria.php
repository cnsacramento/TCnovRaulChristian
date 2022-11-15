<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

/**
 * @property string $correo
 * @property string $contrasenia
 * @property Veterinario[] $veterinarios
 */
class CuentaVeterinaria extends Model
{
    /**
     * The table associated with the model.
     * 
     * @var string
     */
    protected $table = 'cuenta_veterinaria';

    /**
     * The primary key for the model.
     * 
     * @var string
     */
    protected $primaryKey = 'correo';

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
    protected $fillable = ['contrasenia'];

    /**
     * @return \Illuminate\Database\Eloquent\Relations\HasMany
     */
    public function veterinarios()
    {
        return $this->hasMany('App\Models\Veterinario', 'cuenta_veterinaria', 'correo');
    }
}
