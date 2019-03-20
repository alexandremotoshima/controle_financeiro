package br.com.motoshima.controle_financeiro.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.motoshima.controle_financeiro.R
import br.com.motoshima.controle_financeiro.extension.formataParaBrasileiro
import br.com.motoshima.controle_financeiro.model.Despesa
import kotlinx.android.synthetic.main.despesa_item.view.*
import android.widget.Checkable


class ListaDespesaAdapter(private val despesas: List<Despesa>,
                          private val context : Context) : BaseAdapter() {

    override fun getCount(): Int {
        return despesas.count()
    }

    override fun getItemId(position: Int): Long {
       return 0
    }

    override fun getItem(position: Int): Any {
        return despesas[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val viewCriada = LayoutInflater.from(context)
            .inflate(R.layout.despesa_item, parent, false)

        val despesa = despesas[position]
        viewCriada.despesa_nome.text = despesa.nome
        (viewCriada.despesa_pagamento as Checkable).isChecked = despesa.pagamento
        viewCriada.despesa_valor.text = despesa.valor.formataParaBrasileiro()
        viewCriada.despesa_vencimento.text = despesa.vencimento.formataParaBrasileiro()
        val icone = R.drawable.icone_transacao_item_despesa
        viewCriada.despesa_icone
            .setBackgroundResource(icone)


        return viewCriada
    }
}

