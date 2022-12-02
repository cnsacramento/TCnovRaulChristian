<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

/**
 * @property integer $id
 * @property integer $id_tipo_intervencion
 * @property integer $id_mascota
 * @property integer $id_factura
 * @property string $asunto
 * @property string $descripcion
 * @property Veterinario[] $veterinarios
 * @property TipoIntervencion $tipoIntervencion
 * @property Factura $factura
 * @property Mascota $mascota
 * @property Reserva[] $reservas
 */
class Intervencion extends Model
{

    public $timestamps = false;
    /**
     * The table associated with the model.
     *
     * @var string
     */
    protected $table = 'intervencion';

    /**
     * @var array
     */
    protected $fillable = ['id_tipo_intervencion', 'id_mascota', 'id_factura', 'asunto', 'descripcion'];

    /**
     * @return \Illuminate\Database\Eloquent\Relations\BelongsToMany
     */
    public function veterinarios()
    {
        return $this->belongsToMany('App\Models\Veterinario', 'equipo_intervencion', 'id_intervencion', 'dni_veterinario');
    }

    /**
     * @return \Illuminate\Database\Eloquent\Relations\BelongsTo
     */
    public function tipoIntervencion()
    {
        return $this->belongsTo('App\Models\TipoIntervencion', 'id_tipo_intervencion');
    }

    /**
     * @return \Illuminate\Database\Eloquent\Relations\BelongsTo
     */
    public function factura()
    {
        return $this->belongsTo('App\Models\Factura', 'id_factura');
    }

    /**
     * @return \Illuminate\Database\Eloquent\Relations\BelongsTo
     */
    public function mascota()
    {
        return $this->belongsTo('App\Models\Mascota', 'id_mascota');
    }

    /**
     * @return \Illuminate\Database\Eloquent\Relations\HasMany
     */
    public function reservas()
    {
        return $this->hasMany('App\Models\Reserva', 'id_intervencion', 'id');
    }
}
