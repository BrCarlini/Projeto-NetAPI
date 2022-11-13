package br.com.brcarlini.netapiprojeto.api

import br.com.brcarlini.netapiprojeto.model.SerieResposta
import retrofit2.Response
import retrofit2.http.GET

interface SerieApi {

    @GET("tv/popular?api_key=915fb7bff4fd802cc4691a6427f995b3")
    suspend fun recuperarSeriesPopulares(): Response<SerieResposta>

}