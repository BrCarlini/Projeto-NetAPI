package br.com.brcarlini.netapiprojeto.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.brcarlini.netapiprojeto.databinding.ItemSerieBinding
import br.com.brcarlini.netapiprojeto.model.Serie
import com.squareup.picasso.Picasso

class SerieAdapter: RecyclerView.Adapter<SerieAdapter.SerieViewHolder>() {

    private var listaSerie: List<Serie>? = null

    fun adicionarListaSerie( lista: List<Serie>? ){
        listaSerie = lista
        notifyDataSetChanged()
    }


    class SerieViewHolder(itemView: ItemSerieBinding): RecyclerView.ViewHolder(itemView.root){

        private val binding: ItemSerieBinding

        init {
            binding = itemView
        }

        fun bind(serie: Serie){
            binding.textTituloSerie.text = serie.name
            binding.textDataLancSerie.text = "Data de estreia: ${serie.first_air_date}"

            val urlBaseImagem = "https://image.tmdb.org/t/p/"
            val tamanhoImagem = "w780"
            val nomeImagem = serie.backdrop_path


            /*
            *
            * https://api.themoviedb.org/3/ -> w780 -> /y5Z0WesTjvn59jP6yo459eUsbli.jpg
            *
            * */


            val link = urlBaseImagem + tamanhoImagem + nomeImagem

            Picasso.get()
                .load( link )
                .into(binding.imageSerie)


        }




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SerieViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val itemSerieBinding = ItemSerieBinding.inflate(layoutInflater, parent, false)

        return SerieViewHolder(itemSerieBinding)

    }

    override fun onBindViewHolder(holder: SerieViewHolder, position: Int) {

        val serie = listaSerie?.get(position)
        if(serie != null){
            holder.bind(serie)
        }

    }

    override fun getItemCount(): Int {
        if(listaSerie != null){
            return listaSerie!!.size
        }

        return 0
    }
}