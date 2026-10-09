package com.example.a3dapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.a3dapp.databinding.ActivityDetalhesBinding

class DetalhesActivity : AppCompatActivity() {
    companion object {
        const val MUSICA_ID_KEY = "musicaId"
    }

    private lateinit var binding: ActivityDetalhesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetalhesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.detalhesToolbar.setNavigationOnClickListener { finish() }

        val musicaId = intent.getIntExtra(MUSICA_ID_KEY, -1)
        if (RepertorioMock.buscarPorId(musicaId) == null) {
            Toast.makeText(this, R.string.musica_nao_encontrada, Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.detalhes_fragment_container, DetalhesFragment.newInstance(musicaId))
                .commit()
        }
    }
}
