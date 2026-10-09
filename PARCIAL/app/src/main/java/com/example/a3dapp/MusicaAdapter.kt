package com.example.a3dapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a3dapp.databinding.ItemMusicaBinding

class MusicaAdapter(
    private var musicas: List<Musica>,
    private val onMusicaClick: (Musica) -> Unit
) : RecyclerView.Adapter<MusicaAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemMusicaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMusicaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val musica = musicas[position]
        val context = holder.binding.root.context

        holder.binding.tituloTextView.text = musica.titulo
        holder.binding.artistaTextView.text = musica.artista

        if (musica.ensaiada) {
            holder.binding.statusImageView.setImageResource(R.drawable.ic_check_circulo)
            holder.binding.statusImageView.setColorFilter(ContextCompat.getColor(context, R.color.destaque))
            holder.binding.statusImageView.contentDescription = context.getString(R.string.cd_ensaiada)
        } else {
            holder.binding.statusImageView.setImageResource(R.drawable.ic_chevron_direita)
            holder.binding.statusImageView.setColorFilter(ContextCompat.getColor(context, R.color.texto_secundario))
            holder.binding.statusImageView.contentDescription = context.getString(R.string.cd_abrir_detalhes)
        }

        holder.binding.root.setOnClickListener { onMusicaClick(musica) }
    }

    override fun getItemCount(): Int = musicas.size

    fun atualizar(novasMusicas: List<Musica>) {
        musicas = novasMusicas
        notifyDataSetChanged()
    }
}
