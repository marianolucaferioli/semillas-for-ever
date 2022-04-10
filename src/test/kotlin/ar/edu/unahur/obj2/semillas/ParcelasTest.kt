package ar.edu.unahur.obj2.semillas

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue

class ParcelasTest : DescribeSpec ({
    describe("Creación de parcelas") {
        val soja1 = Soja(1.1, 2010)
        val soja2 = Soja(1.1, 2010)
        val soja3 = Soja(1.1, 2010)
        val soja4 = Soja(1.1, 2010)

        val parcela = Parcela(20.0, 1.0, 10)

        parcela.agregarPlanta(soja1)
        parcela.agregarPlanta(soja2)
        parcela.agregarPlanta(soja3)
        parcela.agregarPlanta(soja4)

        it("Corroborar complicaciones") {
            parcela.tieneComplicaciones().shouldBeFalse()
        }

        it("Probamos con una planta mas") {
            parcela.puedePlantar().shouldBeFalse()
        }
    }
})

