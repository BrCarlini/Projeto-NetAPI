package br.com.brcarlini.netapiprojeto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import br.com.brcarlini.netapiprojeto.adapter.FilmeAdapter
import br.com.brcarlini.netapiprojeto.api.FilmeApi
import br.com.brcarlini.netapiprojeto.databinding.ActivityMeusFilmesBinding
import br.com.brcarlini.netapiprojeto.model.FilmeResposta
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class MeusFilmesActivity : AppCompatActivity() {

    //915fb7bff4fd802cc4691a6427f995b3
    /*
    * url base: https://api.themoviedb.org/3/
    * rota ou Endpoint: movie/popular?api_key=915fb7bff4fd802cc4691a6427f995b3&language=en-US&page=1
    * */


    private val filmeApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()) //Fazer a conversão para usarmos as nossas classes
            .build()
            .create( FilmeApi::class.java)
    }

    private val TAG = "info_filme"
    private var filmeAdapter: FilmeAdapter? = null

    private val binding by lazy {
        ActivityMeusFilmesBinding.inflate(layoutInflater)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.title = "Filmes"

        filmeAdapter = FilmeAdapter()
        binding.rvFilmes.adapter = filmeAdapter
        binding.rvFilmes.layoutManager = GridLayoutManager(this, 2)

        irParaActivitySerie()

    }

    //O ciclo de vida onStart serve para quando queremos Carregar dados entre outras coisas
    override fun onStart() {
        super.onStart()
        recuperarFilmesPopulares()
    }

    private fun recuperarFilmesPopulares() {

        // Código que vai ser executado sem atrapalhar a execução principal
        CoroutineScope(Dispatchers.IO).launch {


            var retorno: Response<FilmeResposta>? = null

            try {
                retorno = filmeApi.recuperarFilmesPopulares()
            }catch(e: Exception){
                e.printStackTrace()

            }

            if(retorno != null){
                if(retorno.isSuccessful){

                    val filmeResposta = retorno.body()
                    val listaFilmes = filmeResposta?.results

                    listaFilmes?.forEach { filme ->
                        Log.i(TAG, "titulo: ${filme.title}")
                    }
                    withContext(Dispatchers.Main){
                        filmeAdapter?.adicionarListaFilme( listaFilmes )
                    }

                }else{
                    Log.i(TAG, "erro: ${retorno.code()}")
                }
            }
        }

    }



    private fun irParaActivitySerie(){

        val botaoAvancar = binding.btnAvancarActSerie

        botaoAvancar.setOnClickListener {
            val intent = Intent(this, MinhasSeriesActivity::class.java)
            startActivity(intent)
        }

    }


}