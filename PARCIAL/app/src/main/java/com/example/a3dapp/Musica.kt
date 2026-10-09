package com.example.a3dapp


data class Musica(
    val id: Int,
    val titulo: String,
    val artista: String,
    val tom: String? = null,
    val duracaoEmSegundos: Int? = null,
    val genero: String? = null,
    val observacoes: String? = null,
    val ensaiada: Boolean = false
) {
    init {
        require(titulo.isNotBlank()) { "O título não pode ser vazio" }
        require(duracaoEmSegundos == null || duracaoEmSegundos > 0) { "A duração deve ser maior que zero" }
    }

    fun alternarEnsaiada(): Musica = copy(ensaiada = !ensaiada)
}

object RepertorioMock {
    var musicas: List<Musica> = listOf(
        Musica(
            id = 1,
            titulo = "Livin' on a Prayer",
            artista = "Bon Jovi",
            tom = "Em",
            duracaoEmSegundos = 249,
            genero = "Rock",
            observacoes = "Atenção à entrada do refrão."
        ),
        Musica(
            id = 2,
            titulo = "Sweet Child O' Mine",
            artista = "Guns N' Roses",
            tom = "D",
            duracaoEmSegundos = 356,
            genero = "Hard Rock",
            observacoes = "Solo de guitarra estendido no final.",
            ensaiada = true
        ),
        Musica(
            id = 3,
            titulo = "The Final Countdown",
            artista = "Europe",
            tom = "F#m",
            duracaoEmSegundos = 310,
            genero = "Rock"
        ),
        Musica(
            id = 4,
            titulo = "Jump",
            artista = "Van Halen",
            tom = "C",
            duracaoEmSegundos = 241,
            genero = "Hard Rock",
            observacoes = "Teclado abre a música.",
            ensaiada = true
        ),
        Musica(
            id = 5,
            titulo = "Rock You Like a Hurricane",
            artista = "Scorpions"
        )
    )
        private set

    fun buscarPorId(id: Int): Musica? = musicas.find { it.id == id }

    fun atualizar(musica: Musica) {
        musicas = musicas.map { if (it.id == musica.id) musica else it }
    }
}
