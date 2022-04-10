package ar.edu.unahur.obj2.semillas

class Parcela(open var ancho: Double, open var largo: Double, var horasDeSol: Int) {

    var plantasQueTiene = mutableListOf<Planta>()

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
        var cantidad = 0

        if (ancho > largo) {
            cantidad = Math.floor(this.superficie() / 5).toInt()
        } else {
            cantidad = Math.floor(this.superficie() / 3).toInt()
        }
        return cantidad
    }

    fun tieneComplicaciones(): Boolean {
        return plantasQueTiene.any{planta -> planta.horasDeSolToleradas() < horasDeSol}
    }
}