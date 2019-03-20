package br.com.motoshima.controle_financeiro.ui.dialog

import android.app.DatePickerDialog
import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.alura.financask.extension.converteParaCalendar
import br.com.motoshima.controle_financeiro.R
import br.com.motoshima.controle_financeiro.delegate.DespesaDelegate
import br.com.motoshima.controle_financeiro.extension.formataParaBrasileiro
import br.com.motoshima.controle_financeiro.model.Despesa
import kotlinx.android.synthetic.main.form_despesa.view.*
import java.math.BigDecimal
import java.util.*

class AdicionaDespesaDialog(private val viewGroup: ViewGroup,
                            private val context : Context) {

    private val viewCriada = criaLayout()
    private val campoValor = viewCriada.form_despesa_valor
    private val campoData = viewCriada.form_despesa_data
    private val campoNome = viewCriada.form_despesa_nome
    private val campoPago = viewCriada.form_despesa_pago


    private fun criaLayout(): View {
        return LayoutInflater.from(context)
            .inflate(R.layout.form_despesa,
                viewGroup,
                false)
    }

    fun chama(despesaDelegate: DespesaDelegate) {
        configuraCampoData()
        configuraFormulario(despesaDelegate)
    }

    private fun configuraFormulario(despesaDelegate: DespesaDelegate) {

        val titulo = "Despesa"

        AlertDialog.Builder(context)
            .setTitle(titulo)
            .setView(viewCriada)
            .setPositiveButton("Adicionar",
                { _, _ ->
                    val valorEmTexto = campoValor.text.toString()
                    val dataEmTexto = campoData.text.toString()
                    val nomeEmTexto = campoNome.text.toString()
                    val campoPagoBool = campoPago.isChecked

                    val valor = converteCampoValor(valorEmTexto)
                    val data = dataEmTexto.converteParaCalendar()

                    val despesaCriada = Despesa(nomeEmTexto, valor, data, campoPagoBool)

                    despesaDelegate.delegate(despesaCriada)
                })
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun converteCampoValor(valorEmTexto: String): BigDecimal {
        return try {
            BigDecimal(valorEmTexto)
        } catch (exception: NumberFormatException) {
            Toast.makeText(context,
                "Falha na conversão de valor",
                Toast.LENGTH_LONG)
                .show()
            BigDecimal.ZERO
        }
    }

    private fun configuraCampoData() {
        val hoje = Calendar.getInstance()

        val ano = hoje.get(Calendar.YEAR)
        val mes = hoje.get(Calendar.MONTH)
        val dia = hoje.get(Calendar.DAY_OF_MONTH)

        campoData.setText(hoje.formataParaBrasileiro())
        campoData.setOnClickListener {
            DatePickerDialog(context,
                { _, ano, mes, dia ->
                    val dataSelecionada = Calendar.getInstance()
                    dataSelecionada.set(ano, mes, dia)
                    campoData.setText(dataSelecionada.formataParaBrasileiro())
                }
                , ano, mes, dia)
                .show()
        }
    }
}
