package br.com.motoshima.controle_financeiro.model

import java.math.BigDecimal
import java.util.*

class Despesa(val nome : String,
              val valor : BigDecimal,
              val vencimento : Calendar,
              var pagamento : Boolean) {

}