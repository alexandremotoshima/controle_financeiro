package br.com.motoshima.controle_financeiro.extension

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

fun BigDecimal.formataParaBrasileiro() : String{
    return DecimalFormat
            .getCurrencyInstance(Locale("pt","br"))
            .format(this)

}