<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

/**
 * @property integer $id
 * @property string $nombre
 * @property Veterinario[] $veterinarios
 */
class EspecialidadVeterinario extends Model
{

    public $timestamps = false;
    
    /**
     * The table associated with the model.
     * 
     * @var string
     */
    protected $table = 'especialidad_veterinario';

    /**
     * @var array
     */
    protected $fillable = ['nombre'];

    /**
     * @return \Illuminate\Database\Eloquent\Relations\HasMany
     */
    public function veterinarios()
    {
        return $this->hasMany('App\Models\Veterinario', 'id_especialidad');
    }
}
