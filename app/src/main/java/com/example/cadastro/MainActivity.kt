package com.example.cadastro

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cadastro.databinding.ActivityMainBinding
import com.example.cadastro.domain.Formulario

class MainActivity : AppCompatActivity() {
    private val activityMainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        with (activityMainBinding) {
            val estados = arrayOf(
                "Acre", "Alagoas", "Amapá", "Amazonas", "Bahia", "Ceará", "Espírito Santo",
                "Goiás", "Maranhão", "Mato Grosso", "Mato Grosso do Sul", "Minas Gerais",
                "Pará", "Paraíba", "Paraná", "Pernambuco", "Piauí", "Rio de Janeiro",
                "Rio Grande do Norte", "Rio Grande do Sul", "Rondônia", "Roraima",
                "Santa Catarina", "São Paulo", "Sergipe", "Tocantins"
            )
            estadoSp.adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_dropdown_item, estados)

            val salvarBt = salvarBt
            salvarBt.setOnClickListener {
                val nome = nomeEt.text.toString()
                val telefone = telefoneEt.text.toString()
                val email = emailEt.text.toString()
                val recebeEmails = recebeEmailsCb.isChecked
                val sexoId = findViewById<RadioButton>(sexoRg.checkedRadioButtonId)
                val sexo = if (sexoId == null) "" else sexoId.text.toString()
                val cidade = cidadeEt.text.toString()
                val estado = estadoSp.selectedItem.toString()

                val valoresCadastro = arrayOf(nome,telefone, email, recebeEmails, sexo, cidade, estado)

                if (valoresCadastro.contains("")) {
                    val valoresVazios = mutableListOf<String>()
                    valoresCadastro.forEachIndexed { index, valor ->
                        if (valor == "") {
                            val campo = when (index) {
                                0 -> "Nome"
                                1 -> "Telefone"
                                2 -> "E-mail"
                                4 -> "Sexo"
                                5 -> "Cidade"
                                6 -> "UF"
                                else -> "Campo $index"
                            }
                            valoresVazios.add(campo)
                        }
                    }
                    Toast.makeText(this@MainActivity, "Campo(s) ${valoresVazios.joinToString(", ")} em branco!", Toast.LENGTH_SHORT).show() }
                else {
                    val formulario = Formulario(nome, telefone, email, recebeEmails, sexo, cidade, estado)
                    Toast.makeText(this@MainActivity, formulario.toString(), Toast.LENGTH_SHORT).show()
                }
            }

            limparBt.setOnClickListener {
                nomeEt.setText("")
                telefoneEt.setText("")
                emailEt.setText("")
                recebeEmailsCb.isChecked = false
                sexoRg.clearCheck()
                cidadeEt.setText("")
                estadoSp.setSelection(0)
            }
        }
    }

}
