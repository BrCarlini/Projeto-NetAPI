package br.com.brcarlini.netapiprojeto.model

data class FilmeResposta(
    val page: Int,
    val results: List<Filme>,
    val total_pages: Int,
    val total_results: Int
)