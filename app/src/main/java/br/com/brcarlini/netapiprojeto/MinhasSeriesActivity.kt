package br.com.brcarlini.netapiprojeto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.brcarlini.netapiprojeto.databinding.ActivityMeusFilmesBinding
import br.com.brcarlini.netapiprojeto.databinding.ActivityMinhasSeriesBinding

class MinhasSeriesActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMinhasSeriesBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.title = "Series"

        btnVoltarParaFilmes()
    }



    private fun btnVoltarParaFilmes(){

        val botaoVoltar = binding.btnVoltarActFilmes

        botaoVoltar.setOnClickListener {
            finish()
        }

    }
}