package br.com.motoshima.controle_financeiro.viewModel

import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import br.com.motoshima.controle_financeiro.model.Despesa

class listaDespesaViewModel {
    val listDespesa = ObservableArrayList<Despesa>()
    val loadingVisibility = ObservableBoolean(false)
    val message = ObservableField<String>()
}