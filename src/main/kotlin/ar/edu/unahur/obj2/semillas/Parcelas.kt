package ar.edu.unahur.obj2.semillas

import kotlin.math.floor

abstract class Parcela(open var ancho: Double, open var largo: Double, open var horasDeSol: Int) {

    private var plantasQueTiene = mutableListOf<Planta>()

    fun agregarPlanta(planta: Planta) {
        if (this.puedePlantar() || horasDeSol - 2  <= planta.horasDeSolToleradas()) {
            plantasQueTiene.add(planta)
        } else {
            error("No se puede plantar mas plantitas!")
        }
    }

    fun puedePlantar(): Boolean {
        return plantasQueTiene.size < this.cantidadMaximaDePlantas()
    }

    fun superficie(): Double {
        return ancho * largo
    }

    fun cantidadMaximaDePlantas(): Int {

        return if (ancho > largo) {
            floor(superficie() / 5).toInt()
        } else {
            floor(superficie() / 3).toInt()
        }
    }

    fun tieneComplicaciones(): Boolean {
        return plantasQueTiene.any{planta -> planta.horasDeSolToleradas() < horasDeSol}
    }

    fun horasDeSol() : Int {
        return horasDeSol
    }

    fun plantasEnParcela() : List<Planta> {
        return plantasQueTiene
    }

    abstract fun seAsociaBien(planta : Planta) : Boolean

    fun esIdealParaPlanta(planta : Planta) : Boolean {
        return planta.esParcelaIdeal(this)
    }
}

class ParcelaEcologica(override var ancho : Double, override var largo: Double, override var horasDeSol: Int) : Parcela(ancho, largo, horasDeSol) {

    override fun seAsociaBien(planta : Planta): Boolean {
        return  (!this.tieneComplicaciones() && this.esIdealParaPlanta(planta))
    }
}

class ParcelaIndustrial(override var ancho : Double, override var largo: Double, override var horasDeSol: Int) : Parcela(ancho, largo, horasDeSol) {

    override fun seAsociaBien(planta: Planta) : Boolean {
        return (this.plantasEnParcela().size <= 2 && planta.esFuerte())
    }
}












