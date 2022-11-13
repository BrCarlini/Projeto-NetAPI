package br.com.brcarlini.netapiprojeto.model

data class SerieResposta(
    val page: Int,
    val results: List<Serie>,
    val total_pages: Int,
    val total_results: Int
)