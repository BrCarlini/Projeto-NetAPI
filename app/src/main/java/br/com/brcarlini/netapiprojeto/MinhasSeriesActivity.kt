package br.com.brcarlini.netapiprojeto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import br.com.brcarlini.netapiprojeto.adapter.FilmeAdapter
import br.com.brcarlini.netapiprojeto.adapter.SerieAdapter
import br.com.brcarlini.netapiprojeto.api.SerieApi
import br.com.brcarlini.netapiprojeto.databinding.ActivityMeusFilmesBinding
import br.com.brcarlini.netapiprojeto.databinding.ActivityMinhasSeriesBinding
import br.com.brcarlini.netapiprojeto.model.SerieResposta
import kotlinx.coroutines.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class MinhasSeriesActivity : AppCompatActivity() {



    //915fb7bff4fd802cc4691a6427f995b3
    /*
    *
    * url base ---> https://api.themoviedb.org/3/
    * rota ou endpoint ---> tv/popular?api_key=915fb7bff4fd802cc4691a6427f995b3
    * */


    //Configuração do Retrofit
    private val serieApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory( GsonConverterFactory.create() )
            .build()
            .create(SerieApi::class.java)
    }

    private val TAG = "info_serie"
    private var serieAdapter: SerieAdapter? = null


    private val binding by lazy {
        ActivityMinhasSeriesBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.title = "Series"

        btnVoltarParaFilmes()

        // Instaciando a Classe do Adapter
        serieAdapter = SerieAdapter()

        // Atribuido o adapter ao RecyclerView
        binding.rvSerie.adapter = serieAdapter

        // Configurando o Gerenciador de Layout do RecyclerView
        binding.rvSerie.layoutManager = GridLayoutManager(this, 2)

    }


    // Para fazer o carregamento de dados
    override fun onStart() {
        super.onStart()
        recuperarSeriesPopulares()
    }



    private fun recuperarSeriesPopulares() {

        CoroutineScope( Dispatchers.IO ).launch {

            var retorno: Response<SerieResposta>? = null

            try {
                retorno = serieApi.recuperarSeriesPopulares()
            }catch (e: Exception){
                e.printStackTrace()
            }

            if(retorno != null){
                if(retorno.isSuccessful){

                    val serieResposta = retorno.body()
                    val listaSerie = serieResposta?.results

                    listaSerie?.forEach { serie ->
                        Log.i(TAG, "titulo: ${serie.name}")
                    }
                    withContext( Dispatchers.Main ){
                        serieAdapter?.adicionarListaSerie( listaSerie )
                    }


                }else{
                    Log.i(TAG, "erro: ${retorno.code()}")
                }
            }


        }
    }


    // Configuração do botão de voltar para Filmes, finalizando a Activity Serie
    private fun btnVoltarParaFilmes(){

        val botaoVoltar = binding.btnVoltarActFilmes

        botaoVoltar.setOnClickListener {
            finish()
        }

    }
}