package br.com.motoshima.controle_financeiro.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import br.com.motoshima.controle_financeiro.R
import br.com.motoshima.controle_financeiro.delegate.DespesaDelegate
import br.com.motoshima.controle_financeiro.model.Despesa
import br.com.motoshima.controle_financeiro.ui.adapter.ListaDespesaAdapter
import br.com.motoshima.controle_financeiro.ui.dialog.AdicionaDespesaDialog
import br.com.motoshima.controle_financeiro.viewModel.listaDespesaViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal
import java.util.*

class listaDespesaActivity : AppCompatActivity() {

    lateinit var viewModel: listaDespesaViewModel
    var despesas: MutableList<Despesa> = mutableListOf()

    companion object {
        fun newInstance(viewModel: listaDespesaViewModel): listaDespesaActivity {
            val activity = listaDespesaActivity()
            activity.viewModel = viewModel
            return activity
        }
    }


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configuraListaDespesa()

        configuraFab()
    }

    private fun configuraFab() {
        lista_transacoes_adiciona_despesa
            .setOnClickListener{
                chamaDialogDeAdicao()
            }
    }

    private fun chamaDialogDeAdicao() {
        AdicionaDespesaDialog(window.decorView as ViewGroup, this)
            .chama(object : DespesaDelegate{
                override fun delegate(despesa: Despesa) {
                    adiciona(despesa)
                    menu.close(true)
                }
            })
    }

    private fun adiciona(despesa: Despesa) {
        despesas.add(despesa)
        atualiza()
    }

    private fun atualiza() {
        configuraListaDespesa()
    }

    private fun configuraListaDespesa() {
        val listaDespesaAdapter = ListaDespesaAdapter(despesas,this)
        lista_despesa_listview.adapter = ListaDespesaAdapter(despesas, this)
    }

    private fun despesasExemplo(): MutableList<Despesa> {
        return listOf(Despesa("Luz", BigDecimal(100.00), Calendar.getInstance(), false),
            Despesa("Internet", BigDecimal(150.00), Calendar.getInstance(), false),
            Despesa("Cartão de Crédito", BigDecimal(500.00), Calendar.getInstance(), true)
        ).toMutableList()
    }

    fun createViewModel(){
        return listaDespesaViewModel()
    }
}