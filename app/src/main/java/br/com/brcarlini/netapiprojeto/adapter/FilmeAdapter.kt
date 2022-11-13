package br.com.brcarlini.netapiprojeto.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.brcarlini.netapiprojeto.databinding.ItemFilmeBinding
import br.com.brcarlini.netapiprojeto.model.Filme
import com.squareup.picasso.Picasso

class FilmeAdapter: RecyclerView.Adapter<FilmeAdapter.FilmeViewHolder>() {

    private var listaFilmes: List<Filme>? = null

    fun adicionarListaFilme(lista: List<Filme>?){
        listaFilmes = lista
        notifyDataSetChanged()
    }

    class FilmeViewHolder(itemView: ItemFilmeBinding): RecyclerView.ViewHolder(itemView.root){

        private val binding: ItemFilmeBinding

        init {
            binding = itemView
        }

        fun bind(filme: Filme){
            binding.textTituloFilme.text = filme.title
            binding.textDataLancFilme.text = "Data de estreia: ${filme.release_date}"

            //https://image.tmdb.org/t/p/w500/8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg
            val urlBaseImagem = "https://image.tmdb.org/t/p/"
            val tamanhoImagem = "w780"
            val nomeImagem = filme.backdrop_path


            /*
            *
            * https://image.tmdb.org/t/p/ -> w780 -> /y5Z0WesTjvn59jP6yo459eUsbli.jpg
            *
            * */
            val link = urlBaseImagem + tamanhoImagem + nomeImagem

            Picasso.get()
                .load( link )
                .into(binding.imageFilme)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmeViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val itemFilmeBinding = ItemFilmeBinding.inflate(layoutInflater, parent, false)
        return FilmeViewHolder(itemFilmeBinding)

    }

    override fun onBindViewHolder(holder: FilmeViewHolder, position: Int) {

        val filme = listaFilmes?.get(position)
        if(filme != null){
            holder.bind(filme)
        }

    }

    override fun getItemCount(): Int {
        if(listaFilmes != null){
            return listaFilmes!!.size
        }

        return 0
    }


}