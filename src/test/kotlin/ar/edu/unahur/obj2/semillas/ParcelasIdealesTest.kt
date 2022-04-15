package ar.edu.unahur.obj2.semillas

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue

class ParcelasIdealesTest : DescribeSpec ({
    describe("Creación de parcelas ideales") {

        val soja = Soja(0.75, 2010)
        val menta = Menta(2.3, 2015)
        val peperina = Peperina(0.3, 2019)
        val sojaTransgenica = SojaTransgenica(2.5, 2021)
        val quinoa = Quinoa(1.9, 2004, 5.0)

        val parcela1 = ParcelaIndustrial(20.0, 1.0, 10)
        val parcela2 = ParcelaIndustrial(12.8,12.8, 8)
        val parcela3 = ParcelaEcologica(100.0,50.0, 12)
        val parcela4 = ParcelaEcologica(2.0, 2.0, 4)


        it ("Corroborar parcela ideal de menta y peperina") {
            menta.esParcelaIdeal(parcela1).shouldBeTrue()
            peperina.esParcelaIdeal(parcela2).shouldBeTrue()
            menta.esParcelaIdeal(parcela4).shouldBeFalse()
        }

        it ("Corroborar parcela ideal de soja común") {
            soja.esParcelaIdeal(parcela2).shouldBeTrue()
            soja.esParcelaIdeal(parcela3).shouldBeFalse()
        }

        it ("Corroborar parcela ideal de soja transgénica") {
            sojaTransgenica.esParcelaIdeal(parcela4).shouldBeTrue()
            // --> es exactamente 1.3, pero al redondear hacia abajo da 1.
            sojaTransgenica.esParcelaIdeal(parcela3).shouldBeFalse()
        }

        it ("corroborar parcela ideal de quinoa") {

            parcela4.agregarPlanta(sojaTransgenica)
            parcela1.agregarPlanta(peperina)

            quinoa.esParcelaIdeal(parcela4).shouldBeFalse()
            quinoa.esParcelaIdeal(parcela1).shouldBeTrue()
        }

    }
})