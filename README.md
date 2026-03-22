# 🎈 4inline — Jogo 4 em Linha

[![Kotlin](https://img.shields.io/badge/Kotlin-100%25-7F52FF?style=flat-square&logo=kotlin&logoColor=white)](https://kotlinlang.org/)

**Jogo clássico de 4 em linha no terminal, jogador vs computador, com balões coloridos.**

---

## 📋 Descrição

O **4inline** é uma implementação em Kotlin do clássico Connect 4, jogado inteiramente no terminal. O jogador humano (🔴 vermelho) enfrenta o computador (🔵 azul), colocando balões em colunas até alinhar 4 na horizontal, vertical ou diagonal.

## ✨ Funcionalidades

| Funcionalidade | Descrição |
|---|---|
| 🎮 **Jogador vs Computador** | IA que joga automaticamente e deteta quase-vitórias |
| 📐 **Tabuleiros variáveis** | Suporta 5×6, 6×7 e 7×8 |
| 💾 **Gravar/Carregar** | Guarda e retoma jogos a partir de ficheiro |
| 💡 **Sugestão de jogada** | Escreve `?` para receber sugestão de coluna |
| 💥 **Explodir balões** | Mecânica especial para remover balões do adversário |
| 📊 **Estatísticas** | Contagem de balões e quase-vitórias em tempo real |
| 🏆 **Deteção de vitória** | Horizontal, vertical e diagonal (ambas direções) |

## 🎯 Como Jogar

1. Executar o `Main.kt`
2. Escolher **Novo Jogo** e definir o tamanho do tabuleiro
3. Introduzir o nome do jogador
4. Escolher colunas (A, B, C...) para colocar balões
5. Alinhar 4 balões antes do computador!

### Comandos Especiais

| Comando | Ação |
|---|---|
| `A`, `B`, `C`... | Colocar balão na coluna |
| `?` | Pedir sugestão de jogada |
| `Explodir A` | Explodir o balão no topo da coluna A |
| `Gravar` | Guardar o jogo atual em ficheiro |
| `Sair` | Voltar ao menu principal |

## 🛠️ Tecnologias

- **Linguagem:** Kotlin
- **Interface:** Terminal com caracteres Unicode (╔═║)
- **Cores:** ANSI escape codes (vermelho/azul)

## 🚀 Executar

```bash
# Compilar e executar com Kotlin
kotlinc src/*.kt -include-runtime -d 4inline.jar
java -jar 4inline.jar
```

## 👤 Autor

**Gonçalo Alegria** — [@goncaloalegria](https://github.com/goncaloalegria)

Projeto académico · Universidade Lusófona de Lisboa
