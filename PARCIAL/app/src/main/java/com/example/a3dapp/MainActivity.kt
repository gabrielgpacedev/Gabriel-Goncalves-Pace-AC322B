package com.example.a3dapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a3dapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MusicaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MusicaAdapter(RepertorioMock.musicas) { musica ->
            val intent = Intent(this, DetalhesActivity::class.java)
            intent.putExtra(DetalhesActivity.MUSICA_ID_KEY, musica.id)
            startActivity(intent)
        }
        binding.musicasRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.musicasRecyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        val musicas = RepertorioMock.musicas
        adapter.atualizar(musicas)
        binding.quantidadeTextView.text = musicas.size.toString()
        binding.quantidadeLabelTextView.text =
            resources.getQuantityString(R.plurals.musicas_cadastradas, musicas.size)
    }
}
