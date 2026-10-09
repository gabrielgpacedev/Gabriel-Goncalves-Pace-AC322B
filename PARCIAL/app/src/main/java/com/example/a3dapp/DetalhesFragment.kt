package com.example.a3dapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a3dapp.databinding.FragmentDetalhesBinding
import com.example.a3dapp.databinding.ItemInfoBinding

class DetalhesFragment : Fragment() {
    companion object {
        private const val ARG_MUSICA_ID = "musicaId"

        fun newInstance(musicaId: Int) = DetalhesFragment().apply {
            arguments = Bundle().apply { putInt(ARG_MUSICA_ID, musicaId) }
        }
    }

    private var _binding: FragmentDetalhesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetalhesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val musicaId = requireArguments().getInt(ARG_MUSICA_ID)
        val musica = RepertorioMock.buscarPorId(musicaId) ?: return

        binding.tituloTextView.text = musica.titulo
        binding.artistaTextView.text = musica.artista

        val naoInformado = getString(R.string.valor_nao_informado)
        preencherInfo(binding.tomInfo, R.string.tom, musica.tom ?: naoInformado)
        preencherInfo(binding.duracaoInfo, R.string.duracao, formatarDuracao(musica.duracaoEmSegundos) ?: naoInformado)
        preencherInfo(binding.generoInfo, R.string.genero, musica.genero ?: naoInformado)
        binding.observacoesTextView.text = musica.observacoes ?: getString(R.string.sem_observacoes)

        binding.ensaiadaButton.setOnClickListener {
            val atual = RepertorioMock.buscarPorId(musicaId) ?: return@setOnClickListener
            val atualizada = atual.alternarEnsaiada()
            RepertorioMock.atualizar(atualizada)
            atualizarStatus(atualizada)
        }

        atualizarStatus(musica)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun atualizarStatus(musica: Musica) {
        val status = if (musica.ensaiada) R.string.status_ensaiada else R.string.status_pendente
        preencherInfo(binding.statusInfo, R.string.status, getString(status))
        binding.ensaiadaButton.setText(
            if (musica.ensaiada) R.string.marcar_como_pendente else R.string.marcar_como_ensaiada
        )
    }

    private fun preencherInfo(info: ItemInfoBinding, rotulo: Int, valor: String) {
        info.rotuloTextView.setText(rotulo)
        info.valorTextView.text = valor
    }

    private fun formatarDuracao(segundos: Int?): String? =
        segundos?.let { getString(R.string.duracao_formato, it / 60, it % 60) }
}
