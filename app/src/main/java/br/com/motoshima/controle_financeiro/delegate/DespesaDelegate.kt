package br.com.motoshima.controle_financeiro.delegate

import br.com.motoshima.controle_financeiro.model.Despesa

interface DespesaDelegate {
    fun delegate(despesa: Despesa)
}