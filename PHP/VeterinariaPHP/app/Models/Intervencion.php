<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

/**
 * @property integer $id
 * @property integer $id_tipo_intervencion
 * @property integer $id_mascota
 * @property integer $id_factura
 * @property integer $id_equipo_intervencion
 * @property string $asunto
 * @property string $descripcion
 * @property TiposIntervencion $tiposIntervencion
 * @property Factura $factura
 * @property EquipoIntervencion $equipoIntervencion
 * @property Mascota $mascota
 * @property Sesione[] $sesiones
 */
class Intervencion extends Model
{
    /**
     * The table associated with the model.
     * 
     * @var string
     */
    protected $table = 'intervenciones';

    /**
     * @var array
     */
    protected $fillable = ['id_tipo_intervencion', 'id_mascota', 'id_factura', 'id_equipo_intervencion', 'asunto', 'descripcion'];

    /**
     * @return \Illuminate\Database\Eloquent\Relations\BelongsTo
     */
    public function tiposIntervencion()
    {
        return $this->belongsTo('App\Models\TiposIntervencion', 'id_tipo_intervencion');
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
    public function equipoIntervencion()
    {
        return $this->belongsTo('App\Models\EquipoIntervencion', 'id_equipo_intervencion');
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
    public function sesiones()
    {
        return $this->hasMany('App\Models\Sesione', 'id_intervencion');
    }
}
