val novJogo = "1. Novo Jogo"
val gravJogo = "2. Gravar Jogo"
val lerJogo = "3. Ler Jogo"
val sair = "0. Sair\n"
val nomeFic = "Introduza o nome do ficheiro (ex: jogo.txt)"
val balaoRed = "\u001B[31m\u03D9\u001B[0m"
val balaoblue = "\u001B[34m\u03D9\u001B[0m"
val colinv = "Coluna invalida"
val letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

fun validaTabuleiro(numLinhas: Int, numColunas: Int): Boolean
{
    when{
        (numLinhas == 5 && numColunas == 6) -> return true
        (numLinhas == 6 && numColunas == 7) -> return true
        (numLinhas == 7 && numColunas == 8) -> return true
        else -> return false
    }
}

fun processaColuna(numColunas: Int, coluna: String?): Int?
{
    var count = 0

    if (coluna == null)
    {
        return null
    }

    while ( count < numColunas )
    {
        if (letras[count].toString() == coluna)
        {
            return count
        }
        count++
    }
    return null
}

fun nomeValido(nome: String): Boolean
{
    if ( nome.length !in 3..12 )
    {
        return false
    }

    var count = 0
    while (count < nome.length)
    {
        if (nome[count] == ' ')
        {
            return false
        }
        count++
    }
    return true
}

fun criaTopoTabuleiro(numColunas: Int): String
{
    var count = 1
    var topoTabuleiro = "╔"
    while (count < numColunas)
    {
        topoTabuleiro += "════"
        count++
    }
    topoTabuleiro += "═══╗"
    return topoTabuleiro
}

fun criaLegendaHorizontal(numColunas: Int): String
{
    var count = 1
    var finalTabuleiro = "  A"
    var letras = 'A'
    while (count < numColunas)
    {
        letras = (letras + 1)
        finalTabuleiro = finalTabuleiro + " | " + letras
        count++
    }
    finalTabuleiro += "  "
    return finalTabuleiro
}

fun criaTabuleiro(tabuleiro: Array<Array<String?>>, mostraLegenda: Boolean? = true): String
{
    var tabuleiroDesenhado = ""

    tabuleiroDesenhado += criaTopoTabuleiro(tabuleiro[0].size) + "\n"

    for (linha in 0..tabuleiro.size - 1)
    {
        tabuleiroDesenhado += "║"

        for (coluna in 0..tabuleiro[0].size - 1)
        {
            if (coluna < tabuleiro[linha].size - 1)
            {
                when (tabuleiro[linha][coluna])
                {
                    balaoRed -> tabuleiroDesenhado += " $balaoRed |"
                    balaoblue -> tabuleiroDesenhado += " $balaoblue |"
                    else -> tabuleiroDesenhado += "   |"
                }
            } else {
                when (tabuleiro[linha][coluna])
                {
                    balaoRed -> tabuleiroDesenhado += " $balaoRed ║"
                    balaoblue -> tabuleiroDesenhado += " $balaoblue ║"
                    else -> tabuleiroDesenhado += "   ║"
                }
            }
        }
        if (linha != tabuleiro.size-1)
        {
            tabuleiroDesenhado += "\n"
        }
    }
    if (mostraLegenda == true)
    {
        tabuleiroDesenhado += "\n"+criaLegendaHorizontal(tabuleiro[0].size)
    }
    return tabuleiroDesenhado
}

fun criaTabuleiroVazio(numLinhas : Int , numColunas: Int):Array<Array<String?>>
{
    var tabuleiro:Array<Array<String?>> = emptyArray()
    for (n in 1..numLinhas)
    {
        tabuleiro+= arrayOf(arrayOfNulls(numColunas))
    }
    return tabuleiro
}

fun contaBaloesLinha( tabuleiro:Array<Array<String?>>, linha: Int):Int
{
    var contador = 0
    var count1 = 0

    while (count1<tabuleiro[0].size)
    {
        if (tabuleiro[linha][count1] != null)
        {
            contador++
        }
        count1++
    }
    return contador
}

fun contaBaloesColuna( tabuleiro: Array<Array<String?>>, coluna: Int):Int
{
    var contador = 0
    var count1 = 0

    while (count1<tabuleiro.size)
    {
        if (tabuleiro[count1][coluna] != null)
        {
            contador++
        }
        count1++
    }
    return contador
}

fun colocaBalao(tabuleiro: Array<Array<String?>>, coluna: Int, humano: Boolean):Boolean{
    var posicao=0

    if (coluna>tabuleiro.size){
        return false
    } else
        while (posicao<tabuleiro.size){
            if (tabuleiro[posicao][coluna]==null){
                if (humano==true){
                    tabuleiro[posicao][coluna]=balaoRed
                }
                if (humano==false){
                    tabuleiro[posicao][coluna]=balaoblue
                }
                return true
            }else
                posicao++
        }
    return false
}

fun jogadaNormalComputador(tabuleiro: Array<Array<String?>>):Int
{
    for (linha in 0..tabuleiro.size-1)
    {
        for (coluna in 0..tabuleiro[0].size-1)
        {
            if (tabuleiro[linha][coluna] == null)
            {
                return coluna
            }
        }
    }
    return -1
}

fun eVitoriaHorizontal(tabuleiro: Array<Array<String?>>):Boolean{
    for (linha in 0..tabuleiro.size-1)
    {
        for (coluna in 0..tabuleiro[linha].size-4)
        {
            val tabu = tabuleiro[linha][coluna]
            if (tabu != null &&
                tabu == tabuleiro[linha][coluna + 1] &&
                tabu == tabuleiro[linha][coluna + 2] &&
                tabu == tabuleiro[linha][coluna + 3] )
            {
                return true
            }
        }
    }
    return false
}

fun eVitoriaVertical(tabuleiro: Array<Array<String?>>):Boolean{
    for (coluna in 0..tabuleiro[0].size-1)
    {
        for (linha in 0..tabuleiro.size-4)
        {
            val tabu = tabuleiro[linha][coluna]
            if (tabu != null  &&
                tabu == tabuleiro[linha+1][coluna] &&
                tabu == tabuleiro[linha+2][coluna] &&
                tabu == tabuleiro[linha+3][coluna])
            {
                return true
            }
        }
    }
    return false
}

fun eVitoriaDiagonal(tabuleiro: Array<Array<String?>>):Boolean
{
    if(diagonalparaDireita(tabuleiro) || diagonalparaEsquerda(tabuleiro))
    {
        return true
    }
    return false
}

fun diagonalparaDireita (tabuleiro: Array<Array<String?>>):Boolean      // Função Secundaria  da eVitoriaDiagonal
{
    for(linha in 0..tabuleiro.size-4)
    {
        for (coluna in 0..tabuleiro[linha].size-4)
        {
            val tabu = tabuleiro[linha][coluna]
            if (tabu != null &&
                tabu == tabuleiro[linha+1][coluna+1] &&
                tabu == tabuleiro[linha+2][coluna+2] &&
                tabu == tabuleiro[linha+3][coluna+3])
            {
                return true
            }
        }
    }
    return false
}

fun diagonalparaEsquerda(tabuleiro: Array<Array<String?>>): Boolean {
    for (linha in 0..tabuleiro.size - 4)
    {
        for (coluna in 3..tabuleiro[0].size-1)
        {
            val tabu = tabuleiro[linha][coluna]
            if (tabu != null &&
                tabu == tabuleiro[linha + 1][coluna - 1] &&
                tabu == tabuleiro[linha + 2][coluna - 2] &&
                tabu == tabuleiro[linha + 3][coluna - 3])
            {
                return true
            }
        }
    }
    return false
}

fun ganhouJogo(tabuleiro: Array<Array<String?>>):Boolean{


    return eVitoriaVertical(tabuleiro) || eVitoriaHorizontal(tabuleiro) || eVitoriaDiagonal(tabuleiro)
}

fun eEmpate(tabuleiro: Array<Array<String?>>):Boolean{
    val ultimalinha = tabuleiro.size-1
    for (coluna in 0..tabuleiro[0].size-1)
    {
        if (tabuleiro[ultimalinha][coluna] == null) return false
    }
    return true
}

fun explodeBalao(tabuleiro:Array<Array<String?>>, coordenadas: Pair<Int,Int>):Boolean
{
    val linhaExplodir = coordenadas.first
    val colunaExplodir = coordenadas.second

    if  (colunaExplodir > tabuleiro[0].size-1 || colunaExplodir < 0  || linhaExplodir > tabuleiro.size-1 || linhaExplodir < 0) {
        return false
    }

    if (tabuleiro[linhaExplodir][colunaExplodir] == null) {
        return false
    }

    tabuleiro[linhaExplodir][colunaExplodir] = null
    for (linha in linhaExplodir until tabuleiro.size - 1)
    {
        tabuleiro[linha][colunaExplodir] = tabuleiro[linha+1][colunaExplodir]
    }
    tabuleiro[tabuleiro.size-1][colunaExplodir] = null
    return true
}

fun jogadaExplodirComputador(tabuleiro: Array<Array<String?>>): Pair<Int, Int> {

    for (linha in tabuleiro.indices) {
        var count = 0
        var balao = -1
        for (coluna in tabuleiro[0].indices) {
            if (tabuleiro[linha][coluna] == balaoRed) {
                if (count == 0) balao = coluna
                count++
                if (count == 3) {
                    return Pair(linha, balao)
                }
            } else {
                count = 0
                balao = -1
            }
        }
    }


    for (coluna in tabuleiro[0].indices) {
        var count = 0
        var balao = -1
        for (linha in tabuleiro.indices) {
            if (tabuleiro[linha][coluna] == balaoRed) {
                if (count == 0) balao = linha
                count++
                if (count == 3) {
                    return Pair(balao, coluna)
                }
            } else {
                count = 0
                balao = -1
            }
        }
    }


    var baloes = -1
    var coluna1 = 0


    for (coluna in tabuleiro[0].indices) {
        val numBaloes = contaBaloesColuna(tabuleiro, coluna)
        if (numBaloes >= baloes) {
            baloes = numBaloes
            coluna1 = coluna
        }
    }


    for (linha in tabuleiro.indices) {
        if (tabuleiro[linha][coluna1] != null) {
            return Pair(linha, coluna1)
        }
    }

    return Pair(0, coluna1)
}


fun leJogo(nomeFicheiro: String): Pair<String, Array<Array<String?>>> {
    val file = java.io.File(nomeFicheiro)
    val linhas = file.readLines()
    val nomePlayer = linhas[0]
    val numLinhas = linhas.size - 1
    val numColunas = if (numLinhas > 0) {
        linhas[1].split(",").size
    } else {
        0
    }
    val tabuleiro = criaTabuleiroVazio(numLinhas, numColunas)
    for (i in 0 until numLinhas) {
        val linha = linhas[i + 1]
        val elementos = linha.split(",")
        for (j in 0 until numColunas) {
            when (elementos[j]) {
                "H" -> tabuleiro[i][j] = balaoRed
                "C" -> tabuleiro[i][j] = balaoblue
                else -> tabuleiro[i][j] = null
            }
        }
    }
    return Pair(nomePlayer, tabuleiro)
}

fun gravaJogo(nomeFicheiro: String, tabuleiro: Array<Array<String?>>, nomePlayer: String) {
    val file = java.io.File(nomeFicheiro)

    file.writeText("$nomePlayer\n")

    for (linha in tabuleiro) {
        val linhaTexto = linha.joinToString(separator = ",") { coluna ->
            when (coluna) {
                balaoRed -> "H"
                balaoblue -> "C"
                else -> ""
            }
        }
        file.appendText("$linhaTexto\n")
    }
}

fun menu(): String {
    var tabuleiro1: Array<Array<String?>> = arrayOf()
    var nomePlayer = ""

    println("\nBem-vindo ao jogo \"4 Baloes em Linha\"!\n")
    println(novJogo)
    println(gravJogo)
    println(lerJogo)
    println(sair)

    while (true) {
        val opcao = readln().toIntOrNull()
        if (opcao == 1) {
            val (novoTabuleiro, inputNome) = inicioDoJogo()
            tabuleiro1 = novoTabuleiro
            nomePlayer = inputNome
            println()
            println(novJogo)
            println(gravJogo)
            println(lerJogo)
            println(sair)
        } else if (opcao == 2) {
            if (tabuleiro1.isEmpty()) {
                println("Funcionalidade Gravar nao esta disponivel")
            } else {
                println(nomeFic)
                val nomeFicheiro = readln()
                gravaJogo(nomeFicheiro, tabuleiro1, nomePlayer)
                println("Tabuleiro ${tabuleiro1.size}x${tabuleiro1[0].size} gravado com sucesso")
            }
        } else if (opcao == 3) {
            println(nomeFic)
            val nomeFicheiro = readln()
            val (nome, tabuleiro) = leJogo(nomeFicheiro)
            println("Tabuleiro ${tabuleiro.size}x${tabuleiro[0].size} lido com sucesso!")
            val resultado = inicioDoJogo(tabuleiro, nome)
            tabuleiro1 = resultado.first
            nomePlayer = resultado.second
            //println("\nBem-vindo ao jogo \"4 Baloes em Linha\"!\n")
            println("\n1. Novo Jogo")
            println(gravJogo)
            println(lerJogo)
            println(sair)
        } else if (opcao == 0) {
            println("A sair...")
            return "Sair"
        } else {
            println("Opcao invalida. Por favor, tente novamente.")
        }
    }
}

fun main() {
    do {
        val opcao = menu()
    } while (opcao != "Sair")
}

fun tabuleiro(): Pair<Int, Int> {
    var linhas: Int?
    var coluna: Int?
    do {
        do {
            println("Numero de linhas:")
            linhas = readln().toIntOrNull()?:-1
            if (linhas !in 1..Int.MAX_VALUE) {
                println("Numero invalido")
            }
        } while (linhas !in 1..Int.MAX_VALUE)

        do {
            println("Numero de colunas:")
            coluna = readln().toIntOrNull()?:-1
            if (coluna !in 1..Int.MAX_VALUE) {
                println("Numero invalido")
            }
        } while (coluna !in 1..Int.MAX_VALUE)

        val linhas1 = linhas!!
        val coluna1 = coluna!!
        if (!validaTabuleiro(linhas1, coluna1)) {
            println("Tamanho do tabuleiro invalido")
        }
    } while (!validaTabuleiro(linhas!!, coluna!!))
    return Pair(linhas, coluna)
}


fun inicioDoJogo(tabuleiroGuardado: Array<Array<String?>>? = null, nomeJogadorGuardado: String? = null): Pair<Array<Array<String?>>, String> {

    val (linhas, colunas) = if (tabuleiroGuardado == null) {
        tabuleiro()
    } else {
        Pair(tabuleiroGuardado.size, tabuleiroGuardado[0].size)
    }

    val tabuleiro = tabuleiroGuardado ?: criaTabuleiroVazio(linhas, colunas)
    var nomePlayer = nomeJogadorGuardado ?: ""

    while (!nomeValido(nomePlayer)) {
        println("Nome do jogador 1:")
        nomePlayer = readln()
        if (!nomeValido(nomePlayer)) {
            println("Nome de jogador invalido")
        }
    }

    val ultimaLetra = ('A' + colunas - 1).toString()

    println(criaTabuleiro(tabuleiro, true))
    println("\n$nomePlayer: $balaoRed\nTabuleiro ${linhas}X$colunas")

    do {
        print("Coluna? (A..$ultimaLetra):\n")
        val jogada = readln()

        val colSug = sugestaoJogadaNormalHumano(tabuleiro)

        if (jogada == "?"){
            if (colSug == null){
                println("Nao existe uma sugestao de jogada")
            } else {
                println("Sugestao de jogada na coluna: ${letras[colSug]}")
            }
        }

        if (jogada == "Sair") {
            return Pair(tabuleiro, nomePlayer)
        }

        if (jogada == "Gravar") {
            println(nomeFic)
            val nomeFicheiro = readln()
            gravaJogo(nomeFicheiro, tabuleiro, nomePlayer)
            println("Tabuleiro ${tabuleiro.size}x${tabuleiro[0].size} gravado com sucesso")
            return Pair(tabuleiro, nomePlayer)
        }

        val (jogadaValida, jogadorExplodiu) = processaJogadaJogador(tabuleiro, jogada,nomePlayer, colunas)
        if (jogadaValida) {
            println(criaTabuleiro(tabuleiro, true))

            if (ditaResultado(tabuleiro, nomePlayer)) {
                return Pair(tabuleiro, nomePlayer)
            }


            processaJogadaComputador(tabuleiro, linhas, colunas, jogadorExplodiu)
            println(criaTabuleiro(tabuleiro, true))

            if (ditaResultado(tabuleiro, "Computador")) {
                return Pair(tabuleiro, nomePlayer)
            }

            println("\n$nomePlayer: $balaoRed\nTabuleiro ${linhas}X$colunas")
        }
    } while (true)
}

fun processaJogadaJogador(tabuleiro: Array<Array<String?>>, jogada: String, nomePlayer: String, colunas: Int): Pair<Boolean, Boolean> {
    val palavras = jogada.split(" ")
    if (palavras.size == 2 && palavras[0] == "Explodir") {
        var totalBaloes = 0
        for (linha in tabuleiro) {
            for (balao in linha) {
                if (balao != null) {
                    totalBaloes++
                }
            }
        }

        if (totalBaloes < 2) {
            println("Funcionalidade Explodir nao esta disponivel")
            return Pair(false, false)
        }

        val coluna = palavras[1]
        val colIndex = processaColuna(colunas, coluna) ?: -1

        if (colIndex >= 0 && contaBaloesColuna(tabuleiro, colIndex) == 0) {
            println("Coluna vazia")
            return Pair(false, false)
        }

        val explodiu = explodeBalao(tabuleiro, Pair(0, processaColuna(colunas, coluna) ?: -1))
        if (explodiu) {
            println("Balao $coluna explodido!")
            return Pair(true, true)
        } else {
            println(colinv)
            return Pair(false, false)
        }
    }

    if (jogada == "?") {
        return Pair(false, false)
    }


    val indiceColuna = processaColuna(colunas, jogada)
    if (indiceColuna != null) {
        if (!colocaBalao(tabuleiro, indiceColuna, true)) {
            println(colinv)
            return Pair(false, false)
        }
        print("Coluna escolhida: ")
        print(jogada)
        println()
        return Pair(true, false)
    }

    println(colinv)
    return Pair(false, false)
}

fun processaJogadaComputador(tabuleiro: Array<Array<String?>>, linhas: Int, colunas: Int, jogadorExplodiu: Boolean = false) {
    if (jogadorExplodiu) {
        println()
        println("Prima enter para continuar. O computador ira agora explodir um dos seus baloes")
        readln()

        val coordenadas = jogadaExplodirComputador(tabuleiro)
        if (explodeBalao(tabuleiro, coordenadas)) {
            println("Balao ${('A' + coordenadas.second)},${coordenadas.first + 1} explodido pelo Computador!")
        }
        return
    }

    val colunaComputador = jogadaNormalComputador(tabuleiro)
    if (colunaComputador >= 0) {
        if (colocaBalao(tabuleiro, colunaComputador, false)) {
            println("\nComputador: $balaoblue")
            println("Tabuleiro ${linhas}X${colunas}")
            println("Coluna escolhida: ${('A' + colunaComputador)}")
        }
    }
}

fun ditaResultado(tabuleiro: Array<Array<String?>>, nomePlayer: String): Boolean {
    if (ganhouJogo(tabuleiro)) {
        if (nomePlayer == "Computador") {
            println("\nPerdeu! Ganhou o Computador.")
        } else {
            println("\nParabens "+nomePlayer+"! Ganhou!")
        }

        return true
    }
    if (eEmpate(tabuleiro)) {
        println()
        println("Empate!")

        return true
    }
    return false
}

fun eQuaseVitoriaHorizontal(tabuleiro: Array<Array<String?>>, linha: Int, colunaInicial: Int): Int? {
    if (linha >= tabuleiro.size) {
        return null
    }

    if (colunaInicial + 3 >= tabuleiro[linha].size) {
        return null
    }

    val tabu = balaoRed

    if (tabu == tabuleiro[linha][colunaInicial] &&
        tabu == tabuleiro[linha][colunaInicial + 1] &&
        tabu == tabuleiro[linha][colunaInicial + 2] &&
        null == tabuleiro[linha][colunaInicial + 3]) {
        return (colunaInicial + 3)
    }
    if (balaoblue == tabuleiro[linha][colunaInicial] &&
        balaoblue == tabuleiro[linha][colunaInicial + 1] &&
        balaoblue == tabuleiro[linha][colunaInicial + 2] &&
        null == tabuleiro[linha][colunaInicial + 3]) {
        return (colunaInicial + 3)
    }

    return null
}


fun eQuaseVitoriaVertical(tabuleiro: Array<Array<String?>>, linhaInicial: Int, coluna: Int): Boolean {

    if (linhaInicial + 3 >= tabuleiro.size) {
        return false
    }
    if (coluna >= tabuleiro[linhaInicial].size) {
        return false
    }

    val tabu = balaoRed
    if (tabu == tabuleiro[linhaInicial][coluna]   &&
        tabu == tabuleiro[linhaInicial+1][coluna] &&
        tabu == tabuleiro[linhaInicial+2][coluna] &&
        null == tabuleiro[linhaInicial+3][coluna]) {
        return true
    }
    if (balaoblue == tabuleiro[linhaInicial][coluna]   &&
        balaoblue == tabuleiro[linhaInicial+1][coluna] &&
        balaoblue == tabuleiro[linhaInicial+2][coluna] &&
        null == tabuleiro[linhaInicial+3][coluna]) {
        return true
    }
    return false
}

fun sugestaoJogadaNormalHumano(tabuleiro: Array<Array<String?>>): Int? {
    for (coluna in 0 until tabuleiro[0].size) {
        for (linha in 0 until tabuleiro.size - 3) {
            if (eQuaseVitoriaVertical(tabuleiro, linha, coluna)) {
                return coluna
            }
        }
    }



    for (linha in 0 until tabuleiro.size) {
        for (coluna in 0 until tabuleiro[0].size - 3) {
            if (eQuaseVitoriaHorizontal(tabuleiro, linha, coluna) != null) {
                return coluna + 3
            }
        }
    }
    return null
}

fun calculaEstatisticas(tabuleiro: Array<Array<String?>>): Array<Int> {
    var baloesTotal = 0
    var baloesTotalAzul = 0
    var baloesTotalVermelho = 0
    var quaseVit = 0

    for (linha in tabuleiro.indices) {
        for (coluna in tabuleiro[linha].indices) {
            val balaoAtual = tabuleiro[linha][coluna]


            if (eQuaseVitoriaHorizontal(tabuleiro, linha, coluna) != null) {
                quaseVit++
            }

            if (eQuaseVitoriaVertical(tabuleiro, linha, coluna)) {
                quaseVit++
            }
            if (balaoAtual != null) {
                baloesTotal++
                if (balaoAtual == balaoblue) baloesTotalAzul++
                if (balaoAtual == balaoRed) baloesTotalVermelho++
            }


        }
    }
    return arrayOf(baloesTotal, baloesTotalAzul, baloesTotalVermelho, quaseVit)
}
