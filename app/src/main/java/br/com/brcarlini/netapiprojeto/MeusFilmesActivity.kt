package br.com.brcarlini.netapiprojeto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.brcarlini.netapiprojeto.databinding.ActivityMeusFilmesBinding

class MeusFilmesActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMeusFilmesBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meus_filmes)
    }
}