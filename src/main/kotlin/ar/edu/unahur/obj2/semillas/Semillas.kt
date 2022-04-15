package ar.edu.unahur.obj2.semillas

fun main() {

}

abstract class Planta(open var altura: Double, open val anioSemilla: Int) {
    /*
    ASIGNAR VARIABLES
    1)
    init {
        this.altura = altura
        this.anioSemilla = anioSemilla
    }
    2)
    var alturaPlanta: Double = altura
    var anioSemillaPlanta: Int = anioSemilla
    */

    fun alturaPlanta() : Double {
        return altura
    }

    fun anioObtencionSemilla() : Int {
        return anioSemilla
    }

    open fun horasDeSolToleradas() : Int {
        return 7
    }

    open fun esFuerte() : Boolean {
        return this.horasDeSolToleradas() > 10
    }

    open fun daNuevasSemillas() : Boolean {
        return this.esFuerte()
    }

    abstract fun espacioQueOcupa() : Double

    abstract fun esParcelaIdeal(parcela : Parcela) : Boolean

    fun seAsociaBien(parcela : Parcela) : Boolean {
        return parcela.seAsociaBien(this)
    }

}

open class Menta(override var altura: Double, override val anioSemilla: Int) : Planta(altura, anioSemilla) {

    override fun daNuevasSemillas() : Boolean {
        return super.daNuevasSemillas() || altura > 0.4
    }

    override fun espacioQueOcupa() : Double {
        return altura + 1
    }

    override fun esParcelaIdeal(parcela : Parcela) : Boolean {
        return parcela.superficie() > 6
    }
}



open class Soja(override var altura: Double, override val anioSemilla: Int) : Planta(altura, anioSemilla) {

    override fun horasDeSolToleradas(): Int {
        return if (altura < 0.5) {6} else if (altura in 0.5..1.0) {8} else {12}
    }

    override fun daNuevasSemillas(): Boolean {
        return super.daNuevasSemillas() || (anioSemilla > 2007 && altura in 0.75..0.9)
    }

    override fun espacioQueOcupa(): Double {
        return altura / 2
    }

    override fun esParcelaIdeal(parcela : Parcela) : Boolean {
        return parcela.horasDeSol() == this.horasDeSolToleradas()
    }
}

class Quinoa(override var altura: Double, override val anioSemilla: Int, private var espacioQueOcupa: Double) : Planta(altura, anioSemilla) {

    override fun espacioQueOcupa(): Double {
        return espacioQueOcupa
    }

    override fun horasDeSolToleradas(): Int {
        return if (espacioQueOcupa < 0.3) {10} else (super.horasDeSolToleradas())
    }

    override fun daNuevasSemillas(): Boolean {
        return super.daNuevasSemillas() || anioSemilla in 2001..2008
    }

    override fun esParcelaIdeal(parcela: Parcela): Boolean {
        return parcela.plantasEnParcela().all{ planta -> planta.alturaPlanta() <= 1.5 }
    }
}

// 2* Transgénico

class SojaTransgenica(override var altura: Double, override val anioSemilla: Int) : Soja(altura, anioSemilla) {

    override fun daNuevasSemillas(): Boolean {
        return false
    }

    override fun esParcelaIdeal(parcela: Parcela): Boolean {
        return parcela.cantidadMaximaDePlantas() == 1
    }
}

class Peperina(override var altura: Double, override val anioSemilla: Int) : Menta(altura, anioSemilla) {

    override fun espacioQueOcupa(): Double {
        return super.espacioQueOcupa() * 2
    }

    // parcela ideal -> hereda de Menta
}



