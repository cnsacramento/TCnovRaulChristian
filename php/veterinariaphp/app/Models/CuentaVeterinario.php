<?php

namespace App\Models;


use Illuminate\Foundation\Auth\User as Authenticatable;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Notifications\Notifiable;
use Laravel\Sanctum\HasApiTokens;

/**
 * @property string $correo
 * @property string $contrasenia
 * @property Veterinario[] $veterinarios
 */
class CuentaVeterinario extends Authenticatable
{

    use HasApiTokens, HasFactory, Notifiable;
    public $timestamps = false;
    /**
     * The table associated with the model.
     *
     * @var string
     */
    protected $table = 'cuenta_veterinario';

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
